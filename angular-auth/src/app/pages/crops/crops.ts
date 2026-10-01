import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-crops',
  imports: [CommonModule, FormsModule],
  templateUrl: './crops.html',
  styleUrl: './crops.css'
})
export class Crops {

  crops: any[] = [];
  loading = true;
  showForm = false;

  cropName = '';
  cropType = '';
  season = '';
  duration = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getCrops();
  }

  getCrops() {

    this.http.get<any[]>('http://localhost:8083/crop/all')
      .subscribe({

        next: (data) => {
          console.log('Crop Data:', data);

          this.crops = data;
          this.loading = false;

          this.cdr.detectChanges();
        },

        error: (error) => {
          console.log('Crop Error:', error);

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

    this.cropName = '';
    this.cropType = '';
    this.season = '';
    this.duration = '';
  }

  addCrop() {

  if (
    this.cropName === '' ||
    this.cropType === '' ||
    this.season === '' ||
    this.duration === ''
  ) {
    alert('Please fill all fields');
    return;
  }

  const crop = {
    cropName: this.cropName,
    cropType: this.cropType,
    season: this.season,
    duration: this.duration
  };

  this.http.post(
    'http://localhost:8083/crop/add',
    crop
  )
  .subscribe({

    next: (data) => {

      console.log('Crop Added:', data);

      this.showForm = false;

      this.cropName = '';
      this.cropType = '';
      this.season = '';
      this.duration = '';

      this.getCrops();

      this.cdr.detectChanges();

      alert('Crop added successfully');

    },

    error: (error) => {

      console.log('Add Crop Error:', error);

      alert('Failed to add crop');

    }

  });
}
}
