package kernel360.devinside.domain.post.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponse<T>{

    private T body;

    private Pagination pagination;
}