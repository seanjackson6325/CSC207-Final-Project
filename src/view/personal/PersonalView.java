package view.personal;

import view.ViewManager;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalView extends JPanel {

    PersonalViewModel personalViewModel;

    JToolBar personalToolBar;
    JList<String> todos;

    JScrollPane todoInfoPane;

    public PersonalView(PersonalViewModel personalViewModel)
    {
        this.personalViewModel = personalViewModel;
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
