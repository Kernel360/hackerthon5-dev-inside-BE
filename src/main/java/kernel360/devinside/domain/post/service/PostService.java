package kernel360.devinside.domain.post.service;


import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.domain.PostRequest;
import kernel360.devinside.domain.post.repository.PostRepository;
import kernel360.devinside.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
