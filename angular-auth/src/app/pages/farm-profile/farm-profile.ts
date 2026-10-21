
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

  userId = localStorage.getItem('userId');

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getFarmProfiles();
  }

  getFarmProfiles() {

    if (!this.userId) {
      this.farmProfiles = [];
      this.loading = false;
      alert('User not logged in');
      return;
    }

    this.loading = true;

    this.http.get<any[]>(
      `http://localhost:8083/farm-profile/all?userId=${this.userId}`
    ).subscribe({

      next: (data) => {
        console.log('Farm Profile Data:', data);

        this.farmProfiles = data;
        this.loading = false;
        this.cdr.detectChanges();
      },

      error: (error) => {
        console.log('Farm Profile Error:', error);

        this.farmProfiles = [];
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
    this.farmName = '';
    this.ownerName = '';
    this.location = '';
    this.totalArea = null;
    this.areaUnit = '';
  }

  addFarm() {

    if (
      !this.farmName.trim() ||
      !this.ownerName.trim() ||
      !this.location.trim() ||
      this.totalArea === null ||
      this.totalArea <= 0 ||
      !this.areaUnit.trim()
    ) {
      alert('Please fill all fields correctly');
      return;
    }

    if (!this.userId) {
      alert('User not logged in');
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
      `http://localhost:8083/farm-profile/add?userId=${this.userId}`,
      farm
    ).subscribe({

      next: (data) => {
        console.log('Farm Added:', data);

        this.clearForm();
        this.showForm = false;

        alert('Farm added successfully');
        this.getFarmProfiles();
      },

      error: (error) => {
        console.log('Add Farm Error:', error);
        alert('Failed to add farm');
      }

    });
  }

  deleteFarm(id: number) {

    if (!confirm('Are you sure you want to delete this farm?')) {
      return;
    }

    if (!this.userId) {
      alert('User not logged in');
      return;
    }

    this.http.delete(
      `http://localhost:8083/farm-profile/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({

      next: (data) => {
        console.log('Delete Response:', data);

        alert(data || 'Farm deleted successfully');
        this.getFarmProfiles();
      },

      error: (error) => {
        console.log('Delete Farm Error:', error);
        alert('Failed to delete farm');
      }

    });
  }

}
