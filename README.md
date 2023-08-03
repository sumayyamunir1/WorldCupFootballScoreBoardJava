# World Cup Football Score Board

This project implements a Score Board for tracking football matches during the World Cup.
This allow users to add games, update scores and get the summary of total scores.
The user will have a Menu to selection an option.

1. Start a game
2. Update score
3. Get a summary of Games by total score
4. Remove all games from Score board
5. Exit

# The project structure is organized as following:

NewFootBallScoreBoardJava/
java/src/main/FootballScoreBoard.java  -> this file contains the implementation of all functions
java/src/main/Game.java -> Getter and Setter class 
java/src/test/TestFootballScoreBoard.java  -> this file contains all the test cases for football score board

## Installation

To run this program, you need to have Java 20.0.2 or a compatible version installed on your system.

1. Clone this repository to your local machine.
   git clone <repository_url>
2. Go to the project directory.
3. Make sure you have Java installed. If not, download and install the latest Java Development Kit (JDK) for your operating system from the official Oracle website or OpenJDK distribution.
4. Open a terminal or command prompt and navigate to the project's root directory.
5. Navigate to Project Directory:
Use the cd command to navigate to your project's root directory where the src and lib folders are located. For example:

cd path/to/your/project/directory
in my case it is cd/NewFootBallScoreBoardJava/java

6. Compile Java Classes:
Compile all the Java files in the src/main directory using the javac command. Since you have two packages (main and test), you need to compile both of them. Use the following commands:

javac src/main/FootballScoreBoard.java
javac src/main/Game.java

This will compile all the Java files in the src/main directory.

7. Now, you can run your project by executing the java command with the name of the main class, which is FootballScoreBoard in your case:

java src.main.FootballScoreBoard

This command will run the main method inside the FootballScoreBoard class and start your application.

# To run the test case file in your Java project, follow these instructions:

1. Open a command terminal (CMD) or a terminal emulator of your choice. 

2. Navigate to the root directory of your project using the cd command. For example, if your project is in the folder named NewFootballScoreBoard, use:

cd path/to/NewFootballScoreBoard

3. Compile all the Java files in the src/main and src/test directories. The test classes and the main classes need to be compiled together:

javac src/main/*.java src/test/*.java
javac src/main/FootballScoreBoard.java  src/main/Game.java src/test/TestFootballScoreBoard.java

This will compile all the Java files in both the src/main and src/test directories.

4. Now, you can run the test case file using a testing framework like JUnit. In your case, you are using JUnit, so you need to run the test using the java command with the JUnit runner class:

java -cp .;path/to/junit.jar;path/to/hamcrest.jar org.junit.runner.JUnitCore src.test.TestFootballScoreBoard

Replace path/to/junit.jar and path/to/hamcrest.jar with the actual paths to the JUnit and Hamcrest JAR files. The TestFootballScoreBoard is the name of your test case file.

5. The JUnit runner will execute all the test methods in the TestFootballScoreBoard class and display the test results in the terminal.

# Other way of running test cases individually in visual stuio code:

1. Go to the test file "TestFootballScoreBoard". Right click on each test case and select "Run Test". This will run every test case function seperately or right click and select "Run tests in Current File", which will run all the test cases in a file.

## Assumptions and Solutions from my Side:

1. Currently Scoreboard can take unlimited number of games to be added. We can set the limit number of games to be added.
2. The program keep track of games in memory at the time of execution only and lost data when the program terminated. We can use database or file system to store games data to be used later.
3. Assuming that the maximum score for a game is a positive integer. The program currently checks for negative scores, but it does not check for extremely large scores that might exceed the data type's limit.
