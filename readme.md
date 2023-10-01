# CSC207 Final Project Read Me

Before starting, also read howto.md please (-Kyle)

Please Note that you should run it on JDK11 (universal JDK for CSC207)

Guys send me your teach.cs username to kyle.joung@mail.utoronto.ca or on discord by friday 6pm cause we need to hand the repository.
Sean we need to make the project public so the TAs can see it (stated in the project quercus page)

# Brief description:

Problem Domain: Time Management 

This Project will build a TODO/Calendar app where it provides the user with tools
to help manage their todos and time management; where it has features like teams, git-hub updates
weather, smart suggestions...etc. (App simplifies managing todos by connecting different accounts:
for example, git-hub or other workspaces and automatically adds the events and deadlines)

# APIs

- Doc for Git-hub rest API: https://docs.github.com/en/rest?apiVersion=2022-11-28
- API for Open Weather: https://openweathermap.org/api/one-call-3
  
  example call (call this in a web browser): https://api.openweathermap.org/data/3.0/onecall?lat=33.44&lon=94.04&units=metric&appid=e76bf39d6d8a7ea676663caff3c3601a
  API KEY: e76bf39d6d8a7ea676663caff3c3601a
  
  call limited to once per minute

- Here is a photo of GitHub API being accessed through postman
- ![screenshot of postman.png](photos%2Fscreenshot%20of%20postman.png)

- Here is an example of Java output form the GitHub Rest API (sout)

Response{protocol=h2, code=200, message=, url=https://api.github.com/user/repos}
[{"id":690290612,"node_id":"R_kgDOKST_tA","name":"FizzBuzz","full_name":"kylej143/FizzBuzz"...

since the output is long, only the front part has been added; you can run the 
pvsm in API_GitHub class to achieve the same results!

NEW NOTE: apparently github doesn't like when we share private tokens, so we all each have to use our own token; 
I think a good way to do this is by having each of us making a JSON file with our tokens,
and making a get method to access the token from the class (that way we can all use the class without updating the token everytime someone else uses it)

# Sources and References

- OkHttp: https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.10.0

Documentation for OkHttp: https://square.github.io/okhttp/

- Previous Labs and Class

# Current List of Technical Problems

- Implementing more methods and making data extracted from the API usable
