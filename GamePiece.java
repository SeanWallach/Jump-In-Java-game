//Class written by Ashton and Andrew

public abstract class GamePiece {
	protected int x;
	protected int y;
	private int size;
	private String name;
	
	protected GamePiece(int x, int y, int size, String name) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.name = name;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * When a rabbit collides with this GamePiece, it attempts to jump over
	 * this piece to find the next empty square in the direction it is moving.
	 * 
	 * If it has hit a wall, it turns back and will end up in the same position
	 * in which it had started.
	 * 
	 * @param m the MoveEvent created by the rabbit moving.
	 */
	public void handleBunnyCollision(MoveEvent m) {
		//For each case, the rabbit continues moving in the given direction
		//unless it has reached a wall, in which case it has to turn back.
		switch(m.getDirection()) {
			case 0: 
				if(this.y == 0) m.getSource().move(2);
				else m.getSource().move(0);
				break;
			case 1:
				if(m.getSource().getX() == GameBoard.SIZE - 1) m.getSource().move(3);
				else m.getSource().move(1);
				break;
			case 2:
				if(m.getSource().getY() == GameBoard.SIZE - 1) m.getSource().move(0);
				else m.getSource().move(2);
				break;
			case 3:
				if(this.x == 0) m.getSource().move(1);
				else m.getSource().move(3);
				break;
		}
			
	}
	
	/**
	 * When a fox collides with this piece, the fox cannot try to jump, so
	 * it has to be returned to its previous position, which can be obtained
	 * from the MoveObject.
	 * 
	 * @param m the MoveEvent that occurred as the collision occurred.
	 */
	public void handleFoxCollision(MoveEvent m) {
		//When a fox collides with another GamePiece, this is an illegal move.
		//The fox will be returned to the position it was in previously.
		int oppositeDirection;
		
		if(m.getDirection() > 1) {
			oppositeDirection = m.getDirection() - 2;
		}
		else {
			oppositeDirection = m.getDirection() + 2;
		}
		
		((Fox) m.getSource()).move(oppositeDirection);
	}
	
	/**
	 * Move this piece one position in the given direction.
	 * 
	 * @param direction 0 for up, 1 for right, 2 for down and 3 for left.
	 */
	public void move(int direction) {
		
		switch(direction) {
			case 0: 
				if(this.y == 0) System.out.println("Cannot move up.");
				else this.setY(this.y - 1); // moving up
				break;
			case 1: 
				if(this.x == GameBoard.SIZE - 1) System.out.println("Cannot move right.");
				else this.setX(this.x + 1); // moving right
				break;
			case 2: 
				if(this.y == GameBoard.SIZE - 1) System.out.println("Cannot move down.");
				else this.setY(this.y + 1); // moving down
				break;
			case 3: 
				if(this.x == 0) System.out.println("Cannot move left.");
				else this.setX(this.x - 1); // moving left
				break;
			default: 
				System.out.println("Illegal direction.");
				break;
		}
	}
	
	public String toString() {
		return this.name;
	}
}