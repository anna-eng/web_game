import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { LoginRequest } from '../models/loginRequest';
import { Player } from '../models/player';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  accessToken:string;
  currentUserValue:Player;

  constructor(private http:HttpClient) { 
    this.accessToken = localStorage.getItem("accessToken");
    let playerJson = JSON.parse(localStorage.getItem("user"));
    this.currentUserValue = new Player(playerJson.name);
    this.currentUserValue.id = playerJson.id;
    this.currentUserValue.role = playerJson.role;

  }
  getOauth2Url() {
    return this.http.get("/api/auth/loginUrl").pipe(
      map(
        (response:any) => {
          return response;
        }
      )
    )
  }
  logout() {
     return this.http.get("/api/logout").pipe(
      map(
        success => {
          this.accessToken = null;
          localStorage.removeItem('accessToken')
        }
      )
    )

  }
    
  login(username:String, password:String) {
    let loginRequest = new LoginRequest();
    loginRequest.username = username;
    loginRequest.password = password;

    return this.http.post('/api/authenticate', loginRequest, {observe: 'response'}).pipe(
      map(
        (response:HttpResponse<any>) => {
          let token = response.headers.get("Authorization");
          this.currentUserValue = new Player(username);
          this.accessToken = token;
          this.updateLocalStorage();
          return response
        })
    )
  }
  getUserInfo() {
    return this.http.get("/api/player?username=" + this.currentUserValue.name).pipe(
      map(
        (response:Player) => {
          this.currentUserValue = response;
          this.updateLocalStorage();
          return response;
        }
      )
    )
  }
  private updateLocalStorage() {
    if(!this.currentUserValue) {
      localStorage.removeItem("user");
    } else {
      localStorage.setItem("user", JSON.stringify(this.currentUserValue));
    }

    if(!this.accessToken) {
      localStorage.removeItem("accessToken");
    } else {
      localStorage.setItem("accessToken", this.accessToken);
    }
  } 
  
}
