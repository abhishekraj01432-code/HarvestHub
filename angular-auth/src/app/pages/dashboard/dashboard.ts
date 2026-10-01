import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

  cropCount = 0;
  fieldCount = 0;
  totalExpense = 0;
  harvestYield = 0;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {

    // Total Crops
    this.http.get<number>('http://localhost:8083/crop/count')
      .subscribe((data) => {

        this.cropCount = data;

        console.log('Crop Count:', this.cropCount);

        const element = document.getElementById('cropCount');

        if (element) {
          element.innerText = data.toString();
        }

      });


    // Total Fields
    this.http.get<number>('http://localhost:8083/field/count')
      .subscribe((data) => {

        this.fieldCount = data;

        console.log('Field Count:', this.fieldCount);

        const element = document.getElementById('fieldCount');

        if (element) {
          element.innerText = data.toString();
        }

      });


    // Total Expenses
    this.http.get<number>('http://localhost:8083/expense/total')
      .subscribe((data) => {

        this.totalExpense = data;

        console.log('Total Expense:', this.totalExpense);

        const element = document.getElementById('expenseTotal');

        if (element) {
          element.innerText = '₹' + data.toLocaleString('en-IN');
        }

      });


    // Harvest Yield
    this.http.get<number>('http://localhost:8083/harvest/total')
      .subscribe((data) => {

        this.harvestYield = data;

        console.log('Harvest Yield:', this.harvestYield);

        const element = document.getElementById('harvestYield');

        if (element) {
          element.innerText = data.toString();
        }

      });

  }


  // Logout
  logout() {

    localStorage.removeItem('isLoggedIn');

    this.router.navigate(['/login']);

  }

}
