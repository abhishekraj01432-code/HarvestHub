
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-crop-plan',
  imports: [CommonModule, FormsModule],
  templateUrl: './crop-plan.html',
  styleUrl: './crop-plan.css'
})
export class CropPlan {

  cropPlans: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  fieldName = '';
  cropName = '';
  sowingDate = '';
  expectedHarvestDate = '';
  status = '';

  userId = localStorage.getItem('userId');

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getCropPlans();
  }

  // Get Crop Plans for Logged-in User
  getCropPlans() {

    if (!this.userId) {
      this.cropPlans = [];
      this.loading = false;
      alert('User not logged in');
      return;
    }

    this.loading = true;

    this.http.get<any[]>(
      `https://harvesthub-backend-7973.onrender.com/crop-plan/all?userId=${this.userId}`
    ).subscribe({

      next: (data) => {
        console.log('Crop Plan Data:', data);

        this.cropPlans = data;
        this.loading = false;
        this.cdr.detectChanges();
      },

      error: (error) => {
        console.log('Crop Plan Error:', error);

        this.cropPlans = [];
        this.loading = false;
        this.cdr.detectChanges();
      }

    });
  }

  // Open Form
  openForm() {
    this.showForm = true;
  }

  // Clear Form
  clearForm() {
    this.fieldName = '';
    this.cropName = '';
    this.sowingDate = '';
    this.expectedHarvestDate = '';
    this.status = '';
  }

  // Close Form
  closeForm() {

    if (this.saving) {
      return;
    }

    this.showForm = false;
    this.clearForm();
  }

  // Add Crop Plan
  addCropPlan() {

    if (this.saving) {
      return;
    }

    if (
      !this.fieldName.trim() ||
      !this.cropName.trim() ||
      !this.sowingDate ||
      !this.expectedHarvestDate ||
      !this.status.trim()
    ) {
      alert('Please fill all fields');
      return;
    }

    if (this.expectedHarvestDate < this.sowingDate) {
      alert('Expected harvest date cannot be before sowing date');
      return;
    }

    if (!this.userId) {
      alert('User not logged in');
      return;
    }

    this.saving = true;

    const cropPlan = {
      fieldName: this.fieldName.trim(),
      cropName: this.cropName.trim(),
      sowingDate: this.sowingDate,
      expectedHarvestDate: this.expectedHarvestDate,
      status: this.status.trim()
    };

    this.http.post(
      `https://harvesthub-backend-7973.onrender.com/crop-plan/add?userId=${this.userId}`,
      cropPlan
    ).subscribe({

      next: (data) => {
        console.log('Crop Plan Added:', data);

        this.showForm = false;
        this.clearForm();
        this.saving = false;

        this.getCropPlans();
        this.cdr.detectChanges();

        alert('Crop plan added successfully');
      },

      error: (error) => {
        console.log('Add Crop Plan Error:', error);

        this.saving = false;
        this.cdr.detectChanges();

        alert('Failed to add crop plan');
      }

    });
  }

  // Delete Crop Plan
  deleteCropPlan(id: number) {

    if (!confirm('Are you sure you want to delete this crop plan?')) {
      return;
    }

    if (!this.userId) {
      alert('User not logged in');
      return;
    }

    this.http.delete(
      `https://harvesthub-backend-7973.onrender.com/crop-plan/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({

      next: (data) => {
        console.log('Delete Response:', data);

        this.getCropPlans();
        alert(data || 'Crop plan deleted successfully');
      },

      error: (error) => {
        console.log('Delete Crop Plan Error:', error);
        alert('Failed to delete crop plan');
      }

    });
  }
}
