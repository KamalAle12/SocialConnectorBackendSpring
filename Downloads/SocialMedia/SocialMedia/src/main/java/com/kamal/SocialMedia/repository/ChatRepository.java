package com.kamal.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kamal.SocialMedia.models.Chat;
import com.kamal.SocialMedia.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    
    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
    public List<Chat> findByUserId(@Param("userId") Integer userId);
    
    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}

