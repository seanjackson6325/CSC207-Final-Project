# CSC207 Final Project Read Me

Before starting, also read howto.md please (-Kyle)

Please Note that you should run it on JDK11 (universal JDK for CSC207)

# Brief description:

Problem Domain: Time Management 

This Project will build a TODO/Calendar app where it provides the user with tools
to help manage their todos and time management; where it has features like teams, git-hub updates
weather, smart suggestions...etc. (App simplifies managing todos by connecting different accounts:
for example, git-hub or other workspaces and automatically adds the events and deadlines)

# APIs

- Doc for Git-hub rest API: https://docs.github.com/en/rest?apiVersion=2022-11-28
- API for weather (TODO)


- Here is a photo of GitHub API being accessed through postman
- ![screenshot of postman.png](photos%2Fscreenshot%20of%20postman.png)

- Here is an example of Java output form the GitHub Rest API (sout)

Response{protocol=h2, code=200, message=, url=https://api.github.com/user/repos}
[{"id":690290612,"node_id":"R_kgDOKST_tA","name":"FizzBuzz","full_name":"kylej143/FizzBuzz"...

since the output is long, only the front part has been added; you can run the 
pvsm in API_GitHub class to achieve the same results!

# Sources and References

- OkHttp: https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.10.0

Documentation for OkHttp: https://square.github.io/okhttp/

- Previous Labs and Class

# Current List of Technical Problems

- Implementing more methods and making data extracted from the API usable