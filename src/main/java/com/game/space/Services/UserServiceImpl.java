package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.space.Repository.UserRepo;
import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Exception.UserPassNotMatchException;
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
		else if(userRepo.findByUsername(user.getUsername())!=null){
			throw new UserExistException("User already exist");
		}
		if(user.getEmail()==null) {
			throw new EmailNotGivenException("Email not present");
		}
		else if(userRepo.findByEmail(user.getEmail())!=null){
			throw new UserExistException("User already exist");
		}
		User newUser=userRepo.findByUsername(user.getUsername());
		if(newUser==null) {
			userRepo.save(user);
		}
		else {
			throw new UserExistException("User already exist");
		}
		return newUser;
	}

	@Override
	public User deleteUser(Long id) throws UserNotExistException {
		User newUser=userRepo.getById(id);
		if(newUser==null) {
			throw new UserNotExistException("user does not exist");
		}
		else {
			userRepo.delete(newUser);
		}
		return newUser;
	}

	@Override
	public User updateUser(User user) throws UserNotExistException {
		User newUser=userRepo.getById(user.getId());
		if(newUser==null) {
			throw new UserNotExistException("user does not exist");
		}
		else{
			userRepo.save(user);
		}
		User newUser1=userRepo.getById(user.getId());
		return newUser;
	}

	@Override
	public User getUser(Long id) throws UserNotExistException {
		User newUser=userRepo.getById(id);
		if(newUser==null) {
			throw new UserNotExistException("user does not exist");
		}
		return newUser;
	}

	@Override
	public User getUserByUsernameandPasshash(String usernameorEmail, String pHash) throws UserNotExistException, UserPassNotMatchException {
		User letUser=userRepo.findByUsername(usernameorEmail);
		if(letUser==null) {
			letUser=userRepo.findByEmail(usernameorEmail);
			if(letUser==null) {
				throw new UserNotExistException("user does not exist");
			}
			else {
				if(!letUser.getPhash().equals(pHash)) {
					throw new UserPassNotMatchException("password incorrect");
				}
			}
		}
		else {
			if(!letUser.getPhash().equals(pHash)) {
				throw new UserPassNotMatchException("password incorrect");
			}
		}
		return letUser;
	}

}
