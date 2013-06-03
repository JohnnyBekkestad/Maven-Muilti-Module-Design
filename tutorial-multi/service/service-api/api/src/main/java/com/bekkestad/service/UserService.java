package com.bekkestad.service;

import java.util.List;

import com.bekkestad.model.UserDTO;

public interface UserService {

	void addUser(UserDTO user);
	
	void updateUser(UserDTO user);
	
	void removeUser(UserDTO user);
	
	UserDTO getUser(String username) throws Exception;
	
	UserDTO loginUser(String username, String password);
	
	List<UserDTO> getUsers();
}
