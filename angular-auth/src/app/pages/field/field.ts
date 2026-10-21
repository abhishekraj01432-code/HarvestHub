
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-field',
  imports: [CommonModule, FormsModule],
  templateUrl: './field.html',
  styleUrl: './field.css'
})
export class Field {

  fields: any[] = [];
  loading = true;
  showForm = false;

  fieldName = '';
  area: number | null = null;
  soilType = '';
  irrigationType = '';
  location = '';
  status = '';

  userId = localStorage.getItem('userId');

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getFields();
  }

  getFields() {

    if (!this.userId) {
      this.fields = [];
      this.loading = false;
      alert('User not logged in');
      return;
    }

    this.loading = true;

    this.http.get<any[]>(
      `http://localhost:8083/field/all?userId=${this.userId}`
    ).subscribe({

      next: (data) => {
        console.log('Field Data:', data);

        this.fields = data;
        this.loading = false;
        this.cdr.detectChanges();
      },

      error: (error) => {
        console.log('Field Error:', error);

        this.fields = [];
        this.loading = false;
        this.cdr.detectChanges();
      }

    });
  }

  openForm() {
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
    this.clearForm();
  }

  clearForm() {
    this.fieldName = '';
    this.area = null;
    this.soilType = '';
    this.irrigationType = '';
    this.location = '';
    this.status = '';
  }

  addField() {

    if (
      !this.fieldName.trim() ||
      this.area === null ||
      this.area <= 0 ||
      !this.soilType.trim() ||
      !this.irrigationType.trim() ||
      !this.location.trim() ||
      !this.status.trim()
    ) {
      alert('Please fill all fields correctly');
      return;
    }

    if (!this.userId) {
      alert('User not logged in');
      return;
    }

    const field = {
      fieldName: this.fieldName,
      area: this.area,
      soilType: this.soilType,
      irrigationType: this.irrigationType,
      location: this.location,
      status: this.status
    };

    this.http.post(
      `http://localhost:8083/field/add?userId=${this.userId}`,
      field
    ).subscribe({

      next: (data) => {
        console.log('Field Added:', data);

        this.clearForm();
        this.showForm = false;

        alert('Field added successfully');
        this.getFields();
      },

      error: (error) => {
        console.log('Add Field Error:', error);
        alert('Failed to add field');
      }

    });
  }

  deleteField(id: number) {

    if (!confirm('Are you sure you want to delete this field?')) {
      return;
    }

    if (!this.userId) {
      alert('User not logged in');
      return;
    }

    this.http.delete(
      `http://localhost:8083/field/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({

      next: (data) => {
        console.log('Delete Response:', data);

        alert('Field deleted successfully');
        this.getFields();
      },

      error: (error) => {
        console.log('Delete Field Error:', error);
        alert('Failed to delete field');
      }

    });
  }

}
