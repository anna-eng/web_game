package com.webgame.webgame.controller;

import com.webgame.webgame.DTO.GameDTO;
import com.webgame.webgame.DTO.MoveDTO;
import com.webgame.webgame.domain.Game;
import com.webgame.webgame.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(GameController.BASE_URL)
public class GameController {
    public static final String BASE_URL= "/api/games";


    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/player/{player_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameDTO> getAllGames(@PathVariable(required = true) Long player_id) {
        return this.gameService.getAllForPlayer(player_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO createNewGame(@RequestParam(required = true) Long p1, @RequestParam(required = true) Long p2) {
        return this.gameService.createNewGame(p1, p2);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean hasNewActions(@PathVariable(required = true) Long id,  @RequestParam(required = true) Date date) {
        return this.gameService.hasNewActions(id, date);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameDTO createNewMove(@RequestBody MoveDTO newMove) {
        return this.gameService.createMove(newMove);
    }


}
