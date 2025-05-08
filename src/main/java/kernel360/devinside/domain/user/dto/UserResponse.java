package kernel360.devinside.domain.user.dto;

import kernel360.devinside.domain.user.domain.Role;
import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String email,
        String name,
        String nickname,
        Role role
) {
}
