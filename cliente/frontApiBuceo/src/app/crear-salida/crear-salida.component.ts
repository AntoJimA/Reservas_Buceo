import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { SalidaService } from '../services/salida.service';
import { CommonModule } from '@angular/common';
import { TablaComponent } from '../tabla/tabla.component';

@Component({
  selector: 'app-crear-salida',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule], // Add necessary modules here
  templateUrl: './crear-salida.component.html',
  styleUrls: ['./crear-salida.component.css'] // Fix typo from styleUrl to styleUrls
})
export class CrearSalidaComponent implements OnInit {
  salidaForm!: FormGroup;

  constructor(private fb: FormBuilder, private salidaService: SalidaService) {}

  ngOnInit(): void {
    this.salidaForm = this.fb.group({
      fecha: ['', Validators.required],
      hora: ['', Validators.required],
      capacidad: ['', [Validators.required, Validators.min(2)]], // Add Validators.required for consistency
    });
  }

  onSubmit(): void {
    if (this.salidaForm.valid) {
      const formData = this.salidaForm.value;
      console.log('Form Data:', formData);
      this.salidaService.createSalida(formData).subscribe({
        next: response => console.log('Salida created successfully:', response),
        error: error => console.error('Error creating salida:', error)
      });
      TablaComponent.reloadPage(); // Call static method from TablaComponent
    } else {
      console.log('Formulario no válido');
    }
  }
}
