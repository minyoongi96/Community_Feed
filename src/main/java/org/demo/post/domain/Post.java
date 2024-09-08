package org.demo.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.demo.common.domain.PositiveIntegerCounter;
import org.demo.post.domain.content.Content;
import org.demo.post.domain.content.PostPublicationState;
import org.demo.user.domain.User;
import org.demo.post.domain.content.PostContent;

@Getter
@Builder
@AllArgsConstructor
public class Post {
	private final Long id;
	private final User author;
	private final Content content;
	private final PositiveIntegerCounter likeCount;
	private PostPublicationState state;

	public static Post createPost(Long id, User author, String content, PostPublicationState state){
		return new Post(id, author, new PostContent(content), state);
	}

	public static Post createDefaultPost(Long id, User author, String content){
		return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
	}

	public Post(Long id, User author, Content content, PostPublicationState state) {
		if(author == null){
			throw new IllegalArgumentException("Author cannot be null");
		}

		this.id = id;
		this.author = author;
		this.content = content;
		this.likeCount = new PositiveIntegerCounter();
		this.state = state;
	}

	public Post(Long id, User author, Content content){
		this(id, author, content, PostPublicationState.PUBLIC);
	}

	public int getLikeCount(){
		return likeCount.getCount();
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

		likeCount.increase();
	}

	// 좋아요 취소
	public void unlike(){
		likeCount.decrease();
	}

	public void updatePost(User user, String updateContent, PostPublicationState state){
		if(!this.author.equals(user)){
			throw new IllegalArgumentException();
		}

		this.state = state;
		this.content.updateContent(updateContent);
	}
}
