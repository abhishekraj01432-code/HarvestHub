
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-activity',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './activity.html',
  styleUrl: './activity.css'
})
export class Activity {

private apiUrl = 'https://harvesthub-backend-7973.onrender.com/activity';

  activities: any[] = [];

  userId = Number(localStorage.getItem('userId'));

  loading = true;
  showForm = false;
  saving = false;

  activityName = '';
  fieldName = '';
  activityDate = '';
  description = '';
  status = 'Planned';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getActivities();
  }

  // Get activities for logged-in user
  getActivities() {

    if (!this.userId) {
      this.loading = false;
      alert('Please login first.');
      this.cdr.detectChanges();
      return;
    }

    this.loading = true;

    this.http.get<any[]>(
      `${this.apiUrl}/all?userId=${this.userId}`
    ).subscribe({

      next: (data) => {
        this.activities = data || [];
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

  // Open form
  openForm() {
    this.showForm = true;
  }

  // Reset form
  resetForm() {
    this.activityName = '';
    this.fieldName = '';
    this.activityDate = '';
    this.description = '';
    this.status = 'Planned';
  }

  // Close form
  closeForm() {

    if (this.saving) {
      return;
    }

    this.showForm = false;
    this.resetForm();
  }

  // Add activity
  addActivity() {

    if (this.saving) {
      return;
    }

    if (!this.userId) {
      alert('Please login again.');
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
      activityName: this.activityName.trim(),
      fieldName: this.fieldName.trim(),
      activityDate: this.activityDate,
      description: this.description.trim(),
      status: this.status
    };

    this.http.post(
      `${this.apiUrl}/add?userId=${this.userId}`,
      activity
    ).subscribe({

      next: (data) => {
        console.log('Activity Added:', data);

        this.showForm = false;
        this.resetForm();
        this.saving = false;

        this.getActivities();

        alert('Activity added successfully');
        this.cdr.detectChanges();
      },

      error: (error) => {
        console.log('Add Activity Error:', error);

        this.saving = false;

        alert('Failed to add activity. Check the backend.');
        this.cdr.detectChanges();
      }

    });
  }

  // Delete activity belonging to logged-in user
  deleteActivity(id: number) {

    if (!this.userId || !id) {
      return;
    }

    if (!confirm('Are you sure you want to delete this activity?')) {
      return;
    }

    this.http.delete(
      `${this.apiUrl}/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({

      next: (data) => {
        console.log('Delete Response:', data);

        alert('Activity deleted successfully');
        this.getActivities();
      },

      error: (error) => {
        console.log('Delete Activity Error:', error);

        alert('Failed to delete activity');
      }

    });
  }

}
