import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './User';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  static getUsuario: any;

  constructor(private http:HttpClient) { }


  getUsuario(): Observable<User> {
    return this.http.get<User>('http://localhost:8080/usuarios/mydata').pipe(catchError(this.handleError));
  }
  private handleError(error:HttpErrorResponse):Observable<never>{ {
    if(error.status ===0){
      console.error('An error occurred:', error.error);
    }else{
      console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
    }
    return throwError('Something bad happened; please try again later.');
  }}
}

