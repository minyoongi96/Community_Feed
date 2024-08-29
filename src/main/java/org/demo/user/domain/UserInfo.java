package org.demo.user.domain;

public class UserInfo {
	private final String name;
	private final String profileImageUrl;

	// User 정보 유효성 검증
	public UserInfo(String name, String profileImageUrl) {
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Name cannot be null or empty");
		}
		this.name = name;
		this.profileImageUrl = profileImageUrl;
	}
}
