//Class written by Ashton and Andrew

/**
 * Class for game piece Fox
 * @author (of JavaDoc comments) Nicholas
 */
public class Fox extends GamePiece {
	private boolean upDown;
	private int backX, backY;
	
	/**
	 * Fox constructor
	 * @param s Name of game piece
	 * @param xpos X-coordinate of Fox
	 * @param ypos Y-coordinate of Fox
	 * @param direction Boolean value representing if Fox is vertical or horizontal
	 */
	public Fox(String s, int xpos, int ypos, boolean direction) {
		//The x and y positions that will be stored will be the smaller values (closer to (0, 0))
		//This means when going from 
		super(xpos, ypos, 2, s);
		upDown = direction;
		if(direction) {
			backX = x;
			backY = y + 1;
		}
		else {
			backX = x + 1;
			backY = y;
		}
		System.out.println(backX + "" + backY);
	}
	
	/**
	 * Method to get orientation (vertical/horizontal) of Fox
	 * @return boolean orientation of Fox
	 */
	public boolean getUpDown() {
		return upDown;
	}
	
	/**
	 * Method to get the x-coordinate of the back of the Fox (since the fox takes up 2 tiles)
	 * @return int X-coordinate value
	 */
	public int getBackX() {
		return backX;
	}
	
	/**
	 * Method to get the y-coordinate of the back of the Fox (since the fox takes up 2 tiles)
	 * @return int Y-coordinate value
	 */
	public int getBackY() {
		return backY;
	}

	/**
	 * Fox toString method
	 * @return String game piece name
	 */
	@Override
	public String toString() {
		return super.getName();
	}
	
	/**
	 * Move method for Fox
	 * @param direction Int value representing direction to move
	 */
	@Override
	public void move(int direction) {
		if((upDown && (direction == 0 || direction == 2)) || 
				(!upDown && (direction == 1 || direction == 3))){
			//Checking that the this fox can move in the direction the user is wanting it to.
			super.move(direction);
			//Have to move both the front and back of the fox.
			switch(direction) {
				case 0: 
					if(this.y != 0 && upDown) this.backY = this.backY - 1; // moving up
					break;
				case 1: 
					if(this.x != GameBoard.SIZE && !upDown) this.backX = this.backX + 1; // moving right
					break;
				case 2: 
					if(this.y != GameBoard.SIZE && upDown) this.backY = this.backY + 1; // moving down
					break;
				case 3: 
					if(this.x != 0 && !upDown) this.backX = this.backX - 1; // moving left
					break;
			}
		}
		else System.out.println("Cannot move this fox in that direction.");
	}
}