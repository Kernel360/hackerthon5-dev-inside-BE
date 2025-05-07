package kernel360.devinside.domain.user.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    private String email;

    private String password;

    private String name;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String nickname, Role role) {
        this.name = name;
        this.nickname = nickname;
        this.role = role;
    }
}
