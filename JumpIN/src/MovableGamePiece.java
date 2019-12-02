import java.util.ArrayList;

/**
 * A MovableGamePiece is an extension of GamePiece
 * that is able to move. This will include Foxes and
 * Bunnies, as they are able to move.
 * 
 * @author Ashton
 *
 */
public abstract class MovableGamePiece extends GamePiece{
	protected int solverStartingX, solverStartingY;
	protected ArrayList<int[]> solverPositions;
	
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
		solverStartingX = -1;
		solverStartingY = -1;
		solverPositions = new ArrayList<>();
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
	
	public boolean solverMove(int x, int y, Tile[][] tiles) {
		boolean moved = false;
		
		if(!solverPositions.contains(new int[] {x, y})) {
			moved = move(x, y, tiles);
			if(moved) solverPositions.add(new int[] {x, y});
		}

		return moved;
	}
	
	public void resetSolver() {
		this.solverPositions.clear();
		this.solverStartingX = x;
		this.solverStartingY = y;
		solverPositions.add(new int[] {x, y});
	}
	
	public abstract boolean solverVisited(int x, int y);
	
	public int getSolverStartingX() {
		return this.solverStartingX;
	}
	
	public int getSolverStartingY() {
		return this.solverStartingY;
	}
}