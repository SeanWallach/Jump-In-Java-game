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
		if(successfulMove) this.movesMade.push(new Integer[] {newCoord[0], newCoord[1], direction});
		
		//System.out.println(this.movesMade.peek()[0]+""+ this.movesMade.peek()[1]+""+ this.movesMade.peek()[2] );
		//System.out.println("successfulMove = "+ successfulMove);

		//System.out.println("After moveSolve"+g.getName() +": new X:"+newCoord[0] + " new Y:"+newCoord[1]);
		printBoard();
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
	
	
	public void solve() {
		ArrayList<GamePiece> movingpieces = getMovablePieces();		
		Tile from;
		Tile to;
		
		int target = getTarget();
		int bunniesInHole = 0;
		int direction;
		int count = 0;
		
		boolean reusedMove = false;
		boolean backTracking = false;
		
		//looping the algorithm enough to be able to solve all problems
		while(count < 3) {
			System.out.println("-----------------Round "+ count+"----------------------");							
			for(int i = 0; i < movingpieces.size(); i++) {
				//Get all of the pieces that can move
				movingpieces = getMovablePieces();
				ArrayList<Tile> moveoptions = new ArrayList<>();
				
				if(movingpieces.size() <= i) {
					this.undo();
				}
				else {
					//Get all movement options of the pieces that can move
					moveoptions = possibleMoves(movingpieces.get(i));

				}
				//Capture the current tile being stood on, so that if my piece ever moves back to this spot I know I have exhausted the depth of the branch
				 
				
				//For the Objects moves
				for(int j = 0; j< moveoptions.size(); j++) {
					System.out.println("----Piece "+ i+"----------------------");			
					
					moveoptions = possibleMoves(movingpieces.get(i));
					printMoveInformation(movingpieces);
	
					//Moving The Object to its positions-> always take first option until no more left
					direction = findDirection(moveoptions.get(0), movingpieces.get(i));
										
					reusedMove = repetitionCheck(movingpieces.get(i), moveoptions.get(0).getX(), moveoptions.get(0).getY(), direction);	
					from = getTile(movingpieces.get(i).getX(), movingpieces.get(i).getY());
					to = getTile(moveoptions.get(0).getX(), moveoptions.get(0).getY());
					backTracking = inPieceMemory(movingpieces.get(i), moveoptions.get(0).getX(),moveoptions.get(0).getY());
					
					movingpieces.get(i).printMemory();
					//check madeMoves: only allows non repetitive moves to move
					if(!backTracking ) {
						System.out.println("X; "+ moveoptions.get(0).getX() + "Y: "+ moveoptions.get(0).getY() + "Dir"+ direction);				
						moveSolve(movingpieces.get(i), moveoptions.get(0).getX(), moveoptions.get(0).getY(), direction);
						movingpieces.get(i).setMemory(to, movingpieces.get(i), direction);
					}
					//
					else {
						System.out.println("Move has already been made");
						printMoveInformation(movingpieces);
						moveoptions.remove(moveoptions.get(0));
						moveSolve(movingpieces.get(i), moveoptions.get(0).getX(), moveoptions.get(0).getY(), direction);

					}
					//Check Winning Conditions after having made a move
					for(int winCheck = 0; winCheck < movingpieces.size(); winCheck++) {
						if((movingpieces.get(winCheck) instanceof Bunny) && !getTile(movingpieces.get(winCheck).getX(), movingpieces.get(winCheck).getY()).getGrass()){
							bunniesInHole++;
						}
					}
					if(target == bunniesInHole) {
						j=1000;
						i=1000;
						count=1000;
					}
					bunniesInHole = 0;

				}
				
				
			}
			count++;
			System.out.println("Final Count = "+count);
			
		}
	}
	public boolean inPieceMemory(GamePiece p, int x, int y) {
		Tile to = getTile(x,y);
		int direction = findDirection(to, p);
		Integer[] mem = {to.getX(), to.getY(), p.getX(), p.getY(), direction};
		int count = 0;
		
		if(p.getMemory().size()> 0) {
			System.out.println(p.getName() + p.getX() +"" +p.getY()+""+p.getMemory().size());
			for(int i = 0; i < p.getMemory().size(); i++) {
				for(int j = 0; j < p.getMemory().size(); j++ ) {
					if(mem[0] == p.getMemory().get(j)[0] && mem[1] == p.getMemory().get(j)[1] && mem[2] == p.getMemory().get(j)[2] && mem[3] == p.getMemory().get(j)[3] && mem[4] == p.getMemory().get(j)[4]) {
						return true;
					}
				}
			}	
			if(count == 5) {
				return true;
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
	public boolean repetitionCheck(GamePiece g, int x, int y, int direction) {
		Deque<Integer[]> tempStack = movesMade;
		System.out.println("Stack Accessed");
			
		if(tempStack.size() >= 2) {

			
			Integer[] poping = tempStack.pop();
			System.out.println("poping:      :x = "+poping[0]+", y = "+poping[1]+" direction = "+poping[2]);

			
			Integer[] duplicateMove = tempStack.pop();
			System.out.println("duplicateMove:x = "+duplicateMove[0]+", y = "+duplicateMove[1]+" direction = "+duplicateMove[2]);
			
			System.out.println("given        :x = "+x+", y = "+y+" direction = "+direction);
			if( duplicateMove[0]== x && duplicateMove[1] == y && duplicateMove[2] == direction ) {
				
				System.out.println("MADE IT HERE");
				return true;
			}
		}
		
		return false;	
	}
	
	public void printMoveInformation(ArrayList<GamePiece> movingpieces) {
		//System.out.println("PIECES THAT CAN MOVE");
		//System.out.println("movingpieces.size() = "+movingpieces.size());
		for(int i = 0; i< movingpieces.size(); i++) {
			

			ArrayList<Tile> moveoptions = possibleMoves(movingpieces.get(i));// move options for pieces you're looking at
			
			System.out.println(movingpieces.get(i).getName());
			for(int j = 0; j< moveoptions.size(); j++) {
				System.out.println("	MOVE OPTIONS "+ j);
				System.out.println("	X: "+moveoptions.get(j).getX() +" Y:"+ moveoptions.get(j).getY());
			}
		}
		printBoard();
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
		System.out.println("Movable Pieces:");
		for(int i = 0; i< movablepieces.size(); i++) {
			System.out.println(movablepieces.get(i).getName());
			System.out.println(movablepieces.get(i).getX()+""+ movablepieces.get(i).getY());
		}
		return movablepieces;
	}
	
	public int findDirection(Tile to, GamePiece from) {
		System.out.println("from = X: "+ from.getX()+ ", Y: "+ from.getY());
		System.out.println("To   = X: "+ to.getX()+ ", Y: "+ to.getY());
		
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