package use_case.AddMember;

public class AddMemberOutputData {
    private final String message;

    public AddMemberOutputData(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
