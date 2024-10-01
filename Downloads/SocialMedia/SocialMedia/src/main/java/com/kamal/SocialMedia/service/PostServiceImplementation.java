package com.kamal.SocialMedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamal.SocialMedia.models.Post;
import com.kamal.SocialMedia.repository.PostRepository;
import com.kamal.SocialMedia.repository.UserRepository;
import com.kamal.SocialMedia.models.User;


@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userid) throws Exception {
		User user = userService.findUserById(userid);
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postid, Integer userid) throws Exception {
	    Post post = findPostById(postid);
	    User user = userService.findUserById(userid);

	    // Ensure post and user are not null, and their IDs match
	    if (post.getUser() == null || post.getUser().getId() == null || !post.getUser().getId().equals(user.getId())) {
	        throw new Exception("You can't delete another user post");
	    }

	    postRepository.delete(post);
	    return "Post deleted successfully";
	}


	@Override
	public List<Post> findPostByUserId(Integer userid) {
		
		return postRepository.findPostByUserId(userid);
	}

	@Override
	public Post findPostById(Integer postid) throws Exception {
		Optional<Post> opt = postRepository.findById(postid);
		if(opt.isEmpty()) {
			throw new Exception("post not found with id "+postid);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postid, Integer userid) throws Exception {
		Post post = findPostById(postid);
		User user = userService.findUserById(userid);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postid, Integer userid) throws Exception {
		Post post = findPostById(postid);
		User user = userService.findUserById(userid);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		
		return postRepository.save(post);
	}
	
}
