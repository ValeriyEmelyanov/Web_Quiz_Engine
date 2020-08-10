package engine.repositories;

import engine.entities.QuizCompletion;
import engine.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizComletionRepository extends JpaRepository<QuizCompletion, Long> {
    Page<QuizCompletion> findByUser(User user, Pageable pageable);
}
