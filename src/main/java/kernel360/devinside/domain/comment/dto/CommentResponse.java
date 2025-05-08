package kernel360.devinside.domain.comment.dto;

import kernel360.devinside.domain.comment.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record CommentResponse(
        Long id,
        String userName,
        String content,
        LocalDateTime createdAt,
        boolean liked,
        boolean hated,
        List<CommentResponse> children
) {
    public static CommentResponse fromEntity(Comment comment, List<CommentResponse> childrenComment) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.isLiked(),
                comment.isHated(),
                childrenComment
        );
    }
}
