package kernel360.devinside.domain.post.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import kernel360.devinside.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    private String title;

    private String content;

    private int view;

    private boolean like;

    private boolean hate;
}
