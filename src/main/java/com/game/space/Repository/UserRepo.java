package com.game.space.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.space.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	

}
