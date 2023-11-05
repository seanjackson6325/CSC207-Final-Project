package use_case.CreateTodoTeam;

public class CreateTodoTeamOutputData {
    private final String message;

    public CreateTodoTeamOutputData(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
