package org.demo.post.application.interfaces;

import org.demo.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
