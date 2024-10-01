package com.kamal.SocialMedia.service;

import java.util.List;

import com.kamal.SocialMedia.exceptions.UserException;
import com.kamal.SocialMedia.models.User;

public interface UserService {
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User finduserByEmail(String email);
	
	public User followUser(Integer userid1, Integer userid2) throws UserException;
	
	public User updateUser(User user, Integer userid) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
}
