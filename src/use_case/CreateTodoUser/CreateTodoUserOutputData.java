package use_case.CreateTodoUser;

import java.time.LocalDateTime;

public class CreateTodoUserOutputData {
    private final String message;

    public CreateTodoUserOutputData(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
