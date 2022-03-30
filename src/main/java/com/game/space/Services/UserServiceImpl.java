package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.space.Repository.UserRepo;
import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.IncompleteDataException;
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
	public User saveUser(User user) throws UsernameNotGivenException, EmailNotGivenException, UserExistException, IncompleteDataException {
		if(user.getUsername()==null||user.getUsername().isBlank()) {
			throw new UsernameNotGivenException("Username not present");
		}
		else if(userRepo.findByUsername(user.getUsername())!=null){
			throw new UserExistException("User already exist");
		}
		if(user.getPhash()==null||user.getPhash().isBlank()) {
			throw new IncompleteDataException("Password not present");
		}
		if(user.getEmail()==null||user.getEmail().isBlank()) {
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
		User letUser=userRepo.findByUsername(user.getUsername());
		return letUser;
	}

	@Override
	public User deleteUser(long userId) throws UserNotExistException {
		User newUser=userRepo.findById(userId).get();
		if(newUser==null) {
			throw new UserNotExistException("user does not exist");
		}
		else {
			userRepo.delete(newUser);
		}
		return newUser;
	}

	

	@Override
	public User getUser(long userId) throws UserNotExistException {
		User newUser=userRepo.findById(userId).get();
		if(newUser==null) {
			throw new UserNotExistException("user does not exist");
		}
		return newUser;
	}

	@Override
	public User getUserByUsernameandPasshash(String usernameorEmail, String pHash) throws UserNotExistException, UserPassNotMatchException, IncompleteDataException {
		if(usernameorEmail.isBlank()||pHash.isBlank()) {
			throw new IncompleteDataException("incomplete data");
		}
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

	@Override
	public User UpdateUserUsername(User user,long userId) throws UserExistException, UsernameNotGivenException, UserNotExistException {
		if(user.getUsername().isEmpty()||user.getUsername()=="null") {
			throw new UsernameNotGivenException("Username or id not given");
		}
		List<User> allUsers=userRepo.findAllByUsername(user.getUsername());
		if(allUsers.size()>0) {
			throw new UserExistException("Username already Taken");
		}
		User letUser=userRepo.findById(userId).get();
		if(letUser==null) {
			throw new UserNotExistException("user not found");
		}
		letUser.setUsername(user.getUsername());
		userRepo.save(letUser);
		User newUser=userRepo.findByUsername(user.getUsername());
		return newUser;
	}

	@Override
	public User UpdateUserEmail(User user,long userId) throws UsernameNotGivenException, UserExistException, UserNotExistException {
		
		if(user.getEmail().isEmpty()||user.getEmail()=="null") {
			throw new UsernameNotGivenException("Email or id not given");
		}
		List<User> allUsers=userRepo.findAllByEmail(user.getEmail());
		if(allUsers.size()>0) {
			throw new UserExistException("Email already Taken");
		}
		User letUser=userRepo.findById(userId).get();
		if(letUser==null) {
			throw new UserNotExistException("user not found");
		}
		letUser.setEmail(user.getEmail());
		userRepo.save(letUser);
		return letUser;
	}

	@Override
	public User UpdateUserFname(User user,long userId) throws UserNotExistException, UsernameNotGivenException {
		
		if(user.getFname()=="null") {
			throw new UsernameNotGivenException("Email or id not given");
		}
		User letUser=userRepo.findById(userId).get();
		if(letUser==null) {
			throw new UserNotExistException("user not found");
		}
		letUser.setFname(user.getFname());
		return letUser;
	}

	@Override
	public User UpdateUserLname(User user,long userId) throws UserNotExistException, UsernameNotGivenException {
		if(user.getLname()=="null") {
			throw new UsernameNotGivenException("Email or id not given");
		}
		User letUser=userRepo.findById(userId).get();
		if(letUser==null) {
			throw new UserNotExistException("user not found");
		}
		letUser.setLname(user.getLname());
		return letUser;
	}

}
