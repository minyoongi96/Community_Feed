package org.demo.post.domain.comment;

import org.demo.common.domain.PositiveIntegerCounter;
import org.demo.post.domain.Post;
import org.demo.post.domain.content.Content;
import org.demo.user.domain.User;

public class Comment {
	private final Long id;
	private final Post post;
	private final User author;
	private final Content content;
	private final PositiveIntegerCounter likeCounter;

	public Comment(Long id, Post post, User author, Content content, PositiveIntegerCounter likeCounter) {
		if(post == null){
			throw new IllegalArgumentException("Post cannot be null");
		}

		if(author == null){
			throw new IllegalArgumentException("Author cannot be null");
		}

		if(content == null){
			throw new IllegalArgumentException("Content cannot be null");
		}

		this.id = id;
		this.post = post;
		this.author = author;
		this.content = content;
		this.likeCounter = likeCounter;
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

	public void updateContent(User user, String updateContent){
		if(!this.author.equals(user)){
			throw new IllegalArgumentException();
		}

		this.content.updateContent(updateContent);
	}
}
