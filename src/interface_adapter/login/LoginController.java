package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor)
    {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password)
    {
        LoginInputData input = new LoginInputData(username, password);
        loginInteractor.execute(input);
    }

}
