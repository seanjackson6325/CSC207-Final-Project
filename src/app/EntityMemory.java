package app;

import entity.User;

public class EntityMemory {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        loggedInUser = loggedInUser;
    }


}
