package src.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import src.main.Game;
import src.main.FootballScoreBoard;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class TestFootballScoreBoard {

    private FootballScoreBoard scoreboard_obj;
    private List<Game> games;

    @Before
    public void setUp() {
        scoreboard_obj = new FootballScoreBoard();
    }

    @After
    public void tearDown() {
        scoreboard_obj.getGames().clear();
    }

    // The test simulates user input for a game with valid team names and scores
    @Test
    public void testGameStartWithValidInput() {
        String input = "1\nCanada\nAustralia\n13\n34\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        scoreboard_obj = new FootballScoreBoard();
        // call the FootballScoreBoard method
        scoreboard_obj.gameStart();

        // Assert that only one game is added
        assertEquals(1, scoreboard_obj.getGames().size());

        // Assert the details of the added game
        Game game = scoreboard_obj.getGames().get(0);
        assertEquals("Canada", game.getHomeTeam());
        assertEquals("Australia", game.getAwayTeam());
        assertEquals(13, game.getHomeTeamScore());
        assertEquals(34, game.getAwayTeamScore());
    }

    // The test simulates user input for game, It checks whether the
    // gameStart() method correctly handles this scenario of not adding a game
    // without team names
    @Test
    public void testGameStartWithEmptyTeamNames() {
        String input = "1\n\n\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        scoreboard_obj.gameStart();

        // Assert that no game is added to list without team names
        assertEquals(0, scoreboard_obj.getGames().size());
    }

    // it checks whether the gameStart() method handle multiple inputs
    @Test
    public void testMultipleGameStart() {
        String input = "3\nCanada\nAustralia\n13\n34\nNorway\nItaly\n45\n12\nGermany\nFrance\n19\n27\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        scoreboard_obj = new FootballScoreBoard();
        scoreboard_obj.gameStart();

        // Assert that three games are added
        assertEquals(3, scoreboard_obj.getGames().size());

        // Assert the details of the first game
        Game firstGame = scoreboard_obj.getGames().get(0);
        assertEquals("Canada", firstGame.getHomeTeam());
        assertEquals("Australia", firstGame.getAwayTeam());
        assertEquals(13, firstGame.getHomeTeamScore());
        assertEquals(34, firstGame.getAwayTeamScore());

        // Assert the details of the second game
        Game secondGame = scoreboard_obj.getGames().get(1);
        assertEquals("Norway", secondGame.getHomeTeam());
        assertEquals("Italy", secondGame.getAwayTeam());
        assertEquals(45, secondGame.getHomeTeamScore());
        assertEquals(12, secondGame.getAwayTeamScore());

        // Assert the details of the third game
        Game thirdGame = scoreboard_obj.getGames().get(2);
        assertEquals("Germany", thirdGame.getHomeTeam());
        assertEquals("France", thirdGame.getAwayTeam());
        assertEquals(19, thirdGame.getHomeTeamScore());
        assertEquals(27, thirdGame.getAwayTeamScore());
    }

    // this test validates to update score for the game already exist in the
    // Scorebaord
    @Test
    public void testUpdateScoreExistingGame() {
        // Create a FootballScoreBoard instance
        scoreboard_obj = new FootballScoreBoard();

        // add a game to the scoreboard
        Game game = new Game("Canada", "Australia", 10, 20);
        scoreboard_obj.addGame(game);

        // take new scores(30,40) as input from user and update them for existing game
        String input = "Canada\nAustralia\n30\n40\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the updateScore method
        scoreboard_obj.updateScore();

        // Get the list of games from the scoreboard
        games = scoreboard_obj.getGames();

        // Verify that only one game is present in the scoreboard
        assertEquals(1, games.size());

        // Verify the details of the updated game
        Game updatedGame = games.get(0);
        assertEquals("Canada", updatedGame.getHomeTeam());
        assertEquals("Australia", updatedGame.getAwayTeam());
        assertEquals(30, updatedGame.getHomeTeamScore());
        assertEquals(40, updatedGame.getAwayTeamScore());
    }

    // verify the working of updateScore() method when the user tries to
    // update the scores for a game that does not exist in the FootballScoreBoard
    @Test
    public void testUpdateScoreForNonExistingGame() {
        // Create a FootballScoreBoard instance
        scoreboard_obj = new FootballScoreBoard();

        // Add a game to the scoreboard
        Game game = new Game("Canada", "Australia", 30, 40);
        scoreboard_obj.addGame(game);

        // Game that a user want to update, which doesn't exit in a ScoreBoard
        String input = "India\nPakistan\n50\n60\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the updateScore method
        scoreboard_obj.updateScore();

        // Get the list of games from the scoreboard
        games = scoreboard_obj.getGames();

        // Verify that the number of games remains the same
        assertEquals(1, games.size());

        Game updatedGame = games.get(0);
        assertEquals("Canada", updatedGame.getHomeTeam());
        assertEquals("Australia", updatedGame.getAwayTeam());
        assertEquals(30, updatedGame.getHomeTeamScore());
        assertEquals(40, updatedGame.getAwayTeamScore());
    }

    @Test
    public void testUpdateScoreEmptyTeamNames() {
        scoreboard_obj = new FootballScoreBoard();

        // Add a game to the scoreboard
        Game game = new Game("Australia", "England", 10, 20);
        scoreboard_obj.addGame(game);

        // Assert user input with empty team names
        String input = "\n\n30\n40\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the updateScore method
        scoreboard_obj.updateScore();

        // Get the list of games from the scoreboard
        List<Game> games = scoreboard_obj.getGames();

        // Verify that the score for the existing game has not been updated
        assertEquals(1, games.size());
        Game updatedGame = games.get(0);
        assertEquals("Australia", updatedGame.getHomeTeam());
        assertEquals("England", updatedGame.getAwayTeam());
        assertEquals(10, updatedGame.getHomeTeamScore());
        assertEquals(20, updatedGame.getAwayTeamScore());
    }

    @Test
    public void testUpdateScoreWithNegativeValue() {
        scoreboard_obj = new FootballScoreBoard();

        // Add a game to the scoreboard
        Game game = new Game("Australia", "England", 10, 20);
        scoreboard_obj.addGame(game);

        // Asserts user input with negative scores
        String input = "Canada\nAustralia\n-5\n-8\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the updateScore method
        scoreboard_obj.updateScore();

        // Get the list of games from the scoreboard
        List<Game> games = scoreboard_obj.getGames();

        // Verify that the score for the existing game has not been updated with
        // negative scores
        assertEquals(1, games.size());
        Game updatedGame = games.get(0);
        assertEquals("Australia", updatedGame.getHomeTeam());
        assertEquals("England", updatedGame.getAwayTeam());
        assertEquals(10, updatedGame.getHomeTeamScore());
        assertEquals(20, updatedGame.getAwayTeamScore());
    }

    // this test case validates the working of removeAllGames() when the games will
    // be removed from ScoreBoard
    @Test
    public void testRemoveAllGames() {
        scoreboard_obj = new FootballScoreBoard();

        // Add some games to the scoreboard
        Game game1 = new Game("Australia", "England", 10, 20);
        Game game2 = new Game("Sweden", "Norway", 30, 25);
        scoreboard_obj.addGame(game1);
        scoreboard_obj.addGame(game2);

        // Verify that the games have been added
        List<Game> gamesBeforeRemoval = scoreboard_obj.getGames();
        assertEquals(2, gamesBeforeRemoval.size());

        // Call the removeAllGames method
        scoreboard_obj.removeAllGames();

        // Verify that all games have been removed from the scoreboard
        List<Game> gamesAfterRemoval = scoreboard_obj.getGames();
        assertEquals(0, gamesAfterRemoval.size());
    }

    // test case to verifiy the displayAllGameScores() does not show any game if
    // there is no game in the Scorebaord
    @Test
    public void testDisplayAllGameScoresWithNoScoreBoardGames() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scoreboard_obj = new FootballScoreBoard();
        scoreboard_obj.displayAllGameScores();

        String expectedOutput = "There are no games to display.";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testDisplayAllGameScoresWithScoreBoardGames() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scoreboard_obj = new FootballScoreBoard();

        Game game1 = new Game("Canada", "Australia", 20, 30);
        Game game2 = new Game("Denmark", "Norway", 15, 25);
        scoreboard_obj.addGame(game1);
        scoreboard_obj.addGame(game2);

        scoreboard_obj.displayAllGameScores();

        String expectedOutput = "### Scores for all teams ####:\n" +
                "Scores for team Canada is 20 : Scores for team Australia is 30\n" +
                "Scores for team Denmark is 15 : Scores for team Norway is 25";
        String actualOutput = outputStream.toString().trim().replace("\r", "");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testGameStartWithNonIntegerInput() {
        String input = "Canada\nAustralia\n13\n34\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        scoreboard_obj.gameStart();

        // Assert that no game is added (due to non-integer input for the number of
        // games)
        assertEquals(0, scoreboard_obj.getGames().size());
    }

    @Test
    public void testGameStartWithNegativeScore() {
        String input = "1\nIndia\nPakistan\n-10\n20\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        scoreboard_obj.gameStart();

        // Assert that no game is added (due to negative score)
        assertEquals(0, scoreboard_obj.getGames().size());
    }

}