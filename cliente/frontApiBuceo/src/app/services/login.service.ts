import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap,map } from 'rxjs';
import { Login } from './Login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("");

  constructor(private http: HttpClient) {
    this.currentLoginOn = new BehaviorSubject<boolean>(sessionStorage.getItem('token')!=null);
    this.currentUserData = new BehaviorSubject<String>(sessionStorage.getItem('token')||"");
   }

  public login(username: string, password: string) {
    return this.http.post<any>('http://localhost:8080/auth/login', {username,password}).pipe(tap(data => {
        sessionStorage.setItem('token', data.token);
        this.currentLoginOn.next(true);
        this.currentUserData.next(data);
      }),
      map((data)=> data.token)
    );
  }

  public logout():void {
    sessionStorage.removeItem('token');
    this.currentLoginOn.next(false);
    this.currentUserData.next("");
  }

  public getTokens():String{
    return this.currentUserData.getValue();
  }

}
