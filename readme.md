# TMM Read Me

Welcome to the ReadMe file for the Team Management Manager (TMM)
Here, you will find general instructions, references, and most importantly, the Project Blueprint (with specifications)

# Fixes and Tips:

Git Workflow: make sure your origin/main is up-to-date!
Then create a local branch (with whatever name you want).
Use intellij's built in commit; it will automatically
create the remote branch on git for you. Got to GitHub
and create a merge request.

    - Build -> Rebuild Project (can fix some issues)
    - Delete the remote clone and reclone from VCS
    - Go talk to Kyle :)

# Brief description:

TMM aims to make managing time management in a group environment easy. For example, before a manager can set a time for a worker to do a task, they are doomed to do the following: ask! Now add on to the fact that people's schedules change and the fact that the manager might have multiple people to manage; that seems like a good nightmare fuel.

# Specifications

    - Personal Todo List
    - Create and Manage Groups
    - Group Todo Lists
    - Be able to see other's schedule (in the same group)
    - Be able to request and accept Todos from others on empty time slots
    - Different types of Todos (commute, private, work...etc)
    - Smart Suggestions and Auto Update (Bonus)

# Use Cases
1. Sign in
2. Login
3. Create TODO (Personal)
4. Create TODO (Group)
5. Delete TODO
6. Create Group
7. Add/Remove Group Members -> Invite/Accept/Remove Members

# APIs and Maven Dependencies

- GSON (Json API): https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1
- OkHttp (Http Request API): https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.10.0
- ImageKit.io (Online Database API): https://github.com/imagekit-developer/imagekit-java
- API for Open Weather: https://openweathermap.org/api/one-call-3


# Sources and References

- GSON: https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/module-summary.html
- OkHttp: https://square.github.io/okhttp/
- JSON: https://docs.oracle.com/javaee/7/api/javax/json/JsonObject.html
- ImageKit.io: https://docs.imagekit.io/
- Open Weather: https://openweathermap.org/
- Previous Labs and Class from (CSC207 UofT)

# Documentation for Use Cases (separate md file for Database)

- DeleteTodo(): Takes in an index number of the todo, the list of todos, the team containing the todo list or null if it is from the user. Calling this controller will delete a todo from the user/team.
- CreateTeam(): Takes in a String of the name of the team that is being created. It will create a new team if the team name is unique with an empty list of todos with the user in it.