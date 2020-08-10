package engine.services;

import engine.dtos.QuizCompletionDto;
import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.entities.QuizAnswer;
import engine.entities.QuizCompletion;
import engine.entities.User;
import engine.exceptions.NoUserException;
import engine.exceptions.NotFoundQuizException;
import engine.exceptions.NotMatchesUserException;
import engine.repositories.QuizComletionRepository;
import engine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class QuizService {
    private final QuizRepository repository;
    private final QuizComletionRepository comletionRepository;
    private final UserService userService;

    @Autowired
    public QuizService(
            QuizRepository repository,
            QuizComletionRepository comletionRepository,
            UserService userService) {
        this.repository = repository;
        this.comletionRepository = comletionRepository;
        this.userService = userService;
    }

    @Transactional
    public QuizDto create(Quiz reqQuiz, Principal principal) {
        reqQuiz.setAuthor(principal.getName());
        Quiz quiz = repository.save(reqQuiz);
        return new QuizDto(quiz);
    }

    public QuizDto getById(int id) {
        Quiz quiz = repository.findById(id)
                .orElseThrow(() -> new NotFoundQuizException("Not found"));
        return new QuizDto(quiz);
    }

    public Page<QuizDto> getAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Quiz> page = repository.findAll(pageRequest);
        return page.map(QuizDto::new);
    }

    @Transactional
    public ResultDto checkAnswerById(int id, int[] answer, Principal principal) {
        Quiz quiz = repository.findById(id)
                .orElseThrow(() -> new NotFoundQuizException("Not found"));

        Set<Integer> modelSet = quiz.getAnswer().stream()
                .map(QuizAnswer::getValue)
                .collect(Collectors.toSet());
        Set<Integer> answerSet = Arrays.stream(answer)
                .boxed()
                .collect(Collectors.toSet());

        boolean isCompleted = modelSet.equals(answerSet);

        if (isCompleted) {
            Optional<User> byEmail = userService.getByEmail(principal.getName());
            if (byEmail.isPresent()) {
                QuizCompletion quizCompletion = new QuizCompletion();
                quizCompletion.setUser(byEmail.get());
                quizCompletion.setQuiz(quiz);
                quizCompletion.setCompletedAt(new Date());
                comletionRepository.save(quizCompletion);
            }
        }

        return isCompleted ? ResultDto.getSuccess() : ResultDto.getFailure();
    }

    @Transactional
    public void delete(int id, Principal principal) {
        String email = principal.getName();
        if (email == null) {
            throw new NoUserException("No authorized user");
        }

        Quiz quiz = repository.findById(id)
                .orElseThrow(() -> new NotFoundQuizException("Not found"));

        if (!email.equals(quiz.getAuthor())) {
            throw new NotMatchesUserException("The current user is not the author of the quiz!");
        }

        repository.delete(quiz);
    }

    public Page<QuizCompletionDto> getCompleted(int pageNo, int pageSize, Principal principal) {
        User user = userService.getByEmail(principal.getName())
                .orElseThrow(() -> new NoUserException("No authorized user"));

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "completedAt"));
        Page<QuizCompletion> page = comletionRepository.findByUser(user, pageRequest);
        return page.map(QuizCompletionDto::new);
    }
}
