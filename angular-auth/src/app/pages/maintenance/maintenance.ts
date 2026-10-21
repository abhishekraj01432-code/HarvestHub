
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-maintenance',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './maintenance.html',
  styleUrl: './maintenance.css'
})
export class Maintenance {

  maintenances: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  equipmentName = '';
  maintenanceDate = '';
  nextMaintenanceDate = '';
  maintenanceType = '';
  cost: string | number = '';
  status = '';

  private apiUrl = 'http://localhost:8083/maintenance';
  private userId = Number(localStorage.getItem('userId'));

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getMaintenances();
  }

  getMaintenances() {

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
        this.maintenances = data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.log('Maintenance Error:', error);
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
    this.equipmentName = '';
    this.maintenanceDate = '';
    this.nextMaintenanceDate = '';
    this.maintenanceType = '';
    this.cost = '';
    this.status = '';
  }

  addMaintenance() {

    if (this.saving) {
      return;
    }

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (
      !this.equipmentName.trim() ||
      !this.maintenanceDate ||
      !this.nextMaintenanceDate ||
      !this.maintenanceType.trim() ||
      String(this.cost).trim() === '' ||
      !this.status.trim()
    ) {
      alert('Please fill all fields');
      return;
    }

    const maintenanceCost = Number(this.cost);

    if (!Number.isFinite(maintenanceCost) || maintenanceCost < 0) {
      alert('Please enter a valid cost (zero or greater).');
      return;
    }

    if (this.nextMaintenanceDate < this.maintenanceDate) {
      alert('Next maintenance date cannot be before the maintenance date.');
      return;
    }

    this.saving = true;

    const maintenance = {
      equipmentName: this.equipmentName.trim(),
      maintenanceDate: this.maintenanceDate,
      nextMaintenanceDate: this.nextMaintenanceDate,
      maintenanceType: this.maintenanceType.trim(),
      cost: maintenanceCost,
      status: this.status.trim()
    };

    this.http.post<any>(
      `${this.apiUrl}/add?userId=${this.userId}`,
      maintenance
    ).subscribe({
      next: (data) => {

        this.maintenances = [...this.maintenances, data];

        this.showForm = false;
        this.clearForm();
        this.saving = false;

        this.cdr.detectChanges();

        alert('Maintenance added successfully');
      },
      error: (error) => {
        console.log('Add Maintenance Error:', error);
        this.saving = false;
        this.cdr.detectChanges();

        alert('Failed to add maintenance. Check the backend console.');
      }
    });
  }

  deleteMaintenance(id: number) {

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (!confirm('Are you sure you want to delete this maintenance record?')) {
      return;
    }

    this.http.delete(
      `${this.apiUrl}/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({
      next: () => {

        // Update the table immediately without reloading the page.
        this.maintenances = this.maintenances.filter(
          maintenance => maintenance.id !== id
        );

        this.cdr.detectChanges();

        alert('Maintenance deleted successfully');
      },
      error: (error) => {
        console.log('Delete Maintenance Error:', error);
        alert('Failed to delete maintenance. Please try again.');
      }
    });
  }
}
