package com.webgame.webgame.services;

import com.webgame.webgame.DTO.PlayerDTO;
import com.webgame.webgame.DTO.mapper.PlayerMapper;
import com.webgame.webgame.domain.Player;
import com.webgame.webgame.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player newPlayer =  playerMapper.playerDTOToPlayer(playerDTO);
        return playerMapper.playerToPlayerDTO(playerRepository.save(newPlayer));
    }
    public List<PlayerDTO> getAllLoggedIn() {
        return this.playerRepository.findAll().stream()
                .filter(player -> player.getActive())
                .map(playerMapper::playerToPlayerDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO getPlayerById(Long id) {
        return  playerMapper.playerToPlayerDTO(playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not Found ")));
    }

    public PlayerDTO getPlayerByName(String username) {
        return  playerMapper.playerToPlayerDTO(playerRepository.findByName(username));
    }
}
