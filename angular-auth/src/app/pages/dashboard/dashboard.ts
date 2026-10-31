import {
  Component,
  OnInit,
  OnDestroy,
  ChangeDetectorRef
} from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit, OnDestroy {

  cropCount = 0;
  fieldCount = 0;
  totalExpense = 0;
  harvestYield = 0;
  progress = 0;

  private progressInterval?: ReturnType<typeof setInterval>;

private readonly apiUrl = 'https://harvesthub-backend-7973.onrender.com';

  constructor(
    private http: HttpClient,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const userId = localStorage.getItem('userId');

    console.log('Dashboard userId:', userId);

    if (
      !userId ||
      !Number.isInteger(Number(userId)) ||
      Number(userId) <= 0
    ) {
      console.error('Valid userId not found.');
      this.router.navigate(['/login']);
      return;
    }

    this.loadDashboardData(userId);
    this.startProgress();
  }

  loadDashboardData(userId: string): void {

    const params = { userId };

    // Total Crops
    this.http.get<number>(
      `${this.apiUrl}/crop/count`,
      { params }
    ).subscribe({
      next: (data) => {
        this.cropCount = Number(data) || 0;

        console.log('Crop count:', this.cropCount);

        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Crop API Error:', error);
      }
    });

    // Total Fields
    this.http.get<number>(
      `${this.apiUrl}/field/count`,
      { params }
    ).subscribe({
      next: (data) => {
        this.fieldCount = Number(data) || 0;

        console.log('Field count:', this.fieldCount);

        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Field API Error:', error);
      }
    });

    // Total Expenses
    this.http.get<unknown>(
      `${this.apiUrl}/expense/total`,
      { params }
    ).subscribe({
      next: (data) => {
        this.totalExpense = this.getNumber(data, [
          'totalExpense',
          'total',
          'amount',
          'sum'
        ]);

        console.log('Total expenses:', this.totalExpense);

        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Expense API Error:', error);
      }
    });

    // Total Harvest Yield
    this.http.get<unknown>(
      `${this.apiUrl}/harvest/total`,
      { params }
    ).subscribe({
      next: (data) => {
        this.harvestYield = this.getNumber(data, [
          'harvestYield',
          'totalYield',
          'total',
          'yield',
          'sum'
        ]);

        console.log('Harvest yield:', this.harvestYield);

        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Harvest API Error:', error);
      }
    });
  }

  private getNumber(
    data: unknown,
    keys: string[]
  ): number {

    if (typeof data === 'number') {
      return Number.isFinite(data) ? data : 0;
    }

    if (typeof data === 'string') {
      const value = Number(data);
      return Number.isFinite(value) ? value : 0;
    }

    if (data !== null && typeof data === 'object') {
      const result = data as Record<string, unknown>;

      for (const key of keys) {
        const item = result[key];

        if (item !== undefined && item !== null) {
          const value = Number(item);

          if (Number.isFinite(value)) {
            return value;
          }
        }
      }
    }

    console.error('Unexpected dashboard API response:', data);

    return 0;
  }

  startProgress(): void {
    let value = 0;

    this.progressInterval = setInterval(() => {
      value++;
      this.progress = value;

      this.cdr.detectChanges();

      if (value >= 78 && this.progressInterval) {
        clearInterval(this.progressInterval);
        this.progressInterval = undefined;
      }
    }, 30);
  }

  logout(): void {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('userId');
    localStorage.removeItem('userName');
    localStorage.removeItem('userEmail');

    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    if (this.progressInterval) {
      clearInterval(this.progressInterval);
      this.progressInterval = undefined;
    }
  }
}
