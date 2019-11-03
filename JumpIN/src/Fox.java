//Class written by Ashton and Andrew

/**
 * Class for game piece Fox
 * @author (of JavaDoc comments) Nicholas
 */
public class Fox extends MovableGamePiece {
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
	 * Moving a fox piece.
	 * @param newX the x value it is trying to move to.
	 * @param newY the y value it is trying to move to.
	 * @param tiles 
	 */
	@Override
	public void move(int newX, int newY, Tile[][] tiles) {
		if(!this.canMove(newX, newY)) {
			System.out.println("Fox cannot move to the new position.");
			return;
		}
		
		//Have the tiles remove the old fox position.
		tiles[this.x][this.y].setEmpty();
		tiles[this.backX][this.backY].setEmpty();
		
		//Move the fox piece.
		if(this.upDown) {
			if(this.y > newY) {
				super.setY(newY);
				this.backY = newY + 1;
			}
			else {
				this.backY = newY;
				super.setY(newY - 1);
			}
		}
		else {
			if(this.x > newX) {
				super.setX(newX);
				this.backX = newX + 1;
			}
			else {
				this.backX = newX;
				super.setX(newX - 1);
			}
		}
		//Place the new fox location on the tiles.
		tiles[this.x][this.y].setOnTop(this);
		tiles[this.backX][this.backY].setOnTop(this);
	}
	
	/**
	 * This method checks that the passed in X and Y values are
	 * a legal move for the fox. 
	 * Rules of a fox: cannot jump and can only move vertically
	 * or horizontally depending on the setup of the fox.
	 * 
	 * @param newX the X position the fox will move to.
	 * @param newY the Y position the fox will move to.
	 * @return if the new position is a valid move.
	 */
	private boolean canMove(int newX, int newY) {
		//First testing that the fox is moving in a valid direction.
		if(upDown && (newX != this.x)) return false;
		else if(!upDown && (newY != this.y)) return false;
		
		//Next, testing that the fox does not jump. Foxes can only
		//move one square at a time.
		if(upDown) {
			if(newY < this.y - 1) return false;
			else if(newY > this.backY + 1) return false;
		}
		else {
			if(newX < this.x - 1) return false;
			else if(newX > this.backX + 1) return false;
		}
		//CommentExampleComment
		return true;
	}
	
	/**
	 * Return a single char to represent that the piece is a Fox.
	 */
	@Override
	public char getAcronym() {
		return 'F';
	}

	@Override
	protected boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}
}