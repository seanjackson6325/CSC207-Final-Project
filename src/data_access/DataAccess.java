package data_access;

import com.google.gson.*;

import entity.Team;
import entity.Todo;
import entity.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class DataAccess implements DataAccessInterface {

    private final String dataPath = "src/data_access/data.json";
    private JsonObject data;


    public DataAccess() {
        LoadData();
    }

    public JsonObject getData() {
        return data;
    }

    private void LoadData() {
        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            data = gson.fromJson(br, JsonObject.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    void SaveData() {
        Gson gson = new Gson();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            String json = gson.toJson(data);
            bw.write(json);
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Todo> convertStringtoTodo(List<String> strings) {
        List<Todo> todoList = new ArrayList<>();
        for (String s: strings) {
            String[] toDoAttr = s.split(",");
            todoList.add(
                    new Todo(
                            toDoAttr[0],
                            toDoAttr[1],
                            LocalDateTime.parse(toDoAttr[2]),
                            LocalDateTime.parse(toDoAttr[3]),
                            toDoAttr[4],
                            toDoAttr[5],
                            Boolean.parseBoolean(toDoAttr[6])
                    )
            );
        }
        return todoList;
    }

    private String convertTodotoString(List<Todo> todo) {

        StringBuilder string = new StringBuilder();
        for (Todo s: todo) {
            string.append(",").append("\"").append(s.getName()).append(",")
                    .append(s.getDescription()).append(",")
                    .append(s.getStartTime()).append(",")
                    .append(s.getEndTime()).append(",")
                    .append(s.getRequester()).append(",")
                    .append(s.getRequestedTo()).append(",")
                    .append(s.getStatus()).append("\"");
        }
        return string.substring(1);
    }

    private String convertListToString(List<String> team) {
        StringBuilder string = new StringBuilder();
        for (String s: team) {
            string.append(",").append("\"").append(s).append("\"");
        }
        return string.substring(1);
    }

    @Override
    public void createUser(User user) {
        //checks for existence
        try {
            data.get("USER_" + user.getUsername()).getAsJsonObject();

        } catch(Exception e) {
            String strUserData = "{\"password\":\"" + user.getPassword() +
                    "\",\"todos\":" + "[" + convertTodotoString(user.getTaskList()) + "]" +
                    ",\"teams\":" + "[" + convertListToString(user.getTeam()) + "]" +  "}";

            JsonElement userData = new JsonParser().parse(strUserData).getAsJsonObject();
            data.add("USER_" + user.getUsername(), userData);

            SaveData();
            return;
        }
        throw new RuntimeException("User Already Exists");
    }

    @Override
    public User readUser(String username) {
        //checks for existence
        try {
            JsonObject userData = data.get("USER_" + username).getAsJsonObject();
            String password = userData.get("password").getAsString();

            List<String> toDoAsString = new ArrayList<>();
            for (JsonElement s: userData.get("todos").getAsJsonArray()) {
                toDoAsString.add(s.toString().replace("\"", ""));
            }
            List<Todo> toDo = convertStringtoTodo(toDoAsString);

            List<String> teams = new ArrayList<>();
            for (JsonElement s: userData.get("teams").getAsJsonArray()) {
                teams.add(s.toString().replace("\"", ""));
            }

            return new User(username, password, toDo, teams);

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            data.remove("USER_" + user.getUsername());
            createUser(user);  // contains SaveData()

        } catch(Exception e) {
            throw new RuntimeException("User Does Not Exists");
        }
    }

    @Override
    public void deleteUser(String username) {
        //checks for existence
        try {
            data.remove("USER_" + username);
            SaveData();

        } catch(Exception e) {
            throw new RuntimeException("User Does Not Exists");
        }
    }

    @Override
    public void createTeam(Team team) {
        //checks for existence
        try {
            data.get("TEAM_" + team.getTeamName()).getAsJsonObject();

        } catch(Exception e) {
            String strTeamData = "{\"todos\":" + "[" + convertTodotoString(team.getTeamTasks()) + "]" +
                    ",\"managers\":" + "[" + convertListToString(team.getManagers()) + "]" +
                    ",\"members\":" + "[" + convertListToString(team.getMembers()) + "]" +  "}";

            JsonElement teamData = new JsonParser().parse(strTeamData).getAsJsonObject();
            data.add("TEAM_" + team.getTeamName(), teamData);

            SaveData();
            return;
        }
        throw new RuntimeException("User Already Exists");
    }

    @Override
    public Team readTeam(String teamName) {
        //checks for existence
        try {
            JsonObject teamData = data.get("TEAM_" + teamName).getAsJsonObject();

            List<String> toDoAsString = new ArrayList<>();
            for (JsonElement s: teamData.get("todos").getAsJsonArray()) {
                toDoAsString.add(s.toString().replace("\"", ""));
            }
            List<Todo> toDo = convertStringtoTodo(toDoAsString);


            List<String> managers = new ArrayList<>();
            for (JsonElement s: teamData.get("managers").getAsJsonArray()) {
                managers.add(s.toString().replace("\"", ""));
            }

            List<String> members = new ArrayList<>();
            for (JsonElement s: teamData.get("members").getAsJsonArray()) {
                members.add(s.toString().replace("\"", ""));
            }

            return new Team(teamName, toDo, managers, members);

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public void updateTeam(Team team) {
        try {
            data.remove("TEAM_" + team.getTeamName());
            createTeam(team);  // contains SaveData()

        } catch(Exception e) {
            throw new RuntimeException("Team Does Not Exists");
        }
    }

    @Override
    public void deleteTeam(String teamName) {
        //checks for existence
        try {
            data.remove("TEAM_" + teamName);
            SaveData();

        } catch(Exception e) {
            throw new RuntimeException("Team Does Not Exists");
        }
    }
}
