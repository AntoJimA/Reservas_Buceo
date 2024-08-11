import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../services/login.service';

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

  constructor(private router: Router,private loginService:LoginService) {}

  onSubmit() {
    // Aquí puedes añadir la lógica de autenticación
    
    this.loginService.login(this.username,this.password).subscribe(
      (data)=>{this.router.navigate(['/menu']);},
      (error)=>{alert('Invalid credentials');}
    );
   /* if (this.username === 'user' && this.password === 'password') {
      this.router.navigate(['/menu']);
    } else {
      alert('Invalid credentials');
    }*/
  }

  goToRegister() {
    console.log('Navigating to register');
    this.router.navigate(['/register']);
  }
}
