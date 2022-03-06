package com.game.space.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.space.Model.User;

@Repository
interface UserRepo extends JpaRepository<User,Long> {
	

}
