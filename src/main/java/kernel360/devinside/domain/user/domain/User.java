package kernel360.devinside.domain.user.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseEntity {

    private String email;

    private String password;

    private String name;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User signup(String email, String name, String nickname,String password) {
        return User.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
