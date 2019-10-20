//Class written by Ashton and Andrew

public class Mushroom extends GamePiece {
	
	public Mushroom(String s, int xpos, int ypos) {
		super(xpos, ypos, 1, s);
	}
	
	@Override
	public void move(int direction) {
		System.out.println("Mushrooms cannot move");
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
}