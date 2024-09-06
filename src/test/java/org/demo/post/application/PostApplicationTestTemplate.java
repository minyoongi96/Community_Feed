package org.demo.post.application;

import org.demo.fake.FakeObjectFactory;
import org.demo.post.application.dto.CreateCommentRequestDto;
import org.demo.post.application.dto.CreatePostRequestDto;
import org.demo.post.domain.Post;
import org.demo.post.domain.content.PostPulicationState;
import org.demo.user.application.UserService;
import org.demo.user.application.dto.CreateUserRequestDto;
import org.demo.user.domain.User;

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", "url"));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", "url"));

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPulicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContentText = "this is test comment";
    final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContentText);
}
