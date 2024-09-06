package org.demo.fake;

import org.demo.post.application.CommentService;
import org.demo.post.application.PostService;
import org.demo.post.application.interfaces.CommentRepository;
import org.demo.post.application.interfaces.LikeRepository;
import org.demo.post.application.interfaces.PostRepository;
import org.demo.post.repository.FakeCommentRepository;
import org.demo.post.repository.FakeLikeRepository;
import org.demo.post.repository.FakePostRepository;
import org.demo.user.application.UserRelationService;
import org.demo.user.application.UserService;
import org.demo.user.application.interfaces.UserRelationRepository;
import org.demo.user.application.interfaces.UserRepository;
import org.demo.user.repository.FakeUserRelationRepository;
import org.demo.user.repository.FakeUserRepository;

public class FakeObjectFactory {
    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(postService, userService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
