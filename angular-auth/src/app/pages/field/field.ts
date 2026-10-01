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

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getFields();
  }

  getFields() {

    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/field/all')
      .subscribe({

        next: (data) => {

          console.log('Field Data:', data);

          this.fields = data;
          this.loading = false;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.log('Field Error:', error);

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

    this.fieldName = '';
    this.area = null;
    this.soilType = '';
    this.irrigationType = '';
    this.location = '';
    this.status = '';
  }

  addField() {

    if (
      this.fieldName === '' ||
      this.area === null ||
      this.soilType === '' ||
      this.irrigationType === '' ||
      this.location === '' ||
      this.status === ''
    ) {

      alert('Please fill all fields');
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
      'http://localhost:8083/field/add',
      field
    )
    .subscribe({

      next: (data) => {

        console.log('Field Added:', data);

        this.showForm = false;

        this.fieldName = '';
        this.area = null;
        this.soilType = '';
        this.irrigationType = '';
        this.location = '';
        this.status = '';

        this.getFields();

        this.cdr.detectChanges();

        alert('Field added successfully');

      },

      error: (error) => {

        console.log('Add Field Error:', error);

        alert('Failed to add field');

      }

    });
  }
}
