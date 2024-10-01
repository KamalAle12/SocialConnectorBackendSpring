package com.kamal.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kamal.SocialMedia.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	@Query("select p from Post p where p.user.id=:userid")
	List<Post> findPostByUserId(Integer userid);
}
