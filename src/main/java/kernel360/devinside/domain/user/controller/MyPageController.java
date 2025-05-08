package kernel360.devinside.domain.user.controller;

import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.dto.MyPageResponse;
import kernel360.devinside.domain.user.dto.UserResponse;
import kernel360.devinside.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    // private final PostService postService; // TODO: PostService 구현되면 주입

    @GetMapping
    public ResponseEntity<MyPageResponse> getMyPage(
            @AuthenticationPrincipal LoginUser loginUser
    ) {
        User user = userService.findByToken(loginUser.getUsername());
        UserResponse userInfo = new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getRole());

        // TODO: PostService가 구현되면 로그인 유저의 post 목록을 posts에 대입
        List<Post> posts = List.of(); // postService.getPostsByUserId(user.getId());

        MyPageResponse response = new MyPageResponse(userInfo, posts);
        return ResponseEntity.ok(response);
    }
}
