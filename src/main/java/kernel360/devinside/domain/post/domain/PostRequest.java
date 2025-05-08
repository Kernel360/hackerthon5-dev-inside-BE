package kernel360.devinside.domain.post.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    @NotNull
    private PostCategory postCategory;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
