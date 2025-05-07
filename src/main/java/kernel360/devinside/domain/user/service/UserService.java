package kernel360.devinside.domain.user.service;

import kernel360.devinside.common.exception.CustomException;
import kernel360.devinside.common.exception.ErrorCode;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.dto.LoginRequest;
import kernel360.devinside.domain.user.dto.LoginResponse;
import kernel360.devinside.domain.user.dto.UserSignupRequest;
import kernel360.devinside.domain.user.repository.UserRepository;
import kernel360.devinside.domain.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public User signupUser(UserSignupRequest request) {
        return userRepository.save(User.signup(request.email(), request.name(), request.nickname(), passwordEncoder.encode(request.password())));
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .filter(m -> passwordEncoder.matches(request.password(), m.getPassword()))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_MATCH_LOGIN_INFO));

        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail(), user.getRole());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public User findByToken(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
