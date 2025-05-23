package kernel360.devinside.domain.comment.controller;

import kernel360.devinside.common.PageResponse;
import kernel360.devinside.domain.comment.dto.CommentRequest;
import kernel360.devinside.domain.comment.dto.CommentResponse;
import kernel360.devinside.domain.comment.dto.CommentUpdateRequest;
import kernel360.devinside.domain.comment.service.CommentService;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(
            @AuthenticationPrincipal LoginUser user,
            Post post,
            @RequestBody @Validated CommentRequest request) {
        commentService.add(user, post, request);
        return ResponseEntity.ok("댓글 작성 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id,
                                                @RequestBody CommentUpdateRequest request) {
        commentService.update(id, request);
        return ResponseEntity.ok("댓글 내용 수정 완료");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PageResponse<CommentResponse>> getComments(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<CommentResponse> response = commentService.comments(postId, page, size);
        return ResponseEntity.ok(response);
    }
}
