package com.webgame.webgame.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;


//@Data
@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "game")

public class Game {
    final int GRID_SIZE = 10;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private GameStatus status;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "game_players",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    @JsonIgnore
    Set<Player> players = new HashSet<>();


    @OneToMany( cascade = CascadeType.ALL,
            mappedBy = "game",
            fetch = FetchType.EAGER)
    private List<MatrixRow> grid = new ArrayList<>();



    @OneToMany( cascade = CascadeType.ALL,
            mappedBy = "game",
            fetch = FetchType.EAGER)
    private Set<GameMove> gameMoves = new HashSet<>();


    private Date lastUpdated;

    private Long currentPlayer;

    private boolean rowMovement;

    public Game() {
        this.status = GameStatus.ONGOING;
        Random random = new Random();
        for(int i =0 ; i < GRID_SIZE; i++) {
            grid.add(new MatrixRow(this));
        }
    }
    public boolean isTaken(int row, int column) {
        return this.grid.get(row).getList().get(column) == -1;
    }



    public Game addPlayers(Player player_one, Player player_two) {
        if(this.players.size() == 0) {
            this.players.add(player_one);
            this.players.add(player_two);
            this.currentPlayer = Math.random() > 0.5 ? player_one.getId() : player_two.getId();
        }
        return this;
    }


    public  Game addMove(GameMove move) {
        if(!this.status.equals(GameStatus.FINISHED)) {
            if(move.getPlayer().getId().equals(this.currentPlayer) ) {
                this.currentPlayer = this.players.stream().filter(player -> !player.getId().equals(this.currentPlayer)).findFirst().get().getId();
                move.setValue(this.grid.get(move.getRow()).getList().get(move.getColumn()));
                this.gameMoves.add(move);
                this.grid.get(move.getRow()).getList().set(move.getColumn(), -1);
            } else throw new RuntimeException("Please wait for other player");
        } else throw new RuntimeException("Game already finished");
        return this;
    }
}
