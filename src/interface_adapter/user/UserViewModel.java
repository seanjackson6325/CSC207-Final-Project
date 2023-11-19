package interface_adapter.user;

import app.EntityMemory;
import entity.Todo;
import entity.User;
import view.ViewManager;
import view.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private User loggedInUser;

    private JList<String> todoNames;
    private JEditorPane todoDescriptionTextPane;
    private String[] todoDescriptions;

    public UserViewModel(ViewManager viewManager)
    {
        super("Personal", viewManager);

        loggedInUser = null;

        todoNames = new JList<>(new DefaultListModel<String>());
        todoNames.setLayoutOrientation(JList.VERTICAL);

        todoDescriptionTextPane = new JEditorPane();
        todoDescriptionTextPane.setPreferredSize(new Dimension(200, 200));
        todoDescriptionTextPane.setEditable(false);

        todoDescriptions = null;
    }

    private UserState state = new UserState();

    public UserState getState()
    {
        return state;
    }

    public void setState(UserState state)
    {
        this.state = state;
    }


    public User getLoggedInUser()
    {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser)
    {
        this.loggedInUser = loggedInUser;
    }

    public JList<String> getTodoNames()
    {
        return todoNames;
    }

    public JEditorPane getTodoDescriptionTextPane()
    {
        return todoDescriptionTextPane;
    }

    public String[] getTodoDescriptions()
    {
        return todoDescriptions;
    }

    public void updateDataForView()
    {
        if(loggedInUser != null)
        {
            ArrayList<String> taskNames = new ArrayList();
            ArrayList<String> taskDescriptions = new ArrayList();
            List<Todo> taskList = loggedInUser.getTaskList();
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

}
