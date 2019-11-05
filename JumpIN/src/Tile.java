//Class written by Ashton and Andrew

/**
 * Class for Tile
 * @author (of JavaDoc comments) Nicholas
 */
public class Tile {
	private boolean empty;
	private boolean grass;
	private GamePiece ontop;
	private int x, y;
	
	/**
     * Tile constructor
     * @param x X-coordinate of tile
     * @param y Y-coordinate of tile
     */
	public Tile(int x, int y) {
		empty = true;
		ontop = null;
		//Corner holes
		if((x % 4) == 0 && (y % 4) == 0) grass = false;
		//Center Hole
		else if(x==2 && y == 2) grass = false;
		else grass = true;
		this.x = x;
		this.y = y;
	}
	
	/**
     * Method setOnTop places a game piece
     * on top of the tile
     * @param g GamePiece being set on tile
     */
	public void setOnTop(GamePiece g) {
		ontop = g;
		empty = false;
	}
	
	/**
     * Method getOnTop gets game piece
     * that is on top of tile
     * @return GamePiece that is on top of tile
     */
	public GamePiece getOnTop() {
		return this.ontop;
	}
	
	/**
     * Method setEmpty sets tile to empty
     */
	public void setEmpty() {
		empty = true;
		ontop = null;
	}
	
	/**
     * This function checks if there is currently a 
	 * piece on this tile. This method will primarily
	 * be used for the purpose of testing if a collision
	 * would occur when moving a piece.
	 * 
     * @return boolean representing if tile is empty
     */
	public boolean isEmpty() {
		return this.empty;
	}
	
	/**
     * Method getGrass checks if tile is a grass tile
     * @return boolean representing if tile is grass
     */
	public boolean getGrass() {
		return this.grass;
	}
	
	/**
	 * 
	 * @return the tile's x position
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * 
	 * @return the tile's y position
	 */
	public int getY() {
		return this.y;
	}
	
	/**
     * Method printTile prints a text representation of tiles
     * by showing if the tile is a hole, grass, or game piece.
     */
	public void printTile() {
		if(empty) {
			if(grass) {
				System.out.print("[ ]");
			}else System.out.print("[O]");		
		}
		else {
			System.out.print("[" + ontop.getAcronym() + "]");
		}
	}
	
	/**
     * Tile toString method
     * @return String representing tile
     */
	public String toString() {
		if(empty) {
			if(grass) return "[ ]";
			else return "[O]";		
		}
		else {
			return ("[" + ontop.getAcronym() + "]");
		}
	}
	
	/**
	 * Check if tile objects are 
	 * equal
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
		
		Tile o = (Tile) obj;
		
		if (this.empty != o.empty) {
			return false;
		}
		
		if (!(this.grass != o.grass)) {
			return false;
		}
		
		if (!(this.ontop.equals(o.ontop))) {
			return false;
		}
		
		return true;
	}
}