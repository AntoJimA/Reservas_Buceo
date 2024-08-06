import {HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  constructor(private http:HttpClient) { }

  registroUser(user:User){
    return this.http.post<User>('http://localhost:8080/auth/', user);
  }
}
