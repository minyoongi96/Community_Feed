package org.demo.post.domain.comment;

import org.demo.post.domain.Post;
import org.demo.post.domain.content.CommentContent;
import org.demo.post.domain.content.PostContent;
import org.demo.user.domain.User;
import org.demo.user.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.IllegalFormatCodePointException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {
    private final UserInfo userInfo = new UserInfo("tester", "url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1(){
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowException(){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenComentCreatedAndLike_whenUnlike_thenLikeCountShouldBe0(){
        // given
        comment.like(otherUser);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe0(){
        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenContentShouldBeUpdated(){
        // given
        String newContent = "new content";

        // when
        comment.updateContent(user, newContent);

        // then
        assertEquals(newContent, comment.getContent());
    }

    @Test
    void givenCommentCreated_whenUpdateByOtherUser_thenThrowException(){
        // when, then
        String newContent = "new content";
        assertThrows(IllegalArgumentException.class, () -> comment.updateContent(otherUser, newContent));
    }

    @Test
    void givenCommentCreated_whenUpdateContentIsOver_thenThrowException(){
        // when
        String newContent = "a".repeat(101);

        // them
        assertThrows(IllegalArgumentException.class, () -> comment.updateContent(user, newContent));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenCommentCreated_whenUpdateContentIsEmpty_thenThrowException(String newContent){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateContent(user, newContent));
    }
}
