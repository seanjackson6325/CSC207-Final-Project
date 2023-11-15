package use_case.RemoveMember;

public class RemoveMemberOutputData {
    private final String message;

    public RemoveMemberOutputData(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
