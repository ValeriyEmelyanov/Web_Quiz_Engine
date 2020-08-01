package engine.services;

import engine.dao.QuizDao;
import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.exceptions.NotFoundQuizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public ResultDto checkAnswerById(int id, int[] answer) {
        Optional<Quiz> optionalQuiz = quizDao.getById(id);
        if (optionalQuiz.isEmpty()) {
            throw new NotFoundQuizException("Not found");
        }

        Set<Integer> modelSet = Arrays.stream(optionalQuiz.get().getAnswer()).boxed().collect(Collectors.toSet());
        Set<Integer> answerSet = Arrays.stream(answer).boxed().collect(Collectors.toSet());

        return modelSet.equals(answerSet) ? ResultDto.getSuccess() : ResultDto.getFailure();
    }
}
