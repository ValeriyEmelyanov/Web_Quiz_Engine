package engine.controllers;

import engine.dtos.QuizDto;
import engine.dtos.ResultDto;
import engine.entities.Quiz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    private Quiz quiz;

    public QuizController() {
        quiz = new Quiz(
                "The Java Logo",
                "What is depicted on the Java logo?",
                new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"},
                2);
    }

    @PostMapping("/api/quiz")
    public ResultDto postAnswer(@RequestParam int answer) {
        return answer == quiz.getAnswer() ? ResultDto.getSuccess() : ResultDto.getFailure();
    }

    @GetMapping("/api/quiz")
    public QuizDto getQuiz() {
        return new QuizDto(quiz);
    }
}
