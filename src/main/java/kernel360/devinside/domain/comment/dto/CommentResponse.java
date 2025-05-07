package kernel360.devinside.domain.comment.dto;

import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.user.domain.User;

import java.time.LocalDateTime;

public record CommentResponse(
        User user,
        Post post,
        String content,
        LocalDateTime createdAt,
        boolean liked,
        boolean hated
) {
}
