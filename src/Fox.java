
public class Fox extends GamePiece {
	private String name;
	
	public Fox(String s, int xpos, int ypos, int direction) {
		name = s;
		x = xpos;
		y = ypos;
		fox = true;
		bunny = false;
		mushroom = false;
		
		if(direction == 1) {
			updown = true;
		}else updown = false;		
	}
	
	public boolean getUpDown() {
		return updown;
	}
	@Override
	public String getName(){
		return name;
	}
	public void move() {
		
	}
}
