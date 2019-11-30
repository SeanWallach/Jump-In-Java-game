//Class written by Ashton and Andrew

import java.util.*;

/**
 * Class InfoBook
 * @author (of JavaDoc comments) Nicholas
 */
public class InfoBook {
	public static final int COUNT_BOARDS = 3;
	private ArrayList<GamePiece> pieces;
	
	/**
     * InfoBook constructor
     * Creating the pieces based on the gameVersion selected.
     */
	public InfoBook(int gameVersion) {
		pieces = new ArrayList<>();
		switch(gameVersion) {
			case 0:
				createGameZero();
				break;
			case 1:
				createGameOne();
				break;
			case 2:
				createGameTwo();
				break;
			default:
				System.out.println("This is not a valid game selection.");	
		}
	}
	
	/**
	 * A method to create the new game pieces.
	 */
	private void createGameZero() {
		pieces.clear();
		pieces.add(new Bunny("Bunny1", 3, 0));
		pieces.add(new Mushroom("Mushroom1", 3, 1));
		pieces.add(new Bunny("Bunny2", 4, 2));
		pieces.add(new Mushroom("Mushroom2", 2, 4));
		pieces.add(new Bunny("Bunny3", 1, 4));
		pieces.add(new Fox("Fox1", 1, 0, true));
		pieces.add(new Fox("Fox2", 3, 3, false));
	}
	
	/**
	 * A method to create the new game pieces.
	 */
	private void createGameOne() {
		pieces.clear();
		pieces.add(new Bunny("Bunny1", 2, 1));
		pieces.add(new Mushroom("Mushroom1", 1, 1));
		pieces.add(new Mushroom("Mushroom2", 2, 2));
		pieces.add(new Mushroom("Mushroom3", 3, 0));
		pieces.add(new Fox("Fox1", 3, 1, false));
	}
	
	/**
	 * A method to create the new game pieces.
	 */
	private void createGameTwo() {
		pieces.clear();
		pieces.add(new Bunny("Bunny1", 3, 0));
		pieces.add(new Mushroom("Mushroom1", 1, 2));
		pieces.add(new Mushroom("Mushroom2", 2, 2));
		pieces.add(new Mushroom("Mushroom3", 4, 2));
		pieces.add(new Fox("Fox1", 1, 1, false));
	}
	
	/**
	 * Return the pieces that have been created in order to fill in the game board.
	 * @return the pieces created.
	 */
	public ArrayList<GamePiece> getPieces(){
		return this.pieces;
	}
	
	/**
	 * Check if InfoBook objects
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
		
		InfoBook o = (InfoBook) obj;
		
		for (int i = 0; i < this.pieces.size(); i++) {
			if (!(this.pieces.get(i).equals(o.pieces.get(i)))) {
				return false;
			}
		}
		
		return true;
	}
}