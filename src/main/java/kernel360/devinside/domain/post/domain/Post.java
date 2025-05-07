package kernel360.devinside.domain.post.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import kernel360.devinside.domain.user.domain.User;
import lombok.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    private int view;

    private boolean liked;

    private boolean hated;
}
