//Class written by Ashton and Andrew

public class Tile {
	private boolean empty;
	private boolean grass;
	private GamePiece ontop;
	
	public Tile(int x, int y) {
		empty = true;
		ontop = null;
		//Corner holes
		if((x % 4) == 0 && (y % 4) == 0) grass = false;
		//Center Hole
		else if(x==2 && y == 2) grass = false;
		else grass = true;
	}
	
	public void setOnTop(GamePiece g) {
		ontop = g;
		empty = false;
	}
	
	public GamePiece getOnTop() {
		return this.ontop;
	}
	
	public void setEmpty() {
		empty = true;
		ontop = null;
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	public boolean getGrass() {
		return this.grass;
	}
	
	public void printTile() {
		if(empty) {
			if(grass) {
				System.out.print("[ ]");
			}else System.out.print("[O]");		
		}
		else {
			if(ontop.getClass() == Bunny.class) {
				System.out.print("[B]");
			}
			else if(ontop.getClass() == Mushroom.class) {
				System.out.print("[M]");
			}
			else if(ontop.getClass() == Fox.class) {
				System.out.print("[F]");
			}else System.out.print("ontop = "+ ontop);
		}
	}
	
	public String toString() {
		if(empty) {
			if(grass) return "[ ]";
			else return "[O]";		
		}
		else {
			return ontop.toString();
		}
	}
}