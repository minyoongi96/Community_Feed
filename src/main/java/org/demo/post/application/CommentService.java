package org.demo.post.application;

import org.demo.post.application.dto.CreateCommentRequestDto;
import org.demo.post.application.dto.LikeRequestDto;
import org.demo.post.application.dto.UpdateCommentRequestDto;
import org.demo.post.application.interfaces.CommentRepository;
import org.demo.post.application.interfaces.LikeRepository;
import org.demo.post.domain.Post;
import org.demo.post.domain.comment.Comment;
import org.demo.user.application.UserService;
import org.demo.user.domain.User;

public class CommentService {
    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(PostService postService, UserService userService, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateContent(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto){
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
             return;
        }

        comment.like(user);
        commentRepository.save(comment);
    }

    public void unlikeComment(LikeRequestDto dto){
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            comment.unlike();
            commentRepository.save(comment);
        }
    }
}
