package use_case.Signup;

import java.util.ArrayList;
import java.util.List;

import data_access.DataAccessInterface;
import entity.Todo;
import entity.User;
import Factory.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(DataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.readUser(signupInputData.getUsername()) != null) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime ltd = LocalDateTime.now();
            List<Todo> taskList = new ArrayList<>();
            List<String> teams = new ArrayList<>();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), taskList, teams);
            userDataAccessObject.createUser(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), ltd.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}