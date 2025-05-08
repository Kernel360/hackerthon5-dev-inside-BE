package kernel360.devinside.domain.post.service;


import jakarta.transaction.Transactional;
import kernel360.devinside.domain.post.domain.*;
import kernel360.devinside.domain.post.dto.Pagination;
import kernel360.devinside.domain.post.dto.PostRequest;
import kernel360.devinside.domain.post.dto.PostResponse;
import kernel360.devinside.domain.post.exception.PostNotFoundException;
import kernel360.devinside.domain.post.repository.PostRepository;
import kernel360.devinside.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post create(PostRequest postRequest) {

//         Optional<User> user = userRepository.findById(id);
//         if(user.isPresent()) { }

        Post post = Post.builder()
                .postCategory(postRequest.getPostCategory())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
//                .user(user)
                .view(0)
                .liked(false)
                .hated(false)
                .build();

        return postRepository.save(post);

    }

    public PostResponse<List<Post>> getPosts(String category, Pageable pageable) {
        Page<Post> list;

        if (category == null || category.isEmpty()) {
            list = postRepository.findAll(pageable);
        } else {
            try {
                PostCategory postCategory = PostCategory.valueOf(category.toUpperCase());
                list = postRepository.findByPostCategory(postCategory, pageable);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("존재하지 않는 카테고리입니다: " + category);
            }
        }

        Pagination pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        return PostResponse.<List<Post>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();
    }

    @Transactional
    public Post getPost(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글이 존재하지 않습니다.")
                );

        post.increaseView();

        return post;
    }

    public void delete(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("삭제하려는 게시글이 존재하지 않습니다."));

        postRepository.delete(post);

    }

    public Post update(Long id, PostRequest postRequest) {

        return postRepository.findById(id).map(post -> {
            post.updateContent(postRequest.getContent());
            post.updateTitle(postRequest.getTitle());
            postRepository.save(post);
            return post;
        }).orElseThrow(() -> {
            return new PostNotFoundException("수정하려는 게시글이 존재하지 않습니다.");
        });

    }

}

