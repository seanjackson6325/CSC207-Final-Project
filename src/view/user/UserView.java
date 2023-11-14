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

public class UserView extends JPanel {

    UserViewModel userViewModel;

    // For the list:
    JList<String> todos;
    JPanel addRemoveButtonsPanel;
    JPanel todoListPanel;
    JButton addTodoButton;
    JButton removeTodoButton;

    // for the description:

    public UserView(UserViewModel userViewModel)
    {
        this.userViewModel = userViewModel;

        todos = new JList<>(new DefaultListModel<String>());
        todos.setListData(new String[]
                {"Walk Dog", "Get Groceries", "Make Dinner", "Buy Milk",
                        "Pay Bills", "Another One", "Another 2", "Another 3", "Another 4"
                });
        todos.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(todos);
        listScroller.setPreferredSize(new Dimension(250, 120));

        addRemoveButtonsPanel = new JPanel();

        addTodoButton = new JButton("Add");
        removeTodoButton = new JButton("Remove");

        addRemoveButtonsPanel.add(addTodoButton);
        addRemoveButtonsPanel.add(removeTodoButton);

        addRemoveButtonsPanel.setLayout(new BoxLayout(addRemoveButtonsPanel, BoxLayout.X_AXIS));

        todoListPanel = new JPanel();
        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        todoListPanel.add(listScroller);
        todoListPanel.add(addRemoveButtonsPanel);

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


}
