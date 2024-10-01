package com.kamal.SocialMedia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamal.SocialMedia.config.JwtProvider;
import com.kamal.SocialMedia.exceptions.UserException;
import com.kamal.SocialMedia.models.User;
import com.kamal.SocialMedia.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not exits with userid "+userId);
	}

	@Override
	public User finduserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	@Override
	public User followUser(Integer reqUserId, Integer userid2) throws UserException {
	    User reqUser = findUserById(reqUserId);
	    User user2 = findUserById(userid2);
	    
	    // Ensure followers and followings lists are initialized
	    if (reqUser.getFollowings() == null) {
	        reqUser.setFollowings(new ArrayList<>());
	    }
	    if (user2.getFollowers() == null) {
	        user2.setFollowers(new ArrayList<>());
	    }
	    
	    // Add user2 to user1's followings
	    reqUser.getFollowings().add(user2.getId());
	    // Add user1 to user2's followers
	    user2.getFollowers().add(reqUser.getId());
	    
	    // Save both users
	    userRepository.save(reqUser);
	    userRepository.save(user2);
	    
	    return reqUser;
	}


//	@Override
//	public User followUser(Integer userid1, Integer userid2) throws Exception {
//		User user1 = findUserById(userid1);
//		
//		User user2 = findUserById(userid2);
//		
//		user2.getFollowers().add(user1.getId());
//		user1.getFollowings().add(user2.getId());
//		
//		userRepository.save(user1);
//		userRepository.save(user2);
//		return user1;
//	}

	@Override
	public User updateUser(User user, Integer userid) throws UserException {
		Optional<User> user1 = userRepository.findById(userid);
		
		if(user1.isEmpty()) {
			throw new UserException("User not exit with id "+userid);
		}
		User oldUser = user1.get();
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		
		User updateUser = userRepository.save(oldUser);
		return updateUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		return user;
	}

}
