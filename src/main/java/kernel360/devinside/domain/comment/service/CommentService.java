package kernel360.devinside.domain.comment.service;

import kernel360.devinside.common.PageResponse;
import kernel360.devinside.domain.comment.domain.Comment;
import kernel360.devinside.domain.comment.dto.CommentRequest;
import kernel360.devinside.domain.comment.dto.CommentResponse;
import kernel360.devinside.domain.comment.dto.CommentUpdateRequest;
import kernel360.devinside.domain.comment.repository.CommentRepository;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.repository.PostRepository;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kernel360.devinside.domain.comment.dto.CommentResponse.fromEntity;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //todo 추후 진짜 회원, 게시글 반영
    @Transactional
    public void add(LoginUser user, Post requestPost, CommentRequest request){

        User loginUser = userRepository.findById(user.id()).orElseThrow(()-> new IllegalArgumentException("해당 회원이 없음"));
        Post post2 = postRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없음"));

        Comment parentComment = repository.findById(request.parent().getId()).orElseThrow(()
                -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다"));

        Comment comment = new Comment(loginUser, post2, request);

        parentComment.addChild(comment);

        repository.save(comment);
    }

    @Transactional(readOnly = true)
    public PageResponse<CommentResponse> comments(Long postId, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Comment> parentPage = repository.findAllWithChildrenFetchByPostId(postId, pageable);

        List<CommentResponse> content = parentPage.getContent().stream()
                .map(c -> {
                    List<CommentResponse> children = c.getChildren().stream()
                            .map(child -> fromEntity(child, List.of()))
                            .toList();

                    return fromEntity(c, children);
                })
                .toList();

        return PageResponse.from(content, page, size, parentPage.getTotalElements());
    }

    public void update(Long id, CommentUpdateRequest request){
        Comment comment = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 댓글입니다"));
        comment.updateComment(request);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
