//Class written by Ashton and Andrew

public class Fox extends GamePiece {
	private boolean upDown;
	private int backX, backY;
	
	public Fox(String s, int xpos, int ypos, boolean direction) {
		//The x and y positions that will be stored will be the smaller values (closer to (0, 0))
		//This means when going from 
		super(xpos, ypos, 2, s);
		upDown = direction;
		if(direction) {
			backX = x;
			backY = y + 1;
		}
		else {
			backX = x + 1;
			backY = y;
		}
		System.out.println(backX + "" + backY);
	}
	
	public boolean getUpDown() {
		return upDown;
	}
	
	public int getBackX() {
		return backX;
	}
	
	public int getBackY() {
		return backY;
	}

	@Override
	public String toString() {
		return super.getName();
	}
	
	@Override
	public void move(int direction) {
		if((upDown && (direction == 0 || direction == 2)) || 
				(!upDown && (direction == 1 || direction == 3))){
			super.move(direction);
			switch(direction) {
				case 0: 
					if(this.y != 0 && upDown) this.backY = this.backY - 1; // moving up
					break;
				case 1: 
					if(this.x != GameBoard.SIZE && !upDown) this.backX = this.backX + 1; // moving right
					break;
				case 2: 
					if(this.y != GameBoard.SIZE && upDown) this.backY = this.backY + 1; // moving down
					break;
				case 3: 
					if(this.x != 0 && !upDown) this.backX = this.backX - 1; // moving left
					break;
			}
		}
		else System.out.println("Cannot move this fox in that direction.");
	}
}