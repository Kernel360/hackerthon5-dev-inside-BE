package kernel360.devinside.domain.comment.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    private Long userId;

    private Long postId;

    private String content;

    private Long level;

    private boolean like;

    private boolean hast;
}