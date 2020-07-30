package engine.services;

import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.exceptions.NotFoundQuizException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizService {
    private final Map<Integer, Quiz> quizzes = new HashMap<>();

    public QuizService() {
    }

    public QuizDto create(Quiz reqQuiz) {
        Quiz quiz = new Quiz(reqQuiz.getTitle(), reqQuiz.getText(), reqQuiz.getOptions(), reqQuiz.getAnswer());
        quizzes.put(quiz.getId(), quiz);
        return new QuizDto(quiz);
    }

    public QuizDto getById(int id) {
        Quiz quiz = quizzes.get(id);
        if (quiz != null) {
            return new QuizDto(quiz);
        }
        throw new NotFoundQuizException("Not found");
    }

    public List<QuizDto> getAll() {
        return quizzes.values().stream()
                .map(QuizDto::new)
                .collect(Collectors.toList());
    }

    public ResultDto checkAnswerById(int id, int answer) {
        Quiz quiz = quizzes.get(id);
        if (quiz == null) {
            throw new NotFoundQuizException("Not found");
        }
        return answer == quiz.getAnswer() ? ResultDto.getSuccess() : ResultDto.getFailure();
    }
}
