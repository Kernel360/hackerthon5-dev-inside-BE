package kernel360.devinside.domain.post.service;


import kernel360.devinside.domain.post.domain.Pagination;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.domain.PostRequest;
import kernel360.devinside.domain.post.domain.PostResponse;
import kernel360.devinside.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

//    private final UserRepository userRepository;

    public Post create(PostRequest postRequest) {

        // Optional<User> user = userRepository.findById(id)
        // if(user.isPresent()) { }

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
//                .user(user)
                .view(0)
                .liked(false)
                .hated(false)
                .build();

        return postRepository.save(post);

    }

    public PostResponse<List<Post>> getPosts(Pageable pageable) {

        Page<Post> list = postRepository.findAll(pageable);

        Pagination pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getTotalPages())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        return PostResponse.<List<Post>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();
    }

    public Post getPostDetail(Long id) {

        return postRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("해당 게시글이 존재하지 않습니다.");
                });
    }



}

