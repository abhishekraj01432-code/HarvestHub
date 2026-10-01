import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-farm-profile',
  imports: [CommonModule, FormsModule],
  templateUrl: './farm-profile.html',
  styleUrl: './farm-profile.css'
})
export class FarmProfile {

  farmProfiles: any[] = [];
  loading = true;
  showForm = false;

  farmName = '';
  ownerName = '';
  location = '';
  totalArea: number | null = null;
  areaUnit = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getFarmProfiles();
  }

  getFarmProfiles() {

    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/farm-profile/all')
      .subscribe({

        next: (data) => {

          console.log('Farm Profile Data:', data);

          this.farmProfiles = data;
          this.loading = false;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.log('Farm Profile Error:', error);

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

    this.farmName = '';
    this.ownerName = '';
    this.location = '';
    this.totalArea = null;
    this.areaUnit = '';
  }

  addFarm() {

    if (
      this.farmName === '' ||
      this.ownerName === '' ||
      this.location === '' ||
      this.totalArea === null ||
      this.areaUnit === ''
    ) {

      alert('Please fill all fields');
      return;
    }

    const farm = {

      farmName: this.farmName,
      ownerName: this.ownerName,
      location: this.location,
      totalArea: this.totalArea,
      areaUnit: this.areaUnit

    };

    this.http.post(
      'http://localhost:8083/farm-profile/add',
      farm
    )
    .subscribe({

      next: (data) => {

        console.log('Farm Added:', data);

        this.showForm = false;

        this.farmName = '';
        this.ownerName = '';
        this.location = '';
        this.totalArea = null;
        this.areaUnit = '';

        this.getFarmProfiles();

        this.cdr.detectChanges();

        alert('Farm added successfully');

      },

      error: (error) => {

        console.log('Add Farm Error:', error);

        alert('Failed to add farm');

      }

    });
  }
}
