
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestBunny {
	private ArrayList<GamePiece> pieces;
	private InfoBook book;
	private GameBoard gameboard;

	// Puzzle 2:
	// [O][ ][ ][B][O]
	// [ ][F][F][ ][ ]
	// [ ][M][M][ ][M]
	// [ ][ ][ ][ ][ ]
	// [O][ ][ ][ ][O]

	// 0: Bunny1 at (3, 0)
	// 1: Mushroom1 at (1, 2)
	// 2: Mushroom2 at (2, 2)
	// 3: Mushroom3 at (4, 2)
	// 4: Fox1 at (1, 1)

	// 0: up
	// 1: right
	// 2: down
	// 3: left

	// Puzzle 2 after Fox1 moved right:
	// [O][ ][ ][B][O]
	// [ ][ ][F][F][ ]
	// [ ][M][M][ ][M]
	// [ ][ ][ ][ ][ ]
	// [O][ ][ ][ ][O]

	@Before
	public void setUp() throws Exception {
		book = new InfoBook(2);
		this.pieces = book.getPieces();
		gameboard = new GameBoard(pieces);

		// Move Fox1 to the right to set up for test
		gameboard.movePiece(pieces.get(4).getX(), pieces.get(4).getY(), 1);
	}

	// Case 1: jump over 1 to 3 Gamepiece and land within wall
	@Test
	public void testBunnyLegalMove() {
		// Move downward once. Case 1 with 1 Gamepiece
		gameboard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 2);
		assertEquals(3, pieces.get(0).getX());
		assertEquals(2, pieces.get(0).getY());

		// Move left. Case 1 with 2 Gamepiece
		gameboard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 3);
		assertEquals(0, pieces.get(0).getX());
		assertEquals(2, pieces.get(0).getY());
	}

	// Case 1: move without jumping over Gamepiece(s)
	// Case 2: jump over a Gamepiece and land on another one
	// Case 3: land outside wall after a move
	@Test
	public void testBunnyIllegalMove() {
		// Move left once . Case 1.
		gameboard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 3);
		assertEquals(3, pieces.get(0).getX());
		assertEquals(0, pieces.get(0).getY());

		// Move downward once
		// [O][ ][ ][ ][O]
		// [ ][ ][F][F][ ]
		// [ ][M][M][B][M]
		// [ ][ ][ ][ ][ ]
		// [O][ ][ ][ ][O]
		gameboard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 2);

		// Move right. Case 3
		gameboard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 1);
		assertEquals(3, pieces.get(0).getX());
		assertEquals(2, pieces.get(0).getY());

		// Move left. Case 2
		gameboard.movePiece(pieces.get(0).getX(), pieces.get(0).getY(), 3);
		assertEquals(0, pieces.get(0).getX());
		assertEquals(2, pieces.get(0).getY());

	}
}
