
//Class written by Ashton and Andrew

import java.util.ArrayList;

/**
 * Class GameBoard
 * @author (of JavaDoc comments) Nicholas
 */
public class GameBoard {
	public static final int SIZE = 5;
	private Tile[][] tiles;
	private ArrayList<GamePiece> boardpieces;
	
	/**
	 * GameBoard constructor
	 * create a game board
	 * @param p Arraylist of game pieces
	 */
	public GameBoard(ArrayList<GamePiece> p) {
		boardpieces = p;
		tiles = new Tile[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j] = new Tile(i, j);				
			}
		}
		
		for(GamePiece g: boardpieces) {
			int i = g.getX();
			int j = g.getY();
			tiles[i][j].setOnTop(g);
			if(g instanceof Fox) {
				if(((Fox) g).getUpDown())
					tiles[i][j + 1].setOnTop(g);
				else
					tiles[i + 1][j].setOnTop(g);
			}
		}
	}
	
	/**
	 * Method movePiece moves game piece in specified direction
	 * @param x X-coordinate to where game piece is moving
	 * @param y Y-coordinate to where game piece is moving
	 * @param direction Direction game piece is moving
	 */
	public void movePiece(int x, int y, int direction) {
		
		//Since the GamePieces don't know which other pieces are around them, this
		//class finds the empty position, then tells the piece to move to that
		//new position.
		int[] newCoord = this.getEmptyPosition(x, y, direction);
		
		if(newCoord[0] == x && newCoord[1] == y) {
			System.out.println("Cannot move in that direction.");
			return;
		}
		
		GamePiece g = tiles[x][y].getOnTop();
		
		if(!(g instanceof MovableGamePiece)) {
			System.out.println(g.toString() + " cannot move.");
			return;
		}
		
		//This downcasting is safe because of the previous if statement.
		MovableGamePiece m = (MovableGamePiece) g;
		
		m.move(newCoord[0], newCoord[1], this.tiles);
	}
	
	/**
	 * 
	 * @param currX
	 * @param currY
	 * @param direction
	 * @return
	 */
	private int[] getEmptyPosition(int currX, int currY, int direction) {
		int[] coord = new int[2];
		
		switch(direction) {
		case 0:
			//Checking for empty square upwards from current position.
			for(int j = currY - 1; j >= 0; j--) {
				if(this.tiles[currX][j].isEmpty()) {
					currY = j; 
					break;
				}
			}
			break;
		case 1:
			//Checking for empty square to the right of current position.
			for(int i = currX + 1; i < GameBoard.SIZE; i++) {
				if(this.tiles[i][currY].isEmpty()) {
					currX = i;
					break;
				}
			}
			break;
		case 2:
			//Checking for empty square downwards from the current position.
			for(int j = currY + 1; j < GameBoard.SIZE; j++) {
				if(this.tiles[currX][j].isEmpty()) {
					currY = j; 
					break;
				}
			}
			break;
		case 3:
			//Checking for empty square to the left of current position.
			for(int i = currX - 1; i >= 0; i--) {
				if(this.tiles[i][currY].isEmpty()) {
					currX = i;
					break;
				}
			}
			break;
		}
		
		coord[0] = currX;
		coord[1] = currY;
		return coord;
	}
	
	/**
	 * Method getTile gets the tile at the specified 
	 * coordinates
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @return Tile tile at specified x,y coordinate
	 */
	public Tile getTile(int x, int y) {
		return this.tiles[x][y];
	}
	
	/**
	 * Method printBoard prints the game board
	 */
	public void printBoard() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[j][i].printTile();
			}
			System.out.println("");
		}
	}
	
	/**
	 * Check if GameBoard objects are equal
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		GameBoard o = (GameBoard) obj;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (!(this.getTile(i, j).equals(o.getTile(i, j)))) {
					return false;
				}
			}
		}
		
		return true;
	}
}