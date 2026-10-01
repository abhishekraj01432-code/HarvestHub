import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-harvest',
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
  quantity = '';
  unit = '';
  sellingPrice = '';
  quality = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getHarvests();
  }

  getHarvests() {

    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/harvest/all')
      .subscribe({
        next: (data) => {

          console.log('Harvest Data:', data);

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

  openForm() {
    this.showForm = true;
  }

  closeForm() {

    if (this.saving) {
      return;
    }

    this.showForm = false;

    this.cropName = '';
    this.harvestDate = '';
    this.quantity = '';
    this.unit = '';
    this.sellingPrice = '';
    this.quality = '';
  }

  addHarvest() {

    if (this.saving) {
      return;
    }

    if (
      this.cropName.trim() === '' ||
      this.harvestDate === '' ||
      this.quantity.trim() === '' ||
      this.unit.trim() === '' ||
      this.sellingPrice.trim() === '' ||
      this.quality.trim() === ''
    ) {

      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const harvest = {

      cropName: this.cropName,
      harvestDate: this.harvestDate,
      quantity: Number(this.quantity),
      unit: this.unit,
      sellingPrice: Number(this.sellingPrice),
      quality: this.quality

    };

    this.http.post<any>(
      'http://localhost:8083/harvest/add',
      harvest
    )
    .subscribe({

      next: (data) => {

        console.log('Harvest Added:', data);

        // Add new record directly to table
        this.harvests = [...this.harvests, data];

        // Close form
        this.showForm = false;

        // Clear fields
        this.cropName = '';
        this.harvestDate = '';
        this.quantity = '';
        this.unit = '';
        this.sellingPrice = '';
        this.quality = '';

        // Stop saving
        this.saving = false;

        // Update table immediately
        this.cdr.detectChanges();

        alert('Harvest added successfully');
      },

      error: (error) => {

        console.log('Add Harvest Error:', error);

        this.saving = false;

        this.cdr.detectChanges();

        alert('Failed to add harvest');
      }
    });
  }
}
