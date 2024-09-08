package org.demo.user.domain;

import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.demo.common.domain.PositiveIntegerCounter;

@Getter
@AllArgsConstructor
@Builder
public class User {

	private Long id;
	private UserInfo userInfo;
	private PositiveIntegerCounter followingCounter;
	private PositiveIntegerCounter followerCounter;

	public User(Long id, UserInfo userInfo) {
		this.id = id;
		this.userInfo = userInfo;
		this.followingCounter = new PositiveIntegerCounter();
		this.followerCounter = new PositiveIntegerCounter();
	}

	public void follow(User targetUser){
		// 자기 자신은 follow할 수 없음
		if(targetUser.equals(this)){
			throw new IllegalArgumentException("Cannot follow self");
		}
		followingCounter.increase();
		targetUser.increaseFollowerCounter();
	}

	public void unfollow(User targetUser){
		if(targetUser.equals(this)){
			throw new IllegalArgumentException("Cannot unfollow self");
		}

		this.followingCounter.decrease();
		targetUser.decreaseFollowerCounter();
	}

	private void increaseFollowerCounter(){
		followerCounter.increase();
	}

	private void decreaseFollowerCounter(){
		followerCounter.decrease();
	}

	public int followingCount() {
		return followingCounter.getCount();
	}

	public int followerCount() {
		return followerCounter.getCount();
	}

	public String getName(){
		return userInfo.getName();
	}

	public String getProfileImage(){
		return userInfo.getProfileImageUrl();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
