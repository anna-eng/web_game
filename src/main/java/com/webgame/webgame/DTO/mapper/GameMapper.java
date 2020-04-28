package com.webgame.webgame.DTO.mapper;

import com.webgame.webgame.DTO.GameDTO;
import com.webgame.webgame.domain.Game;
import com.webgame.webgame.domain.GameMove;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.stream.Collectors;

@Mapper
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);
    PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    GameDTO gameToGameDTOBasic(Game game);
    Game gameDTOToGameBasic(GameDTO gameDTO);

    default GameDTO gameToGameDTO(Game game) {
        if(game == null) return null;
        GameDTO dto = gameToGameDTOBasic(game);
        dto.setPlayers(game.getPlayers().stream().map(playerMapper::playerToPlayerDTO).collect(Collectors.toSet()));
        dto.setCurrentPlayer(game.getCurrentPlayer());
        HashMap<Long, Integer> scores = new HashMap<>();
        for(GameMove move : game.getGameMoves()) {
            scores.put(move.getPlayer().getId(), scores.containsKey(move.getPlayer().getId()) ? scores.get(move.getPlayer().getId()) +move.getValue() : move.getValue());
        }
        dto.setPlayerScores(scores);
        int[][] grid = new int[10][10];
        for(int i =0; i < grid.length; i++) {
            for(int  j =0; j < grid[0].length; j++) {
                grid[i][j] = game.getGrid().get(i).getList().get(j);
            }
        }
        dto.setGameGrid(grid);
        return dto;
    }

}
