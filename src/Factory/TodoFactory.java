package Factory;

import entity.Todo;
import entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class TodoFactory {
    public Todo create(String name, String description, LocalDateTime startTime, LocalDateTime endTime, String requester,
                       String requestedTo, Boolean status) {
        return new Todo(name, description, startTime, endTime, requester, requestedTo, status);
    }
}
