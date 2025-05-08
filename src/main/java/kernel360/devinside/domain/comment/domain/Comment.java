package kernel360.devinside.domain.comment.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import kernel360.devinside.domain.comment.dto.CommentRequest;
import kernel360.devinside.domain.comment.dto.CommentUpdateRequest;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    private String content;

    private boolean liked;

    private boolean hated;

    public Comment(User user, Post post, CommentRequest request) {
        this.user = user;
        this.post = post;
        this.parent =request.parent();
        this.content = request.content();
        this.liked = false;
        this.hated = false;
    }

    public void updateComment(CommentUpdateRequest request) {
        this.content = request.content();
    }

    public void addChild(Comment child) {
        children.add(child);
    }
}