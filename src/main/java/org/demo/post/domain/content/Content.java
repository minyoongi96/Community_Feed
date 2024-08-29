package org.demo.post.domain.content;

import org.demo.post.domain.common.DateTimeInfo;

// 게시글, 댓글 등 Content 유효성 검사를 위한 추상화 객체
public abstract class Content {
	String contentText;
	final DateTimeInfo dateTimeInfo;

	public Content(String contentText) {
		checkText(contentText);
		this.contentText = contentText;
		this.dateTimeInfo = new DateTimeInfo();
	}

	public void updateContent(String updateContent){
		checkText(updateContent);
		this.contentText = updateContent;
		this.dateTimeInfo.updateEditDateTime();
	}

	protected abstract void checkText(String contentText);

	public String getContentText() {
		return contentText;
	}
}
