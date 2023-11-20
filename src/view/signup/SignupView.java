package view.signup;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import use_case.Signup.SignupInputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignupView extends JPanel {

    private SignupViewModel signupViewModel;
    private SignupController signupController;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField1 = new JPasswordField(15);
    private final JPasswordField passwordInputField2 = new JPasswordField(15);

    private JButton backButton;
    private JButton signUpButton;

    public SignupView(SignupViewModel signupViewModel, SignupController signupController)
    {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        backButton = new JButton(SignupViewModel.BACK_BUTTON_STRING);
        signUpButton = new JButton(SignupViewModel.SIGNUP_BUTTON_STRING);

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel(SignupViewModel.USERNAME_FIELD_STRING));
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel1 = new JPanel();
        passwordPanel1.add(new JLabel(SignupViewModel.PASSWORD_FIELD_STRING1));
        passwordPanel1.add(passwordInputField1);

        JPanel passwordPanel2 = new JPanel();
        passwordPanel2.add(new JLabel(SignupViewModel.PASSWORD_FIELD_STRING2));
        passwordPanel2.add(passwordInputField2);

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        signupViewModel.getViewManager().switchToLastView();
                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = signupViewModel.getState().getUsername();
                        String password = signupViewModel.getState().getPassword();
                        String repeatPassword = signupViewModel.getState().getRepeatPassword();
                        signupController.execute(username, password, repeatPassword);
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = usernameInputField.getText() + e.getKeyChar();
                        signupViewModel.getState().setUsername(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField1.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = passwordInputField1.getText() + e.getKeyChar();
                        signupViewModel.getState().setPassword(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField2.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = passwordInputField2.getText() + e.getKeyChar();
                        signupViewModel.getState().setRepeatPassword(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(usernamePanel);
        this.add(passwordPanel1);
        this.add(passwordPanel2);
        this.add(signUpButton);
        this.add(backButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}