package Factory;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginUserDataAccessInterface;
import view.ViewManager;
import view.login.LoginView;

public class LoginFactory {

    public static LoginView createLoginView(ViewManager viewManager,
                                     LoginViewModel loginViewModel,
                                     LoginUserDataAccessInterface userDataAccess)
    {
        LoginController controller = createLoginController(viewManager, loginViewModel, userDataAccess);
        return new LoginView(loginViewModel, controller);
    }

    private static LoginController createLoginController(ViewManager viewManager,
                                                 LoginViewModel loginViewModel,
                                                 LoginUserDataAccessInterface userDataAccess)
    {
        LoginOutputBoundary presenter = new LoginPresenter(viewManager, loginViewModel);
        LoginInteractor interactor = new LoginInteractor(userDataAccess, presenter);
        return new LoginController(interactor);
    }

}
