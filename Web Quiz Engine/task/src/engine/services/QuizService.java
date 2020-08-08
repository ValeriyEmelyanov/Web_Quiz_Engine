package engine.services;

import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.entities.QuizAnswer;
import engine.exceptions.NoUserException;
import engine.exceptions.NotFoundQuizException;
import engine.exceptions.NotMatchesUserException;
import engine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizRepository repository;

    @Autowired
    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public QuizDto create(Quiz reqQuiz, Principal principal) {
        reqQuiz.setAuthor(principal.getName());
        Quiz quiz = repository.save(reqQuiz);
        return new QuizDto(quiz);
    }

    public QuizDto getById(int id) {
        Optional<Quiz> optionalQuiz = repository.findById(id);
        if (optionalQuiz.isEmpty()) {
            throw new NotFoundQuizException("Not found");
        }
        return new QuizDto(optionalQuiz.get());
    }

    public List<QuizDto> getAll() {
        return repository.findAll().stream()
                .map(QuizDto::new)
                .collect(Collectors.toList());
    }

    public ResultDto checkAnswerById(int id, int[] answer) {
        Optional<Quiz> optionalQuiz = repository.findById(id);
        if (optionalQuiz.isEmpty()) {
            throw new NotFoundQuizException("Not found");
        }

        Set<Integer> modelSet = optionalQuiz.get().getAnswer().stream()
                .map(QuizAnswer::getValue)
                .collect(Collectors.toSet());
        Set<Integer> answerSet = Arrays.stream(answer)
                .boxed()
                .collect(Collectors.toSet());

        return modelSet.equals(answerSet) ? ResultDto.getSuccess() : ResultDto.getFailure();
    }

    public void delete(int id, Principal principal) {
        Optional<Quiz> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundQuizException("Not found");
        }

        String email = principal.getName();
        if (email == null) {
            throw new NoUserException("No authorized user");
        }

        Quiz quiz = byId.get();
        if (!email.equals(quiz.getAuthor())) {
            throw new NotMatchesUserException("The current user is not the author of the quiz!");
        }

        repository.delete(quiz);
    }
}
