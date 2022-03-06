package com.game.space.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.space.Model.Game;
import com.game.space.Model.User;

@Repository
public interface GameRepo extends JpaRepository<Game,Long>  {

}
