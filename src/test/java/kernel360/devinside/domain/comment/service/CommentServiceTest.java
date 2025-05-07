/*
package kernel360.devinside.domain.comment.service;

import kernel360.devinside.domain.comment.domain.Comment;
import kernel360.devinside.domain.comment.dto.CommentRequest;
import kernel360.devinside.domain.comment.repository.CommentRepository;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.domain.PostRepository;
import kernel360.devinside.domain.user.domain.Role;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.domain.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    private void createPost() {
        User user = User.builder().name("User")
                .nickname("개발자")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Post post = Post.builder()
                .title("개발자")
                .content("백엔드 개발자가 되어봅시다")
                .build();
        postRepository.save(post);
    }

    @Test
    void 댓글생성(){

        createPost();

        User user = userRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("해당 회원이 없음"));
        Post post = postRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없음"));

        CommentRequest request = CommentRequest.builder()
                .content("내용입니다")
                .build();

        commentService.add(user, post, request);

        Comment c = commentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));

        Assertions.assertEquals(c.getUser().getId(), 1L);
        Assertions.assertEquals(c.getPost().getId(), 1L);
    }
}*/
