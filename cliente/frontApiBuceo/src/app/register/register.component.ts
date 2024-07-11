import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  opciones : string[] = ['Open Water','Advanced Open Water', 'Rescue Diver', 'Divemaster', 'Instructor'];
  dropdownVisible = false;

  user={
    nombre:'',
    apellido:'',
    email:'',
    nivelbuceo:'',
    username:'',
    password:''
  };
  constructor(private router: Router) {}
  register(){
    if(this.user.nombre == '' || this.user.apellido == '' || this.user.email == '' || this.user.nivelbuceo == '' || this.user.username == '' || this.user.password == ''){
      alert('Por favor, rellene todos los campos');
      return;
    }
    alert('Usuario registrado'+ this.user.nombre + ' ' + this.user.apellido + ' ' + this.user.email + ' ' + this.user.nivelbuceo + ' ' + this.user.username + ' ' + this.user.password);
    console.log(this.user);
  };

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  onSelectChange(event: any) {
    console.log('Seleccionaste:', event.target.value);
    this.dropdownVisible = false; // Ocultar dropdown después de seleccionar
  }
  goBack(){
    this.router.navigate(['/login']);
  }
}
