package engine.controllers;

import engine.dtos.AnswerDto;
import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class QuizController {
    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping("/api/quizzes")
    public QuizDto create(@Valid @RequestBody Quiz quiz, Principal principal) {
        return service.create(quiz, principal);
    }

    @GetMapping("/api/quizzes/{id}")
    public QuizDto getById(@PathVariable(name = "id") int id) {
        return service.getById(id);
    }

    @GetMapping("/api/quizzes")
    public List<QuizDto> getAll() {
        return  service.getAll();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResultDto checkAnswer(@PathVariable(name = "id") int id, @RequestBody AnswerDto answer) {
        return service.checkAnswerById(id, answer.getAnswer());
    }

    @DeleteMapping("/api/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id, Principal principal) {
        service.delete(id, principal);
    }
}
