import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rotation',
  imports: [CommonModule, FormsModule],
  templateUrl: './rotation.html',
  styleUrl: './rotation.css'
})
export class Rotation {

  rotations: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  fieldName = '';
  previousCrop = '';
  nextCrop = '';
  rotationDate = '';
  season = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getRotations();
  }

  getRotations() {

    this.http.get<any[]>('http://localhost:8083/rotation/all')
      .subscribe({

        next: (data) => {

          console.log('Rotation Data:', data);

          this.rotations = data;
          this.loading = false;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.log('Rotation Error:', error);

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

    this.clearForm();
  }

  clearForm() {

    this.fieldName = '';
    this.previousCrop = '';
    this.nextCrop = '';
    this.rotationDate = '';
    this.season = '';
  }

  addRotation() {

    if (this.saving) {
      return;
    }

    if (
      this.fieldName.trim() === '' ||
      this.previousCrop.trim() === '' ||
      this.nextCrop.trim() === '' ||
      this.rotationDate === '' ||
      this.season.trim() === ''
    ) {

      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const rotation = {

      fieldName: this.fieldName,
      previousCrop: this.previousCrop,
      nextCrop: this.nextCrop,
      rotationDate: this.rotationDate,
      season: this.season

    };

    this.http.post<any>(
      'http://localhost:8083/rotation/add',
      rotation
    )
    .subscribe({

      next: (data) => {

        console.log('Rotation Added:', data);

        // Add new record directly to table
        this.rotations.push(data);

        // Create new array for Angular update
        this.rotations = [...this.rotations];

        // Close form
        this.showForm = false;

        // Clear form
        this.clearForm();

        // Stop saving
        this.saving = false;

        // Update UI immediately
        this.cdr.markForCheck();
        this.cdr.detectChanges();

        alert('Rotation record added successfully');
      },

      error: (error) => {

        console.log('Add Rotation Error:', error);

        this.saving = false;

        this.cdr.detectChanges();

        alert('Failed to add rotation record');
      }

    });
  }
}
