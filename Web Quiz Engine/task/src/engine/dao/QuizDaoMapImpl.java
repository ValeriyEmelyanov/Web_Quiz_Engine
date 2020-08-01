package engine.dao;

import engine.entities.Quiz;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuizDaoMapImpl implements QuizDao {
    private final Map<Integer, Quiz> quizzes = new HashMap<>();

    @Override
    public Optional<Quiz> getById(int id) {
        return Optional.ofNullable(quizzes.get(id));
    }

    @Override
    public List<Quiz> getAll() {
        return new ArrayList<>(quizzes.values());
    }

    @Override
    public Quiz save(Quiz quiz) {
        Quiz newQuiz = new Quiz(quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());
        quizzes.put(newQuiz.getId(), newQuiz);
        return newQuiz;
    }
}
