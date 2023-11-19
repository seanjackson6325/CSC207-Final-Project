package view.user;

import entity.Todo;
import interface_adapter.user.UserViewModel;
import view.DateTimeInputPanel;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TodoInputView.showView(null, "Enter Todo Attributes");
            }
        });

        removeTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Remove todo?");
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

        this.add(todoListPanel);
    }

    private static class TodoInputView
    {
        private static JPanel startTimePanel;
        private static JPanel endTimePanel;

        private static JPanel namePanel;
        private static JTextField nameField;
        private static JLabel nameLabel;

        private static JPanel viewPanel;
        private static JFrame viewFrame;


        public static void showView(Todo previous, String title)
        {
            startTimePanel = new DateTimeInputPanel(null);
            endTimePanel = new DateTimeInputPanel(null);

            namePanel = new JPanel();
            nameField = new JTextField(15);
            nameLabel = new JLabel("Name: ");
            namePanel.add(nameLabel);
            namePanel.add(nameField);


            viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            viewPanel.add(namePanel);
            viewPanel.add(new JLabel("Start: "));
            viewPanel.add(startTimePanel);
            viewPanel.add(new JLabel("End: "));
            viewPanel.add(endTimePanel);


            viewFrame = new JFrame(title);
            viewFrame.setLocationRelativeTo(null);
            viewFrame.add(viewPanel);
            viewFrame.pack();
            viewFrame.setVisible(true);
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
    }



}
