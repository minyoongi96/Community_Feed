package org.demo.post.domain.content;

public class PostContent extends Content {

	public PostContent(String contentText) {
		super(contentText);
	}

	@Override
	protected void checkText(String contentText){
		if(contentText == null || contentText.isEmpty()){
			throw new IllegalArgumentException("Content text is null or empty");
		}

		if(contentText.length() < 5 || contentText.length() > 500){
			throw new IllegalArgumentException("Invalid post content");
		}
	}
}
