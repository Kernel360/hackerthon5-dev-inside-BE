package kernel360.devinside.domain.user.service;

import kernel360.devinside.common.exception.CustomException;
import kernel360.devinside.common.exception.ErrorCode;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.dto.*;
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

    @Transactional
    public void delete(LoginUser loginUser) {
        User user = userRepository.findById(loginUser.id())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

    public User findByToken(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public UserUpdateResponse updateNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateUserName(newNickname);
        userRepository.save(user);

        return new UserUpdateResponse(user.getId(), user.getNickname());
    }

    @Transactional
    public UserPasswordUpdateResponse updatePassword(Long userId, String password, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER);
        }

        user.updatePassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return new UserPasswordUpdateResponse(user.getId(), user.getPassword());

    }


}
