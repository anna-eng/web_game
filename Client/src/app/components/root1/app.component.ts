import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {LoginService} from '../../services/login.service'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  constructor(private authenticationService:LoginService, private router:Router) {

  }

  ngOnInit() {
    const token = this.authenticationService.accessToken;
    let currentPlayer = this.authenticationService.currentUserValue;
    let url = window.location.href;
    if ( token|| url.includes("app-oauth2-redirect") ||  url.includes("oauth2redirect") ) {
      return true;
    }       

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login']);
    return false;
  }

}
