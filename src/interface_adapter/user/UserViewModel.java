package interface_adapter.user;

import app.EntityMemory;
import entity.Todo;
import entity.User;
import view.DateTimeInputPanel;
import view.ViewManager;
import view.ViewModel;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private User loggedInUser;

    private Todo[] userTodos;
    private JList<String> todoNames;
    private JTextPane todoDescriptionTextPane;
    private String[] todoDescriptions;

    private String[] todoStartTimes;
    private String[] todoEndTimes;

    public UserViewModel(ViewManager viewManager)
    {
        super("Personal", viewManager);

        loggedInUser = null;

        todoNames = new JList<>(new DefaultListModel<String>());
        todoNames.setLayoutOrientation(JList.VERTICAL);

        todoDescriptionTextPane = new JTextPane();
        todoDescriptionTextPane.setPreferredSize(new Dimension(240, 200));
        todoDescriptionTextPane.setEditable(false);

        userTodos = null;
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

    public String[] getTodoStartTimes()
    {
        return todoStartTimes;
    }

    public String[] getTodoEndTimes()
    {
        return todoEndTimes;
    }

    public Todo[] getUserTodos()
    {
        return userTodos;
    }


    public void updateDataForView()
    {
        if(loggedInUser != null)
        {
            ArrayList<Todo> todos = new ArrayList<>();
            ArrayList<String> taskNames = new ArrayList<>();
            ArrayList<String> taskDescriptions = new ArrayList<>();
            ArrayList<String> taskStartTimes = new ArrayList<>();
            ArrayList<String> taskEndTimes = new ArrayList<>();
            List<Todo> taskList = loggedInUser.getTaskList();
            for(Todo todo : taskList)
            {
                todos.add(todo);
                taskNames.add(todo.getName());
                String description = todo.getDescription().replaceAll("\\\\r\\\\n", " ");
                taskDescriptions.add(description);
                taskStartTimes.add(DateTimeInputPanel.getFormattedTimeString(todo.getStartTime()));
                taskEndTimes.add(DateTimeInputPanel.getFormattedTimeString(todo.getEndTime()));
            }

            Todo[] todosInput = new Todo[todos.size()];
            String[] taskNamesInput = new String[taskNames.size()];
            String[] taskDescriptionsInput = new String[taskDescriptions.size()];
            String[] taskStartTimesInput = new String[taskStartTimes.size()];
            String[] taskEndTimesInput = new String[taskEndTimes.size()];

            todos.toArray(todosInput);
            taskNames.toArray(taskNamesInput);
            taskDescriptions.toArray(taskDescriptionsInput);
            taskStartTimes.toArray(taskStartTimesInput);
            taskEndTimes.toArray(taskEndTimesInput);

            userTodos = todosInput;
            todoNames.setListData(taskNamesInput);
            todoDescriptions = taskDescriptionsInput;
            todoStartTimes = taskStartTimesInput;
            todoEndTimes = taskEndTimesInput;
        }
    }

}
