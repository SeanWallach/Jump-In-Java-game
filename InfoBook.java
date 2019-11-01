//Class written by Ashton and Andrew

import java.util.ArrayList;

/**
 * Class InfoBook
 * @author (of JavaDoc comments) Nicholas
 */
public class InfoBook {
	private ArrayList<int[]> setups;
	
	/**
     * InfoBook constructor
     * creating ArrayList of where on the game board
     * all the attributes are located
     */
	public InfoBook() {
		setups = new ArrayList<int[]>();
		/*setups follow the following orders
		Position: attribute
		0: Bunny1 X
		1: Bunny1 Y
		2: Mushroom1 X
		3: Mushroom1 Y
		4: Bunny2 X
		5: Bunny2 Y
		6: Mushroom2 X
		7: Mushroom2 Y
		8: Bunny3 X
		9: Bunny3 Y
		10: Mushroom3 X
		11: Mushroom3 Y
		12: Fox1 X
		13: Fox1 Y
		14: Fox1 isVertical
		15: Fox2 X
		16: Fox2 Y
		17: Fox2 isVertical
		*/
		int[] positions1 = {3,0,3,1,4,2,2,4,1,4,GameBoard.SIZE,GameBoard.SIZE,1,0,1,3,3,0} ;
		setups.add(positions1);
		int[] positions2 = {2,1,1,1,GameBoard.SIZE,GameBoard.SIZE,2,2,GameBoard.SIZE,GameBoard.SIZE,3,0,3,1,0,GameBoard.SIZE,GameBoard.SIZE,GameBoard.SIZE};
        setups.add(positions2);
        int[] positions3 = {3,0,1,2,GameBoard.SIZE,GameBoard.SIZE,2,2,GameBoard.SIZE,GameBoard.SIZE,4,2,1,1,0,GameBoard.SIZE,GameBoard.SIZE,GameBoard.SIZE};
        setups.add(positions3);
	}
	
	/**
     * Method getSetup gets the game setup
     * @param index Int value representing index
     * @return int[] array of game setup
     */
	public int[] getSetup(int index) {
		return setups.get(index);
	}
	
	/**
	 * A getter for the size of setup.
	 * 
	 * @return the current number of setups created.
	 */
	public int getSetupCount() {
		return this.setups.size();
	}
}