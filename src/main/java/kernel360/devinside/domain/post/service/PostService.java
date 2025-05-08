package kernel360.devinside.domain.post.service;


import jakarta.transaction.Transactional;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.domain.PostCategory;
import kernel360.devinside.domain.post.dto.Pagination;
import kernel360.devinside.domain.post.dto.PostRequest;
import kernel360.devinside.domain.post.dto.PostResponse;
import kernel360.devinside.domain.post.exception.PostNotFoundException;
import kernel360.devinside.domain.post.repository.PostRepository;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
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

    public Post create(
            PostRequest postRequest,
            LoginUser loginUser
    ) {

        User user = userRepository.findById(loginUser.id())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자 입니다."));

        Post post = Post.builder()
                .postCategory(postRequest.getPostCategory())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(user)
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

    public PostResponse<List<Post>> getPostsByUser(LoginUser loginUser, Pageable pageable) {
        Page<Post> list = postRepository.findAllByUserId(loginUser.id(), pageable);

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



    public void delete(LoginUser loginUser, Long id) {

        if (loginUser == null) {
            throw new RuntimeException("로그인 정보가 없습니다.");
        }

        Post post = postRepository.findById(id)
                .map(p -> {
                    if (p.getUser().getId().equals(loginUser.id())) {
                        return p;
                    } else {
                        throw new RuntimeException("삭제 권한이 없습니다.");
                    }
                })
                .orElseThrow(() -> new PostNotFoundException("삭제하려는 게시글이 존재하지 않습니다."));

        postRepository.delete(post);

    }

    public Post update(LoginUser loginUser, Long id, PostRequest postRequest) {

        if (loginUser == null) {
            throw new RuntimeException("로그인 정보가 없습니다.");
        }


        return postRepository.findById(id).map(post -> {
            if (post.getUser().getId().equals(loginUser.id())) {
                post.updateContent(postRequest.getContent());
                post.updateTitle(postRequest.getTitle());
                postRepository.save(post);
                return post;
            } else {
                throw new RuntimeException("수정 권한이 없습니다.");
            }
        }).orElseThrow(() -> {
            return new PostNotFoundException("수정하려는 게시글이 존재하지 않습니다.");
        });

    }

}

