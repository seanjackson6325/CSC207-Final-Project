package app;

import entity.User;

public class EntityMemory {
    private User loggedInUser;

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }


}
