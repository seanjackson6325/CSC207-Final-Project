package interface_adapter.login;

public class LoginState {

    String username = "";
    String password = "";
    String usernameError = null;
    String passwordError = null;

    public LoginState() {}

    public LoginState(LoginState copy)
    {
        this.username = copy.username;
        this.password = copy.password;
        this.usernameError = copy.usernameError;
        this.passwordError = copy.passwordError;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsernameError()
    {
        return usernameError;
    }

    public String getPasswordError()
    {
        return passwordError;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUsernameError(String usernameError)
    {
        this.usernameError = usernameError;
    }

    public void setPasswordError(String passwordError)
    {
        this.passwordError = passwordError;
    }

}
