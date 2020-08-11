package engine.controllers;

import engine.dtos.AnswerDto;
import engine.dtos.QuizCompletionDto;
import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class QuizController {
    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping("/api/quizzes")
    @ResponseStatus(HttpStatus.CREATED)
    public QuizDto create(@Valid @RequestBody Quiz quiz, Principal principal) {
        return service.create(quiz, principal);
    }

    @GetMapping("/api/quizzes/{id}")
    public QuizDto getById(@PathVariable(name = "id") int id) {
        return service.getById(id);
    }

    @GetMapping("/api/quizzes")
    public Page<QuizDto> getAll(
            @RequestParam(name = "page", defaultValue = "0") int pageNo,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        return service.getAll(pageNo, pageSize);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResultDto checkAnswer(
            @PathVariable(name = "id") int id,
            @RequestBody AnswerDto answer,
            Principal principal) {
        return service.checkAnswerById(id, answer.getAnswer(), principal);
    }

    @DeleteMapping("/api/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id, Principal principal) {
        service.delete(id, principal);
    }

    @GetMapping("/api/quizzes/completed")
    public Page<QuizCompletionDto> getCompleted(
            @RequestParam(name = "page", defaultValue = "0") int pageNo,
            @RequestParam(name = "size", defaultValue = "10") int pageSize,
            Principal principal) {
        return service.getCompleted(pageNo, pageSize, principal);
    }
}
