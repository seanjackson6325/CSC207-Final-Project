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
     * @exception RuntimeException if there is already a user or a datbase error.
     */
    void createUser(User user);

    /**
     * Returns: null if there are no User with the String provided
     */
    User readUser(String username);

    /**
     * Precondition: username already exist in the Database
     * @exception RuntimeException if there isn't a user or a datbase error.
     */
    void updateUser(User user);

    /**
     * Precondition: username already exist in the Database
     * @exception RuntimeException if there isn't a user or a datbase error.
     */
    void deleteUser(String username);

    /**
     * Precondition: teamName does not already exist in the Database (is unique)
     * @exception RuntimeException if there is already a team or a datbase error.
     */
    void createTeam(Team team);

    /**
     * Returns: null if there are no Team with the String provided
     */
    Team readTeam(String teamName);

    /**
     * Precondition: teamname already exist in the Database
     * @exception RuntimeException if there isn't a team or a datbase error.
     */
    void updateTeam(Team team);

    /**
     * Precondition: teamname already exist in the Database
     * @exception RuntimeException if there isn't a team or a datbase error.
     */
    void deleteTeam(String teamName);

}
