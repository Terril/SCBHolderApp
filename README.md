# SCBHolderApp

# Features
# Application flow
The application is based on reactie programming. Majorly all the libraries and code bases align to the usage of reflection paradigm

# APIs
This app uses the follwoing API in https://jsonplaceholder.typicode.com/

# Local database for offline uses
Since we have used Room with LiveData the application can be used in local mode. LiveData act as an Observer and checks for an update from the 
server and quicky updates the database. Room being part of the Android architecture has a well commenced DAO pattern.

# Architecture and code quality
Library used
Exposy for complex recycler views
Retrofit, RxJava and Jackson for network abstractions
Dagger for dependency injection
Room for local database
Databinding for better UI layouts

# Planned Task
To use Mockito and Espresso to do Unit as well as UI Testing
To integrated with CI majorly Jenkins or Travis(Github owns and easy to use with Github).
To include Lint or SonarQue to check for code smell and violations so it becomes easy to track the quality of code.

