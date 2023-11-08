package app;

import view.login.LoginView;
import view.login.LoginViewModel;
import view.ViewManager;
import view.personal.PersonalView;
import view.personal.PersonalViewModel;
import view.signup.SignupView;
import view.signup.SignupViewModel;

import javax.swing.*;

// best tutorial for swing stuff:
// https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html

public class Main {

    public static void main(String[] args)
    {
        JFrame applicationFrame = new JFrame("Team Task Manager");
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ViewManager viewManager = new ViewManager(applicationFrame);

        SignupViewModel signupViewModel = new SignupViewModel(viewManager);
        SignupView signupView = new SignupView(signupViewModel);

        PersonalViewModel personalViewModel = new PersonalViewModel(viewManager);
        PersonalView personalView = new PersonalView(personalViewModel);

        LoginViewModel loginViewModel = new LoginViewModel(viewManager, signupViewModel, personalViewModel);
        LoginView loginView = new LoginView(loginViewModel);

        viewManager.addView(loginView, loginViewModel.getName());
        viewManager.addView(signupView, signupViewModel.getName());
        viewManager.addView(personalView, personalViewModel.getName());
        viewManager.switchToView(loginViewModel.getName());

        applicationFrame.setVisible(true);
        applicationFrame.setResizable(true);
    }
}