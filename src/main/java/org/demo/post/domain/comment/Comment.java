package org.demo.post.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.demo.common.domain.PositiveIntegerCounter;
import org.demo.post.domain.Post;
import org.demo.post.domain.content.CommentContent;
import org.demo.post.domain.content.Content;
import org.demo.post.domain.content.PostContent;
import org.demo.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class Comment {
	private final Long id;
	private final Post post;
	private final User author;
	private final Content content;
	private final PositiveIntegerCounter likeCount;

	public static Comment createComment(Post post, User author, String content){
		return new Comment(null, post, author, new CommentContent(content));
	}

	public Comment(Long id, Post post, User author, Content content) {
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
		this.likeCount = new PositiveIntegerCounter();
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

	public void updateContent(User user, String updateContent){
		if(!this.author.equals(user)){
			throw new IllegalArgumentException();
		}

		this.content.updateContent(updateContent);
	}

	public int getLikeCount(){
		return likeCount.getCount();
	}

	public String getContent(){
		return content.getContentText();
	}

	public Content getContentObject(){
		return content;
	}
}
