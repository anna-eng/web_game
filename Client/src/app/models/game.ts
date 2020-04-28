import { Player } from './player';
import { Move } from './move';

export class Game {
    id?: String;
    player1:Player;
    player2:Player;
    moves:Move[];
    scores:Map<Number, Number>;
    gameGrid:Number[][];

    constructor({id, players, moves, scores, gameGrid }) {
        this.id = id;
        this.player1 = players[0];
        this.player2 = players[1];
        this.moves = moves;
        this.scores = scores;
        this.gameGrid = gameGrid;
    }
}