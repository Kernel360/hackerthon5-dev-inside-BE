package kernel360.devinside.domain.post.repository;

import kernel360.devinside.domain.post.domain.Post;
import kernel360.devinside.domain.post.domain.PostCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByPostCategory(PostCategory category, Pageable pageable);

}
