
public class Mushroom extends GamePiece {
	private String name;
	
	public Mushroom(String s, int xpos, int ypos) {
		name = s;
		x = xpos;
		y = ypos;
		fox = false;
		bunny = false;
		mushroom = true;
	}
	public void move() {
		
	}
	@Override
	public String getName(){
		return name;
	}
}
