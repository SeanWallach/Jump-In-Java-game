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
public class JumpInGUI extends JFrame implements ActionListener {
	private Game game;
	
	private JMenuItem puzzle0, puzzle1, puzzle2, hint, undo, redo, reset, puzzleCreate;
	private JMenu puzzleMenu, options, helpMenu;
	
	private volatile boolean running;
    private int puzzlenumber;
    
    private JumpInButton[][] square;
    
    private GamePiece selectedPiece;
    private ArrayList<Tile> moveOptions;
	
    /**
     * Create the new GUI.
     */
	public JumpInGUI() {		
		super("JumpIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);		
		setLocation(400,200);
		
		//Menu Items: Related to Info Book and puzzle selection
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		puzzleMenu = new JMenu("Puzzle Selection");
		options = new JMenu("Options");
		options.setEnabled(false);
		helpMenu = new JMenu("Help");
		
		
		menuBar.add(puzzleMenu);
		menuBar.add(options);
		menuBar.add(helpMenu);

		puzzleCreate = new JMenuItem("puzzle creation");
		puzzleCreate.addActionListener(e-> {
			new PuzzleBuilder();
		});
		
		puzzle0 = new JMenuItem("puzzle 0");
		puzzle0.addActionListener(e -> {
			puzzlenumber = 0;
		});
		
		puzzle1 = new JMenuItem("puzzle 1");
		puzzle1.addActionListener(e -> {
			puzzlenumber = 1;
		});
		
		puzzle2 = new JMenuItem("puzzle 2");
		puzzle2.addActionListener(e -> {
			puzzlenumber = 2;
		});
		
		puzzleMenu.add(puzzleCreate);
		puzzleMenu.add(puzzle0);
		puzzleMenu.add(puzzle1);
		puzzleMenu.add(puzzle2);
		
		undo = new JMenuItem("Undo");
		undo.addActionListener(e -> {
			this.selectedPiece = null;
			game.undo();
			this.updateBoardVisuals();
		});
		
		redo = new JMenuItem("Redo");
		redo.addActionListener(e -> {
			this.selectedPiece = null;
			game.redo();
			this.updateBoardVisuals();
		});
		
		reset = new JMenuItem("Reset");
		redo.addActionListener(e -> {
			
		});
		
		
		options.add(undo);
		options.add(redo);
		options.add(reset);
		
		hint = new JMenuItem("Hint");
		hint.addActionListener(e -> {
			if(puzzlenumber == -1) JOptionPane.showMessageDialog(null, "Select a puzzle from the Puzzle Selection menu.");
			else {
				//need to implement
				game.solve();
				
			}
		});
		
		JMenuItem instructions = new JMenuItem("Intructions");
		instructions.addActionListener(e -> JOptionPane.showMessageDialog(null, getInstructions(), "JumpIN Instructions", -1));
		
		helpMenu.add(instructions);
		helpMenu.add(hint);
		
		//Waits For a puzzle selection to set up board
		running = false;
		puzzlenumber = -1;
		
		setVisible(true);
		
		//Note running was declared as volatile because otherwise the program would try to
		//optimize this and decide that running will never change.
		//(i.e. determining this is an infinite loop)
		while(!running) {
			if(puzzlenumber >= 0 && puzzlenumber < InfoBook.COUNT_BOARDS) running = true;
		}
		
		puzzleMenu.setEnabled(false);
		options.setEnabled(true);
		
		game = new Game(puzzlenumber);	
		
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
		this.updateBoardVisuals();
		this.setVisible(true);
	}
	
	/**
	 * This deals with when a button is pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JumpInButton b = ((JumpInButton) event.getSource());
		
		//A tile that is not a GamePiece was pressed.
		if(game.getGameBoard().getTile(b.getX(), b.getY()).isEmpty()) {
			if(selectedPiece != null) {
				if(selectedPiece.canMove(b.getX(), b.getY(), game.getGameBoard().getTiles())) {
					
					int direction;
					//Choose movement direction
					if(b.getX() == selectedPiece.getX()) {
						if(b.getY() > selectedPiece.getY()) {//down
							direction = 2;
						}
						else {// up
							direction = 0;
						}
					}
					else {
						if(b.getX() > selectedPiece.getX()) {
							direction = 1;
						}
						else {
							direction = 3;
						}
					}
					
					//Move in the given direction.
					game.getGameBoard().movePiece(selectedPiece.getX(), selectedPiece.getY(), direction);
					
					game.testGameState(game);
					if(game.getRunning() == false) {
						for(int i = 0; i < GameBoard.SIZE; i++) {
							for(int j = 0; j < GameBoard.SIZE; j++) {
								//If game is over, cannot press the pieces anymore.
								square[i][j].setEnabled(false);
							}
						}
						this.undo.setEnabled(false);
						this.redo.setEnabled(false);
						this.running = false;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Not a valid move for a " + selectedPiece.getClass().toString());
				}
			}
			selectedPiece = null;
			this.updateBoardVisuals();
		}
		else {
			//This is for when a GamePiece is pressed.
			this.selectedPiece = game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop();
			moveOptions = game.getGameBoard().possibleMoves(selectedPiece);
			this.updateMoveOptionVisuals();
		}
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
	public static void main(String args[]) {
		
		JumpInGUI jumpin = new JumpInGUI();
		
		while(jumpin.running) {
			//Waiting for game to end.
			if(jumpin.game.getGameBoard().showSolverMoves()) {
				jumpin.updateBoardVisuals();
			}
		}
		JOptionPane.showMessageDialog(null, "Congratulations, you have won!");
	}

}