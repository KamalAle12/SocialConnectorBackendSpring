package com.kamal.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamal.SocialMedia.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
