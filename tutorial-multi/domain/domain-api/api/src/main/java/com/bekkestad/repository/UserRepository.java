package com.bekkestad.repository;

import java.util.List;

import javax.ejb.Local;

import com.bekkestad.model.UserDTO;

@Local
public interface UserRepository {

	List<UserDTO> getAllUsers();
	UserDTO getUser(String username);	
	UserDTO getUser(String username, String password);
	Long addUser(UserDTO user);
	void updateUser(UserDTO user);
	void deleteUser(UserDTO user);
}
