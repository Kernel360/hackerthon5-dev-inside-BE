package kernel360.devinside.domain.post.dto;

import kernel360.devinside.domain.post.domain.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDto {

    private Long userId;

    private PostCategory postCategory;

    private String title;

    private String content;

    private int view;

    private boolean liked;

    private boolean hated;

}
