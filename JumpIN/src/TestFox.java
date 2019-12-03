
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.junit.Before;
import org.junit.Test;

public class TestFox {
	private ArrayList<GamePiece> pieces;
	private GameBoard testGameBoard = null;
	private Game jumpin = null;

	// Puzzle 0:
	// [O][F][ ][B][O]
	// [ ][F][ ][M][ ]
	// [ ][ ][O][ ][B]
	// [ ][ ][ ][F][F]
	// [O][B][M][ ][O]

	// 0: Bunny1 at (3, 0)
	// 1: Mushroom1 at (3, 1)
	// 2: Bunny2 at (4, 2)
	// 3: Mushroom2 at (2, 4)
	// 4: Bunny3 at (1, 4)
	// 5: Fox1 at (1, 0)
	// 6: Fox2 at (3, 3)

	// 0: up
	// 1: right
	// 2: down
	// 3: left
	@Before
	public void setUp() throws Exception {
		jumpin = new Game();
		

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("src/saves"));
		String file = "test0.ser";
		jumpin.load(file);
		pieces = jumpin.getGameBoard().getPieces();
		testGameBoard = new GameBoard(pieces);
	}

	// If fox's head and tail both on the same column, can only move vertically
	// Case 1: No gamepiece in path
	// Case 2: A gamepiece block the path
	// Case 3: Hit the wall
	@Test
	public void testFoxLegalVerticalMove() {
		// Move downward once . Case 1.
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 2);
		assertEquals(1, pieces.get(4).getX());
		assertEquals(1, pieces.get(4).getY());

	}

	@Test
	public void testFoxIllegalVerticalMove() {
		// Move left
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 3);
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check to see if any pieces moved
		//assertEquals(1, pieces.get(4).getX());
		//assertEquals(1, pieces.get(4).getY());

		// Move right
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 1);
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check to see if any pieces moved
		//assertEquals(1, pieces.get(4).getX());
		//assertEquals(1, pieces.get(4).getY());

		// Move upward once. Case 3
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 0);
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check to see if any pieces moved
		//assertEquals(1, pieces.get(4).getX());
		//assertEquals(1, pieces.get(4).getY());

		// Move downward 3 times. Case 2
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 2);
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 2);
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 2);
		assertEquals(1, pieces.get(4).getX());
		assertEquals(2, pieces.get(4).getY());

	}

	// If fox's head and tail both on the same row, can only move horizontally
	// Case 1: No gamepiece in path
	// Case 2: A gamepiece block the path
	// Case 3: Hit the wall
	@Test
	public void testFoxLegalHorizontalMove() {
		// Move left once. Case 1
		testGameBoard.movePiece(pieces.get(7).getX(), pieces.get(7).getY(), 3);
		assertEquals(2, pieces.get(7).getX());
		assertEquals(3, pieces.get(7).getY());

	}

	// Move fox 1 downward twice.
	// [O][ ][ ][B][O]
	// [ ][ ][ ][M][ ]
	// [ ][F][O][ ][B]
	// [ ][F][ ][F][F]
	// [O][B][M][ ][O]
	@Test
	public void testFoxIllegalHorizontalMove() {
		// Move upward.
		testGameBoard.movePiece(pieces.get(7).getX(), pieces.get(7).getY(), 0);
		//assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check to see if any pieces moved
		assertEquals(3, pieces.get(7).getX());
		assertEquals(3, pieces.get(7).getY());

		// Move downward.
		testGameBoard.movePiece(pieces.get(7).getX(), pieces.get(7).getY(), 2);
		//assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check to see if any pieces moved
		assertEquals(3, pieces.get(7).getX());
		assertEquals(3, pieces.get(7).getY());

		// Move right. Case 3
		testGameBoard.movePiece(pieces.get(7).getX(), pieces.get(7).getY(), 1);
		//assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check to see if any pieces moved
		assertEquals(3, pieces.get(7).getX());
		assertEquals(3, pieces.get(7).getY());

		// Move Fox1 downward to block Fox2
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 2);
		testGameBoard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 2);

		// Move left 2 times. Case 3
		testGameBoard.movePiece(pieces.get(7).getX(), pieces.get(7).getY(), 3);
		testGameBoard.movePiece(pieces.get(7).getX(), pieces.get(7).getY(), 3);
		assertEquals(2, pieces.get(7).getX());
		assertEquals(3, pieces.get(7).getY());
	}
}
