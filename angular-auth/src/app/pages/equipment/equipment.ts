
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-equipment',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './equipment.html',
  styleUrl: './equipment.css'
})
export class Equipment {

  equipment: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  equipmentName = '';
  equipmentType = '';
  model = '';
  purchaseDate = '';
  status = '';

  private apiUrl = 'https://harvesthub-backend-7973.onrender.com/equipment';
  private userId = Number(localStorage.getItem('userId'));

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getEquipment();
  }

  // Get equipment for the logged-in user
  getEquipment() {
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
        this.equipment = data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.log('Equipment Error:', error);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  // Open form
  openForm() {
    this.showForm = true;
  }

  // Reset form fields
  resetForm() {
    this.equipmentName = '';
    this.equipmentType = '';
    this.model = '';
    this.purchaseDate = '';
    this.status = '';
  }

  // Close form
  closeForm() {
    if (this.saving) return;

    this.showForm = false;
    this.resetForm();
  }

  // Add equipment
  addEquipment() {
    if (this.saving) return;

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (
      !this.equipmentName.trim() ||
      !this.equipmentType.trim() ||
      !this.model.trim() ||
      !this.purchaseDate ||
      !this.status.trim()
    ) {
      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const newEquipment = {
      equipmentName: this.equipmentName.trim(),
      equipmentType: this.equipmentType.trim(),
      model: this.model.trim(),
      purchaseDate: this.purchaseDate,
      status: this.status.trim()
    };

    this.http.post<any>(
      `${this.apiUrl}/add?userId=${this.userId}`,
      newEquipment
    ).subscribe({
      next: (data) => {
        this.equipment = [...this.equipment, data];

        this.showForm = false;
        this.resetForm();
        this.saving = false;

        this.cdr.detectChanges();
        alert('Equipment added successfully');
      },
      error: (error) => {
        console.log('Add Equipment Error:', error);
        this.saving = false;
        this.cdr.detectChanges();

        alert('Failed to add equipment. Please check the backend.');
      }
    });
  }

  // Delete equipment without refreshing the page
  deleteEquipment(id: number) {
    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (!confirm('Are you sure you want to delete this equipment?')) {
      return;
    }

    this.http.delete(
      `${this.apiUrl}/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({
      next: () => {
        this.equipment = this.equipment.filter(
          item => item.id !== id
        );

        this.cdr.detectChanges();

        alert('Equipment deleted successfully');
      },
      error: (error) => {
        console.log('Delete Equipment Error:', error);
        alert('Failed to delete equipment. Please check the backend.');
      }
    });
  }
}
