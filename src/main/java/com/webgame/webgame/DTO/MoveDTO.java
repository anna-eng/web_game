package com.webgame.webgame.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveDTO {
    private Long game;
    private int column;
    private int row;
    private Date created;
    private Long player;
}
