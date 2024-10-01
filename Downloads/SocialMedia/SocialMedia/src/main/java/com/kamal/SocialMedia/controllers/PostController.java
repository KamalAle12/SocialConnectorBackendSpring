package com.kamal.SocialMedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamal.SocialMedia.models.Post;
import com.kamal.SocialMedia.models.User;
import com.kamal.SocialMedia.response.ApiResponse;
import com.kamal.SocialMedia.service.PostService;
import com.kamal.SocialMedia.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt, 
			@RequestBody Post post) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		Post createdPost  = postService.createNewPost(post, reqUser.getId());
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/api/posts/{postid}")
	public ResponseEntity<ApiResponse> deletePost(
	        @PathVariable Integer postid, 
	        @RequestHeader("Authorization") String jwt) throws Exception {
	    
	    try {
	        User reqUser = userService.findUserByJwt(jwt);
	        String message = postService.deletePost(postid, reqUser.getId());
	        ApiResponse res = new ApiResponse(message, true);
	        return new ResponseEntity<>(res, HttpStatus.OK);
	    } catch (Exception e) {
	        // Handle the exception for unauthorized post deletion
	        ApiResponse res = new ApiResponse(e.getMessage(), false);
	        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);  // Return 403 Forbidden for unauthorized action
	    }
	}

	
	@GetMapping("/api/posts/{postid}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postid) throws Exception{
		
		Post post = postService.findPostById(postid);
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/api/posts/user/{userid}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userid){
		List<Post> posts = postService.findPostByUserId(userid);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savePostHandler(
			@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post post = postService.savedPost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, 
			@RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post post = postService.likePost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	
}
