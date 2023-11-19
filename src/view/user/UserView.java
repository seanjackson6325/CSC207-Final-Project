package view.user;

import entity.Todo;
import interface_adapter.user.UserViewModel;
import view.DateTimeInputPanel;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class UserView extends JPanel {

    UserViewModel userViewModel;

    // For the list selector:
    JList<String> todoNames;
    JPanel addRemoveButtonsPanel;
    JPanel todoListPanel;
    JButton addTodoButton;
    JButton removeTodoButton;

    // for the description:
    JPanel todoDescriptionPanel;
    JEditorPane todoDescriptionTextPane;
    String[] todoDescriptions;

    public UserView(UserViewModel userViewModel)
    {
        this.userViewModel = userViewModel;

        // Initialize everything that needs user info

        todoNames = new JList<>(new DefaultListModel<String>());
        todoNames.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(todoNames);
        listScroller.setPreferredSize(new Dimension(250, 120));

        this.updateViewData();

        // initialize the rest

        addTodoButton = new JButton("Add");
        removeTodoButton = new JButton("Remove");

        todoDescriptionTextPane = new JTextPane();
        todoDescriptionTextPane.setPreferredSize(new Dimension(200, 200));
        todoDescriptionTextPane.setEditable(false);

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

        todoNames.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                todoDescriptionTextPane.setText(todoDescriptions[todoNames.getSelectedIndex()]);
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
        todoDescriptionPanel.add(todoDescriptionTextPane);

        this.add(todoListPanel);
        this.add(todoDescriptionPanel);
    }

    private class TodoInputView
    {
        private JPanel startTimePanel;
        private JPanel endTimePanel;

        private JPanel namePanel;
        private JTextField nameField;
        private JLabel nameLabel;

        private JPanel viewPanel;
        private JFrame viewFrame;

        private JPanel confirmButtonPanel;
        private JButton confirm, cancel;

        public TodoInputView(Todo previous, String title)
        {
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

            viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            viewPanel.add(namePanel);
            viewPanel.add(new JLabel("Start: "));
            viewPanel.add(startTimePanel);
            viewPanel.add(new JLabel("End: "));
            viewPanel.add(endTimePanel);
            viewPanel.add(confirmButtonPanel);

            viewFrame = new JFrame(title);
            viewFrame.setLocationRelativeTo(null);
            viewFrame.add(viewPanel);
            viewFrame.pack();
            viewFrame.setVisible(true);

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Todo> newTaskList = userViewModel.getLoggedInUser().getTaskList();
                    newTaskList.add(new Todo("Test Name", "Test Description", null, null, null, null, false));
                    userViewModel.getLoggedInUser().setTaskList(newTaskList);
                    updateViewData();
                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

        }
    }

    public void updateViewData()
    {
        ArrayList<String> taskNames = new ArrayList();
        ArrayList<String> taskDescriptions = new ArrayList();
        List<Todo> taskList = userViewModel.getLoggedInUser().getTaskList();
        for(Todo todo : taskList)
        {
            taskNames.add(todo.getName());
            taskDescriptions.add(todo.getDescription());
        }

        String[] taskNamesInput = new String[taskNames.size()];
        String[] taskDescriptionsInput = new String[taskDescriptions.size()];

        taskNames.toArray(taskNamesInput);
        taskDescriptions.toArray(taskDescriptionsInput);

        todoNames.setListData(taskNamesInput);
        todoDescriptions = taskDescriptionsInput;
    }

}
