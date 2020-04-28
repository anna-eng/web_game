package com.webgame.webgame.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.BooleanFlag;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(unique = true, name = "name")
    private String name;

    @ManyToMany(mappedBy = "players", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Game> games = new HashSet<>();

    @BooleanFlag
    private Boolean active = true;
    @Enumerated
    private Role role;
    public Player(String name, String password) {
        this.role = Role.ROLE_PLAYER;
        this.name = name;
        this.password = password;
    }
//    public Player addGame(Game game) {
//        this.games.add(game);
//        return this;
//    }
}
