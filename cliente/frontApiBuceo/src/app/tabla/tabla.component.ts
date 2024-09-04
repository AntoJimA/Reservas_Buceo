import { Component } from '@angular/core';
import { SalidaService } from '../services/salida.service'; 
import { Salida } from '../services/Salida';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import { Apuntarse } from '../services/apuntarse';

@Component({
  selector: 'app-tabla',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './tabla.component.html',
  styleUrl: './tabla.component.css'
})
export class TablaComponent {
  activity:Salida[] = [];
  constructor(private salidaService:SalidaService) { }

  ngOnInit(): void {
   this.salidaService.getSalidas().subscribe((data: any) => {this.activity = data;});
  }

  deleteActivity(id:number){
    this.salidaService.deleteSalida(id).subscribe();
    this.activity = this.activity.filter((activity)=>activity.id != id);
  }

  apuntarse(id:number){
    const apuntarse: Apuntarse = {idUsuario: id};
    this.salidaService.apuntarse(id,apuntarse).subscribe();
    this.activity = this.activity.filter((activity)=>activity.id != id);
  }

  desapuntarse(id:number){
    this.salidaService.desapuntarse(id).subscribe();
    this.activity = this.activity.filter((activity)=>activity.id != id);
  }

  createActivity(salida:Salida){
    this.salidaService.createSalida(salida).subscribe();
    this.activity.push(salida);
  }

  updateActivity(salida:Salida){
    this.salidaService.updateSalida(salida).subscribe();
    this.activity = this.activity.filter((activity)=>activity.id != salida.id);
    this.activity.push(salida);
  }

  static reloadPage(){
    window.location.reload();
  }

}
