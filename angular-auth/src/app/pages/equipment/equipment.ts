import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-equipment',
  imports: [CommonModule, FormsModule],
  templateUrl: './equipment.html',
  styleUrl: './equipment.css'
})
export class Equipment {

  equipment: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  equipmentName = '';
  equipmentType = '';
  model = '';
  purchaseDate = '';
  status = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getEquipment();
  }

  getEquipment() {

    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/equipment/all')
      .subscribe({

        next: (data) => {

          console.log('Equipment Data:', data);

          this.equipment = data;
          this.loading = false;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.log('Equipment Error:', error);

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

    this.equipmentName = '';
    this.equipmentType = '';
    this.model = '';
    this.purchaseDate = '';
    this.status = '';
  }

  addEquipment() {

    if (this.saving) {
      return;
    }

    if (
      this.equipmentName.trim() === '' ||
      this.equipmentType.trim() === '' ||
      this.model.trim() === '' ||
      this.purchaseDate === '' ||
      this.status.trim() === ''
    ) {

      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const equipment = {

      equipmentName: this.equipmentName,
      equipmentType: this.equipmentType,
      model: this.model,
      purchaseDate: this.purchaseDate,
      status: this.status

    };

    this.http.post<any>(
      'http://localhost:8083/equipment/add',
      equipment
    )
    .subscribe({

      next: (data) => {

        console.log('Equipment Added:', data);

        // Add directly to table without reload
        this.equipment = [...this.equipment, data];

        this.showForm = false;

        this.equipmentName = '';
        this.equipmentType = '';
        this.model = '';
        this.purchaseDate = '';
        this.status = '';

        this.saving = false;

        this.cdr.detectChanges();

        alert('Equipment added successfully');
      },

      error: (error) => {

        console.log('Add Equipment Error:', error);

        this.saving = false;

        this.cdr.detectChanges();

        alert('Failed to add equipment');
      }

    });
  }
}
