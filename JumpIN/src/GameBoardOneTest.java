import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

/**
 * 
 * @author Nicholas Porter
 * Testing Game functions using Game Board One
 *
 */

public class GameBoardOneTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private Game jumpin = null;
	private InfoBook testBookOne = null;
	private GameBoard testGameBoard = null;
	private GamePiece testPiece = null;
	
	@Rule
	public final TextFromStandardInputStream input
		= emptyStandardInputStream();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		testBookOne = new InfoBook(1);
		input.provideLines("1");
		jumpin = new Game(1);
		testGameBoard = new GameBoard(testBookOne.getPieces());
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	/**
	 * Perform undo action before any moves
	 * have been made. Check if pieces match up with
	 * InfoBook 0 to confirm that undo has not caused
	 * any unexpected changes
	 */
	@Test
	public void testUndoInvalid() {
		testGameBoard.undo();
		testGameBoard = new GameBoard(testBookOne.getPieces());
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}
	
	/**
	 * Perform undo action after move Bunny1
	 * first check to make sure the board actually changed
	 * then perform undo and check if bunny is in the proper position
	 */
	@Test
	public void testUndo() {
		testGameBoard.movePiece(testBookOne.getPieces().get(0).getX(), testBookOne.getPieces().get(0).getY(), 2);
		assertEquals(false, jumpin.getGameBoard().equals(testGameBoard)); // check that piece was moved from original position
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}
	
	/**
	 * Perform undo action after moving Bunny1
	 * check to make sure undo was performed
	 * perform redo
	 * check if Bunny1 is in the expected position
	 */
	@Test
	public void testRedo() {
		testGameBoard.movePiece(testBookOne.getPieces().get(0).getX(), testBookOne.getPieces().get(0).getY(), 2);
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check that undo was performed (piece should be back in original position)
		testGameBoard.redo();
		assertEquals(2, testBookOne.getPieces().get(0).getX());
		assertEquals(3, testBookOne.getPieces().get(0).getY());
		
	}
	
	/* solve is not yet implemented 
	@Test
	public void testSolve() {
		testGameBoard.solve();
		assertEquals(false, jumpin.getRunning());
	}
	*/
	
	/**
	 * Testing that the proper puzzle
	 * has been selected by comparing 
	 * current gameboard to InfoBook0
	 */
	@Test
	public void testGetPieces() {
		assertEquals(true, testBookOne.getPieces().equals(jumpin.getPieces()));
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
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}

}
