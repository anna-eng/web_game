import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {  HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './services/jwt-interceptor';
import { AppComponent } from './components/root1/app.component';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './components/login/login.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorInterceptor } from './services/error-interceptor';
import { PlayersComponent } from './components/players/players.component';
import { GamesComponent } from './components/games/games.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    PlayersComponent,
    GamesComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', component: HomepageComponent },
      { path: 'login', component: LoginComponent }
    ])
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
