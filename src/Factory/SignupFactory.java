package Factory;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;
import use_case.Signup.SignupUserDataAccessInterface;
import view.ViewManager;
import view.signup.SignupView;

public class SignupFactory {

    public static SignupView createSignupView(ViewManager viewManager,
                                             SignupViewModel signupViewModel,
                                             SignupUserDataAccessInterface userDataAccess,
                                             UserFactory userFactory)
    {
        SignupController controller = createSignupController(viewManager, signupViewModel, userDataAccess, userFactory);
        return new SignupView(signupViewModel, controller);
    }

    private static SignupController createSignupController(ViewManager viewManager,
                                                         SignupViewModel signupViewModel,
                                                         SignupUserDataAccessInterface userDataAccess,
                                                         UserFactory userFactory)
    {
        SignupOutputBoundary presenter = new SignupPresenter(viewManager, signupViewModel);
        SignupInteractor interactor = new SignupInteractor(userDataAccess, presenter, userFactory);
        return new SignupController(interactor);
    }
}
