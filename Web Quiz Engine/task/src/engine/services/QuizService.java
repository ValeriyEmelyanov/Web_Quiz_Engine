package engine.services;

import engine.dao.QuizDao;
import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.exceptions.NotFoundQuizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizDao quizDao;

    @Autowired
    public QuizService(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public QuizDto create(Quiz reqQuiz) {
        Quiz quiz = quizDao.save(reqQuiz);
        return new QuizDto(quiz);
    }

    public QuizDto getById(int id) {
        Optional<Quiz> optionalQuiz = quizDao.getById(id);
        if (optionalQuiz.isEmpty()) {
            throw new NotFoundQuizException("Not found");
        }
        return new QuizDto(optionalQuiz.get());
    }

    public List<QuizDto> getAll() {
        return quizDao.getAll().stream()
                .map(QuizDto::new)
                .collect(Collectors.toList());
    }

    public ResultDto checkAnswerById(int id, int answer) {
        Optional<Quiz> optionalQuiz = quizDao.getById(id);
        if (optionalQuiz.isEmpty()) {
            throw new NotFoundQuizException("Not found");
        }
        return answer == optionalQuiz.get().getAnswer() ? ResultDto.getSuccess() : ResultDto.getFailure();
    }
}
