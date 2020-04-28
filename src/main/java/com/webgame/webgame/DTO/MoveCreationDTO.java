package com.webgame.webgame.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MoveCreationDTO {
    private Long gameId;
    private int column;
    private  int row;
}
