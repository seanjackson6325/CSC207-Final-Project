package view.user;

import interface_adapter.user.UserViewModel;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class UserView extends JPanel {

    UserViewModel userViewModel;

    JToolBar personalToolBar;
    JList<String> todos;

    JPanel addRemoveButtonsPanel;
    JPanel todoListPanel;
    JButton addTodo;
    JButton removeTodo;

    JPanel todoDescriptionPanel;
    JButton editTodo;


    public UserView(UserViewModel userViewModel)
    {
        this.userViewModel = userViewModel;
        personalToolBar = new JToolBar();

        todos = new JList<>(new DefaultListModel<String>());
        todos.setListData(new String[]
                {"Walk Dog", "Get Groceries", "Make Dinner", "Buy Milk",
                        "Pay Bills", "Another One", "Another 2", "Another 3", "Another 4"
                });
        todos.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(todos);
        listScroller.setPreferredSize(new Dimension(250, 120));

        addRemoveButtonsPanel = new JPanel();

        addTodo = new JButton("Add");
        removeTodo = new JButton("Remove");

        addRemoveButtonsPanel.add(addTodo);
        addRemoveButtonsPanel.add(removeTodo);

        addRemoveButtonsPanel.setLayout(new BoxLayout(addRemoveButtonsPanel, BoxLayout.X_AXIS));

        todoListPanel = new JPanel();
        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        todoListPanel.add(listScroller);
        todoListPanel.add(addRemoveButtonsPanel);

        this.add(todoListPanel);
    }

}
