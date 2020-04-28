package com.webgame.webgame.DTO;

import com.webgame.webgame.domain.GameStatus;
import com.webgame.webgame.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private Set<PlayerDTO> players;
    private GameStatus status;
    private Long currentPlayer;
    private HashMap<Long, Integer> playerScores;
    private int[][] gameGrid;
    private Set<MoveDTO> moves;
}
