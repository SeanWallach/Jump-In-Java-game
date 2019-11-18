import java.util.Stack;

/**
 * A MovableGamePiece is an extension of GamePiece
 * that is able to move. This will include Foxes and
 * Bunnies, as they are able to move.
 * 
 * @author Ashton
 *
 */
public abstract class MovableGamePiece extends GamePiece{

	/**
	 * MovableGamePiece is an extension of GamePiece that adds move functionality.
	 * 
	 * @param x the initial x position
	 * @param y the initial y position
	 * @param size the size of the piece (foxes are 2)
	 * @param name the name of the piece
	 */
	protected MovableGamePiece(int x, int y, int size, String name) {
		super(x, y, size, name);
	}
	
	/**
     * Set game piece x coordinate
     * @param x Int value representing x-coordinate
     */
	public void setX(int x) {
		super.x = x;
	}
	
	/**
     * Set game piece y coordinate
     * @param y Int value representing y-coordinate
     */
	public void setY(int y) {
		super.y = y;
	}
	
	/**
	 * Returns true because a bunny is a piece that can move.
	 */
	@Override
	public boolean canMove() {
		return true;
	}
	
	
	/**
	 * A requirement that all classes extending this class will create a move method.
	 * 
	 * @param newX the x it will move to.
	 * @param newY the y it will move to.
	 * @param tiles the board that the piece will place itself on.
	 */
	public abstract boolean move(int newX, int newY, Tile[][] tiles);
}
