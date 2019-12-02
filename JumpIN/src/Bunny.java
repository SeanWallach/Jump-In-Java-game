import java.awt.Color;

import javax.swing.JOptionPane;

//Class written by Ashton and Andrew

/**
 * Class for game piece Bunny
 * @author (of Javadoc comments) Nicholas
 * 
 */
public class Bunny extends MovableGamePiece {
	
	/**
	 * Bunny constructor
	 * @param s Name of game piece
	 * @param xpos X-coordinate of Bunny
	 * @param ypos Y-coordinate of Bunny
	 */
	public Bunny(String s, int xpos, int ypos) {
		super(xpos, ypos, 1, s);
	}
	
	/**
	 * Bunny toString method
	 * @return String game piece name
	 */
	@Override
	public String toString() {
		return super.getName();
	}

	/**
	 * Moving a bunny piece.
	 * @param newX the x value it is trying to move to.
	 * @param newY the y value it is trying to move to.
	 * @param tiles 
	 */
	@Override
	public boolean move(int newX, int newY, Tile[][] tiles) {
		if(!this.canMove(newX, newY, tiles)){
			JOptionPane.showMessageDialog(null, "This is not a valid move for a bunny.");
			return false;
		}
		
		//Have the tiles remove the bunny piece then replace it in its new position.
		tiles[this.x][this.y].setEmpty();
		super.setX(newX);
		super.setY(newY);
		tiles[this.x][this.y].setOnTop(this); 
		return true;
	}
	
	/**
	 * Testing that the attempted move is a valid move for the bunny.
	 * 
	 * @param newX the x value of the new position
	 * @param newY the y value of the new position
	 * @return if it is a valid move for the bunny
	 */
	@Override
	public boolean canMove(int newX, int newY, Tile[][] tiles) {
		if(!tiles[newX][newY].isEmpty()) return false;
		//Bunnies cannot move on angles.
		if(newX != this.x && newY != this.y) return false;
		//Bunnies must hop another piece.
		if(Math.abs(newX - this.x) < 2 && Math.abs(newY - this.y) < 2) return false;
		
		if(newY < y) {
			for(int i = y; i > newY; i--) {
				if(tiles[x][i].isEmpty()) return false;
			}
		}
		else if(newY > y) {
			for(int i = y; i < newY; i++) {
				if(tiles[x][i].isEmpty()) return false;
			}
		}
		else if(newX > x) {
			for(int i = x; i < newX; i++) {
				if(tiles[i][y].isEmpty()) return false;
			}
		}
		else {
			for(int i = x; i > newX; i--) {
				if(tiles[i][y].isEmpty()) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Return a single char to represent that the piece is a Bunny.
	 */
	@Override
	public char getAcronym() {
		return 'B';
	}
	
	/**
	 * Update the GUI by placing this piece on the GUI.
	 */
	@Override
	public void placePiece(JumpInButton[][] square) {
		square[this.x][this.y].setBackground(Color.GRAY);
	}
	
	@Override
	public boolean solverVisited(int x, int y) {
		for(int[] i : solverPositions) {
			if(i[0] == x && i[1] == y) return true;
		}
		return false;
	}
	
}
