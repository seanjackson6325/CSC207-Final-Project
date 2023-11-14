package app;

import Factory.LoginFactory;
import Factory.SignupFactory;
import Factory.UserFactory;
import data_access.DataAccess;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import view.login.LoginView;
import interface_adapter.login.LoginViewModel;
import view.ViewManager;
import view.user.UserView;
import interface_adapter.user.UserViewModel;
import view.signup.SignupView;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;

// best tutorial for swing stuff:
// https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html

public class Main {

    public static void main(String[] args)
    {

	    EntityMemory runTimeUser = new EntityMemory();

        DataAccess dataAccess = new DataAccess();

        LoginUserDataAccessInterface loginDataAccess = null; // NEED A FILE_DATA_OBJECT
        SignupUserDataAccessInterface signupDataAccess = null; // NEED A FILE_DATA_OBJECT


        JFrame applicationFrame = new JFrame("Team Task Manager");
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ViewManager viewManager = new ViewManager(applicationFrame);

        SignupViewModel signupViewModel = new SignupViewModel(viewManager);
        SignupView signupView = SignupFactory.createSignupView(viewManager, signupViewModel, dataAccess, new UserFactory());

        UserViewModel userViewModel = new UserViewModel(viewManager);
        UserView userView = new UserView(userViewModel);

        LoginViewModel loginViewModel = new LoginViewModel(viewManager, signupViewModel, userViewModel);
        LoginView loginView = LoginFactory.createLoginView(viewManager, loginViewModel, dataAccess);

        viewManager.addView(loginView, loginViewModel.getName());
        viewManager.addView(signupView, signupViewModel.getName());
        viewManager.addView(userView, userViewModel.getName());
        viewManager.switchToView(loginViewModel.getName());

        applicationFrame.setVisible(true);
        applicationFrame.setResizable(true);
    }
}