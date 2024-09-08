package org.demo.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.demo.common.repository.entity.TimeBaseEntity;
import org.demo.post.domain.Post;
import org.demo.post.domain.comment.Comment;
import org.demo.user.domain.User;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {
    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeUser) {
        this.id = new LikeIdEntity(post.getId(), likeUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likeUser) {
        this.id = new LikeIdEntity(comment.getId(), likeUser.getId(), LikeTarget.COMMENT.name());
    }
}
