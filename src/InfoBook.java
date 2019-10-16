import java.util.ArrayList;

public class InfoBook {
	private ArrayList<int[]> setups;
	private ArrayList<int[]> solutions;
	
	public InfoBook() {
		setups = new ArrayList<int[]>();
		int[] positions1 = {3,0,3,1,4,2,2,4,1,4,5,5,1,0,1,3,3,0} ;
		setups.add(positions1);
		solutions = new ArrayList<int[]>();
	}
	
	public int[] getSetup(int index) {
		return setups.get(index);
	}
}
