package kernel360.devinside.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserNicknameUpdateRequest(

        @NotBlank(message = "이메일은 비어 있을 수 없습니다")
        String nickname

) {
}
