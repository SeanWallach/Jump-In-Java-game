//Class written by Ashton and Andrew

/**
 * Abstract class GamePiece
 * 
 * @author (of JavaDoc comments) Nicholas
 * 
 */
public abstract class GamePiece {
	protected int x;
	protected int y;
	private int size;
	private String name;

	/**
	 * GamePiece constructor
	 * 
	 * @param x    This is the piece's x-coordinate
	 * @param y    This is the piece's y-coordinate
	 * @param size Describes the number of tiles the piece covers
	 * @param name The name of the game piece
	 */
	protected GamePiece(int x, int y, int size, String name) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.name = name;
	}

	/**
	 * Get game piece x-coordinate
	 * 
	 * @return int value representing x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get game piece y-coordinate
	 * 
	 * @return int value representing y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get game piece size
	 * 
	 * @return int value representing size of game piece
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Get the name of the game piece
	 * 
	 * @return String representing the name of the game piece
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * toString method for game piece
	 * 
	 * @return String representing game piece name
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * This method is used to place this piece on the board of tiles.
	 * 
	 * @param tiles the current board of tiles.
	 */
	public void placeOnTiles(Tile[][] tiles) {
		tiles[x][y].setOnTop(this);
	}

	/**
	 * This will be used for printing a String representation of the board. The
	 * piece will return a character that represents it on the board.
	 * 
	 * @return a char representation of the piece.
	 */
	public abstract char getAcronym();
}