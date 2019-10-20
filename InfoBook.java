//Class written by Ashton and Andrew

import java.util.ArrayList;

public class InfoBook {
	private ArrayList<int[]> setups;
	private ArrayList<int[]> solutions;
	
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
		10: Fox1 X
		11: Fox1 Y
		12: Fox1 isVertical
		13: Fox2 X
		14: Fox2 Y
		15: Fox2 isVertical
		*/
		int[] positions1 = {3,0,3,1,4,2,2,4,1,4,1,0,1,3,3,0} ;
		setups.add(positions1);
		solutions = new ArrayList<int[]>();
	}
	
	public int[] getSetup(int index) {
		return setups.get(index);
	}
}