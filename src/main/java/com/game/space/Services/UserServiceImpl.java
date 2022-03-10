package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.space.Repository.UserRepo;
import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UsernameNotGivenException;
import com.game.space.Model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepo userRepo;

	@Override
	public List<User> getAllUsers() {
		List<User> userList=new ArrayList<User>();
		userList=userRepo.findAll();
		return userList;
	}

	@Override
	public User saveUser(User user) throws UsernameNotGivenException, EmailNotGivenException, UserExistException {
		if(user.getUsername()==null) {
			throw new UsernameNotGivenException("Username not present");
		}
		if(user.getEmail()==null) {
			throw new EmailNotGivenException("Email not present");
		}
		User newUser=userRepo.findByUsername(user.getUsername());
		if(newUser==null) {
			userRepo.save(newUser);
		}
		else {
			throw new UserExistException("User already exist");
		}
		return newUser;
	}

	@Override
	public User deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsernameandPasshash(String username, String pHash) {
		// TODO Auto-generated method stub
		return null;
	}

}
