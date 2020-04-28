import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Player } from '../models/player';
@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  constructor(private http:HttpClient) { }
  getPlayersList() {
    let url;
    url = "/api/player/all";
    return this.http.get(url, {observe: 'response'}).pipe(
      map(
        (response:HttpResponse<any>) => {
          return response.body;
        }
      )
    )
  }
}
