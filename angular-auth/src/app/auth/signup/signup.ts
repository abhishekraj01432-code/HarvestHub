import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-signup',
  imports: [FormsModule, RouterLink],
  templateUrl: './signup.html',
  styleUrl: './signup.css'
})
export class Signup {

  name = '';
  email = '';
  password = '';
  confirmPassword = '';

  constructor(
    private router: Router,
    private userService: UserService
  ) {}

  signup() {

    if (
      this.name === '' ||
      this.email === '' ||
      this.password === '' ||
      this.confirmPassword === ''
    ) {
      alert('Please fill all fields');
      return;
    }

    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match');
      return;
    }

    const user = {
      name: this.name,
      email: this.email,
      password: this.password
    };

    this.userService.signup(user).subscribe({
      next: () => {
        alert('Account created successfully');
        this.router.navigate(['/login']);
      },
      error: () => {
        alert('Signup failed');
      }
    });
  }
}
