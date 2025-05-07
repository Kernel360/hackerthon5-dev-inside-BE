package kernel360.devinside.domain.post.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import kernel360.devinside.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    private String title;

    private String content;

    private int view;

    private boolean liked;

    private boolean hated;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
