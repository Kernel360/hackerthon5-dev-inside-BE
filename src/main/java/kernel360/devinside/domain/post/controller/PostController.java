package kernel360.devinside.domain.post.controller;

import jakarta.validation.Valid;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.dto.PostRequest;
import kernel360.devinside.domain.post.dto.PostResponse;
import kernel360.devinside.domain.post.service.PostService;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<Post> create(
            @Valid
            @RequestBody
            PostRequest postRequest,
            @AuthenticationPrincipal
            LoginUser loginUser
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.create(postRequest, loginUser));
    }

    @GetMapping("")
    public ResponseEntity<PostResponse<List<Post>>> getPosts(
            @RequestParam(required = false) String postCategory,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return ResponseEntity.ok(postService.getPosts(postCategory, sortedPageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(
            @PathVariable
            Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPost(id));
    }

    @PutMapping("/{id}")
    public Post update(
            @PathVariable
            Long id,
            @RequestBody
            PostRequest postRequest,
            @AuthenticationPrincipal
            LoginUser loginUser
    ) {
        return postService.update(loginUser, id, postRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> delete(
            @PathVariable
            Long id,
            @AuthenticationPrincipal
            LoginUser loginUser
    ) {

        postService.delete(loginUser,id);

        HashMap<String, Object> msg = new HashMap<>();
        msg.put("message", "게시글 삭제에 성공했습니다.");

        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

}
