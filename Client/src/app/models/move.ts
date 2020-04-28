
export class Move {
    column:Number;
    row:Number;
    created:Date;
    player:Number;
    game:Number;

    constructor({column, row, created, player, game}) {
        this.column = column;
        this.row = row;
        this.created = created;
        this.player =player;
        this.game = game;
    }
}