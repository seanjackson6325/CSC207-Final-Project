package interface_adapter.deleteTodo;

import entity.Team;
import entity.Todo;
import use_case.DeleteTodo.DeleteTodoInputBoundary;
import use_case.DeleteTodo.DeleteTodoInputData;

import java.util.List;

public class DeleteTodoController {

    final DeleteTodoInputBoundary deleteTodoInteractor;

    public DeleteTodoController(DeleteTodoInputBoundary deleteTodoInteractor)
    {
        this.deleteTodoInteractor = deleteTodoInteractor;
    }

    /**
     * @param indexNumber: the index of the To-do in the List<To-do>
     * @param list: the alias of the list we want to change
     * @param team: the alias of the Team of the list; null if the list is from the user
     */
    public void execute(int indexNumber, List<Todo> list, Team team)
    {
        DeleteTodoInputData input = new DeleteTodoInputData(indexNumber, list, team);
        deleteTodoInteractor.execute(input);
    }

}
