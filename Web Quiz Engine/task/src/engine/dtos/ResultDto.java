package engine.dtos;

public class ResultDto {
    private static final ResultDto SUCCESS = new ResultDto(true, "Congratulations, you're right!");
    private static final ResultDto FAILURE = new ResultDto(false, "Wrong answer! Please, try again.");

    private boolean success;
    private String feedback;

    private ResultDto(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

    public static ResultDto getSuccess() {
        return SUCCESS;
    }

    public static ResultDto getFailure() {
        return FAILURE;
    }
}
