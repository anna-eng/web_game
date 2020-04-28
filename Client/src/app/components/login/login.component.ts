import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {LoginService} from '../../services/login.service'
import { HttpResponse } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  oauth2Url: String;
  errorMessage:String;
  password:String;
  username:String;
  constructor(private authService:LoginService, private router:Router) { }

  ngOnInit() {
    // this.getOauth2Url()
  }
  login() {
    this.errorMessage ='';
    if(!this.username)  {
      this.errorMessage +="\n* Invalid Email";
    } 
    if(this.errorMessage) return;

    this.authService.login(this.username, this.password)
    .subscribe(
      success => {
        console.log(success);
        // this.router.navigate([""])
        this.authService.getUserInfo()
        .subscribe(
          success => {
            console.log(success);
            this.router.navigate([""])
          },
          error => {
            alert("Please check your username and password.");
            this.errorMessage = "Please check your username and password."
          }
        )
      },
      error => {
        alert("Please check your username and password.");
        this.errorMessage = "Please check your username and password."
      }
    )



    
  } 
  signup() {
    this.router.navigate(["/signup"])
  }
  getOauth2Url() {
    this.authService.getOauth2Url().subscribe(
      (response:any) => {
        this.oauth2Url = response.url;
      }
    )
  }
}
