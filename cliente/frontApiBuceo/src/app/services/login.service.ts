import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap,map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) { }
  login(username: string, password: string): Observable<any> {
    return this.http.post('http://localhost:8080/auth/login', { username, password })
      .pipe(
        tap((response: any) => {
          localStorage.setItem('token', response.token);
        })
      );
  }

  getToken(): String | null {
    return localStorage.getItem('token');
  }

}
