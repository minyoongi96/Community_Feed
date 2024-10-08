package org.demo.post.application;

import org.demo.post.application.dto.LikeRequestDto;
import org.demo.post.application.dto.UpdateCommentRequestDto;
import org.demo.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment(){
        // when
        Comment comment = commentService.createComment(commentRequestDto);

        // then
        assertEquals(commentContentText, comment.getContent());
    }

    @Test
    void givenCreateComment_whenUpdated_thenReturnUpdatedComment(){
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "update content");
        Comment updateComment = commentService.updateComment(updateCommentRequestDto);

        // then
        assertEquals(comment.getId(), updateComment.getId());
        assertEquals(comment.getAuthor(), updateComment.getAuthor());
        assertEquals(comment.getContent(), updateComment.getContent());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike(){
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithoutLike(){
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());

    }
}
