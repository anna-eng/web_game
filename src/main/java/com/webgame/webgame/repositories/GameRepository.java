package com.webgame.webgame.repositories;

import com.webgame.webgame.domain.Game;
import com.webgame.webgame.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
