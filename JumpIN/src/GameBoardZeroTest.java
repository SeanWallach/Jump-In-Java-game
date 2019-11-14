import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.io.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;


/**
 * 
 * @author Nicholas Porter
 * Testing game board 0
 *
 */
public class GameBoardZeroTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private Game jumpin = null;
	private InfoBook testBookZero = null;
	private GameBoard testGameBoard = null;
	
	@Rule
	public final TextFromStandardInputStream input
		= emptyStandardInputStream();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		testBookZero = new InfoBook(0);
		input.provideLines("0");
		jumpin = new Game(0);
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	
	/**
	 * Testing that the proper puzzle
	 * has been selected by comparing 
	 * current gameboard to InfoBook0
	 */
	@Test
	public void testGetPieces() {
		assertEquals(true, testBookZero.getPieces().equals(jumpin.getPieces()));
	}
	
	/**
	 * Test that the game is running
	 */
	@Test
	public void testGetRunning() {
		assertEquals(true, jumpin.getRunning());
	}
	
	/**
	 * Test the gameboard has been 
	 * correctly set up by comparing
	 * to expected gameboard
	 */
	@Test
	public void TestGetGameBoard() {
		testGameBoard = new GameBoard(testBookZero.getPieces());
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}

}