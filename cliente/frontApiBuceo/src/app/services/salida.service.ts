import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Salida } from './Salida';

@Injectable({
  providedIn: 'root'
})
export class SalidaService {

  constructor(private http:HttpClient) {}

  getSalidas(){
    return this.http.get<Salida[]>('http://localhost:8080/salidas/getSalidas');
  }

  deleteSalida(id:number){
    return this.http.delete('http://localhost:8080/salidas/deleteSalida/'+id);
  }

  apuntarse(id:number){
    return this.http.put('http://localhost:8080/salidas/apuntarseSalida/{id}'+id,null);
  }

  desapuntarse(id:number){
    return this.http.put('http://localhost:8080/salidas/desapuntarseSalida/'+id,null);
  }

  createSalida(salida:Salida){
    return this.http.post('http://localhost:8080/salidas/saveSalida',salida);
  }

  updateSalida(salida:Salida){
    return this.http.put('http://localhost:8080/salidas/updateSalida/',salida);
  }
}
