package engine.dao;

import engine.entities.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizDao {
    Optional<Quiz> getById(int id);
    List<Quiz> getAll();
    Quiz save(Quiz quiz);
}
