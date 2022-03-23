package com.game.space.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.space.Model.Game;

@Repository
public interface GameRepo extends JpaRepository<Game,String>  {
	Game findByUniquename(String name);
}
