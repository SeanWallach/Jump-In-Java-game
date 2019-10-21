//Class written by Ashton and Andrew

/**
 * Class for game piece Bunny
 * @author (of Javadoc comments) Nicholas
 * 
 */
public class Bunny extends GamePiece {
	
	/**
	 * Bunny constructor
	 * @param s Name of game piece
	 * @param xpos X-coordinate of Bunny
	 * @param ypos Y-coordinate of Bunny
	 */
	public Bunny(String s, int xpos, int ypos) {
		super(xpos, ypos, 1, s);
	}
	
	/**
	 * Bunny toString method
	 * @return String game piece name
	 */
	@Override
	public String toString() {
		return super.getName();
	}
	

	/**
	 * Move Bunny method
	 * @param direction Int value representing direction to move
	 */
	@Override
	public void move(int direction) {
		super.move(direction);
	}
}