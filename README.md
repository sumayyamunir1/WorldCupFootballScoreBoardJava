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
java/src/test/TestFootballScoreBoard.java  -> this file contains all the test cases for score board

## Installation

To run this program, you need to have Java 20.0.2 or a compatible version installed on your system.

1. Clone this repository to your local machine.
   git clone <repository_url>
2. Go to the project directory.
3. Make sure we have Java installed. If not, download and install the latest Java Development Kit (JDK) for your operating system from the official Oracle website or OpenJDK distribution.
4. Open a terminal or command prompt and navigate to the project's root directory.
5. Navigate to Project Directory:
Use the cd command to navigate to your project's root directory where the src and lib folders are located. For example:

cd path/to/your/project/directory

6.Compile Java Classes:
Compile Java classes using the javac command. Since we have two packages (main and test), we need to compile both of them. Use the following commands:

javac src/main/FootballScoreBoard.java
javac src/main/Game.java
javac src/test/TestFootballScoreBoard.java

6. Run the unit tests to verify that the functionality is working correctly:

java -cp src:test org.junit.runner.JUnitCore TestFootballScoreBoard

7. Run Test Cases
We can use the java command with the -cp option to specify the classpath and the JUnit runner:

java -cp src:test org.junit.runner.JUnitCore TestFootballScoreBoard

