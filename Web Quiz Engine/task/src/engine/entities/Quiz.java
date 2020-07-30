package engine.entities;

public class Quiz {
    private static int counter = 0;

    private int id;
    private String title;
    private String text;
    private String[] options;
    private int answer;

    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, int answer) {
        this.id = ++counter;
        this.title = title;
        this.text = text;
        this.options = options.clone();
        this.answer = answer;
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

    public String[] getOptions() {
        return options.clone();
    }

    public void setOptions(String[] options) {
        this.options = options.clone();
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
