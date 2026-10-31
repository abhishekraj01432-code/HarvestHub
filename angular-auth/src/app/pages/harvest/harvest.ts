
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-harvest',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './harvest.html',
  styleUrl: './harvest.css'
})
export class Harvest {

  harvests: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  cropName = '';
  harvestDate = '';
  quantity: string | number = '';
  unit = '';
  sellingPrice: string | number = '';
  quality = '';

  private apiUrl = 'https://harvesthub-backend-7973.onrender.com/harvest';
  private userId = Number(localStorage.getItem('userId'));

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getHarvests();
  }

  // Get harvest records for the logged-in user
  getHarvests() {
    if (!this.userId) {
      this.loading = false;
      alert('User not logged in. Please login again.');
      return;
    }

    this.loading = true;

    this.http.get<any[]>(
      `${this.apiUrl}/all?userId=${this.userId}`
    ).subscribe({
      next: (data) => {
        this.harvests = data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.log('Harvest Error:', error);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  // Open the add form
  openForm() {
    this.showForm = true;
  }

  // Close the form
  closeForm() {
    if (this.saving) return;

    this.showForm = false;
    this.resetForm();
  }

  // Reset form fields
  resetForm() {
    this.cropName = '';
    this.harvestDate = '';
    this.quantity = '';
    this.unit = '';
    this.sellingPrice = '';
    this.quality = '';
  }

  // Add a new harvest
  addHarvest() {
    if (this.saving) return;

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (
      !this.cropName.trim() ||
      !this.harvestDate ||
      String(this.quantity).trim() === '' ||
      !this.unit.trim() ||
      String(this.sellingPrice).trim() === '' ||
      !this.quality.trim()
    ) {
      alert('Please fill all fields');
      return;
    }

    const harvestQuantity = Number(this.quantity);
    const price = Number(this.sellingPrice);

    if (!Number.isFinite(harvestQuantity) || harvestQuantity <= 0) {
      alert('Please enter a valid quantity greater than zero');
      return;
    }

    if (!Number.isFinite(price) || price < 0) {
      alert('Please enter a valid selling price');
      return;
    }

    this.saving = true;

    const harvest = {
      cropName: this.cropName.trim(),
      harvestDate: this.harvestDate,
      quantity: harvestQuantity,
      unit: this.unit.trim(),
      sellingPrice: price,
      quality: this.quality.trim()
    };

    this.http.post<any>(
      `${this.apiUrl}/add?userId=${this.userId}`,
      harvest
    ).subscribe({
      next: (data) => {
        this.harvests = [...this.harvests, data];

        this.showForm = false;
        this.resetForm();
        this.saving = false;

        this.cdr.detectChanges();
        alert('Harvest added successfully');
      },
      error: (error) => {
        console.log('Add Harvest Error:', error);
        this.saving = false;
        this.cdr.detectChanges();

        alert('Failed to add harvest. Please check the backend.');
      }
    });
  }

  // Delete a harvest and update the table immediately
  deleteHarvest(id: number) {
    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (!confirm('Are you sure you want to delete this harvest?')) {
      return;
    }

    this.http.delete(
      `${this.apiUrl}/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({
      next: () => {
        this.harvests = this.harvests.filter(
          harvest => harvest.id !== id
        );

        this.cdr.detectChanges();

        alert('Harvest deleted successfully');
      },
      error: (error) => {
        console.log('Delete Harvest Error:', error);
        alert('Failed to delete harvest. Please try again.');
      }
    });
  }
}
