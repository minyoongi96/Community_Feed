package org.demo.user.application;

import org.demo.user.application.dto.CreateUserRequestDto;
import org.demo.user.application.dto.FollowUserRequestDto;
import org.demo.user.application.interfaces.UserRelationRepository;
import org.demo.user.application.interfaces.UserRepository;
import org.demo.user.domain.User;
import org.demo.user.repository.FakeUserRelationRepository;
import org.demo.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();

    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenThrowError(){
        // given
        userRelationService.follow(requestDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateSameUser_whenFollow_thenThrowError(){
        // given
        FollowUserRequestDto sameUserDto = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUserDto));
    }

    @Test
    void givenCreateTwoUserFollow_whenUnfollow_thenUserUnfollowSaved(){
        // given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unfollow(requestDto);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenThrowError(){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenSameUser_whenFollow_thenThrowError(){
        // when
        FollowUserRequestDto sameUserDto = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUserDto));
    }
}
