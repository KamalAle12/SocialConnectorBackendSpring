package com.kamal.SocialMedia.request;


public class CreateChatRequest {
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public CreateChatRequest() {
		
	}

	public CreateChatRequest(Integer userId) {
		super();
		this.userId = userId;
	}
	
	
	
	
}
