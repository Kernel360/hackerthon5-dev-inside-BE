package kernel360.devinside.domain.comment.service;

import kernel360.devinside.domain.comment.domain.Comment;
import kernel360.devinside.domain.comment.dto.CommentRequest;
import kernel360.devinside.domain.comment.repository.CommentRepository;
import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.repository.PostRepository;
import kernel360.devinside.domain.user.domain.User;
import kernel360.devinside.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;



    //todo 추후 진짜 회원, 게시글 반영
    public void add(User user, Post post, CommentRequest request){

        User user1 = userRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("해당 회원이 없음"));
        Post post2 = postRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없음"));

        Comment comment = new Comment(user1, post2, request);
        repository.save(comment);
    }
}
