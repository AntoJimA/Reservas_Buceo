import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
/*import { RegistroService } from '../services/registro.service';*/
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  opciones : string[] = ['Open Water','Advanced Open Water', 'Rescue Diver', 'Divemaster', 'Instructor'];
  dropdownVisible = false;

  user={
    nombre:'',
    apellido:'',
    email:'',
    nivelBuceo:'',
    username:'',
    password:'',
    role:''
  };
  constructor(private router: Router,/*privateregistroService:RegistroService*/) {}
  register(){
    if(this.user.nombre === '' || this.user.apellido === '' || this.user.email === '' || this.user.nivelBuceo == '' || this.user.username === '' || this.user.password === '' || this.user.role === ''){
      alert('Por favor, rellene todos los campos');
      return;
    }
   /* this.registroService.registroUser(this.user).subscribe(result=>{console.log(result)});*/
    alert('Usuario registrado'+ this.user.nombre + ' ' + this.user.apellido + ' ' + this.user.email + ' ' + this.user.nivelBuceo + ' ' + this.user.username + ' ' + this.user.password);
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
