import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Nicholas Porter
 * Testing Game Functions using Game Board Two
 * 
 */

public class GameBoardTwoTest {
	private ArrayList<GamePiece> pieces;
	private Game jumpin = null;
	private GameBoard testGameBoard = null;
	

	
	@Before
	public void setUp() {
		jumpin = new Game();
		

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("src/saves"));
		String file = "test2.ser";
		jumpin.load(file);
		pieces = jumpin.getGameBoard().getPieces();
		testGameBoard = new GameBoard(pieces);
	}
	

	
	/**
	 * Perform undo action before any moves
	 * have been made. Check if pieces match up with
	 * InfoBook 0 to confirm that undo has not caused
	 * any unexpected changes
	 */
	@Test
	public void testUndoInvalid() {
		jumpin.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}
	
	/**
	 * Perform undo action after move Bunny1
	 * first check to make sure the board actually changed
	 * then perform undo and check if bunny is in the proper position
	 */
	@Test
	public void testUndo() {
		testGameBoard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 0);
		assertEquals(false, jumpin.getGameBoard().equals(testGameBoard)); // check that piece was moved from original position
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}
	
	/**
	 * Perform undo action after moving Fox1
	 * check to make sure undo was performed
	 * perform redo
	 * check if Bunny1 is in the expected position
	 */
	@Test
	public void testRedo() {
		testGameBoard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 0);
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check that undo was performed (piece should be back in original position)
		testGameBoard.redo();
		assertEquals(0, pieces.get(0).getX());
		assertEquals(1, pieces.get(0).getY());
		
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
		assertEquals(true, pieces.equals(jumpin.getPieces()));
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

