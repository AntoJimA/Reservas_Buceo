import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user={
    nombre:'',
    apellido:'',
    email:'',
    nivelbuceo:'',
    username:'',
    password:''
  };

  register(){
    alert('Usuario registrado'+ this.user.nombre + ' ' + this.user.apellido + ' ' + this.user.email + ' ' + this.user.nivelbuceo + ' ' + this.user.username + ' ' + this.user.password);
    console.log(this.user);
  };
}
