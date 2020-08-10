package engine.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "The \"title\" field is requied")
    private String title;

    @NotBlank(message = "The \"title\" field is requied")
    private String text;

    @NotNull
    @Size(min = 2)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<QuizOption> options;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<QuizAnswer> answer;

    private String author;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuizCompletion> quizCompletions;

    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = Arrays.stream(options).map(QuizOption::new).collect(Collectors.toList());
        if (answer == null) {
            this.answer = Collections.emptyList();
        } else {
            this.answer = Arrays.stream(answer).mapToObj(QuizAnswer::new).collect(Collectors.toList());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuizOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuizOption> options) {
        this.options = options;
    }

    public List<QuizAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<QuizAnswer> answer) {
        this.answer = answer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<QuizCompletion> getQuizCompletions() {
        return quizCompletions;
    }

    public void setQuizCompletions(List<QuizCompletion> quizCompletions) {
        this.quizCompletions = quizCompletions;
    }
}
