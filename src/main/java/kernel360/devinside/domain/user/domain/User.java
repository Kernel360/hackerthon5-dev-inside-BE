package kernel360.devinside.domain.user.domain;

import jakarta.persistence.*;
import kernel360.devinside.common.BaseEntity;

@Entity
public class User extends BaseEntity {

    private String email;

    private String password;

    private String name;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;
}
