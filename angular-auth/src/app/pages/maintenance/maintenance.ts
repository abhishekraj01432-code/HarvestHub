import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-maintenance',
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
  cost = '';
  status = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getMaintenances();
  }

  getMaintenances() {

    this.http.get<any[]>('http://localhost:8083/maintenance/all')
      .subscribe({
        next: (data) => {

          console.log('Maintenance Data:', data);

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

    if (
      this.equipmentName.trim() === '' ||
      this.maintenanceDate === '' ||
      this.nextMaintenanceDate === '' ||
      this.maintenanceType.trim() === '' ||
      this.cost.trim() === '' ||
      this.status.trim() === ''
    ) {

      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const maintenance = {
      equipmentName: this.equipmentName,
      maintenanceDate: this.maintenanceDate,
      nextMaintenanceDate: this.nextMaintenanceDate,
      maintenanceType: this.maintenanceType,
      cost: Number(this.cost),
      status: this.status
    };

    this.http.post<any>(
      'http://localhost:8083/maintenance/add',
      maintenance
    )
    .subscribe({

      next: (data) => {

        console.log('Maintenance Added:', data);

        // Add new record immediately
        this.maintenances.push(data);

        // Create a new array for Angular
        this.maintenances = [...this.maintenances];

        // Close form
        this.showForm = false;

        // Clear form
        this.clearForm();

        // Stop saving
        this.saving = false;

        // Force Angular UI update
        this.cdr.markForCheck();
        this.cdr.detectChanges();

        alert('Maintenance added successfully');
      },

      error: (error) => {

        console.log('Add Maintenance Error:', error);

        this.saving = false;

        this.cdr.detectChanges();

        alert('Failed to add maintenance');
      }
    });
  }
}
