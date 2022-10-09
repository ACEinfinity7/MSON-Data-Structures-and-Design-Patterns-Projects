import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyAgentTest {

	Connect4Game game;
	final int NUM_OF_TEST_GAMES = 50;

	@Before
	public void setUp() throws Exception {
		game = new Connect4Game(7, 6);
	}

	// tests ICanWin() if both players place vertical lines of tokens
	@Test
	public void testICanWinVerticallySimple() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(1);
			yellowAgent.moveOnColumn(2);
		}

		assertEquals(1, redAgent.iCanWin());

	}

	// tests ICanWin() after both players place some tokens then form a vertical
	// line
	@Test
	public void testICanWinVerticallyTop4() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		for (int i = 0; i < 2; i++) {
			redAgent.moveOnColumn(1);
			yellowAgent.moveOnColumn(2);
		}

		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(2);
			yellowAgent.moveOnColumn(1);
		}

		assertEquals(2, redAgent.iCanWin());

	}

	// tests ICanWin() if players place horizontal lines at the bottom of the board
	@Test
	public void testICanWinHorizontallySimple() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, true);
		game.clearBoard();
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(i);
			yellowAgent.moveOnColumn(6);
		}

		assertEquals(3, redAgent.iCanWin());
	}

	// tests ICanWin() if players place horizontal lines above the bottom row of the
	// board
	@Test
	public void testICanWindHorizontallyNonBottomRow() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();

		redAgent.moveOnColumn(1);
		yellowAgent.moveOnColumn(0);
		redAgent.moveOnColumn(3);
		yellowAgent.moveOnColumn(2);

		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(i);
			yellowAgent.moveOnColumn(6);
		}

		assertEquals(3, redAgent.iCanWin());

	}

	// tests ICanWin() with a diagonal line that goes up from left to right
	@Test
	public void testICanWinDiagonallyUpLeftToRight() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(i);
			yellowAgent.moveOnColumn(i + 1);
			redAgent.moveOnColumn(i + 2);
			yellowAgent.moveOnColumn(i + 3);
		}

		assertEquals(3, redAgent.iCanWin());

	}

	// tests ICanWin() with a diagonal line that goes down from left to right
	@Test
	public void testICanWinDiagonallyDownLeftToRight() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		int numCols = game.getColumnCount() - 1;
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(numCols - i);
			yellowAgent.moveOnColumn(numCols - i - 1);
			redAgent.moveOnColumn(numCols - i - 2);
			yellowAgent.moveOnColumn(numCols - i - 3);
		}

		assertEquals(3, redAgent.iCanWin());
	}

	// tests TheyCanWin() with vertical rows of tokens
	@Test
	public void testTheyCanWin() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(1);
			yellowAgent.moveOnColumn(2);
		}

		assertEquals(2, redAgent.theyCanWin());
	}

	// test TheyCanWin() with horizontal rows of tokens
	@Test
	public void testTheyCanWinHorizontally() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(i);
			yellowAgent.moveOnColumn(i);
		}

		assertEquals(3, yellowAgent.theyCanWin());
	}

	// test TheyCanWin with diagonal row of tokens
	@Test
	public void testTheyCanWinDiagonally() {
		MyAgent redAgent = new MyAgent(game, true);
		MyAgent yellowAgent = new MyAgent(game, false);
		game.clearBoard();
		for (int i = 0; i < 3; i++) {
			redAgent.moveOnColumn(i);
			yellowAgent.moveOnColumn(i + 1);
			redAgent.moveOnColumn(i + 2);
			yellowAgent.moveOnColumn(i + 3);
		}

		assertEquals(3, yellowAgent.theyCanWin());
	}

	// Tests you can win against a Beginner agent as Red
	@Test
	public void testRedWinningBeginnerAgent() {
		Agent redAgent = new MyAgent(game, true);
		Agent yellowAgent = new BeginnerAgent(game, false);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'R') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Red against Beginner");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);
	}

	// Tests you can win against a Beginner agent as Yellow
	@Test
	public void testYellowWinningBeginnerAgent() {
		Agent redAgent = new BeginnerAgent(game, true);
		Agent yellowAgent = new MyAgent(game, false);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Yellow against Beginner");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);
	}

	// Tests you can win against a Random agent as Red
	@Test
	public void testRedWinningRandomAgent() {
		Agent redAgent = new MyAgent(game, true);
		Agent yellowAgent = new RandomAgent(game, false);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'R') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Red against Random");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);
	}

	// Tests you can win against a Random agent as Yellow
	@Test
	public void testYellowWinningRandomAgent() {
		Agent redAgent = new RandomAgent(game, true);
		Agent yellowAgent = new MyAgent(game, false);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Yellow against Random");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);
	}

	// BONUS TODO: Write testCases to play against IntermediateAgent
	@Test
	public void testRedWinningIntermediateAgent() {
		Agent redAgent = new MyAgent(game, true);
		Agent yellowAgent = new IntermediateAgent(game, true);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Red against Intermediate");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);

	}

	@Test
	public void testYellowWinningIntermediateAgent() {
		Agent redAgent = new IntermediateAgent(game, true);
		Agent yellowAgent = new MyAgent(game, true);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as against Intermediate");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);

	}

	// SUPER BONUS TODO: Write testCases to playAgainst AdvancedAgent
	@Test
	public void testRedWinningAdvancedAgent() {
		Agent redAgent = new MyAgent(game, true);
		Agent yellowAgent = new AdvancedAgent(game, true);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Red against Advanced");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);

	}

	@Test
	public void testYellowWinningAdvancedAgent() {
		Agent redAgent = new AdvancedAgent(game, true);
		Agent yellowAgent = new MyAgent(game, true);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Yellow against Advanced");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);

	}

	// SUPER BONUS TODO: Write testCases to playAgainst BrilliantAgent
	@Test
	public void testRedWinningBrilliantAgent() {
		Agent redAgent = new MyAgent(game, true);
		Agent yellowAgent = new BrilliantAgent(game, true);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Red against Brilliant");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);

	}

	@Test
	public void testYellowWinningBrilliantAgent() {
		Agent redAgent = new BrilliantAgent(game, true);
		Agent yellowAgent = new MyAgent(game, true);
		int numberOfWins = 0;
		for (int i = 0; i < NUM_OF_TEST_GAMES; i++) {
			game.clearBoard();
			while (!game.boardFull() && game.gameWon() == 'N') {
				redAgent.move();
				if (game.gameWon() != 'R') {
					yellowAgent.move();
				}
			}

			if (game.gameWon() == 'Y') {
				numberOfWins++;
			}
		}
		System.out.println("You won: " + numberOfWins + " games as Yellow against Brilliant");
		// Test that you win over 90% of your games
		assertTrue(numberOfWins >= 45);

	}

}
