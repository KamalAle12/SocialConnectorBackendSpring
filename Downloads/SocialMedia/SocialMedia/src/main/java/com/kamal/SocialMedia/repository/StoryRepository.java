package com.kamal.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamal.SocialMedia.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {
	
	public List<Story> findByUserId(Integer userId);
}
