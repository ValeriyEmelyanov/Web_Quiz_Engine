package engine.dtos;

import engine.entities.QuizCompletion;

import java.util.Date;

public class QuizCompletionDto {
    private int id;
    private Date completedAt;

    public QuizCompletionDto() {
    }

    public QuizCompletionDto(QuizCompletion quizCompletion) {
        this.id = quizCompletion.getQuiz().getId();
        this.completedAt = quizCompletion.getCompletedAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
