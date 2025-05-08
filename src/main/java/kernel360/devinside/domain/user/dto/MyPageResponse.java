package kernel360.devinside.domain.user.dto;

import kernel360.devinside.domain.post.domain.Post;
import lombok.Builder;

import java.util.List;

@Builder
public record MyPageResponse (
        UserResponse userResponse,
        List<Post> posts
){

}
