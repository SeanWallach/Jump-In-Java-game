//Class written by Ashton and Andrew

/**
 * Class for MoveEvent
 * @author (of JavaDoc comments) Nicholas
 */
public class MoveEvent {
	private int startingX, startingY, direction;
	private GamePiece source;
	
	/**
     * MoveEvent constructor
     * @param startingX Starting x-coordinate
     * @param startingY Starting y-coordinate
     * @param direction Direction of move
     * @param source Game piece being moved
     */
	public MoveEvent(int startingX, int startingY, int direction, GamePiece source) {
		this.source = source;
		this.startingX = startingX;
		this.startingY = startingY;
		//Direction contains the direction in which the piece is moving.
		//0 for up, 1 for right, 2 for down and 3 for left.
		this.direction = direction;
	}

	/**
     * Method getStartingX gets the starting
     * x-coordinate
     * @return int X-coordinate
     */
	public int getStartingX() {
		return startingX;
	}

	/**
     * Method getStartingY gets the starting
     * y-coordinate
     * @return int Y-coordinate
     */
	public int getStartingY() {
		return startingY;
	}

	/**
     * Method getSources gets the 
     * game piece
     * @return source Game piece
     */
	public GamePiece getSource() {
		return source;
	}
	
	/**
     * Method getDirection gets the 
     * direction the game piece is moving
     * @return direction game piece is moving
     */
	public int getDirection() {
		return direction;
	}
}
