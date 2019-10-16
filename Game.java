import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private GameBoard gameboard;
	private ArrayList<GamePiece> pieces;
	private InfoBook book;
	private boolean run;
	private int puzzlenumber;
	
	public Game() { 
		Scanner puzzle_selection = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter puzzle number you wish to play: 0,1,2");
	    puzzlenumber = puzzle_selection.nextInt();  // Read user input
	    System.out.println("Puzzle selected is: " + puzzlenumber); 
	    
		book = new InfoBook();
		run = true;
		pieces = new ArrayList<GamePiece>();

		pieces.add(new Bunny("bunny1", book.getSetup(puzzlenumber)[0], book.getSetup(puzzlenumber)[1]));
		pieces.add(new Bunny("bunny2", book.getSetup(puzzlenumber)[4], book.getSetup(puzzlenumber)[5]));
		pieces.add(new Bunny("bunny3", book.getSetup(puzzlenumber)[8], book.getSetup(puzzlenumber)[9]));	
		pieces.add(new Mushroom("mushroom1", book.getSetup(puzzlenumber)[2], book.getSetup(puzzlenumber)[3]));
		pieces.add(new Mushroom("mushroom2", book.getSetup(puzzlenumber)[5], book.getSetup(puzzlenumber)[6]));
		pieces.add(new Mushroom("mushroom3", book.getSetup(puzzlenumber)[10], book.getSetup(puzzlenumber)[11]));		
		pieces.add(new Fox("fox1", book.getSetup(0)[12], book.getSetup(puzzlenumber)[13], book.getSetup(puzzlenumber)[14]));
		pieces.add(new Fox("fox1", book.getSetup(0)[15], book.getSetup(puzzlenumber)[16], book.getSetup(puzzlenumber)[17]));
		
		gameboard = new GameBoard(pieces);
	}
	
	public static void main(String args[]) {
		
		Game jumpin = new Game();
		
		while(jumpin.run) {
			jumpin.gameboard.printBoard();
			
		    Scanner input = new Scanner(System.in);  // Create a Scanner object
		    System.out.println("Enter the piece you wish to move");
		    String move_piece = input.nextLine();  // Read user input
		    System.out.println("Piece: " + move_piece);  // Output user input
		    
		    for(int i = 0; i<8; i++) {
		    	 System.out.println("i"+ i);
		    	 System.out.println("movepiece = " + move_piece);
		    	 System.out.println("jumpin.pieces.get(i).getName() = "+ jumpin.pieces.get(i).getName());
		    	 
		    	 if(move_piece == jumpin.pieces.get(i).getName()) {
		    		 System.out.println("entered");
		    		 if(jumpin.pieces.get(i).getClass() == Bunny.class) {
		    			 System.out.println("Piece Class: Bunny");
		    		 }
		    		 else if(jumpin.pieces.get(i).getClass() == Fox.class) {
		    			 System.out.println("Piece Class: Bunny");
		    		 }
		    		 else if(jumpin.pieces.get(i).getClass() == Mushroom.class) {
		    			 System.out.println("Piece Class: Bunny");
		    		 }
		    		 else System.out.println("not working");	
		    	 }
		    }
		   
			//Step 2a -> get player decisions
			
			//loop game
			System.out.println("looped successfully");
			//jumpin.run = false;
		}	
		System.out.println("GAME IS DONE");
	}
}
