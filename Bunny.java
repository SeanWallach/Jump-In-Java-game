
public class Bunny extends GamePiece {
	private String name;
	
	public Bunny(String s, int xpos, int ypos) {
		name = s;
		x = xpos;
		y = ypos;
		fox = false;
		bunny = true;
		mushroom = false;
	}
	public void move() {
		
	}
	@Override
	public String getName(){
		return name;
	}
}
