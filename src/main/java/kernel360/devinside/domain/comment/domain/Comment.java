package kernel360.devinside.domain.comment.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import kernel360.devinside.domain.comment.dto.CommentRequest;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_id")
    private Post post;

    private String content;

    private Long level;

    private boolean liked;

    private boolean hated;

    public Comment(User user, Post post, CommentRequest request) {
        this.user = user;
        this.post = post;
        this.content = request.content();
        this.level = request.level();
        this.liked = false;
        this.hated = false;
    }
}