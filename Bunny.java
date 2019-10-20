//Class written by Ashton and Andrew

public class Bunny extends GamePiece {
	
	public Bunny(String s, int xpos, int ypos) {
		super(xpos, ypos, 1, s);
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
	
	@Override
	public void move(int direction) {
		super.move(direction);
	}
}