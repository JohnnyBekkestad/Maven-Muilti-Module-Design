package com.bekkestad.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import com.bekkestad.model.UserDTO;
import com.bekkestad.repository.UserRepository;


@SessionScoped
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserRepository users;
	
	public void addUser(UserDTO user){
		users.addUser(user);
	}
	
	public void updateUser(UserDTO user){
		users.updateUser(user);
	}
	
	public void removeUser(UserDTO user){
		users.deleteUser(user);
	}
	
	public UserDTO getUser(String username) throws Exception{
		return users.getUser(username);
	}
	
	public UserDTO loginUser(String username, String password){
		return users.getUser(username, password);
	}
	
	public List<UserDTO> getUsers(){
		return users.getAllUsers();
	}

}
