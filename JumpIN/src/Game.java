//Class written by Ashton and Andrew
// Milestone 4: written by Sean and Lyndon 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Class Game
 * @author (of JavaDoc comments) Nicholas
 */
public class Game implements Serializable {
	private GameBoard gameboard;
	private ArrayList<GamePiece> pieces;
	private InfoBook book;
	private boolean running;
	
	/**
	 * Game constructor
	 * creating a new game, allowing the player
	 * to select the puzzle they wish to play.
	 */
	public Game(int puzzlenumber) {
		
		book = new InfoBook(puzzlenumber);
		this.pieces = book.getPieces();
		this.running = true;
		
		gameboard = new GameBoard(pieces);
	}
	
	/**
	 * Method getPieces gets the game pieces
	 * @return ArrayList<GamePiece> of game pieces.
	 */
	public ArrayList<GamePiece> getPieces(){
		return this.pieces;
	}
	
	/**
	 * Set the pieces needed for this game.
	 * @param pieces to add to the Game.
	 */
	public void setGamePieces(Collection<GamePiece> pieces) {
		this.pieces.addAll(pieces);
	}
	
	/**
	 * Method getGameBoard gets the jumpin gameboard
	 * @return GameBoard
	 */
	public GameBoard getGameBoard() {
		return this.gameboard;
	}
	
	/**
	 * Method getRunning checks if game is running
	 * @return boolean representing if game is running
	 */
	public boolean getRunning() {
		return this.running;
	}
	
	
	/**
	 * Method set running sets if game is running or not
	 * @param running Boolean value representing if game is running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * Method testGameState tests the state of the game
	 * @param jumpin is the current game that is being tested
	 */
	public void testGameState(Game jumpin) {
		boolean allBunniesInHoles = true;
		//Check if all bunnies have found their final position in one of the holes.
		for(GamePiece g : jumpin.getPieces()) {
			if(g instanceof Bunny) {
				if(jumpin.getGameBoard().getTile(g.getX(), g.getY()).getGrass()) {
					allBunniesInHoles = false;
				}
			}
		}
		if(allBunniesInHoles) jumpin.setRunning(false);
	}
	
	/**
	 * Method reset takes in a puzzle number, gets the appropriate pieces from a new infobook, and 
	 * sets the board according to the puzzlenumber.
	 * @param puzzlenumber int puzzle number for infobook's reference
	 */
	public void reset(int puzzlenumber) {
		ArrayList<GamePiece> p = new InfoBook(puzzlenumber).getPieces();
		this.gameboard.reset(p);
	}
	
  /**
	 * Undo function, delegates task to the gameboard.
	 */
	public void undo() {
		this.gameboard.undo();
	}
	
	/**
	 * Redo function, delegates task to the gameboard.
	 */
	public void redo() {
		this.gameboard.redo();
	}
	
	/**
	 * Saves the state of the game using serialization 
	 * 
	 * @param filename specifies the filename you want to use to save as
	 */
	public void save(String filename) {
		 try {
			 // Saves the current gameboard as a serialized object according to the user specified filename
	         FileOutputStream fileOut = new FileOutputStream("src/saves/" + filename + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(gameboard);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /src/saves/" + filename + ".ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	/**
	 * Loads the state of the game that was saved from above
	 * 
	 * @param filename specifies the file that you want to load 
	 */
	public void load(String filename) {
		try {
	         FileInputStream fileIn = new FileInputStream("src/saves/" + filename + ".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         gameboard = (GameBoard) in.readObject();	// cast object that was read from the filesystem, replaces the current gameboard
	         System.out.printf("Load serialized data from /src/saves/" + filename + ".ser");
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Gameboard class not found");
	         c.printStackTrace();
	         return;
	      }
	}
}
