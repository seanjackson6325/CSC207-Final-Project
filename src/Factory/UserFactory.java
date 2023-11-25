package Factory;

import data_access.DataAccessInterface;
import entity.Todo;
import entity.User;
import interface_adapter.deleteTodo.DeleteTodoController;
import interface_adapter.deleteTodo.DeleteTodoPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user.UserController;
import interface_adapter.user.UserPresenter;
import interface_adapter.user.UserViewModel;
import use_case.CreateTodoUser.CreateTodoUserInputBoundary;
import use_case.CreateTodoUser.CreateTodoUserInteractor;
import use_case.CreateTodoUser.CreateTodoUserOutputBoundary;
import use_case.DeleteTodo.DeleteTodoInteractor;
import use_case.DeleteTodo.DeleteTodoOutputBoundary;
import use_case.EditTodo.EditTodoInteractor;
import use_case.EditTodo.EditTodoOutputBoundary;
import use_case.MarkDone.MarkDoneInputBoundary;
import use_case.MarkDone.MarkDoneInteractor;
import use_case.MarkDone.MarkDoneOutputBoundary;
import use_case.MarkDone.MarkDoneOutputData;
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
        UserController userController = createUserController(viewManager, userViewModel, userDataAccess);
        DeleteTodoController deleteController = createDeleteTodoController(viewManager, userViewModel, userDataAccess);
        return new UserView(userViewModel, userController, deleteController);
    }

    public static UserController createUserController(ViewManager viewManager,
                                                      UserViewModel userViewModel,
                                                      DataAccessInterface userDataAccess)
    {
        CreateTodoUserOutputBoundary presenter = new UserPresenter(viewManager, userViewModel);
        CreateTodoUserInteractor createInteractor = new CreateTodoUserInteractor(userDataAccess, presenter);

        EditTodoInteractor editInteractor = new EditTodoInteractor(userDataAccess, (EditTodoOutputBoundary) presenter);
        MarkDoneInteractor markDoneInteractor = new MarkDoneInteractor(userDataAccess, (MarkDoneOutputBoundary) presenter);
        return new UserController(createInteractor, editInteractor, markDoneInteractor);
    }

    public static DeleteTodoController createDeleteTodoController(ViewManager viewManager,
                                                                  UserViewModel userViewModel,
                                                                  DataAccessInterface userDataAccess)
    {
        DeleteTodoOutputBoundary presenter = new DeleteTodoPresenter(viewManager, userViewModel);
        DeleteTodoInteractor interactor = new DeleteTodoInteractor(userDataAccess, presenter);
        return new DeleteTodoController(interactor);
    }
}

