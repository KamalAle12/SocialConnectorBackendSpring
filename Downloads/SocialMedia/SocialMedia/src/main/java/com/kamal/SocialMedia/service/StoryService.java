package com.kamal.SocialMedia.service;

import java.util.List;

import com.kamal.SocialMedia.models.Story;
import com.kamal.SocialMedia.models.User;

public interface StoryService {
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
