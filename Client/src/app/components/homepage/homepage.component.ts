import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private authService:LoginService, private router:Router) { }

  ngOnInit() {
  }
  logout() {
    this.authService.logout().subscribe(
      success => {
        this.router.navigate(["/login"])
      }
    )
    
  }
}
