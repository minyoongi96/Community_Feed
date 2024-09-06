package org.demo.post.application;

import org.demo.fake.FakeObjectFactory;
import org.demo.post.application.dto.CreatePostRequestDto;
import org.demo.post.application.dto.LikeRequestDto;
import org.demo.post.domain.Post;
import org.demo.post.domain.content.PostPulicationState;
import org.demo.user.application.UserService;
import org.demo.user.application.dto.CreateUserRequestDto;
import org.demo.user.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate{

//    private final UserService userService = FakeObjectFactory.getUserService();
//    private final PostService postService = FakeObjectFactory.getPostService();
//
//    private final User user = userService.createUser(new CreateUserRequestDto("user1", "url"));
//    private final User otherUser = userService.createUser(new CreateUserRequestDto("user2", "url"));
//
//    private final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content", PostPulicationState.PUBLIC);

    @Test
    void givneRequestDto_whenCreate_thenReturnPost(){
        // when
        Post savedPost = postService.createPost(postRequestDto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost(){
        // when
        Post savedPost = postService.createPost(postRequestDto);

        // when
        Post updatedPost = postService.updatePost(savedPost.getId(), postRequestDto);

        // then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike(){
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenLikedTwice_thenReturnPostWithLike(){
        //given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostLike_whenUnliked_thenReturnPostWithoutLike(){
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike(){
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

}
