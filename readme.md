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
    - Create your own account
    - Create and Manage Groups
    - Group Todo Lists
    - Has accurate weather analysis to base Todo tasks on
    - Be able to edit a personal or team Todo task
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
6. Edit TODO
7. Create Team
8. Add/Remove Team Members -> Invite/Accept/Remove Members
9. Mark As Done

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

# Responsibilities

Add your name in the bracket for the things you've worked on substantially
This is done in order to keep track of the responsibilities and if we have problems (for example on how to use a certain method); we know who to ask.

example:

- [x] NameOfArea (People who worked on the area)

Checked means that the class is ready for use and is done!

Non-Coding Related
- [x] Project Blueprint and Specification (Group)
- [x] Documentation / ReadMe (Kyle, Sean)
- [x] UML Diagram (Kyle)
- [ ] Sequence Diagram ()

Below are Coding Related:

(App)
- [ ] Main (Sean)
- [x] EntityMemory (Kyle)

(View / ViewModel)
- [ ] Prototype View/View model (Sean)
  - [ ] Test ()
- [ ] Login/Signup View ()
  - [ ] Test ()
- [ ] Login/Signup ViewModel ()
  - [ ] Test ()
- [ ] TodoCreation View ()
  - [ ] Test ()
- [ ] TodoCreation ViewModel ()
  - [ ] Test ()
- [ ] Personal View ()
  - [ ] Test ()
- [ ] Personal ViewModel ()
  - [ ] Test ()
- [ ] Group View ()
  - [ ] Test ()
- [ ] Group ViewModel ()
  - [ ] Test ()

(Entity)
- [x] User (Kyle)
  - [ ] Test (Kyle)
- [x] UserFactory (Darryl)
  - [ ] Test (Darryl)
- [x] Todo (Kyle)
  - [ ] Test (Kyle)
- [x] TodoFactory (Darryl)
  - [ ] Test (Darryl)
- [x] Team (Kyle)
  - [ ] Test (Kyle)

(DataAccess)
- [x] DAO Implementation (Kyle)
  - [x] Test (Kyle)
- [x] DAO online database API Implementation (Kyle)
  - [x] Test (Kyle)

(Use Cases CreateTodoUser)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test () 
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Cases CreateTodoTeam)

- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Case DeleteToDo)
- [ ] InputBoundary (kyle)
  - [ ] Test ()
- [ ] InputData ()
  - [ ] Test ()
- [ ] OutputBoundary ()
  - [ ] Test ()
- [ ] OutputData ()
  - [ ] Test ()
- [ ] Interactor ()
  - [ ] Test ()

(Use Case EditTodo)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Cases Signup)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Cases Login)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Case CreateGroup)
- [ ] InputBoundary (Kyle)
  - [ ] Test ()
- [ ] InputData (Kyle)
  - [ ] Test ()
- [ ] OutputBoundary (Kyle)
  - [ ] Test ()
- [ ] OutputData (Kyle)
  - [ ] Test ()
- [ ] Interactor (Kyle)
  - [ ] Test ()

(Use Case AddManager)
- [ ] InputBoundary ()
  - [ ] Test ()
- [ ] InputData ()
  - [ ] Test ()
- [ ] OutputBoundary ()
  - [ ] Test ()
- [ ] OutputData ()
  - [ ] Test ()
- [ ] Interactor ()
  - [ ] Test ()

(Use Case RemoveManager)
- [ ] InputBoundary ()
  - [ ] Test ()
- [ ] InputData ()
  - [ ] Test ()
- [ ] OutputBoundary ()
  - [ ] Test ()
- [ ] OutputData ()
  - [ ] Test ()
- [ ] Interactor ()
  - [ ] Test ()

(Use Case AddMember)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Case RemoveMember)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

(Use Case MarkDone)
- [x] InputBoundary (Darryl)
  - [ ] Test ()
- [x] InputData (Darryl)
  - [ ] Test ()
- [x] OutputBoundary (Darryl)
  - [ ] Test ()
- [x] OutputData (Darryl)
  - [ ] Test ()
- [x] Interactor (Darryl)
  - [ ] Test ()

# Diagrams (For references, not accurate; outdated)

![UML Diagram (CreateTodoUser).png](photos%2FUML%20Diagram%20%28CreateTodoUser%29.png)