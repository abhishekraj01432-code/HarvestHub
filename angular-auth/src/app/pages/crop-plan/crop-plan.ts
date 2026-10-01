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

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getCropPlans();
  }

  getCropPlans() {
    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/crop-plan/all')
      .subscribe({
        next: (data) => {
          console.log('Crop Plan Data:', data);
          this.cropPlans = data;
          this.loading = false;
          this.cdr.detectChanges();
        },
        error: (error) => {
          console.log('Crop Plan Error:', error);
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
    this.fieldName = '';
    this.cropName = '';
    this.sowingDate = '';
    this.expectedHarvestDate = '';
    this.status = '';
  }

  addCropPlan() {

    if (this.saving) {
      return;
    }

    if (
      this.fieldName.trim() === '' ||
      this.cropName.trim() === '' ||
      this.sowingDate === '' ||
      this.expectedHarvestDate === '' ||
      this.status.trim() === ''
    ) {
      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const cropPlan = {
      fieldName: this.fieldName,
      cropName: this.cropName,
      sowingDate: this.sowingDate,
      expectedHarvestDate: this.expectedHarvestDate,
      status: this.status
    };

    this.http.post(
      'http://localhost:8083/crop-plan/add',
      cropPlan
    )
    .subscribe({
      next: (data) => {
        console.log('Crop Plan Added:', data);

        this.showForm = false;

        this.fieldName = '';
        this.cropName = '';
        this.sowingDate = '';
        this.expectedHarvestDate = '';
        this.status = '';

        this.saving = false;

        this.getCropPlans();

        this.cdr.detectChanges();

        alert('Crop plan added successfully');
      },
      error: (error) => {
        console.log('Add Crop Plan Error:', error);

        this.saving = false;

        alert('Failed to add crop plan');
      }
    });
  }
}
