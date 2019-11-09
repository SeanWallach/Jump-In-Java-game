import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Nicholas Porter
 * Testing that InfoBook is properly set up
 *
 */
public class InfoBookTest {
	private InfoBook testBook = null;
	private ArrayList<GamePiece> boardZeroPieces = null;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}

	/**
	 * Test that InfoBook is creating the correct game
	 * by comparing the game pieces of InfoBook instance to 
	 * an ArrayList of gamepieces containing the expected
	 * gamepieces for InfoBook game 0
	 */
	@Test
	public void testCreateGameZero() {
		boardZeroPieces = new ArrayList<>();
		boardZeroPieces.add(new Bunny("Bunny1", 3, 0));
		boardZeroPieces.add(new Mushroom("Mushroom1", 3, 1));
		boardZeroPieces.add(new Bunny("Bunny2", 4, 2));
		boardZeroPieces.add(new Mushroom("Mushroom2", 2, 4));
		boardZeroPieces.add(new Bunny("Bunny3", 1, 4));
		boardZeroPieces.add(new Fox("Fox1", 1, 0, true));
		boardZeroPieces.add(new Fox("Fox2", 3, 3, false));
		testBook = new InfoBook(0);
		assertEquals(true, testBook.getPieces().equals(boardZeroPieces));
	}
	
	/**
	 * Testing for invalid puzzle number
	 * when creating a new InfoBook
	 */
	@Test
	public void testInvalidGameVersion() {
		testBook = new InfoBook(10);
		assertEquals(true, ("This is not a valid game selection." + System.lineSeparator()).equals(outContent.toString()));
	}

}