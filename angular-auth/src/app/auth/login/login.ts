import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  email = '';
  password = '';

  constructor(
    private router: Router,
    private userService: UserService
  ) {}

  login() {

    if (this.email === '' || this.password === '') {
      alert('Please enter email and password');
      return;
    }

    this.userService.login(this.email, this.password).subscribe({

      next: (user) => {

        if (user) {

          localStorage.setItem('isLoggedIn', 'true');

          alert('Login successful');

          this.router.navigate(['/dashboard']);

        } else {

          alert('Invalid email or password');

        }
      },

      error: () => {
        alert('Login failed');
      }

    });
  }
}

