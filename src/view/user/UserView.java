package view.user;

import entity.Todo;
import entity.User;
import interface_adapter.deleteTodo.DeleteTodoController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.user.UserController;
import interface_adapter.user.UserViewModel;
import view.DateTimeInputPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class UserView extends JPanel {

    /**
     *  FOR RECEIVING AND SENDING DATA
     */
    UserViewModel userViewModel;
    UserController userController;
    DeleteTodoController deleteTodoController;

    /**
     *  FOR THE TASK SELECTOR
     */
    JPanel addRemoveButtonsPanel;
    JPanel todoListPanel;
    JButton addTodoButton;
    JButton removeTodoButton;
    JButton editTodoButton;
    JButton completeTodoButton;

    /**
     *  FOR THE DESCRIPTION SECTION
     */
    JPanel todoDescriptionPanel;
    JPanel todoTimePanel;
    JEditorPane todoDescriptionStartTime;
    JEditorPane todoDescriptionEndTime;
    JPanel todoStatusPanel;
    JEditorPane todoDescriptionStatus;

    /**
     *  FOR THE TOOLBAR
     */
    JMenuBar menuBar;
    JMenu mainMenu, viewMenu, weatherMenu;
    JMenuItem logoutMainMenuItem, teamViewMenuItem, weatherViewMenuItem;

    /**
     *  FOR ADDING AND EDITING TASKS (INTERNAL CLASS BELOW)
     */
    TodoInputView todoInputView;
    EditTodoInputView editTodoInputView;

    /**
     *
     * Main constructor for this UserView object.
     *
     * @param userViewModel
     * @param userController
     */

    public UserView(UserViewModel userViewModel, UserController userController, DeleteTodoController deleteTodoController)
    {
        this.userViewModel = userViewModel;
        this.userController = userController;
        this.deleteTodoController = deleteTodoController;
        todoInputView = null;

        // Initialize everything that needs user info
        JScrollPane listScroller = new JScrollPane(userViewModel.getTodoNames());
        listScroller.setPreferredSize(new Dimension(250, 120));

        userViewModel.updateDataForView();

        mainMenu = new JMenu("Menu");
        logoutMainMenuItem = new JMenuItem("Logout");
        mainMenu.add(logoutMainMenuItem);

        viewMenu = new JMenu("View");
        teamViewMenuItem = new JMenuItem("Switch to Team View");
        viewMenu.add(teamViewMenuItem);

        weatherMenu = new JMenu("Weather");
        weatherViewMenuItem = new JMenuItem("Check Weather");
        weatherMenu.add(weatherViewMenuItem);

        // initialize the rest
        addTodoButton = new JButton("Add");
        removeTodoButton = new JButton("Remove");
        editTodoButton = new JButton("Edit");
        completeTodoButton = new JButton("Mark Done");

        todoDescriptionStartTime = new JEditorPane();
        todoDescriptionEndTime = new JEditorPane();

        todoDescriptionStatus = new JEditorPane();

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(todoInputView == null)
                {
                    todoInputView = new TodoInputView(null, "Enter Todo Attributes");
                }
            }
        });

        removeTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userViewModel.getTodoNames().getSelectedIndex() == -1)
                {
                    JOptionPane.showMessageDialog(null, "Select a Todo to Remove");
                }
                else
                {
                    int option = JOptionPane.showConfirmDialog(null, "Remove todo?");
                    if(option == 0)
                    {
                        deleteTodoController.execute(userViewModel.getTodoNames().getSelectedIndex(),
                                userViewModel.getLoggedInUser().getTaskList(), null);
                        userViewModel.updateDataForView();
                    }
                }
            }
        });

        editTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userViewModel.getTodoNames().getSelectedIndex() == -1)
                {
                    JOptionPane.showMessageDialog(null, "Select a Todo to Edit");
                }
                else
                {
                    if(editTodoInputView == null)
                    {
                        userViewModel.getEditState().setTodoPrevName(userViewModel.getUserTodos()[userViewModel.getTodoNames().getSelectedIndex()].getName());
                        userViewModel.getEditState().setTodoNewName(userViewModel.getUserTodos()[userViewModel.getTodoNames().getSelectedIndex()].getName());
                        editTodoInputView = new EditTodoInputView(
                                userViewModel.getUserTodos()[userViewModel.getTodoNames().getSelectedIndex()],
                                "Edit Todo Attributes");
                        userViewModel.updateDataForView();
                    }
                }
            }
        });

        completeTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userViewModel.getTodoNames().getSelectedIndex() == -1)
                {
                    JOptionPane.showMessageDialog(null, "Select a Todo to Mark Done");
                }
                else
                {
                    userViewModel.getEditState().setTodoStatus(true);
                    userController.executeMarkDone(userViewModel.getUserTodos()[userViewModel.getTodoNames().getSelectedIndex()].getName());

                    int option = JOptionPane.showConfirmDialog(null, "Remove completed todo?");
                    if(option == 0)
                    {
                        deleteTodoController.execute(userViewModel.getTodoNames().getSelectedIndex(),
                                userViewModel.getLoggedInUser().getTaskList(), null);
                        userViewModel.updateDataForView();
                    }

                    userViewModel.updateDataForView();
                }
            }
        });

        userViewModel.getTodoNames().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = userViewModel.getTodoNames().getSelectedIndex();
                if(index != -1) {
                    todoDescriptionStartTime.setText("Start: " + userViewModel.getTodoStartTimes()[index]);
                    todoDescriptionEndTime.setText("End: " + userViewModel.getTodoEndTimes()[index]);
                    String status = "";
                    if(userViewModel.getTodoStatuses()[index])
                    {
                        status = "Complete";
                    }
                    else
                    {
                        status = "Incomplete";
                    }
                    todoDescriptionStatus.setText("Status: " + status);
                    userViewModel.getTodoDescriptionTextPane().setText(
                            userViewModel.getTodoDescriptions()[userViewModel.getTodoNames().getSelectedIndex()]);
                }
            }
        });

        logoutMainMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userViewModel.getViewManager().switchToView("Login");
            }
        });

        teamViewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userViewModel.getViewManager().switchToView("team");
            }
        });

        weatherViewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show pop up of weather
            }
        });


        // create all the panels and add everything

        addRemoveButtonsPanel = new JPanel();

        addRemoveButtonsPanel.add(addTodoButton);
        addRemoveButtonsPanel.add(removeTodoButton);
        addRemoveButtonsPanel.add(editTodoButton);
        addRemoveButtonsPanel.add(completeTodoButton);

        addRemoveButtonsPanel.setLayout(new BoxLayout(addRemoveButtonsPanel, BoxLayout.X_AXIS));

        todoListPanel = new JPanel();
        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        todoListPanel.add(listScroller);
        todoListPanel.add(addRemoveButtonsPanel);
        todoListPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        todoTimePanel = new JPanel();
        todoTimePanel.setLayout(new BoxLayout(todoTimePanel, BoxLayout.Y_AXIS));
        todoTimePanel.add(todoDescriptionStartTime);
        todoTimePanel.add(todoDescriptionEndTime);
        todoTimePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        todoDescriptionStartTime.setEditable(false);
        todoDescriptionEndTime.setEditable(false);

        todoStatusPanel = new JPanel();
        todoStatusPanel.add(todoDescriptionStatus);
        todoStatusPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        todoDescriptionStatus.setEditable(false);


        menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(600, 50));
        menuBar.add(mainMenu);
        menuBar.add(viewMenu);
        menuBar.add(weatherMenu);

        todoDescriptionPanel = new JPanel();
        todoDescriptionPanel.setLayout(new BoxLayout(todoDescriptionPanel, BoxLayout.Y_AXIS));
        todoDescriptionPanel.add(todoTimePanel);
        todoDescriptionPanel.add(todoStatusPanel);
        todoDescriptionPanel.add(userViewModel.getTodoDescriptionTextPane());
        todoDescriptionPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel content = new JPanel();
        content.add(todoListPanel);
        content.add(todoDescriptionPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(menuBar);
        this.add(content);
        this.setPreferredSize(new Dimension(600, 320));
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
            todoDescriptionEditor.setPreferredSize(new Dimension(240, 200));
            todoDescriptionEditor.setMaximumSize(new Dimension(240, 200));
            todoDescriptionPanel = new JPanel();
            todoDescriptionPanel.setLayout(new BoxLayout(todoDescriptionPanel, BoxLayout.Y_AXIS));
            todoDescriptionPanel.add(new JLabel("Description"));
            todoDescriptionPanel.add(todoDescriptionEditor);

            if(previous != null)
            {
                nameField.setText(previous.getName());
                todoDescriptionEditor.setText(previous.getDescription());
                startTimePanel.setInput(previous.getStartTime());
                endTimePanel.setInput(previous.getEndTime());
            }

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
            viewFrame.setResizable(false);
            viewFrame.setVisible(true);

            viewFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    todoInputView = null;
                }
            });

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!startTimePanel.isValidInput() || !endTimePanel.isValidInput()) {
                        JOptionPane.showMessageDialog(null, "Please finish entering valid information");
                        userViewModel.getState().setFailed(true);
                        return;
                    }

                    String user = userViewModel.getLoggedInUser().getUsername();

                    userController.executeAdd(
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
                        closeView();
                    }
                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeView();
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

        public void closeView()
        {
            viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
            todoInputView = null;
        }

    }

    private class EditTodoInputView {
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

        public EditTodoInputView(Todo previous, String title) {
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
            todoDescriptionEditor.setPreferredSize(new Dimension(240, 200));
            todoDescriptionEditor.setMaximumSize(new Dimension(240, 200));
            todoDescriptionPanel = new JPanel();
            todoDescriptionPanel.setLayout(new BoxLayout(todoDescriptionPanel, BoxLayout.Y_AXIS));
            todoDescriptionPanel.add(new JLabel("Description"));
            todoDescriptionPanel.add(todoDescriptionEditor);

            if(previous != null)
            {
                nameField.setText(previous.getName());
                todoDescriptionEditor.setText(previous.getDescription());
                startTimePanel.setInput(previous.getStartTime());
                endTimePanel.setInput(previous.getEndTime());
            }

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
            viewFrame.setResizable(false);
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

                    userController.executeEdit(
                            userViewModel.getEditState().getTodoPrevName(),
                            userViewModel.getEditState().getTodoNewName(),
                            todoDescriptionEditor.getText(),
                            startTimePanel.getLocalDateTime(),
                            endTimePanel.getLocalDateTime(),
                            user,
                            false,
                            user
                    );

                    if (!userViewModel.getState().isFailed()) {
                        userViewModel.updateDataForView();
                        closeView();
                    }
                }
            });

            viewFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    editTodoInputView = null;
                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeView();
                }
            });

            nameField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    userViewModel.getEditState().setTodoNewName(
                            (nameField.getText() + e.getKeyChar()).replaceAll("\b", ""));
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }

        public void closeView()
        {
            viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
            editTodoInputView = null;
        }

    }

}
