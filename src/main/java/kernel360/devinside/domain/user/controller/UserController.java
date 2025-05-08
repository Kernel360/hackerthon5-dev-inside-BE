package kernel360.devinside.domain.user.controller;

import jakarta.validation.Valid;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.dto.*;
import kernel360.devinside.domain.user.service.UserService;
import kernel360.devinside.domain.user.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Value("${cookie.valid-time}")
    private long COOKIE_VALID_TIME;

    @Value("${cookie.name}")
    private String COOKIE_NAME;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserSignupResponse> signup(@Valid @RequestBody UserSignupRequest request) {
        User user = userService.signupUser(request);
        UserSignupResponse response = new UserSignupResponse(user.getEmail(), user.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        ResponseCookie cookie = CookieUtil.createCookie(COOKIE_NAME, response.accessToken(), COOKIE_VALID_TIME);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getLoginResponse(@AuthenticationPrincipal LoginUser loginUser) {
        String email = loginUser.getUsername();
        User user = userService.findByToken(email);
        UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getRole());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/nickname")
    public ResponseEntity<UserUpdateResponse> updateNickname(
            @AuthenticationPrincipal LoginUser loginUser,
            @Valid @RequestBody UserNicknameUpdateRequest request
    ) {

        UserUpdateResponse response = userService.updateNickname(loginUser.id(), request.nickname());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/password")
    public ResponseEntity<UserPasswordUpdateResponse> updatePassword(
            @AuthenticationPrincipal LoginUser loginUser,
            @Valid @RequestBody UserPasswordUpdateRequest request
    ) {

        UserPasswordUpdateResponse response = userService.updatePassword(loginUser.id(), request.password(), request.newPassword());
        return ResponseEntity.ok(response);

    }


}
