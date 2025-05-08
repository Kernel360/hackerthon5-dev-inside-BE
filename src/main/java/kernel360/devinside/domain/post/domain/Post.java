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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostCategory postCategory;

    private String title;

    private String content;

    private int view;

    private boolean liked;

    private boolean hated;

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    public void increaseView() {
        this.view++;
    }

}
