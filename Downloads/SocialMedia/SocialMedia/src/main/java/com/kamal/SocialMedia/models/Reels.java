package com.kamal.SocialMedia.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reels {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String title;
	private String video;
	
	
//	One user can create multiple reeels but one reels has only one user
	@ManyToOne
	private User user;


	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Reels() {
		
	}


	public Reels(Integer id, String title, String video, User user) {
		super();
		this.id = id;
		this.title = title;
		this.video = video;
		this.user = user;
	}


	
	
	
	
}
