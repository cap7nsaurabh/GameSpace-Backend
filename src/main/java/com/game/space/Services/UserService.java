package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.IncompleteDataException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Exception.UserPassNotMatchException;
import com.game.space.Exception.UsernameNotGivenException;
import com.game.space.Model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public User saveUser(User user) throws UsernameNotGivenException, EmailNotGivenException, UserExistException, IncompleteDataException;
	public User deleteUser(long id) throws UserNotExistException;
	//public User updateUser(User user) throws UserNotExistException, UserExistException;
	public User getUser(long id) throws UserNotExistException;
	public User getUserByUsernameandPasshash(String usernameorEmail, String pHash) throws UserNotExistException, UserPassNotMatchException, IncompleteDataException;
	public User UpdateUserUsername(User user,long userId) throws UserExistException, UsernameNotGivenException, UserNotExistException;
	public User UpdateUserEmail(User user,long userId) throws UsernameNotGivenException, UserExistException, UserNotExistException;
	public User UpdateUserFname(User user,long userId) throws UserNotExistException, UsernameNotGivenException;
	public User UpdateUserLname(User user,long userId) throws UserNotExistException, UsernameNotGivenException;

}
