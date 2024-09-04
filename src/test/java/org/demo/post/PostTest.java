package org.demo.post;

import org.demo.post.domain.Post;
import org.demo.post.domain.content.PostContent;
import org.demo.post.domain.content.PostPulicationState;
import org.demo.user.domain.User;
import org.demo.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {

    private final UserInfo userInfo = new UserInfo("tester", "url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1(){
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeSameUseR_thenThrowError(){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0(){
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated(){
        // when
        String updateContent = "this is an updateContent";
        post.updatePost(user, updateContent, PostPulicationState.PUBLIC);

        // then
        assertEquals(updateContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateContentByOtherUser_thenThrowException(){
        // when, then
        String updateContent = "this is an updateContent";
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, updateContent, PostPulicationState.PUBLIC));


    }
}