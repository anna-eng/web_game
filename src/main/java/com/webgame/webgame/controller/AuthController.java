package com.webgame.webgame.controller;

import com.webgame.webgame.DTO.PlayerDTO;
import com.webgame.webgame.domain.Role;
import com.webgame.webgame.services.AuthenticationService;
import com.webgame.webgame.services.PlayerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AuthController {

    private static final String authorizationRequestBaseUri = "/api/oauth2/authorize-client";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();


    private final AuthenticationService authenticationService;

    private final PlayerService playerService;

    public AuthController(AuthenticationService authenticationService, PlayerService playerService) {
        this.authenticationService = authenticationService;
        this.playerService = playerService;
    }
    @GetMapping("/test")
    public String loginUser() {
        return  "fefefe";
    }

    @GetMapping("/login")
    public PlayerDTO loginUser(@RequestBody PlayerDTO loginRequest) {
        return authenticationService.login(loginRequest);
    }
    @GetMapping("/logout")
    public void logoutUser() {
        // update the player to have the active = false
        return;
    }
    @PostMapping("/register")
    public PlayerDTO createUser(HttpServletRequest request, @RequestBody PlayerDTO signUpRequest) {
        if(signUpRequest.getRole().equals(Role.ROLE_PLAYER) || signUpRequest.getRole() == null) {
            return playerService.createPlayer(signUpRequest);
        }
        return null;
     }
}
