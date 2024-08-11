import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistroService } from '../services/registro.service';
import { User } from '../services/User';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegistroService] // Asegúrate de añadir el servicio aquí
})
export class RegisterComponent {

  opciones: string[] = ['Open Water', 'Advanced Open Water', 'Rescue Diver', 'Divemaster', 'Instructor'];
  dropdownVisible = false;

 user:User = new User('','','','','','','');
  constructor(private router: Router, private registroService: RegistroService) {}

  register() {
    if (this.user.nombre === '' || this.user.apellido === '' || this.user.email === '' || this.user.nivelBuceo === '' || this.user.username === '' || this.user.password === '' || this.user.role === '') {
      alert('Por favor, rellene todos los campos');
      return;
    }

    alert('Usuario registrado: ' + this.user.nombre + ' ' + this.user.apellido + ' ' + this.user.email + ' ' + this.user.nivelBuceo + ' ' + this.user.username + ' ' + this.user.password);
    console.log(this.user);

    // Aquí puedes llamar a un método del servicio para registrar al usuario
     this.registroService.registroUser(this.user).subscribe(Response => { console.log("Usuario registrado"); });
  }

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  onSelectChange(event: any) {
    console.log('Seleccionaste:', event.target.value);
    this.dropdownVisible = false; // Ocultar dropdown después de seleccionar
  }

  goBack() {
    this.router.navigate(['/login']);
  }
}
