package kernel360.devinside.domain.comment.repository;

import kernel360.devinside.domain.comment.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {


}
