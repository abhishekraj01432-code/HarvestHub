
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rotation',
  standalone: true,
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

  private apiUrl = 'http://localhost:8083/rotation';
  private userId = Number(localStorage.getItem('userId'));

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getRotations();
  }

  getRotations() {

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

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (
      !this.fieldName.trim() ||
      !this.previousCrop.trim() ||
      !this.nextCrop.trim() ||
      !this.rotationDate ||
      !this.season.trim()
    ) {
      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const rotation = {
      fieldName: this.fieldName.trim(),
      previousCrop: this.previousCrop.trim(),
      nextCrop: this.nextCrop.trim(),
      rotationDate: this.rotationDate,
      season: this.season.trim()
    };

    this.http.post<any>(
      `${this.apiUrl}/add?userId=${this.userId}`,
      rotation
    ).subscribe({
      next: (data) => {

        this.rotations = [...this.rotations, data];

        this.showForm = false;
        this.clearForm();
        this.saving = false;

        this.cdr.detectChanges();

        alert('Rotation record added successfully');
      },
      error: (error) => {
        console.log('Add Rotation Error:', error);
        this.saving = false;
        this.cdr.detectChanges();

        alert('Failed to add rotation record. Check the backend console.');
      }
    });
  }

  deleteRotation(id: number) {

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (!confirm('Are you sure you want to delete this rotation record?')) {
      return;
    }

    this.http.delete(
      `${this.apiUrl}/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({
      next: () => {

        // Remove the deleted row immediately.
        this.rotations = this.rotations.filter(
          rotation => rotation.id !== id
        );

        this.cdr.detectChanges();

        alert('Rotation record deleted successfully');
      },
      error: (error) => {
        console.log('Delete Rotation Error:', error);
        alert('Failed to delete rotation record. Please try again.');
      }
    });
  }
}
