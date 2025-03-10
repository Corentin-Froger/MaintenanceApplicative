
package trivia;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameTest {
    @Test
    @Disabled("On test avec la mise à jour")
    public void caracterizationTest() {
        // runs 10.000 "random" games to see the output of old and new code matches
        for (int seed = 1; seed < 10_000; seed++) {
            testSeed(seed, false);
        }
    }

    private void testSeed(int seed, boolean printExpected) {
        String expectedOutput = extractOutput(new Random(seed), new GameOld());
        if (printExpected) {
            System.out.println(expectedOutput);
        }
        String actualOutput = extractOutput(new Random(seed), new Game());
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @Disabled("enable back and set a particular seed to see the output")
    public void oneSeed() {
        testSeed(1, true);
    }

    private String extractOutput(Random rand, IGame aGame) {
        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream inmemory = new PrintStream(baos)) {
            // WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
            System.setOut(inmemory);

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");

            boolean notAWinner;
            do {
                aGame.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    notAWinner = aGame.wrongAnswer();
                } else {
                    notAWinner = aGame.handleCorrectAnswer();
                }
            } while (notAWinner);
        } finally {
            System.setOut(old);
        }

        return baos.toString();
    }

    @Test
    public void testMaxPlayers() {
        Game game = new Game();

        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PrintStream inmemory = new PrintStream(baos)) {
            System.setOut(inmemory);

            game.add("A");
            game.add("B");
            game.add("C");
            game.add("D");
            game.add("E");
            game.add("F");
            game.add("G");
        } finally {
            System.setOut(old);
        }

        String actualOutput = baos.toString();
        String expectedOutput = """
                A was added\r
                They are player number 1\r
                B was added\r
                They are player number 2\r
                C was added\r
                They are player number 3\r
                D was added\r
                They are player number 4\r
                E was added\r
                They are player number 5\r
                F was added\r
                They are player number 6\r
                Too many players (6 / 6)\r
                """;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSamePlayerName() {
        Game game = new Game();

        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PrintStream inmemory = new PrintStream(baos)) {
            System.setOut(inmemory);

            game.add("A");
            game.add("B");
            game.add("C");
            game.add("B");
            game.add("D");
        } finally {
            System.setOut(old);
        }

        String actualOutput = baos.toString();
        String expectedOutput = """
                A was added\r
                They are player number 1\r
                B was added\r
                They are player number 2\r
                C was added\r
                They are player number 3\r
                B is already played\r
                D was added\r
                They are player number 4\r
                """;
        assertEquals(expectedOutput, actualOutput);
        assertEquals(4, game.howManyPlayers());
    }
}
