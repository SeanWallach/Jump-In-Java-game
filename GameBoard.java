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
	private GamePiece lastPieceMoved;
	private boolean showSolverMoves;
	
	
	
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
		
		showSolverMoves = false;
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j] = new Tile(i, j);				
			}
		}
		
		if(boardpieces != null) {
			for(GamePiece g: boardpieces) {
				placePiece(g);
			}
		}
		
	}
	
	public void placePiece(GamePiece g) {
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
	public void removePiece(int x, int y/*, GamePiece g*/) {
		getTile(x, y).setEmpty();	
	}

	/**
	 * Method movePiece moves game piece in specified direction
	 * @param x X-coordinate to where game piece is moving
	 * @param y Y-coordinate to where game piece is moving
	 * @param direction Direction game piece is moving
	 */
	public void movePiece(int x, int y, int direction) {
		undoCalls.clear();
		move(x, y, direction);
	}
	
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
	
	private void moveSolve(GamePiece g, int x, int y, int direction) {
		//Since the GamePieces don't know which other pieces are around them, this
		//class finds the empty position, then tells the piece to move to that
		//new position.
		int[] newCoord = {x,y};
		
		
		if(!(g instanceof MovableGamePiece)) {
			System.out.println(g.toString() + " cannot move.");
			return;
		}
				
		//This downcasting is safe because of the previous if statement.
		MovableGamePiece m = (MovableGamePiece) g;
				
		boolean successfulMove = m.move(newCoord[0], newCoord[1], this.tiles);
		if(successfulMove) {
			this.movesMade.push(new Integer[] {newCoord[0], newCoord[1], direction});
			lastPieceMoved = g;
			g.addToMemory(getTile(g.getX(),g.getY()));
		}
		
		//System.out.println(this.movesMade.peek()[0]+""+ this.movesMade.peek()[1]+""+ this.movesMade.peek()[2] );
		//System.out.println("successfulMove = "+ successfulMove);

		//System.out.println("After moveSolve"+g.getName() +": new X:"+newCoord[0] + " new Y:"+newCoord[1]);
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
	
	public boolean showSolverMoves() {
		return showSolverMoves;
	}
	public void solve() {
		
		//Sets the pieces initial tile to memory
		for(int i = 0; i<boardpieces.size(); i++) {
			int x = boardpieces.get(i).getX();
			int y = boardpieces.get(i).getY();
			boardpieces.get(i).addToMemory(getTile(x,y));
		}
		ArrayList<GamePiece> movingPieces = getMovablePieces();		
		ArrayList<Tile> moveOptions = new ArrayList<Tile>();
		
		Tile from;
		Tile to;
		 
		int target = getTarget();
		int bunniesInHole = 0;
		int direction;
		int count = 0;
		
		
		boolean backTracking;
		boolean solved = false;
		
		//looping the algorithm enough to be able to solve all problems
		while(count < 100000) {
			System.out.println("-----------------Round "+ count+"----------------------");
			System.out.println("Board Before round");
			printBoard();
			//Get all of the pieces that can move this round
			movingPieces = getMovablePieces();
			
			//For all Pieces that can move on the game board
			for(int i = 0; i < movingPieces.size(); i++) {
				
				
				//Get all of the movement options for the current piece being looked at 
				moveOptions = possibleMoves(movingPieces.get(i));
				
				
				//For the all of the possible moves of the piece
				for(int j = 0; j< moveOptions.size(); j++) {
					
					System.out.println("----Active Piece = "+ movingPieces.get(i).getName()+"----------------------");
					System.out.println("Board Before piece Options");
					printBoard();
					
					//Prints the last Piece to be moved
					if(lastPieceMoved != null) {
						System.out.println("lastPieceMoved = " + lastPieceMoved.getName());
					}
					else {
						System.out.println("First move of Solver");
					}
					
	
					//Movement Logic of piece: checks to see if the piece can move and if it can it goes through the movement logic
					if(moveOptions.size() > 0) {
						
						//finds the the values required for the movement to function: direction, the starting tile, the ending tile
						direction = findDirection(moveOptions.get(0), movingPieces.get(i));					
						from = getTile(movingPieces.get(i).getX(), movingPieces.get(i).getY());
						to = getTile(moveOptions.get(0).getX(), moveOptions.get(0).getY());
						
						//checks to see if the tile the piece is going is the tile it moved from previously
						backTracking = checkPieceMemory(movingPieces.get(i), to);
						
						//Checks for backtracking with the pieces, if it is not backtracking the piece just moves normally
						if(lastPieceMoved == null) {
							System.out.println("First move of Solver");
							moveSolve(movingPieces.get(i), moveOptions.get(0).getX(), moveOptions.get(0).getY(), direction);
							printBoard();
						}
						else {
							if(!backTracking) {			
								moveSolve(movingPieces.get(i), moveOptions.get(0).getX(), moveOptions.get(0).getY(), direction);
								
								
							}
							//if the piece does back track
							else {
								
								System.out.println("Move has already been made");
								System.out.println("Piece has "+moveOptions.size()+ " possible moves");
								System.out.println("Game has "+movingPieces.size()+ " movable pieces");
								
								//exception case for when there is only one possible move to be made in the entire game and it is the piece backtracking back to where it came from
								if(moveOptions.size()<= 1 && movingPieces.size() <=1) {
									if(lastPieceMoved == movingPieces.get(i)) {
										System.out.println("Same Piece As last round is moving");

									}
									System.out.println("the piece must go back there is only one possible move it can make and it is backtracking");
									
									//moves the piece back
									moveSolve(movingPieces.get(i), moveOptions.get(0).getX(), moveOptions.get(0).getY(), direction);
									
								}
								//if the move is backtracking, and there is other options in the game
								else {
									
									//if there is more than one option the piece can make remove the first move which would make it backtrack and do the next possible move it can make
									if(moveOptions.size() > 1 ) {
										moveOptions.remove(0);
										moveSolve(movingPieces.get(i), moveOptions.get(0).getX(), moveOptions.get(0).getY(), direction);
									}
									//
									else {
										System.out.println("Do the other moves of the pieces");
									}
									
								}
							}
						}
						System.out.println("Board After Piece:");
						printBoard();
						
					}
					
					//Updates the movement options of the piece after it finishes its movement logic
					moveOptions = possibleMoves(movingPieces.get(i));
					
					//Check Winning Conditions after having made a move
					for(int winCheck = 0; winCheck < movingPieces.size(); winCheck++) {
						if((movingPieces.get(winCheck) instanceof Bunny) && !getTile(movingPieces.get(winCheck).getX(), movingPieces.get(winCheck).getY()).getGrass()){
							bunniesInHole++;
						}
					}
					if(target == bunniesInHole) {
						System.out.println("Solved");
						solved = true;
						count = 100000000;
						j=1000;
						i=1000;
					}
					bunniesInHole = 0;
					
				}
				if(!solved) {
					if(movingPieces.size()>=1) {
						movingPieces.get(i).clearMemory();
					}
				}	
			}
			showSolverMoves = true;
			count++;
			System.out.println("Final Count = "+count);
			
		}
	}
	public boolean checkPieceMemory(GamePiece p, Tile to) {
		System.out.println("Entered checkPieceMemory of" + p.getName());
		p.printMemory();
		if(p.getMemory().size()> 0) {
			for(int i = 0; i < p.getMemory().size(); i++) {
				System.out.println(to.getX() +""+ p.getMemory().get(i).getX() +" " +to.getY() +""+ p.getMemory().get(i).getY() );
				if(to.getX() == p.getMemory().get(i).getX() && to.getY() == p.getMemory().get(i).getY()) {
					return true;
				}
			}
		}
		return false;
	}
	public int getTarget() {
		int target = 0;
		for(int i = 0; i< boardpieces.size();  i++) {
			if(boardpieces.get(i) instanceof Bunny) {
				target++;
			}
		}
		return target;
		
	}


	
	public ArrayList<GamePiece> getMovablePieces(){
		ArrayList<GamePiece> simpieces = boardpieces;
		ArrayList<GamePiece> movablepieces = new ArrayList<GamePiece>();
	
		//Get all piece that can move in current position
		for(int i = 0; i< simpieces.size(); i++) {
			//active piece being looked at
			GamePiece simpiece = simpieces.get(i);
					
			//if the piece can move somewhere add to moving pieces array
			if(possibleMoves(simpieces.get(i)).size() > 0) {
				movablepieces.add(simpiece);
				//System.out.println(simpiece.getName()+": added to movable pieces");
			}
		}
		return movablepieces;
	}
	
	public int findDirection(Tile to, GamePiece from) {	
		//check if the tile you're moving to is up or down from you
		if(to.getX() == from.getX()) {
			if(to.getY() > from.getY()) {
				return 2;//down
			}
			else {
				return 0;//up
			}
		}
		//check if the tile you're moving to is left or right from you
		else {
			if(to.getX() > from.getX()) {
				return 1;//right
			}
			else {
				return 3;//left
			}
		}
	}
	
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
	
	public void redo() {
		if(undoCalls.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cannot redo.");
			return;
		}
		Integer[] lastMove = undoCalls.pop();
		
		this.move(lastMove[0], lastMove[1], lastMove[2]);
	}
}