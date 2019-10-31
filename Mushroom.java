//Class written by Ashton and Andrew

/**
 * Class for game piece Mushroom
 * @author (for JavaDoc comments) Nicholas
 */
public class Mushroom extends GamePiece {
	
	/**
     * Mushroom constructor
     * @param s Name of game piece
     * @param xpos X-coordinate of Mushroom
     * @param ypos Y-coordinate of Mushroom
     */
	public Mushroom(String s, int xpos, int ypos) {
		super(xpos, ypos, 1, s);
	}
	
	/**
     * Move method for Mushroom
     * Mushroom can not move so although the method takes an
     * int value, the game piece does not move and the statement
     * "Mushrooms cannot move" is printed
     * @param direction
     */
	@Override
	public void move(int direction) {
		System.out.println("Mushrooms cannot move");
	}
	
	/**
     * Mushroom toString method
     * @return String name of game piece
     */
	@Override
	public String toString() {
		return super.getName();
	}
}