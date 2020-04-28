package com.webgame.webgame.services;


import com.webgame.webgame.DTO.PlayerDTO;
import com.webgame.webgame.DTO.mapper.PlayerMapper;
import com.webgame.webgame.domain.Player;
import com.webgame.webgame.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public AuthenticationService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }


    public PlayerDTO login(PlayerDTO loginRequest) {
         if(loginRequest.getName() != null) {
            Player player = playerRepository.findByName(loginRequest.getName());
            if(player.getPassword().equals(loginRequest.getPassword())) return playerMapper.playerToPlayerDTO(player);
            return null;
        } else throw new RuntimeException("Username/Email were not specified.");
    }

}
