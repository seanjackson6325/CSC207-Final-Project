package view.login;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;

public class LoginView extends JPanel implements PropertyChangeListener {

    private LoginViewModel loginViewModel;
    private LoginController loginController;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private JButton loginButton;
    private JButton signUpButton;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController)
    {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_STRING);
        signUpButton = new JButton(LoginViewModel.SIGNUP_BUTTON_STRING);

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel(LoginViewModel.USERNAME_FIELD_STRING));
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel(LoginViewModel.PASSWORD_FIELD_STRING));
        passwordPanel.add(passwordInputField);



        loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(loginButton))
                        {
                            loginViewModel.getViewManager().switchToView(loginViewModel.getPersonalViewModel().getName());
                            String username = loginViewModel.getState().getUsername();
                            String password = loginViewModel.getState().getPassword();
                            loginController.execute(username, password);
                        }
                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(signUpButton))
                        {
                            loginViewModel.getViewManager().switchToView(loginViewModel.getSignupViewModel().getName());
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = usernameInputField.getText() + e.getKeyChar();
                        loginViewModel.getState().setUsername(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = passwordInputField.getText() + e.getKeyChar();
                        loginViewModel.getState().setPassword(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(loginButton);
        this.add(signUpButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
