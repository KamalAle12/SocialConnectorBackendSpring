package com.kamal.SocialMedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamal.SocialMedia.models.Chat;
import com.kamal.SocialMedia.models.User;
import com.kamal.SocialMedia.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);
		
		if(isExist!=null) {
			return isExist;
		}
		
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> opt  = chatRepository.findById(chatId);
		
		if(opt.isEmpty()) {
			throw new Exception("Chat not found with id - "+chatId);
		}
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChar(Integer userId) {
		
		return chatRepository.findByUserId(userId);
	}

}
