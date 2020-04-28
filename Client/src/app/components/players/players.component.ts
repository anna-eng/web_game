import { Component, OnInit } from '@angular/core';
import { PlayersService } from 'src/app/services/players.service';
import { Player } from 'src/app/models/player';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {
  list:Player[];
  
  constructor( private service:PlayersService) {

  }

  ngOnInit() {
    this.getLoggedInPlayers()
  }

  getLoggedInPlayers() {
      this.service.getPlayersList().subscribe(
        (response:Player[]) => {
          this.list = response;
          console.log('')
        }
      )
  }
  startGame(id) {
    console.log(id);
  }
}
