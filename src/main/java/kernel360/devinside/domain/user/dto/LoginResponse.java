package kernel360.devinside.domain.user.dto;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        Long id,
        String email,
        String name
) {
}
