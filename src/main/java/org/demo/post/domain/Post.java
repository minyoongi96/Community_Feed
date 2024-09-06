package org.demo.post.domain;

import org.demo.common.domain.PositiveIntegerCounter;
import org.demo.post.domain.content.Content;
import org.demo.post.domain.content.PostPulicationState;
import org.demo.user.domain.User;
import org.demo.post.domain.content.PostContent;

public class Post {
	private final Long id;
	private final User author;
	private final Content content;
	private final PositiveIntegerCounter likeCounter;
	private PostPulicationState state;

	public static Post createPost(Long id, User author, String content, PostPulicationState state){
		return new Post(id, author, new PostContent(content), state);
	}

	public static Post createDefaultPost(Long id, User author, String content){
		return new Post(id, author, new PostContent(content), PostPulicationState.PUBLIC);
	}

	public Post(Long id, User author, Content content, PostPulicationState state) {
		if(author == null){
			throw new IllegalArgumentException("Author cannot be null");
		}

		this.id = id;
		this.author = author;
		this.content = content;
		this.likeCounter = new PositiveIntegerCounter();
		this.state = state;
	}

	public Post(Long id, User author, Content content){
		this(id, author, content, PostPulicationState.PUBLIC);
	}

	public Long getId() {
		return this.id;
	}

	public User getAuthor() {
		return this.author;
	}

	public int getLikeCount(){
		return likeCounter.getCount();
	}

	public String getContent(){
		return content.getContentText();
	}

	public Content getContentObject(){
		return this.content;
	}

	// 좋아요
	public void like(User user){
		if(this.author.equals(user)){
			throw new IllegalArgumentException();
		}

		likeCounter.increase();
	}

	// 좋아요 취소
	public void unlike(){
		likeCounter.decrease();
	}

	public void updatePost(User user, String updateContent, PostPulicationState state){
		if(!this.author.equals(user)){
			throw new IllegalArgumentException();
		}

		this.state = state;
		this.content.updateContent(updateContent);
	}
}
