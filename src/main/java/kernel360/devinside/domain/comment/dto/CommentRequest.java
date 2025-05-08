package kernel360.devinside.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import kernel360.devinside.domain.comment.domain.Comment;
import lombok.Builder;

@Builder
public record CommentRequest(
        Long level,
        @NotBlank
        String content,
        Comment parent,
        boolean like,
        boolean hate
) {
}
