package Factory;

import data_access.DataAccessInterface;
import entity.Todo;
import entity.User;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user.UserController;
import interface_adapter.user.UserPresenter;
import interface_adapter.user.UserViewModel;
import use_case.CreateTodoUser.CreateTodoUserInputBoundary;
import use_case.CreateTodoUser.CreateTodoUserInteractor;
import use_case.CreateTodoUser.CreateTodoUserOutputBoundary;
import use_case.EditTodo.EditTodoInteractor;
import use_case.EditTodo.EditTodoOutputBoundary;
import view.ViewManager;
import view.user.UserView;

import java.util.List;

public class UserFactory {
    public User create(String name, String password, List<Todo> taskList, List<String> teams) {
        return new User(name, password, taskList, teams);
    }

    public static UserView createUserView(ViewManager viewManager,
                                          UserViewModel userViewModel,
                                          DataAccessInterface userDataAccess)
    {
        UserController controller = createUserController(viewManager, userViewModel, userDataAccess);
        return new UserView(userViewModel, controller);
    }

    public static UserController createUserController(ViewManager viewManager,
                                                      UserViewModel userViewModel,
                                                      DataAccessInterface userDataAccess)
    {
        CreateTodoUserOutputBoundary presenter = new UserPresenter(viewManager, userViewModel);
        CreateTodoUserInteractor createInteractor = new CreateTodoUserInteractor(userDataAccess, presenter);

        EditTodoInteractor editInteractor = new EditTodoInteractor(userDataAccess, (EditTodoOutputBoundary) presenter);
        return new UserController(createInteractor, editInteractor);
    }

}