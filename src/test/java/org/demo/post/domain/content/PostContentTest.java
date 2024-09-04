package org.demo.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class PostContentTest {
    @org.junit.jupiter.api.Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        // given
        String text = "this is a test";

        // when
        PostContent postContent = new PostContent(text);

        // then
        assertEquals(text, postContent.getContentText());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String empty){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(empty));
    }

    @Test
    void givenContentIsSmall_whenCreated_thenThrowError(){
        // given
        String smallText = "z".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(smallText));
    }

    @Test
    void givenContentIsLarge_whenCreated_thenThrowError(){
        // given
        String largeText = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(largeText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슳"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord){
        // given
        String content = koreanWord.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenCreatedContent_whenLengthIsOkUpdated_thenReturnTextContent(){
        // given
        String text = "this is a test Content";
        PostContent postContent = new PostContent(text);

        // when
        String updateText = "this is an updated test Content";
        postContent.updateContent(updateText);

        // then
        assertEquals(updateText, postContent.getContentText());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenCreateContent_whenLengthIsEmptyUpdated_thenThrowError(String empty){
        // given
        String text = "this is a test Content";
        PostContent postContent = new PostContent(text);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(empty));
    }

    @Test
    void givenCreateContent_whenLengthIsSmallUpdated_thenThrowError(){
        // given
        String text = "this is a test Content";
        PostContent postContent = new PostContent(text);

        // when, then
        String smallText = "z".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(smallText));
    }

    @Test
    void givenCreateContent_whenLenthIsLargeUpdated_thenThrowError(){
        // given
        String text = "this is a test Content";
        PostContent postContent = new PostContent(text);

        // when, then
        String largeText = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(largeText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"감, 둗, 힣, 닭, 뭃"})
    void givenCreateContet_whenLengthIsOverAndKoreanUpdated_thenThrowError(String koreanWord){
        // given
        String text = "this is a test Content";
        PostContent postContent = new PostContent(text);

        // when, then
        String updateText = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updateText));
    }
}

