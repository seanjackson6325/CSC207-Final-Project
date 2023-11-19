package interface_adapter.login;

import interface_adapter.user.UserViewModel;
import interface_adapter.signup.SignupViewModel;
import view.ViewManager;
import view.ViewModel;

public class LoginViewModel extends ViewModel {

    public static final String LOGIN_BUTTON_STRING = "Login";
    public static final String SIGNUP_BUTTON_STRING = "Sign Up";
    public static final String USERNAME_FIELD_STRING = "Username: ";
    public static final String PASSWORD_FIELD_STRING = "Password: ";

    private SignupViewModel signupViewModel;

    private UserViewModel userViewModel;

    private LoginState loginState;

    public LoginViewModel(ViewManager viewManager, SignupViewModel signupViewModel, UserViewModel userViewModel)
    {
        super("Login", viewManager);
        this.signupViewModel = signupViewModel;
        this.userViewModel = userViewModel;
        this.loginState = new LoginState();
    }

    public SignupViewModel getSignupViewModel()
    {
        return signupViewModel;
    }

    public void setSignupViewModel(SignupViewModel signupViewModel)
    {
        this.signupViewModel = signupViewModel;
    }

    public UserViewModel getUserViewModel()
    {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel)
    {
        this.userViewModel = userViewModel;
    }

    public LoginState getState()
    {
        return loginState;
    }

    public void setState(LoginState state)
    {
        this.loginState = state;
    }
}
