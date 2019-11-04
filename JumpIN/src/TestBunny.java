
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestBunny {
	private ArrayList<GamePiece> pieces;
	private InfoBook book;
	private GameBoard gameboard;

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
		book = new InfoBook(0);
		this.pieces = book.getPieces();
		gameboard = new GameBoard(pieces);
	}

	// Case 1: jump over 1 to 3 Gamepiece and land within wall

	@Test
	public void testBunnyMove() {
		fail("Not yet implemented");
	}

	// Case 1: move without jumping over Gamepiece(s)
	// Case 2: jump over a Gamepiece and land on another one
	// Case 3: land outside wall after a move

	@Test
	public void testBunnyMoveIllegal() {
		fail("Not yet implemented");
	}
}
