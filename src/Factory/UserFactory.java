package Factory;

import entity.Todo;
import entity.User;

import java.util.List;

public class UserFactory {
    public User create(String name, String password, List<Todo> taskList, List<String> teams) {
        return new User(name, password, taskList, teams);
    }
}
