package app;

import Factory.LoginFactory;
import Factory.SignupFactory;
import Factory.TeamFactory;
import Factory.UserFactory;
import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.createTeam.TeamViewModel;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import view.login.LoginView;
import interface_adapter.login.LoginViewModel;
import view.ViewManager;
import view.team.TeamView;
import view.user.UserView;
import interface_adapter.user.UserViewModel;
import view.signup.SignupView;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.time.LocalDateTime;

// best tutorial for swing stuff:
// https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html

// THINGS TO FIX:
// 1: Empty username and password should not be allowed as this breaks the login system

// Login Info for test Users
// "User1", "pass1"
// "User2", "pass2"
// "User3", "pass3"


public class Main {

    public static void main(String[] args)
    {
        DataAccessInterface dataAccess = new DataAccess();

        JFrame applicationFrame = new JFrame("Team Task Manager");
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ViewManager viewManager = new ViewManager(applicationFrame);

        SignupViewModel signupViewModel = new SignupViewModel(viewManager);
        SignupView signupView = SignupFactory.createSignupView(viewManager, signupViewModel, dataAccess, new UserFactory());

        TeamViewModel teamViewModel = new TeamViewModel(viewManager);
        TeamView teamView = TeamFactory.createTeamView(viewManager, teamViewModel, dataAccess);

        UserViewModel userViewModel = new UserViewModel(viewManager);
        UserView userView = UserFactory.createUserView(viewManager, userViewModel, teamViewModel, dataAccess);

        LoginViewModel loginViewModel = new LoginViewModel(viewManager, signupViewModel, userViewModel);
        LoginView loginView = LoginFactory.createLoginView(viewManager, loginViewModel, dataAccess);

        // FOR TESTING THE TEAM VIEW
        viewManager.addView(loginView, loginViewModel.getName());
        viewManager.addView(signupView, signupViewModel.getName());
        viewManager.addView(userView, userViewModel.getName());
        viewManager.addView(teamView, teamViewModel.getName());
        viewManager.switchToView(loginViewModel.getName());

        applicationFrame.setLocationRelativeTo(null);
        applicationFrame.setVisible(true);
        applicationFrame.setResizable(false);
    }
}
