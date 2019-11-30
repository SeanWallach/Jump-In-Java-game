//Class written by Ashton and Andrew

import java.util.*;

import javax.swing.JOptionPane;

/**
 * Class GameBoard
 * @author (of JavaDoc comments) Nicholas
 */
public class GameBoard{

	public static final int SIZE = 5;
	private Tile[][] tiles;
	private ArrayList<GamePiece> boardpieces;
	private Deque<Integer[]> movesMade;
	private Deque<Integer[]> undoCalls;
	
	/**
	 * GameBoard constructor
	 * create a game board
	 * @param p Arraylist of game pieces
	 */
	public GameBoard(ArrayList<GamePiece> p) {
		movesMade = new ArrayDeque<>();
		undoCalls = new ArrayDeque<>();
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
	 * Method prepares for moving a piece, then moves the piece.
	 * @param x X-coordinate to where game piece is moving
	 * @param y Y-coordinate to where game piece is moving
	 * @param direction Direction game piece is moving
	 */
	public void movePiece(int x, int y, int direction) {
		undoCalls.clear();
		move(x, y, direction);
	}
	
	/**
	 * Method move moves game piece in specified direction
	 * @param x X-coordinate to where game piece is moving
	 * @param y Y-coordinate to where game piece is moving
	 * @param direction Direction game piece is moving
	 */
	private void move(int x, int y, int direction) {
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
				
		boolean successfulMove = m.move(newCoord[0], newCoord[1], this.tiles);
		if(successfulMove) this.movesMade.push(new Integer[] {newCoord[0], newCoord[1], direction});
	}
	
	// This function calculates the possible moves available to the selected GamePiece
	public ArrayList<Tile> possibleMoves(GamePiece p) {
		ArrayList<Tile> moves = new ArrayList<Tile>();
		
		if(p.canMove()) {
			for(int i = 0; i < GameBoard.SIZE; i++) {
				for(int j = 0; j < GameBoard.SIZE; j++) {
					if(p.canMove(i, j, tiles)) moves.add(this.getTile(i, j));
				}
			}
		}
		
		return moves;
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
	 * 
	 * @return the current tiles representing the gameboard.
	 */
	public Tile[][] getTiles(){
		return this.tiles;
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
		System.out.println("");
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
	
	/**
	 * Method reset resets the board to its original setup according to its puzzlenumber
	 * @param p gamepieces from infobook
	 */
	public void reset(ArrayList<GamePiece> p) {		
		boardpieces = p;
		
		// Cycle through the board and set all tiles to empty
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				tiles[i][j].setEmpty();
			}	
		}
		
		// Cycle through all gamepieces and set them on the appropriate board
		for(GamePiece g: p) {
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
		
		movesMade.clear();
		undoCalls.clear();
	}
	
  /**
	 * Undoes the last move made by the player.
	 */
	public void undo() {
		if(movesMade.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cannot undo.");
			return;
		}
		Integer[] lastMove = movesMade.pop();
		GamePiece g = this.tiles[lastMove[0]][lastMove[1]].getOnTop();
		
		//To undo a move, it must move in the opposite direction.
		this.move(lastMove[0], lastMove[1], (lastMove[2] + 2) % 4);
		
		//Ensuring that the undo move is not able to be undone without the redo function.
		this.undoCalls.push(new Integer[] {g.getX(), g.getY(), lastMove[2]});
		this.movesMade.pop();
	}
	
	/**
	 * Redoes the last move made by a player (assuming that an undo has just occurred)
	 */
	public void redo() {
		if(undoCalls.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cannot redo.");
			return;
		}
		Integer[] lastMove = undoCalls.pop();
		
		this.move(lastMove[0], lastMove[1], lastMove[2]);
	}
}
