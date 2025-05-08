package kernel360.devinside.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserPasswordUpdateRequest(

        @NotBlank(message = "비밀번호는 비어 있을 수 없습니다")
        String password,

        @NotBlank(message = "새 비밀번호는 비어 있을 수 없습니다")
        String newPassword

) {
}
