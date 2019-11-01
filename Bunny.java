//Class written by Ashton and Andrew

/**
 * Class for game piece Bunny
 * @author (of Javadoc comments) Nicholas
 * 
 */
public class Bunny extends MovableGamePiece {
	
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
	 * Moving a bunny piece.
	 * @param newX the x value it is trying to move to.
	 * @param newY the y value it is trying to move to.
	 * @param tiles 
	 */
	@Override
	public void move(int newX, int newY, Tile[][] tiles) {
		if(!this.canMove(newX, newY)){
			System.out.println("This is not a valid move for a bunny.");
			return;
		}
		
		//Have the tiles remove the bunny piece then replace it in its new position.
		tiles[this.x][this.y].setEmpty();
		super.setX(newX);
		super.setY(newY);
		tiles[this.x][this.y].setOnTop(this); 
	}
	
	/**
	 * Testing that the attempted move is a valid move for the bunny.
	 * 
	 * @param newX the x value of the new position
	 * @param newY the y value of the new position
	 * @return if it is a valid move for the bunny
	 */
	private boolean canMove(int newX, int newY) {
		//Bunnies cannot move on angles.
		if(newX != this.x && newY != this.y) return false;
		//Bunnies must hop another piece.
		if(Math.abs(newX - this.x) < 2 && Math.abs(newY - this.y) < 2) return false;
		
		return true;
	}
}