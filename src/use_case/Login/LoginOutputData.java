package use_case.Login;
import entity.User;

public class LoginOutputData {

    private final String username;
    private final User user;
    private boolean useCaseFailed;

    public LoginOutputData(String username, User user, boolean useCaseFailed) {
        this.username = username;
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public User getUser() { return user; }
}
