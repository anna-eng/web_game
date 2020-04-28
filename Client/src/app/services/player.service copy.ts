// import { Injectable } from '@angular/core';
// import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
// import {Alien} from '../models/alien'
// import { map } from 'rxjs/operators';

// @Injectable({
//   providedIn: 'root'
// })
// export class AliensService {

//   constructor(private http:HttpClient) { 
//   }

//   getAlienList(nameSearch) {
//     let url;
//     url = "/api/";
//     if(nameSearch) {
//       url += '?name='+nameSearch
//     }
//     return this.http.get(url, {observe: 'response'}).pipe(
//       map(
//         (response:HttpResponse<any>) => {
//           return response.body;
//         }
//       )
//     )
//   }
//   getAlienInfoById(id) {
//     let url  = '/api/'+id;
//     return this.http.get(url).pipe(
//       map(
//         (data:any) => {
//           return data;
//         }
//       )
//     )
//   }

//   getAlienSubbordinatesById(id) {
//     let url  = '/api/'+id +'/subbordinates'
//     return this.http.get(url).pipe(
//       map(
//         (data:Alien[]) => {
//           return data;
//         }
//       )
//     )
//   }
//   saveAlien(newAlien) {
//     let url  = '/api/'
//     return this.http.post(url, newAlien,  { headers: new HttpHeaders({'Content': 'application/json; charset=utf-8'})}).pipe(
//       map(
//         (data:Alien) => {
//           return data;
//         }
//       )
//     )
//   }
//   deleteAlienById(id) {
//     let url  = '/api/'+id;
//     return this.http.delete(url).pipe(
//       map(
//         success => {return true}
//       )
//     )
//   }
// }
