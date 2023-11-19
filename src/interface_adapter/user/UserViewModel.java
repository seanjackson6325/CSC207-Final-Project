package interface_adapter.user;

import entity.Todo;
import entity.User;
import view.ViewManager;
import view.ViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private User loggedInUser;

    /*
    todos.setListData(new String[]
                {"Walk Dog", "Get Groceries", "Make Dinner", "Buy Milk",
                        "Pay Bills", "Another One", "Another 2", "Another 3", "Another 4"
                });
     */


    public UserViewModel(ViewManager viewManager)
    {
        super("Personal", viewManager);

        // Test code for view
        List<Todo> taskList = new ArrayList();
        loggedInUser = new User("Sean", "test", taskList, null);
        Todo todo1 = new Todo
                (
                        "Walk Dog",
                        "Take my dog out for a walk in the park",
                        LocalDateTime.of(2023/*year*/, 11/*month*/, 7/*day*/, 10/*hour*/, 0/*minute*/),
                        LocalDateTime.of(2023/*year*/, 11/*month*/, 7/*day*/, 12/*hour*/, 0/*minute*/),
                        loggedInUser.getUsername(),
                        loggedInUser.getUsername(),
                        false
                );
        Todo todo2 = new Todo
                (
                        "Get Groceries",
                        "Make sure to buy chips for party on Saturday.",
                        LocalDateTime.of(2023/*year*/, 11/*month*/, 7/*day*/, 16/*hour*/, 0/*minute*/),
                        LocalDateTime.of(2023/*year*/, 11/*month*/, 7/*day*/, 17/*hour*/, 30/*minute*/),
                        loggedInUser.getUsername(),
                        loggedInUser.getUsername(),
                        false
                );

        taskList.add(todo1);
        taskList.add(todo2);
        loggedInUser = new User("Sean", "test", taskList, null);
    }

    public User getLoggedInUser()
    {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser)
    {
        this.loggedInUser = loggedInUser;
    }

}
