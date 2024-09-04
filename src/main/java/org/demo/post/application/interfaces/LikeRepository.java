package org.demo.post.application.interfaces;

import org.demo.post.domain.Post;
import org.demo.post.domain.comment.Comment;
import org.demo.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    boolean checkLike(Comment comment, User user);

    void like(Post post, User user);

    void unlike(Post post, User user);
}
