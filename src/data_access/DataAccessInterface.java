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
public interface DataAccessInterface {

    /**
     * Precondition: username does not already exist in the Database (is unique)
     * You can check this by running readUser(username) == null
     */
    void createUser(User user);

    /**
     * Returns: null if there are no User with the String provided
     */
    User readUser(String username);

    /**
     * Precondition: username already exist in the Database
     * You can check this by running readUser(username) != null
     */
    void updateUser(User user);

    void deleteUser(String username);

    /**
     * Precondition: teamName does not already exist in the Database (is unique)
     * You can check this by running readUser(teamName) == null
     */
    void createTeam(Team team);

    /**
     * Returns: null if there are no Team with the String provided
     */
    Team readTeam(String teamName);

    /**
     * Precondition: teamname already exist in the Database
     * You can check this by running readUser(teamname) != null
     */
    void updateTeam(Team team);


    void deleteTeam(String teamName);

}
