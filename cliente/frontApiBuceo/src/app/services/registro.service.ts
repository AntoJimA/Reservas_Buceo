import {HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  private url = 'http://localhost:8080/auth/register';

  constructor(private http:HttpClient) { }

  public registroUser(user:User):Observable<any>{
    return this.http.post<any>(this.url, user);
  }
}
