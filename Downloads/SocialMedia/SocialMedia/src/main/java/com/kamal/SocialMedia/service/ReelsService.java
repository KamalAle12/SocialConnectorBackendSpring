package com.kamal.SocialMedia.service;

import java.util.List;


import com.kamal.SocialMedia.models.Reels;
import com.kamal.SocialMedia.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Integer userId) throws Exception;
}
