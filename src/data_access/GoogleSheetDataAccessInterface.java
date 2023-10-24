package data_access;

import entity.Team;
import entity.User;

/**
 *
 * create(): saves the object in the database
 * read(): find the object in the database and returns the object
 * update(): find the object and update the object in the database
 * delete(): find the object and delete it in the database
 *
 */
public interface GoogleSheetDataAccessInterface {

    /**
     * Precondition: username does not already exist in the Database (is unique)
     */
    void createUser(User user);

    User readUser(String username);

    void updateUser(User user);

    void deleteUser(String username);

    /**
     * Precondition: teamName does not already exist in the Database (is unique)
     */
    void createTeam(Team team);

    Team readTeam(String teamName);

    void updateTeam(Team team);

    void deleteTeam(String teamName);

}
