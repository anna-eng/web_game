package com.webgame.webgame.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

//@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "game_move")
public class GameMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "move_row")
    private int row;

    @Column(name = "move_column")
    private int column;

    @Column(name = "created")
    private Date created = new Date();

    private int value;
    public GameMove(Game game, Player player, int row, int column, Date created) {
        this.game = game;
        this.player = player;
        this.row = row;
        this.column = column;
        this.created = created;
    }
}
