package interface_adapter;

import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    @Override
    public void prepareSuccessView(LoginOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
