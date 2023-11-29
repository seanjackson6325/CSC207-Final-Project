package interface_adapter.signup;

import use_case.Signup.SignupOutputBoundary;
import use_case.Signup.SignupOutputData;
import view.ViewManager;

import javax.swing.*;

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
        JOptionPane.showMessageDialog(null, "Account created!");
        viewManager.switchToLastView();
    }

    @Override
    public void prepareFailView(String error) {
        viewManager.showErrorMessage(error);
    }
}
