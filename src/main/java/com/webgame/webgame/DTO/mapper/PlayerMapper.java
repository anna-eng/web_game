package com.webgame.webgame.DTO.mapper;

import com.webgame.webgame.DTO.PlayerDTO;
import com.webgame.webgame.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDTO playerToPlayerDTO(Player player);

    Player playerDTOToPlayer(PlayerDTO playerDTO);

}
