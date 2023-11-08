package interface_adapter.signup;

public class SignupState {

    String username = "";
    String password = "";
    String repeatPassword = "";
    String usernameError = null;
    String passwordError = null;

    String creationTime = "";



    public SignupState() {}

    public SignupState(SignupState state)
    {
        username = state.username;
        password = state.password;
        repeatPassword = state.repeatPassword;
        usernameError = state.usernameError;
        passwordError = state.passwordError;
        creationTime = state.creationTime;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRepeatPassword()
    {
        return repeatPassword;
    }

    public String getUsernameError()
    {
        return usernameError;
    }

    public String getPasswordError()
    {
        return passwordError;
    }

    public String getCreationTime()
    {
        return creationTime;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }

    public void setUsernameError(String error)
    {
        usernameError = error;
    }

    public void setPasswordError(String error)
    {
        passwordError = error;
    }

    public void setCreationTime(String time)
    {
        creationTime = time;
    }

}
