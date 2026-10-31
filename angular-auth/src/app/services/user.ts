import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface User {
  id: number;
  name: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

private apiUrl = 'https://harvesthub-backend-7973.onrender.com/user';

  constructor(private http: HttpClient) {}

  signup(user: any) {
    return this.http.post<User>(
      `${this.apiUrl}/signup`,
      user
    );
  }

  login(email: string, password: string) {
    return this.http.post<User>(
      `${this.apiUrl}/login?email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`,
      {}
    );
  }
}
