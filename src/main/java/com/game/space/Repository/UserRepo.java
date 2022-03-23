package com.game.space.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.space.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
	User findByUsername(String username);
	User findByEmail(String email);
	List<User>findAllByUsername(String username);
	List<User>findAllByEmail(String email);
	

}
