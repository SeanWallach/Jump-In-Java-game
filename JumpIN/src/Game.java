
//Class written by Ashton and Andrew

import java.util.*;

/**
 * Class Game
 * 
 * @author (of JavaDoc comments) Nicholas
 */
public class Game {
	private GameBoard gameboard;
	private ArrayList<GamePiece> pieces;
	private InfoBook book;
	private boolean running;
	private static Scanner input;

	/**
	 * Game constructor creating a new game, allowing the player to select the
	 * puzzle they wish to play.
	 */
	public Game() {
		input = new Scanner(System.in); // Create a Scanner object.
		running = false;
		int puzzlenumber = -1;
		while (!running) {
			System.out.print("Enter puzzle number you wish to play: ");
			for (int i = 0; i < InfoBook.COUNT_BOARDS - 1; i++) {
				System.out.print(i + ", ");
			}
			System.out.println(InfoBook.COUNT_BOARDS - 1);

			puzzlenumber = input.nextInt(); // Read user input
			System.out.println("Puzzle selected is: " + puzzlenumber);
			if (puzzlenumber >= 0 && puzzlenumber < InfoBook.COUNT_BOARDS)
				running = true;
		}

		book = new InfoBook(puzzlenumber);
		this.pieces = book.getPieces();

		gameboard = new GameBoard(pieces);
	}

	/**
	 * Main method to create new game of jumpin
	 */
	public static void main(String args[]) {

		Game jumpin = new Game();

		while (jumpin.getRunning()) {
			// The read-eval-print loop for the game.
			jumpin.gameboard.printBoard();

			for (int i = 0; i < jumpin.pieces.size(); i++) {
				System.out.print(i);

				System.out.println(": " + jumpin.pieces.get(i).getName() + " at (" + jumpin.pieces.get(i).getX() + ", "
						+ jumpin.pieces.get(i).getY() + ")");

			}

			int move_piece = -1;
			while (move_piece < 0 || move_piece >= jumpin.getPieces().size()) {
				System.out.println("Enter the piece you wish to move.");
				move_piece = input.nextInt(); // Read user input
			}

			System.out.println("Enter the direction you wish to move.");
			System.out.println("0: up \n1: right \n2: down \n3: left");
			int direction = input.nextInt(); // Read user input direction

			jumpin.gameboard.movePiece(jumpin.pieces.get(move_piece).getX(), jumpin.pieces.get(move_piece).getY(),
					direction);

			// At the end of each iteration, the game state must be tested.
			jumpin.testGameState(jumpin);
		}
		System.out.println("GAME IS WON");
		input.close();
	}

	/**
	 * Method getPieces gets the game pieces
	 * 
	 * @return ArrayList<GamePiece> of game pieces.
	 */
	public ArrayList<GamePiece> getPieces() {
		return this.pieces;
	}

	public void setGamePieces(Collection<GamePiece> pieces) {
		this.pieces.addAll(pieces);
	}

	/**
	 * Method getGameBoard gets the jumpin gameboard
	 * 
	 * @return GameBoard
	 */
	public GameBoard getGameBoard() {
		return this.gameboard;
	}

	/**
	 * Method getRunning checks if game is running
	 * 
	 * @return boolean representing if game is running
	 */
	public boolean getRunning() {
		return this.running;
	}

	/**
	 * Method set running sets if game is running or not
	 * 
	 * @param running Boolean value representing if game is running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Method testGameState tests the state of the game
	 * 
	 * @param jumpin is the current game that is being tested
	 */
	public void testGameState(Game jumpin) {
		boolean allBunniesInHoles = true;
		// Check if all bunnies have found their final position in one of the holes.
		for (GamePiece g : jumpin.getPieces()) {
			if (g instanceof Bunny) {
				if (jumpin.getGameBoard().getTile(g.getX(), g.getY()).getGrass()) {
					allBunniesInHoles = false;
				}
			}
		}
		if (allBunniesInHoles)
			jumpin.setRunning(false);
	}
}