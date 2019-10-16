
public class Tile {
	private boolean empty;
	private boolean grass;
	private Object ontop;
	
	public Tile(int x, int y) {
		empty = true;
		ontop = null;
		//Top left Hole
		if(x==0 && y == 0) {
			grass = false;
		}
		//Top right Hole
		else if(x==0 && y==4) {
			grass = false;
		}
		//Center Hole
		else if(x==2 && y == 2) {
			grass = false;
		}
		//Bottom left Hole
		else if(x==4 && y==0) {
			grass = false;
		}
		//Bottom right Hole
		else if(x==4 && y==4) {
			grass = false;
		}
		else grass = true;
	}
	public void setOnTop(Object o) {
		ontop = o;
	}
	public void setFull() {
		empty = false;
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
}
