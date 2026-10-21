
import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-expense',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './expense.html',
  styleUrl: './expense.css'
})
export class Expense {

  expenses: any[] = [];

  loading = true;
  showForm = false;
  saving = false;

  expenseType = '';
  amount = '';
  expenseDate = '';
  description = '';

  private apiUrl = 'http://localhost:8083/expense';

  private userId = Number(localStorage.getItem('userId'));

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getExpenses();
  }

  getExpenses() {

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
        this.expenses = data;
        this.loading = false;
        this.cdr.detectChanges();
      },

      error: (error) => {
        console.log('Expense Error:', error);
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
    this.resetForm();
  }

  resetForm() {
    this.expenseType = '';
    this.amount = '';
    this.expenseDate = '';
    this.description = '';
  }

  addExpense() {

    if (this.saving) {
      return;
    }

    if (!this.userId) {
      alert('Please login again.');
      return;
    }


if (
  !this.expenseType.trim() ||
  this.amount === '' ||
  this.amount === null ||
  this.amount === undefined ||
  !this.expenseDate ||
  !this.description.trim()
) {
  alert('Please fill all fields');
  return;
}


    const expenseAmount = Number(this.amount);

    if (!Number.isFinite(expenseAmount) || expenseAmount <= 0) {
      alert('Please enter a valid amount greater than zero');
      return;
    }

    this.saving = true;

    const expense = {
      expenseType: this.expenseType.trim(),
      amount: expenseAmount,
      expenseDate: this.expenseDate,
      description: this.description.trim()
    };

    this.http.post<any>(
      `${this.apiUrl}/add?userId=${this.userId}`,
      expense
    ).subscribe({

      next: (data) => {

        console.log('Expense Added:', data);

        this.showForm = false;
        this.resetForm();
        this.saving = false;

        alert('Expense added successfully');

        this.getExpenses();
      },

      error: (error) => {

        console.log('Add Expense Error:', error);

        this.saving = false;
        this.cdr.detectChanges();

        alert('Failed to add expense. Please check the backend.');
      }

    });
  }

  deleteExpense(id: number) {

    if (!this.userId) {
      alert('Please login again.');
      return;
    }

    if (!confirm('Are you sure you want to delete this expense?')) {
      return;
    }

    this.http.delete(
      `${this.apiUrl}/delete/${id}?userId=${this.userId}`,
      { responseType: 'text' }
    ).subscribe({

      next: (data) => {

        console.log('Delete Response:', data);

        alert('Expense deleted successfully');

        this.getExpenses();
      },

      error: (error) => {

        console.log('Delete Expense Error:', error);

        alert('Failed to delete expense. Please check the backend.');
      }

    });
  }
}
