package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Exception.UserPassNotMatchException;
import com.game.space.Exception.UsernameNotGivenException;
import com.game.space.Model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public User saveUser(User user) throws UsernameNotGivenException, EmailNotGivenException, UserExistException;
	public User deleteUser(Long id) throws UserNotExistException;
	public User updateUser(User user) throws UserNotExistException;
	public User getUser(Long id) throws UserNotExistException;
	public User getUserByUsernameandPasshash(String usernameorEmail, String pHash) throws UserNotExistException, UserPassNotMatchException;

}
