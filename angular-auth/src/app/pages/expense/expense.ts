import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-expense',
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

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {
    this.getExpenses();
  }

  getExpenses() {

    this.loading = true;

    this.http.get<any[]>('http://localhost:8083/expense/all')
      .subscribe({
        next: (data) => {

          console.log('Expense Data:', data);

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

    this.expenseType = '';
    this.amount = '';
    this.expenseDate = '';
    this.description = '';
  }

  addExpense() {

    if (this.saving) {
      return;
    }

    if (
      this.expenseType.trim() === '' ||
      this.amount === '' ||
      this.expenseDate === '' ||
      this.description.trim() === ''
    ) {

      alert('Please fill all fields');
      return;
    }

    this.saving = true;

    const expense = {

      expenseType: this.expenseType,
      amount: Number(this.amount),
      expenseDate: this.expenseDate,
      description: this.description

    };

    this.http.post<any>(
      'http://localhost:8083/expense/add',
      expense
    )
    .subscribe({

      next: (data) => {

        console.log('Expense Added:', data);

        // Add newly saved record directly to table
        this.expenses.push(data);

        // Close form
        this.showForm = false;

        // Clear fields
        this.expenseType = '';
        this.amount = '';
        this.expenseDate = '';
        this.description = '';

        // Stop saving
        this.saving = false;

        this.cdr.detectChanges();

        alert('Expense added successfully');

        // Get latest data from database
        this.getExpenses();
      },

      error: (error) => {

        console.log('Add Expense Error:', error);

        this.saving = false;

        alert('Failed to add expense');

        this.cdr.detectChanges();
      }
    });
  }
}
