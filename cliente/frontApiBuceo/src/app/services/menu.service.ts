import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  static getUsuario: any;

  constructor(private http:HttpClient) { }


  getUsuario(): Observable<User> {
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('token') // Asegúrate de que el token esté almacenado en localStorage
    });
    return this.http.get<User>('http://localhost:8080/usuarios/mydata', { headers });
  }
}
