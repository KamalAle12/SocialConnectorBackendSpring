package com.kamal.SocialMedia.service;

import java.util.List;

import com.kamal.SocialMedia.models.Chat;
import com.kamal.SocialMedia.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChar(Integer userId);
}
