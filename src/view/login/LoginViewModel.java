package view.login;

import view.ViewManager;
import view.ViewModel;
import view.personal.PersonalViewModel;
import view.signup.SignupViewModel;

public class LoginViewModel extends ViewModel {

    public static final String LOGIN_BUTTON_STRING = "Login";
    public static final String SIGNUP_BUTTON_STRING = "Sign Up";
    public static final String USERNAME_FIELD_STRING = "Username: ";
    public static final String PASSWORD_FIELD_STRING = "Password: ";

    private SignupViewModel signupViewModel;

    private PersonalViewModel personalViewModel;

    public LoginViewModel(ViewManager viewManager, SignupViewModel signupViewModel, PersonalViewModel personalViewModel)
    {
        super("Login", viewManager);
        this.signupViewModel = signupViewModel;
        this.personalViewModel = personalViewModel;
    }

    public SignupViewModel getSignupViewModel()
    {
        return signupViewModel;
    }

    public void setSignupViewModel(SignupViewModel signupViewModel)
    {
        this.signupViewModel = signupViewModel;
    }

    public PersonalViewModel getPersonalViewModel()
    {
        return personalViewModel;
    }

    public void setPersonalViewModel(PersonalViewModel personalViewModel)
    {
        this.personalViewModel = personalViewModel;
    }


}
