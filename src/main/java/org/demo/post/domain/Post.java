package org.demo.post.domain;

import org.demo.common.domain.PositiveIntegerCounter;
import org.demo.post.domain.content.PostPulicationState;
import org.demo.user.domain.User;
import org.demo.post.domain.content.PostContent;

public class Post {
	private final Long id;
	private final User author;
	private final PostContent postContent;
	private final PositiveIntegerCounter likeCounter;
	private PostPulicationState state;

	public Post(Long id, User author, PostContent postContent, PositiveIntegerCounter likeCounter) {
		if(author == null){
			throw new IllegalArgumentException("Author cannot be null");
		}

		this.id = id;
		this.author = author;
		this.postContent = postContent;
		this.likeCounter = likeCounter;
		this.state = PostPulicationState.PUBLIC;
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
		this.postContent.updateContent(updateContent);
	}
}
