import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8083/user';

  constructor(private http: HttpClient) {}

  signup(user: any) {
    return this.http.post(`${this.apiUrl}/signup`, user);
  }

  login(email: string, password: string) {
    return this.http.post(
      `${this.apiUrl}/login?email=${email}&password=${password}`,
      {}
    );
  }
}
