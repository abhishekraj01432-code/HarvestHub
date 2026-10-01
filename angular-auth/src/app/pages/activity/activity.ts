import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-activity',
  imports: [CommonModule, FormsModule],
  templateUrl: './activity.html',
  styleUrl: './activity.css'
})
export class Activity {

  activities: any[] = [];
  loading = true;
  showForm = false;
  saving = false;

  activityName = '';
  fieldName = '';
  activityDate = '';
  description = '';
  status = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getActivities();
  }

  getActivities() {
    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/activity/all')
      .subscribe({
        next: (data) => {
          console.log('Activity Data:', data);
          this.activities = data;
          this.loading = false;
          this.cdr.detectChanges();
        },
        error: (error) => {
          console.log('Activity Error:', error);
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
    this.activityName = '';
    this.fieldName = '';
    this.activityDate = '';
    this.description = '';
    this.status = '';
  }

  addActivity() {

    if (this.saving) {
      return;
    }

    if (
      this.activityName.trim() === '' ||
      this.fieldName.trim() === '' ||
      this.activityDate === '' ||
      this.description.trim() === '' ||
      this.status.trim() === ''
    ) {
      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const activity = {
      activityName: this.activityName,
      fieldName: this.fieldName,
      activityDate: this.activityDate,
      description: this.description,
      status: this.status
    };

    this.http.post(
      'http://localhost:8083/activity/add',
      activity
    )
    .subscribe({
      next: (data) => {
        console.log('Activity Added:', data);

        this.showForm = false;

        this.activityName = '';
        this.fieldName = '';
        this.activityDate = '';
        this.description = '';
        this.status = '';

        this.saving = false;

        this.getActivities();

        this.cdr.detectChanges();

        alert('Activity added successfully');
      },
      error: (error) => {
        console.log('Add Activity Error:', error);

        this.saving = false;

        alert('Failed to add activity');
      }
    });
  }
}
