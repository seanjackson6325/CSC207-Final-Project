# Documentation of ImageKit.io and Data Access:

By: Kyle Joung

This file will discuss:
- Data Access and How it works
- ImageKit and how data is stored online
- Errors and Possible Fixes
- Test files and cautions

# Data Access:

Just refer to src/data_access/DataAccessInterface if you just want to know how to use the class:

Important: DataAccess methods will throw run time exceptions for all different types of errors for the simplicity of the other team to implement!

Important: DataAccess is reliant on the fact that data.json exists on ImageKit.io of the correct format. If the database is corrupt, we're doomed because I'm not doing database replication. rm -rf for life!

1. How Todo()s are saved
   - Todos are saved as a string, values seperated by commas.
   - this is parsed by .split(",") and concatenated.
   - Is saved in User() and Team() in lists; this is okay due to the fact that we can have duplicate todos
2. How User()s and Team()s are saved
    - They are saved as a JSON object, where the name (unique) is the key and the value is a JSON object of corresponding variables in the class.
    - example: {"name": {"var1": value1,...}}
3. Methods
   - read: returns the User or Team instance in the database; returns null if there are no instance in the database
     - Note that there is an early return in case of a null, so there is no need for a search method as run times are the same.
     - However, do not use this method to check for existence as the relevant methods you are trying to use also have search integrated.
   - Create, Update, Delete
     - When you try to run these methods and the instance you are trying to call does (not) exist in the database, it will return a run time error.
     - These can also return API related errors as run time error (more on API section)

# ImageKit:

1. ImageKit Limits: 20GB Bandwidth, 20GB Media storage, 1000 Video processing units, and 500 Extension units, unlimited request.
2. Also, I can see the request IP Addresses... (in ImageKit), so use a VPN if you feel insecure.
3. UNEXPECTED ERRORS (IMPORTANT): ImageKit sometimes have serverside Errors, most often with POST requests; so although the implementations are correct, the API methods can still return an Unknown Error.
4. How these Errors are handled: Normally, just rerequesting fixes it. After waiting ~2 seconds, the request is made again internally in API methods.
5. Possible fix; you can make a request queue in the API code to retry until it works and if it doesn't work for X amount of times, record that data seperately.
6. FOR THE SCOPE OF THIS CLASS: it is really unlikely actually code will not work; so when you catch runtime errors, just say the username does(n't) exist in the database in your fail views.
7. When requesting GET; the most recent version is called by putting a UNIX time (seconds) in the request. ImageKit does not have much documentation on this but calling the update time of the latest instance returns the latest version; so if you edit the code and we do a http request with a UNIX time of 100 years into the future, that version will be returned for all UNIX time before that date (except for ones already called) even if there is a newer json version.

# Test Files:

Note that the DataAccessTest will actually run in the data.json file, which is used in production; so take caution if there is a change of the test file or dependent files, it can mess up the database (this won't happen because DataAccess and API is done and is final). ImageKitIoAPITest is ran on APItest.json, which is okay to run anytime.