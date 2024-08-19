import { Component } from '@angular/core';
import { SalidaService } from '../services/salida.service'; 
import { Salida } from '../services/Salida';

@Component({
  selector: 'app-tabla',
  standalone: true,
  imports: [],
  templateUrl: './tabla.component.html',
  styleUrl: './tabla.component.css'
})
export class TablaComponent {
  salidas:Salida[] = [];
  constructor(private salidaService:SalidaService) { }

  ngOnInit(): void {
   this.salidaService.getSalidas().subscribe((data: any) => {this.salidas = data;});
  }
}
