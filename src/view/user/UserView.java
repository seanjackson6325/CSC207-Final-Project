package view.user;

import entity.Todo;
import interface_adapter.user.UserController;
import interface_adapter.user.UserViewModel;
import view.DateTimeInputPanel;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class UserView extends JPanel {

    UserViewModel userViewModel;
    UserController userController;

    // For the list selector:
    JPanel addRemoveButtonsPanel;
    JPanel todoListPanel;
    JButton addTodoButton;
    JButton removeTodoButton;

    // for the description:
    JPanel todoDescriptionPanel;

    public UserView(UserViewModel userViewModel, UserController userController)
    {
        this.userViewModel = userViewModel;
        this.userController = userController;

        // Initialize everything that needs user info
        JScrollPane listScroller = new JScrollPane(userViewModel.getTodoNames());
        listScroller.setPreferredSize(new Dimension(250, 120));

        userViewModel.updateDataForView();

        // initialize the rest

        addTodoButton = new JButton("Add");
        removeTodoButton = new JButton("Remove");

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TodoInputView(null, "Enter Todo Attributes");
            }
        });

        removeTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Remove todo?");
            }
        });

        userViewModel.getTodoNames().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = userViewModel.getTodoNames().getSelectedIndex();
                if(index != -1)
                    userViewModel.getTodoDescriptionTextPane().setText(
                        userViewModel.getTodoDescriptions()[index]);
            }
        });

        // create all the panels and add everything

        addRemoveButtonsPanel = new JPanel();

        addRemoveButtonsPanel.add(addTodoButton);
        addRemoveButtonsPanel.add(removeTodoButton);

        addRemoveButtonsPanel.setLayout(new BoxLayout(addRemoveButtonsPanel, BoxLayout.X_AXIS));

        todoListPanel = new JPanel();
        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        todoListPanel.add(listScroller);
        todoListPanel.add(addRemoveButtonsPanel);

        todoDescriptionPanel = new JPanel();
        todoDescriptionPanel.add(userViewModel.getTodoDescriptionTextPane());

        this.add(todoListPanel);
        this.add(todoDescriptionPanel);
    }

    private class TodoInputView {
        private DateTimeInputPanel startTimePanel;
        private DateTimeInputPanel endTimePanel;

        private JPanel namePanel;
        private JTextField nameField;
        private JLabel nameLabel;

        private JPanel viewPanel;
        private JFrame viewFrame;

        private JPanel confirmButtonPanel;
        private JButton confirm, cancel;

        JPanel todoDescriptionPanel;
        JEditorPane todoDescriptionEditor;

        public TodoInputView(Todo previous, String title) {
            startTimePanel = new DateTimeInputPanel(null);
            endTimePanel = new DateTimeInputPanel(null);

            namePanel = new JPanel();
            nameField = new JTextField(15);
            nameLabel = new JLabel("Name: ");
            namePanel.add(nameLabel);
            namePanel.add(nameField);

            confirmButtonPanel = new JPanel();
            confirm = new JButton("Confirm");
            cancel = new JButton("Cancel");
            confirmButtonPanel.add(confirm);
            confirmButtonPanel.add(cancel);

            todoDescriptionEditor = new JEditorPane();
            todoDescriptionEditor.setPreferredSize(new Dimension(160, 160));
            todoDescriptionPanel = new JPanel();
            todoDescriptionPanel.setLayout(new BoxLayout(todoDescriptionPanel, BoxLayout.Y_AXIS));
            todoDescriptionPanel.add(new JLabel("Description"));
            todoDescriptionPanel.add(todoDescriptionEditor);

            viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            viewPanel.add(namePanel);
            viewPanel.add(new JLabel("Start: "));
            viewPanel.add(startTimePanel);
            viewPanel.add(new JLabel("End: "));
            viewPanel.add(endTimePanel);
            viewPanel.add(todoDescriptionPanel);
            viewPanel.add(confirmButtonPanel);

            viewFrame = new JFrame(title);
            viewFrame.setLocationRelativeTo(null);
            viewFrame.add(viewPanel);
            viewFrame.pack();
            viewFrame.setVisible(true);

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!startTimePanel.isValidInput() || !endTimePanel.isValidInput()) {
                        JOptionPane.showMessageDialog(null, "Please finish entering valid information");
                        userViewModel.getState().setFailed(true);
                        return;
                    }

                    String user = userViewModel.getLoggedInUser().getUsername();

                    userController.execute(
                            userViewModel.getState().getTodoName(),
                            todoDescriptionEditor.getText(),
                            startTimePanel.getLocalDateTime(),
                            endTimePanel.getLocalDateTime(),
                            user,
                            false,
                            user
                    );

                    if (!userViewModel.getState().isFailed()) {
                        userViewModel.updateDataForView();
                        viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
                    }
                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
                }
            });

            nameField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    userViewModel.getState().setTodoName(nameField.getText() + e.getKeyChar());
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

        }
    }

}
