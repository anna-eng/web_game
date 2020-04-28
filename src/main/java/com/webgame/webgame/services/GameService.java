package com.webgame.webgame.services;


import com.webgame.webgame.DTO.GameDTO;
import com.webgame.webgame.DTO.MoveDTO;
import com.webgame.webgame.DTO.mapper.GameMapper;
import com.webgame.webgame.domain.Game;
import com.webgame.webgame.domain.GameMove;
import com.webgame.webgame.domain.Player;
import com.webgame.webgame.repositories.GameRepository;
import com.webgame.webgame.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final GameMapper gameMapper;
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameMapper = gameMapper;
    }
    public List<GameDTO> getAll() {
        return this.gameRepository.findAll().stream().map(gameMapper::gameToGameDTO).collect(Collectors.toList());
    }

    public GameDTO createNewGame(Long p1, Long p2) {
        Player player1 = playerRepository.findById(p1).orElseThrow(() -> new RuntimeException("Player not Found " + p1));
        Player player2 = playerRepository.findById(p2).orElseThrow(() -> new RuntimeException("Player not Found " + p2));
        Game newGame = new Game();
        newGame.addPlayers(player1, player2);
        return gameMapper.gameToGameDTO(gameRepository.save(newGame));
    }

    public List<GameDTO> getAllForPlayer(Long id) {
        Player p = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not Found "));
        return  p.getGames().stream().map(gameMapper::gameToGameDTO).collect(Collectors.toList());
    }

    public boolean hasNewActions(Long id, Date date) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not Found "));
        return game.getLastUpdated().compareTo(date) > 1;
    }

    public GameDTO createMove(MoveDTO newMove) {
        Game game =   gameRepository.findById(newMove.getGame()).orElseThrow(() -> new RuntimeException("Game not Found "));
        Player player = playerRepository.findById(newMove.getPlayer()).orElseThrow(() -> new RuntimeException("Player not Found "));
        GameMove move = new GameMove(game, player, newMove.getRow(), newMove.getColumn(), newMove.getCreated());
        // check if legal
        game.addMove(move);
        return gameMapper.gameToGameDTO(gameRepository.save(game));
    }
}
