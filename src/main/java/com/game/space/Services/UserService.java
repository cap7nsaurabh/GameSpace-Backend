package com.game.space.Services;

import java.util.List;

import com.game.space.Model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public User saveUser(User user);
	public User deleteUser(Long id);
	public User updateUser(User user);
	public User getUser(Long id);
	public User getUserByUsernameandPasshash(String username, String pHash);

}
