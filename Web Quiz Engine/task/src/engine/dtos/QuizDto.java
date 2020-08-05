package engine.dtos;

import engine.entities.Quiz;
import engine.entities.QuizOption;

public class QuizDto {
    private int id;
    private String title;
    private String text;
    private String[] options;

    public QuizDto() {
    }

    public QuizDto(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.text = quiz.getText();
        this.options = quiz.getOptions().stream().map(QuizOption::getName).toArray(String[]::new);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

}
