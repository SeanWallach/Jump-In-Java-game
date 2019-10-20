//Class written by Ashton and Andrew

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private GameBoard gameboard;
	private ArrayList<GamePiece> pieces;
	private InfoBook book;
	private boolean running;
	private int puzzlenumber;
	private static Scanner input;
	
	public Game() { 
		input = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter puzzle number you wish to play: 0,1,2");
	    puzzlenumber = input.nextInt();  // Read user input
	    System.out.println("Puzzle selected is: " + puzzlenumber); 
	    
		book = new InfoBook();
		running = true;
		pieces = new ArrayList<GamePiece>();
		
		boolean f1Vert = book.getSetup(puzzlenumber)[12] == 1;
		boolean f2Vert = book.getSetup(puzzlenumber)[15] == 1;

		pieces.add(new Bunny("Bunny1", book.getSetup(puzzlenumber)[0], book.getSetup(puzzlenumber)[1]));
		pieces.add(new Bunny("Bunny2", book.getSetup(puzzlenumber)[4], book.getSetup(puzzlenumber)[5]));
		pieces.add(new Bunny("Bunny3", book.getSetup(puzzlenumber)[8], book.getSetup(puzzlenumber)[9]));	
		pieces.add(new Mushroom("Mushroom1", book.getSetup(puzzlenumber)[2], book.getSetup(puzzlenumber)[3]));
		pieces.add(new Mushroom("Mushroom2", book.getSetup(puzzlenumber)[6], book.getSetup(puzzlenumber)[7]));	
		pieces.add(new Fox("Fox1", book.getSetup(puzzlenumber)[10], book.getSetup(puzzlenumber)[11], f1Vert));
		pieces.add(new Fox("Fox2", book.getSetup(puzzlenumber)[13], book.getSetup(puzzlenumber)[14], f2Vert));
		
		gameboard = new GameBoard(pieces);
	}
	
	public static void main(String args[]) {
		
		Game jumpin = new Game();
		
		while(jumpin.getRunning()) {
			jumpin.gameboard.printBoard();
		    
		    for(int i = 0; i < jumpin.pieces.size(); i++) {
		    	 System.out.print(i);
		    	 
		    	 System.out.println(": " + jumpin.pieces.get(i).getName() + " at (" + 
		    	 jumpin.pieces.get(i).getX() +", "+ jumpin.pieces.get(i).getY() +")");
		    	 
		    }
		    
		    System.out.println("Enter the piece you wish to move.");
		    int move_piece = input.nextInt();  // Read user input
		    
		    System.out.println("Enter the direction you wish to move.");
		    System.out.println("0: up \n1: right \n2: down \n3: left");
		    int direction = input.nextInt();
		    
		    jumpin.gameboard.movePiece(jumpin.pieces.get(move_piece).getX(), jumpin.pieces.get(move_piece).getY(), direction);
			
		    jumpin.testGameState(jumpin);
		}	
		System.out.println("GAME IS DONE");
		input.close();
	}
	
	public ArrayList<GamePiece> getPieces(){
		return this.pieces;
	}
	
	public GameBoard getGameBoard() {
		return this.gameboard;
	}
	
	public boolean getRunning() {
		return this.running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void testGameState(Game jumpin) {
		boolean allBunniesInHoles = true;
		for(GamePiece g : jumpin.getPieces()) {
			if(g instanceof Bunny) {
				if(jumpin.getGameBoard().getTile(g.getX(), g.getY()).getGrass()) {
					allBunniesInHoles = false;
				}
			}
		}
		if(allBunniesInHoles) jumpin.setRunning(false);
	}
}