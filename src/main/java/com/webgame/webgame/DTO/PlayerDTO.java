package com.webgame.webgame.DTO;

import com.webgame.webgame.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    @NotNull
    private String name;
    private String password;
    private Role role;
}
