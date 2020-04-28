import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { LoginService } from './login.service';
@Injectable({
  providedIn: 'root'
})
export class GamesService {

  constructor(private http:HttpClient, private authService:LoginService) { }

  getGamesList() {
    let url;
    url = "/api/games/player/" +this.authService.currentUserValue.id;
    return this.http.get(url, {observe: 'response'}).pipe(
      map(
        (response:HttpResponse<any>) => {
          return response.body;
        }
      )
    )
  }
}
