package kernel360.devinside.domain.user.repository;

import kernel360.devinside.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
