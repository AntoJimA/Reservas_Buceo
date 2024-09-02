import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { SalidaService } from '../services/salida.service';

@Component({
  selector: 'app-crear-salida',
  standalone: true,
  imports: [],
  templateUrl: './crear-salida.component.html',
  styleUrl: './crear-salida.component.css'
})
export class CrearSalidaComponent implements OnInit {
  salidaForm!: FormGroup;
  constructor(private fb: FormBuilder,private salidaService:SalidaService) { }
  
  ngOnInit(): void {
    this.salidaForm = this.fb.group({
      fecha: ['',Validators.required],
      hora: ['',Validators.required],
      capacidad: ['',Validators.min(2)],
  });
  }


  onSubmit(): void {
    if (this.salidaForm.valid) {
      const formData = this.salidaForm.value;
      console.log('Form Data:', formData);
      // Aquí puedes manejar los datos del formulario, por ejemplo, enviarlos a un servidor
      this.salidaService.createSalida(formData).subscribe();
    } else {
      console.log('Formulario no válido');
    }
  }
}
