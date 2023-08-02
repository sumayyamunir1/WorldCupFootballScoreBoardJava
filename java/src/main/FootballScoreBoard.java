package src.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class FootballScoreBoard {

    // arraylist for storing football games
    private List<Game> football_games;
    private List<Game> sortedGames;
    private List<Game> diffGamesTotalGames;
    private List<Integer> totalScores;

    public FootballScoreBoard() {
        this.football_games = new ArrayList<>();
        this.diffGamesTotalGames = new ArrayList<>();
        this.totalScores = new ArrayList<>();
    }

    // add gamnes in a Scoreboard one by one using this method
    public void addGame(Game game) {
        this.football_games.add(game);
    }

    // this method will return the games stored in a List
    public List<Game> getGames() {
        return football_games;
    }

    public void displayAllGameScores() {
        // this condition is used to check if the games list is empty
        if (football_games.isEmpty()) {
            System.out.print("There are no games in a ScoreBoard to display.");
            return;
        }

        // if the games list if not empty, it will show all the team scores saved in a
        // list against each game
        System.out.println("### Scores for all teams ####:");
        for (int i = 0; i < football_games.size(); i++) {
            // get the games from list one by one and display them
            Game game = football_games.get(i);
            System.out.println("Scores for team " + game.getHomeTeam() + " is " + game.getHomeTeamScore()
                    + " : Scores for team " + game.getAwayTeam() + " is " + game.getAwayTeamScore());
        }
    }

    // this method will start adding the games in the Scoreboard
    public void gameStart() {
        Scanner scanner = new Scanner(System.in);
        int numGames = 0;
        // the conditions below doesn't allow user to input -ive or invalid number as
        // the number os games for Scoreboard
        while (numGames <= 0) {
            try {
                System.out.print("Enter the number of games to add in a ScoreBoard: ");
                // is used to take input value from user
                numGames = scanner.nextInt();
                scanner.nextLine();

                if (numGames <= 0) {
                    System.out.println(
                            "You have entered -ive number...! Please enter a positive number greater than zero.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        // loop is used to go through the number of games added to the list and ask user
        // to enter Scores for all the added games
        for (int i = 0; i < numGames; i++) {
            System.out.println("Add Scores for Game number " + (i + 1));
            System.out.print("Enter name of home team : ");
            String homeTeamName = scanner.nextLine().trim();
            System.out.print("Enter name of away team : ");
            String awayTeamName = scanner.nextLine().trim();
            // this condition checks if Any of the team is not empty, then start taking
            // Scores for those teams
            if (!homeTeamName.isEmpty() && !awayTeamName.isEmpty()) {
                int homeTeamScore = -1;
                int awayTeamScore = -1;
                // check if user enter anything other than -ive number or any character
                while (homeTeamScore < 0 || awayTeamScore < 0) {
                    try {
                        System.out.print("Enter scores for home team " + homeTeamName + ": ");
                        homeTeamScore = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter scores for away team " + awayTeamName + ": ");
                        awayTeamScore = Integer.parseInt(scanner.nextLine());
                        // check if user enter any -ive scores for any team
                        if (homeTeamScore < 0 || awayTeamScore < 0) {
                            System.out.println("Scores cannot be negative. Please enter valid scores.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                }
                // initialized game class instance having parameters for gameList
                Game game = new Game(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);
                // add the game instance to a list of games
                addGame(game);
                System.out.println("Your score for Game " + (i + 1) + " has been entered.");
            } else {
                System.out.println("You have not entered Away team or Home team name for Game number " + (i + 1));
            }
        }
        // ask the user for a valid choice to see the added score
        System.out.print("Do you want to see the added scores? Press 1 for YES: ");
        int userChoice = Integer.parseInt(scanner.nextLine());
        if (userChoice == 1) {
            // if user select 1 , then the below function will display Scores for all the
            // added games
            displayAllGameScores();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // ask user to Enter name of teams, to update the Score for
    public void updateScore() {
        boolean scoreUpdated = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the details of the game to update:");
        System.out.print("Enter name of home team: ");
        String homeTeamName = scanner.nextLine();
        System.out.print("Enter name of away team: ");
        String awayTeamName = scanner.nextLine();

        for (Game game : football_games) {
            if (game.getHomeTeam().equals(homeTeamName) && game.getAwayTeam().equals(awayTeamName)) {
                int homeTeamScore = -1;
                int awayTeamScore = -1;
                while (homeTeamScore < 0 || awayTeamScore < 0) {
                    try {
                        System.out.print("Enter new score for " + homeTeamName + ": ");
                        homeTeamScore = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new score for " + awayTeamName + ": ");
                        awayTeamScore = Integer.parseInt(scanner.nextLine());
                        if (homeTeamScore < 0 || awayTeamScore < 0) {
                            System.out.println("Scores cannot be negative. Please enter valid scores.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                }
                game.setHomeTeamScore(homeTeamScore);
                game.setAwayTeamScore(awayTeamScore);
                scoreUpdated = true;
                break;
            }
        }

        if (scoreUpdated) {
            System.out.println("Game score has been updated successfully.");
            // call this function and Display the Games with updated Scores
            displayAllGameScores();
        } else {
            System.out.println("Game with the entered teams not found in the scoreboard.");
        }
    }

    // this function display the Games having teams with same and Maximum Scores on
    // the top of the list
    public void getSummary() {
        // this condition is used to check if there are no games in the list
        if (football_games.isEmpty()) {
            System.out.println("No games found in the scoreboard.");
            return;
        }
        // saved a copy of Football games list into sortedList
        sortedGames = new ArrayList<>(football_games);
        // sorting in decending order based on homeTeamScore + awayTeamScore
        Collections.sort(sortedGames, Comparator
                .comparingInt((Game game) -> game.getHomeTeamScore() + game.getAwayTeamScore())
                .reversed()
                .thenComparingInt(Game::getHomeTeamScore)
                .reversed());

        diffGamesTotalGames = new ArrayList<>();
        totalScores = new ArrayList<>();

        for (Game game : sortedGames) {
            int totalScore = game.getHomeTeamScore() + game.getAwayTeamScore();
            if (!totalScores.contains(totalScore)) {
                totalScores.add(totalScore);
                diffGamesTotalGames.add(game);
            }
        }
        Collections.reverse(diffGamesTotalGames);

        System.out.println("Summary of Games by Total Score:");
        for (Game game : diffGamesTotalGames) {
            System.out.println("Scores for team " + game.getHomeTeam() + " is " + game.getHomeTeamScore() +
                    " : Scores for team " + game.getAwayTeam() + " is " + game.getAwayTeamScore());
        }
    }

    // this function will remove games from ScoreBoard
    public void removeAllGames() {
        this.football_games.clear();
        System.out.println("All games have been removed");
    }

    public static void main(String[] args) {
        FootballScoreBoard scoreboard = new FootballScoreBoard();
        Scanner scanner = new Scanner(System.in);
        String menu;

        do {
            System.out.println("\n###### Main ######:");
            System.out.println("1. Start a game");
            System.out.println("2. Update score");
            System.out.println("3. Get a summary of Games by total score");
            System.out.println("4. Remove all games from Score board");
            System.out.println("5. Exit");
            System.out.print("Enter your choice...! ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    scoreboard.gameStart();
                    break;
                case 2:
                    scoreboard.updateScore();
                    break;
                case 3:
                    scoreboard.getSummary();
                    break;
                case 4:
                    scoreboard.removeAllGames();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select options from 1 to 5");
            }
            // ask the user to press M to see the menu again
            System.out.print("Press M to see the menu again: ");
            menu = scanner.nextLine();
        } while (menu.equalsIgnoreCase("M"));

        scanner.close();
    }
}
