//Class written by Ashton and Andrew

public class MoveEvent {
	private int startingX, startingY, direction;
	private GamePiece source;
	
	public MoveEvent(int startingX, int startingY, int direction, GamePiece source) {
		this.source = source;
		this.startingX = startingX;
		this.startingY = startingY;
		//Direction contains the direction in which the piece is moving.
		//0 for up, 1 for right, 2 for down and 3 for left.
		this.direction = direction;
	}

	public int getStartingX() {
		return startingX;
	}

	public int getStartingY() {
		return startingY;
	}

	public GamePiece getSource() {
		return source;
	}
	
	public int getDirection() {
		return direction;
	}
}
