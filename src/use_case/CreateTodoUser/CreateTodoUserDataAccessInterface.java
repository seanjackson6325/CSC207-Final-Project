package use_case.CreateTodoUser;

import entity.User;

public interface CreateTodoUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
