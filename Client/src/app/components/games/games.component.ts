import { Component, OnInit } from '@angular/core';
import { GamesService } from 'src/app/services/games.service';
import { Game } from 'src/app/models/game';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {
  list:Game[];
  currentGame:Game;
  
  constructor(private service:GamesService) { }

  ngOnInit() {
    this.refreshMyGamesList();
  }
  refreshMyGamesList() {
    this.service.getGamesList().subscribe(
      (response:Game[]) => {
        this.list = response;
        console.log('')
      }
    )
  }
  startGame(game) {
    this.currentGame = game;
  }

}
