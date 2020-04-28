package com.webgame.webgame.controller;


import com.webgame.webgame.DTO.PlayerDTO;
import com.webgame.webgame.services.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;


    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public void createPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO);
    }
    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllLoggedIn();
    }
    @GetMapping
    public PlayerDTO getPlayerInfo(@RequestParam(required = true) String username) {
        return playerService.getPlayerByName(username);
    }
}
