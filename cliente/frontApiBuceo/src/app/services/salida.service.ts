import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Salida } from './Salida';

@Injectable({
  providedIn: 'root'
})
export class SalidaService {

  constructor(private http:HttpClient) {}

  getSalidas(){
    return this.http.get<Salida[]>('http://localhost:8080/salida/getSalidas');
  }
}
