package interface_adapter.signup;

import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import view.ViewManager;

public class SignupPresenter implements SignupOutputBoundary {

    private SignupViewModel signupViewModel;
    private ViewManager viewManager;

    public SignupPresenter(ViewManager viewManager, SignupViewModel signupViewModel)
    {
        this.viewManager = viewManager;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData user) {
        signupViewModel.getState().setUsername(user.getUsername());
        signupViewModel.getState().setCreationTime(user.getCreationTime());
        viewManager.switchToLastView();
    }

    @Override
    public void prepareFailView(String error) {
        viewManager.showErrorMessage(error);
    }
}
