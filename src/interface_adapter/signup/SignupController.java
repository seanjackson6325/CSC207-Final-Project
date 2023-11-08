package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary signupInputBoundary;

    public SignupController(SignupInputBoundary signupInputBoundary)
    {
        this.signupInputBoundary = signupInputBoundary;
    }

    public void execute(String username, String password, String repeatPassword)
    {
        SignupInputData input = new SignupInputData(username, password, repeatPassword);
        signupInputBoundary.execute(input);
    }


}
