package com.kamal.SocialMedia.service;

import java.util.List;

import com.kamal.SocialMedia.models.Message;
import com.kamal.SocialMedia.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessage(Integer chatId) throws Exception;
}
