package kernel360.devinside.domain.post.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
