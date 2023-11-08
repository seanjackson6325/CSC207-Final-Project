package view.signup;

import view.ViewManager;
import view.ViewModel;

public class SignupViewModel extends ViewModel {

    public static final String BACK_BUTTON_STRING = "Back";
    public static final String SIGNUP_BUTTON_STRING = "Sign Up";
    public static final String USERNAME_FIELD_STRING = "Username: ";
    public static final String PASSWORD_FIELD_STRING1 = "Password: ";
    public static final String PASSWORD_FIELD_STRING2 = "Confirm Password: ";

    public SignupViewModel(ViewManager viewManager)
    {
        super("Signup", viewManager);
    }

}
