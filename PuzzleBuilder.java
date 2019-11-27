import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The main GUI creation for the JumpIn game.
 * @author Ashton + Andrew + Sean
 *
 */
public class PuzzleBuilder extends JFrame implements ActionListener {
	private Game game;
	
	private JMenuItem addFoxPiece,addBunnyPiece ,addMushroomPiece;
	private JMenu addPieceMenu, removePiece, savePuzzle;
	
	private int remainingBunnyPieces;
	private int remainingFoxPieces;
	private int remainingMushroomPieces;
    
    private int currentPieceAdding;
    
    private JumpInButton[][] square;
    
    private GamePiece selectedPiece;
    private ArrayList<Tile> moveOptions;
	
    /**
     * Create the new GUI.
     */
	public PuzzleBuilder() {		
		super("Puzzle Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);		
		setLocation(400,200);
		
		//Menu Items: Related to Info Book and puzzle selection
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		addPieceMenu = new JMenu("Add Piece");
		removePiece = new JMenu("Remove Piece");
		removePiece.addActionListener(e -> {
			currentPieceAdding = 0;
			System.out.println(currentPieceAdding);
		});

		savePuzzle = new JMenu("Save Puzzle");
		
		menuBar.add(addPieceMenu);
		menuBar.add(removePiece);
		menuBar.add(savePuzzle);
		
		addBunnyPiece = new JMenuItem("Bunny");
		addBunnyPiece.addActionListener(e -> {
			currentPieceAdding = 1;
			System.out.println(currentPieceAdding);
		});
		
		addFoxPiece = new JMenuItem("Fox");
		addFoxPiece.addActionListener(e -> {
			currentPieceAdding = 2;
			System.out.println(currentPieceAdding);
		});
		
		addMushroomPiece = new JMenuItem("Mushroom");
		addMushroomPiece.addActionListener(e -> {
			currentPieceAdding = 3;
			System.out.println(currentPieceAdding);
		});
		
		addPieceMenu.add(addBunnyPiece);
		addPieceMenu.add(addFoxPiece);
		addPieceMenu.add(addMushroomPiece);
		
		game = new Game();
		//Button board: Related to GameBoard and game 
		square = new JumpInButton[GameBoard.SIZE][GameBoard.SIZE];
		JPanel panel = new JPanel(new GridLayout(GameBoard.SIZE, GameBoard.SIZE));
		for(int j = 0; j < GameBoard.SIZE; j++) {
			for(int i = 0; i < GameBoard.SIZE; i++) {
				square[i][j] = new JumpInButton(i, j);
				if(!game.getGameBoard().getTile(i, j).getGrass()) 
					square[i][j].setBackground(Color.BLACK);
				else square[i][j].setBackground(Color.GREEN);	
				
				square[i][j].addActionListener(this);
				panel.add(square[i][j]);
			}
		}
		add(panel, BorderLayout.CENTER);
		
		remainingBunnyPieces = 3;
		remainingFoxPieces = 2;
		remainingMushroomPieces = 3;
		
		//this.updateBoardVisuals();
		this.setVisible(true);
	
		
		//Note running was declared as volatile because otherwise the program would try to
		//optimize this and decide that running will never change.
		//(i.e. determining this is an infinite loop)
	}
	
	/**
	 * This deals with when a button is pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JumpInButton b = ((JumpInButton) event.getSource());
		
		//A tile that is not a GamePiece was pressed.
		if(game.getGameBoard().getTile(b.getX(), b.getY()).isEmpty()) {
			//adding a Bunny
			if(currentPieceAdding == 1) {
				if(remainingBunnyPieces > 0) {
					game.getGameBoard().placePiece(new Bunny("Bunny",b.getX(), b.getY()));
					remainingBunnyPieces -= 1;
					System.out.println("Bunny should be added");
				}
				else { System.out.println("Bunny should not be added, to full"); }
				
			}
			//adding a Fox
			else if(currentPieceAdding == 2) {
				System.out.println("Add Fox to be made");
			}
			//adding a Mushroom
			else if(currentPieceAdding == 3) {
				if(remainingMushroomPieces > 0) {
					game.getGameBoard().placePiece(new Mushroom("Mushroom",b.getX(), b.getY()));
					remainingMushroomPieces -= 1;
					System.out.println("Mushroom should be added");
				}
				else { System.out.println("Mushroom should not be added, to full"); }
			}
			
		}
		else {
			//This is for when a GamePiece is pressed.
			if(currentPieceAdding == 0) {
				game.getGameBoard().removePiece(b.getX(), b.getY());
				System.out.println("Piece Should be removed");

			}
				
			this.selectedPiece = game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop();
			moveOptions = game.getGameBoard().possibleMoves(selectedPiece);
			this.updateMoveOptionVisuals();
		}
		updateBoardVisuals();
	}
	
	/**
	 * Show the possible moves in yellow.
	 */
	public void updateMoveOptionVisuals() {
		this.updateBoardVisuals();
		for(Tile t : moveOptions) {
			this.square[t.getX()][t.getY()].setBackground(Color.YELLOW);
		}
	}
	
	/**
	 * Update all the tiles with the piece placements.
	 */
	public void updateBoardVisuals() {
		for(int i = 0; i < GameBoard.SIZE; i++) {
			for(int j = 0; j < GameBoard.SIZE; j++) {
				if(!game.getGameBoard().getTile(i, j).isEmpty()) {
					game.getGameBoard().getTile(i, j).getOnTop().placePiece(square);
				}
				else {
					if(!game.getGameBoard().getTile(i, j).getGrass()) 
						square[i][j].setBackground(Color.BLACK);
					else square[i][j].setBackground(Color.GREEN);
				}
			}
		}
	}	

	/**
	 * Create a String of instructions.
	 * @return a String containing the instructions on how to play JumpIN.
	 */
	public String getInstructions() {
		String s = "1. To start the game, select a puzzle from the Puzzle Selection menu.\n";
		s += "2. Once you have selected a game, the board will be visible.\n";
		s += "   Foxes are orange, mushrooms are red and bunnies are grey.\n";
		s += "   Empty squares are green and holes are black.\n";
		s += "3. When you click on a piece, the game will highlight in yellow\n";
		s += "   all the possible moves for this piece.\n";
		s += "   Bunnies must jump another piece, foxes move based on their alignment,\n";
		s += "   mushrooms cannot move.\n";
		s += "4. You can select one of those squares to move the piece or select\n";
		s += "   another piece to see the possible moves for that piece.\n";
		s += "5. The game is won when all bunnies are in one of the holes.\n";
		s += "6. In the menus, you can also find undo, redo, reset and help\n";
		s += "   functions to help you win.\n";
		s += "Good Luck!";
		return s;
	}
	
	/**
	 * Main method for the GUI.
	 * 
	 * @param args runtime arguments
	 */


}