import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import org.junit.*;


/**
 * 
 * @author Nicholas Porter
 * Testing Game functions using Game Board Zero
 *
 */
public class GameBoardZeroTest {
	private ArrayList<GamePiece> pieces;
	private Game jumpin = null;
	private GameBoard testGameBoard = null;

	
	
	@Before
	public void setUp() {
		jumpin = new Game();
		

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("src/saves"));
		String file = "test0.ser";
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
		testGameBoard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 2);
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
		testGameBoard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 2);
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check that undo was performed (piece should be back in original position)
		testGameBoard.redo();
		assertEquals(3, pieces.get(0).getX());
		assertEquals(2, pieces.get(0).getY());
		
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
