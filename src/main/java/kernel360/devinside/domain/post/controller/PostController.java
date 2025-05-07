package kernel360.devinside.domain.post.controller;

import jakarta.validation.Valid;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.domain.PostRequest;
import kernel360.devinside.domain.post.domain.PostResponse;
import kernel360.devinside.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public Post post(
            @Valid
            @RequestBody
            PostRequest postRequest) {

        return postService.create(postRequest);
    }

    @GetMapping("")
    public PostResponse<List<Post>> getPosts(
            @PageableDefault(size = 10, direction = Sort.Direction.DESC)
            Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping("/{id}")
    public Post Get(
            @PathVariable
            Long id
    ) {
        return postService.getPostDetail(id);
    }




}
