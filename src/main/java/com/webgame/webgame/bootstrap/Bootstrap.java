package com.webgame.webgame.bootstrap;

import com.webgame.webgame.domain.Game;
import com.webgame.webgame.domain.GameStatus;
import com.webgame.webgame.domain.Player;
import com.webgame.webgame.domain.Role;
import com.webgame.webgame.repositories.GameRepository;
import com.webgame.webgame.repositories.PlayerRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public Bootstrap(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }
    public void loadData() {
        Player p1 = new Player("Sarah", new BCryptPasswordEncoder().encode("sample"));
        Player p2 = new Player("Alex", new BCryptPasswordEncoder().encode("sample"));
        Player p3 = new Player("Jack", new BCryptPasswordEncoder().encode("sample"));
        Player admin = new Player("Admin", new BCryptPasswordEncoder().encode("admin"));

        admin.setRole(Role.ADMIN_PLAYER);

        p1 = playerRepository.save(p1);
        p2 = playerRepository.save(p2);
        p3 = playerRepository.save(p3);
        admin  = playerRepository.save(admin);

        Game g1 = new Game();
        g1.setStatus(GameStatus.ONGOING);
        g1.addPlayers(p1, p2);

        Game g2 = new Game();
        g2.setStatus(GameStatus.ONGOING);
        g2.addPlayers(p1, p3);


        gameRepository.save(g1);
        gameRepository.save(g2);
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    loadData();
    }


}
