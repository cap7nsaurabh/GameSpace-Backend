package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UsernameNotGivenException;
import com.game.space.Model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public User saveUser(User user) throws UsernameNotGivenException, EmailNotGivenException, UserExistException;
	public User deleteUser(Long id);
	public User updateUser(User user);
	public User getUser(Long id);
	public User getUserByUsernameandPasshash(String username, String pHash);

}
