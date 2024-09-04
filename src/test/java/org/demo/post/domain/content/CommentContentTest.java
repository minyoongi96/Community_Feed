package org.demo.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext(){
        // given
        String contentText = "this is a test content";

        // when
        CommentContent content = new CommentContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowException(){
        // given
        String contentText = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가", "갉", "힣"})
    void givenKoreanContentLengthIsOver_whenCreateCommentContent_thenThrowException(String koreanContent){
        // when, then
        String contentText = koreanContent.repeat(101);
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmptyContent_whenCreateCommentContent_thenThrowException(String contentText){
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }
}
