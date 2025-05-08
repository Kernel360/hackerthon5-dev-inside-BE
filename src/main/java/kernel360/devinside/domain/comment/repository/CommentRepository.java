package kernel360.devinside.domain.comment.repository;

import kernel360.devinside.domain.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // todo: 댓글 조회할때 parent가 null인 값들만 조회하기 -> Children 리스트 까지 전부
    @Query(
            value = "SELECT c FROM Comment c " +
                    "LEFT JOIN FETCH c.user u " +
                    "LEFT JOIN FETCH c.children ch " +
                    "WHERE c.post.id = :postId AND c.parent IS NULL"
    )
    Page<Comment> findAllWithChildrenFetchByPostId(
            @Param("postId") Long postId,
            Pageable pageable
    );

}