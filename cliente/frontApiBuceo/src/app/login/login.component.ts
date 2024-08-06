import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  onSubmit() {
    // Aquí puedes añadir la lógica de autenticación
    if (this.username === 'user' && this.password === 'password') {
      this.router.navigate(['/menu']);
    } else {
      alert('Invalid credentials');
    }
  }

  goToRegister() {
    console.log('Navigating to register');
    this.router.navigate(['/register']);
  }
}
