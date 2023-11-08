package view.user;

import interface_adapter.user.UserViewModel;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class UserView extends JPanel {

    UserViewModel userViewModel;

    JToolBar personalToolBar;
    JList<String> todos;

    JScrollPane todoInfoPane;

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
        listScroller.setPreferredSize(new Dimension(250, 80));

        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();

        try
        {
            doc.insertString(0, "Some type of shitty description", null);
        }
        catch(javax.swing.text.BadLocationException e)
        {

        }

        todoInfoPane = new JScrollPane();
        todoInfoPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        todoInfoPane.setPreferredSize(new Dimension(250, 145));
        todoInfoPane.setMinimumSize(new Dimension(10, 10));



        this.add(listScroller);
        this.add(todoInfoPane);
    }

}
