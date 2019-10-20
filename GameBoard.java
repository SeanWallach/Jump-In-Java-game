
//Class written by Ashton and Andrew

import java.util.ArrayList;

public class GameBoard {
	public static final int SIZE = 5;
	private Tile[][] tiles;
	private ArrayList<GamePiece> boardpieces;
	
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
	
	public void movePiece(int x, int y, int direction) {
		GamePiece g = tiles[x][y].getOnTop();
		Tile newTile = tiles[x][y];
		
		switch(direction) {
			case 0: // moving up
				if(y == 0) System.out.println("Cannot move up.");
				else newTile = tiles[x][y - 1]; 
				break;
			
			case 1: // moving right
				if(g instanceof Fox) {
					if(x + 2 == GameBoard.SIZE) System.out.println("Cannot move right.");
					else newTile = tiles[x + 2][y];
				}
				else {
					if(x + 1 == GameBoard.SIZE) System.out.println("Cannot move right.");
					else newTile = tiles[x + 1][y]; 
				}
				break;
			
			case 2: // moving down
				if(g instanceof Fox) {
					if(y + 2 == GameBoard.SIZE) System.out.println("Cannot move down.");
					else newTile = tiles[x][y + 2];
				}
				else {
					if(y + 1 == GameBoard.SIZE) System.out.println("Cannot move down.");
					else newTile = tiles[x][y + 1]; 
				}
				break;
			
			case 3: // moving left
				if(x == 0) System.out.println("Cannot move left.");
				else newTile = tiles[x - 1][y];
				break;
				
			default: 
				System.out.println("Illegal direction.");
				break;
		}
		
		if(newTile != null) {
			if(g instanceof Fox) {
				//Foxes need to clear both the back and front tile when moving.
				tiles[((Fox) g).getBackX()][((Fox) g).getBackY()].setEmpty();
			}
			g.move(direction);
			tiles[x][y].setEmpty();
			
			if(!(newTile.isEmpty())) {
				if(g instanceof Fox) newTile.getOnTop().handleFoxCollision(new MoveEvent(
						x, y, direction, g));
				
				else {
					boolean foundTile = false;
					//Trying to find a position for the bunny to land in the given direction.
					//It will continue moving in the direction until it either finds an unoccupied
					//tile or it hits a wall.
					while(!foundTile) {
						newTile.getOnTop().handleBunnyCollision(new MoveEvent(
								g.getX(), g.getY(), direction, g));
						//Checking if the bunny has found a final position.
						if(tiles[g.getX()][g.getY()].isEmpty()) foundTile = true;
						
						//Checking if the bunny is about to collide with a wall.
						boolean hitWall = false;
						switch(direction) {
							case 0:
								if(g.getY() == 0) hitWall = true;
								break;
							case 1:
								if(g.getX() == GameBoard.SIZE - 1) hitWall = true;
								break;
							case 2:
								if(g.getY() == GameBoard.SIZE - 1) hitWall = true;
								break;
							case 3:
								if(g.getX() == 0) hitWall = true;
								break;
						}
						if(!foundTile && hitWall) {
							//No spots for the bunny, must return to starting position.
							g.setX(x);
							g.setY(y);
							break;
						}
					}
				}
			}
			
			//The next lines are determining and setting the final positions of the moved piece
			//and setting that piece on its new tile. 
			if(g instanceof Fox) {
				tiles[((Fox) g).getBackX()][((Fox) g).getBackY()].setOnTop(g);
			}
			
			if(g instanceof Bunny) {
				if(Math.abs(x - g.getX()) < 2 && Math.abs(y - g.getY()) < 2) {
					g.setX(x);
					g.setY(y);
				}
			}
			
			tiles[g.getX()][g.getY()].setOnTop(g);
			
		}
	}
	
	public Tile getTile(int x, int y) {
		return this.tiles[x][y];
	}
	
	public void printBoard() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[j][i].printTile();
			}
			System.out.println("");
		}
	}
}