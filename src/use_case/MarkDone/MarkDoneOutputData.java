package use_case.MarkDone;

public class MarkDoneOutputData {
    private final String message;

    public MarkDoneOutputData(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
