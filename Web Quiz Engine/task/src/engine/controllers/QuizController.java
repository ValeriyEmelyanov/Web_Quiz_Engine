package engine.controllers;

import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import engine.exceptions.NotFoundQuizException;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuizController {
    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping("/api/quizzes")
    public QuizDto create(@RequestBody Quiz reqQuiz) {
        return service.create(reqQuiz);
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
    public ResultDto checkAnswer(@PathVariable(name = "id") int id, @RequestParam int answer) {
        return service.checkAnswerById(id, answer);
    }
}
