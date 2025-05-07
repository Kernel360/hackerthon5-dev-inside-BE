package kernel360.devinside.domain.comment.dto;

import kernel360.devinside.domain.comment.domain.Comment;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.user.domain.User;
import lombok.Builder;

@Builder
public record CommentRequest(
        Long level,
        String content,
        boolean like,
        boolean hast

) {
    public Comment toEntity(User user, Post post, CommentRequest request) {
        return new Comment(user, post, request);
    }
}
