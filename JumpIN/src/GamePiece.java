import java.util.ArrayList;

//Class written by Ashton and Andrew

/**
 * Abstract class GamePiece
 * @author (of JavaDoc comments) Nicholas 
 * 
 */
public abstract class GamePiece {
	protected int x;
	protected int y;
	private int size;
	private String name;
	private ArrayList<Tile> memory;
	
	/**
     *      GamePiece constructor
     * @param x This is the piece's x-coordinate
     * @param y This is the piece's y-coordinate
     * @param size Describes the number of tiles the piece covers
     * @param name The name of the game piece
     */
	protected GamePiece(int x, int y, int size, String name) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.name = name;
		this.memory = new ArrayList<Tile>();
	}
	public ArrayList<Tile> getMemory(){
		return memory;
	}
	public void addToMemory(Tile lastTile) {
		memory.add(lastTile);
	}
	public void clearMemory() {
		for(int i = 0; i< memory.size(); i++) {
			memory.remove(0);
		}
	}
	public void printMemory() {
		if(memory.size()>0) {
			System.out.println("Last Tile: "+ memory.get(0).getX() + ", " + memory.get(0).getY());
		}
	}
	/**
     * Get game piece x-coordinate
     * @return int value representing x-coordinate 
     */
	public int getX() {
		return x;
	}
	
	 /**
     * Get game piece y-coordinate
     * @return int value representing y-coordinate
     */
	public int getY() {
		return y;
	}
	
	/**
     * Get game piece size
     * @return int value representing size of game piece
     */
	public int getSize() {
		return size;
	}
	
	/**
     * Get the name of the game piece
     * @return String representing the name of the game piece
     */
	public String getName(){
		return this.name;
	}
	
	/**
     * toString method for game piece
     * @return String representing game piece name
     */
	public String toString() {
		return this.name;
	}
	
	/**
	 * This will be used for printing a String representation of
	 * the board. The piece will return a character that represents
	 * it on the board.
	 * @return a char representation of the piece.
	 */
	public abstract char getAcronym();

	/**
	 * 
	 * @return if a given piece is able to move.
	 */
	public abstract boolean canMove();
	
	/**
	 * 
	 * @param x the x position the piece is trying to move to.
	 * @param y the y position the piece is trying to move to.
	 * @return true if the piece is able to move to this position.
	 */
	public abstract boolean canMove(int x, int y, Tile[][] tiles);

	/**
	 * Allows the piece to draw itself on the GUI
	 * @param square the array of Buttons in the GUI.
	 */
	public abstract void placePiece(JumpInButton[][] square);
	
	/**
	 * check if GamePiece objects
	 * are equal
	 * @param obj
	 * @return boolean
	 */
	@Override 
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		GamePiece o = (GamePiece) obj;
		
		if (this.x != o.x) {
			return false;
		}
		
		if(this.y != o.y) {
			return false;
		}
		
		if(this.size != o.size) {
			return false;
		}
		
		if((this.name == null) ? (o.name != null) : !this.name.equals(o.name)) {
			return false;
		}
		
		return true;
  }
}

