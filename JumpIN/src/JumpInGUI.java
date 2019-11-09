import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
	
	private JMenuItem puzzle0, puzzle1, puzzle2;
	private JMenu puzzleMenu;
	
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
		menuBar.add(puzzleMenu);
		
		puzzle0 = new JMenuItem("puzzle 0");
		puzzle0.addActionListener(e -> {
			puzzlenumber = 0;
			puzzleMenu.setEnabled(false);
		});
		
		puzzle1 = new JMenuItem("puzzle 1");
		puzzle1.addActionListener(e -> {
			puzzlenumber = 1;
			puzzleMenu.setEnabled(false);
		});
		
		puzzle2 = new JMenuItem("puzzle 2");
		puzzle2.addActionListener(e -> {
			puzzlenumber = 2;
			puzzleMenu.setEnabled(false);
		});
		
		puzzleMenu.add(puzzle0);
		puzzleMenu.add(puzzle1);
		puzzleMenu.add(puzzle2);
		
		setVisible(true);
		
		//Waits For a puzzle selection to set up board
		running = false;
		puzzlenumber = -1;
		
		//Note running was declared as volatile because otherwise the program would try to
		//optimize this and decide that running will never change.
		//(i.e. determining this is an infinite loop)
		while(!running) {
			if(puzzlenumber >= 0 && puzzlenumber < InfoBook.COUNT_BOARDS) running = true;
		}
		
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
				if(selectedPiece.canMove(b.getX(), b.getY())) {
					
					int direction;
					//Choose movement direction
					if(b.getX() == selectedPiece.getX()) {
						if(b.getY() > selectedPiece.getY()) direction = 2;
						else direction = 0;
					}
					else {
						if(b.getX() > selectedPiece.getX()) direction = 1;
						else direction = 3;
					}
					
					//Move in the given direction.
					game.getGameBoard().movePiece(selectedPiece.getX(), selectedPiece.getY(), direction);
					updateBoardVisuals();
					game.testGameState(game);
					if(game.getRunning() == false) {
						for(int i = 0; i < GameBoard.SIZE; i++) {
							for(int j = 0; j < GameBoard.SIZE; j++) {
								//If game is over, cannot press the pieces anymore.
								square[i][j].setEnabled(false);
							}
						}
						JOptionPane.showMessageDialog(null, "Congratulations, you have won!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Not a valid move for a " + selectedPiece.getClass().toString());
				}
			}
			selectedPiece = null;
		}
		else {
			//This is for when a GamePiece is pressed.
			this.selectedPiece = game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop();
			moveOptions = game.getGameBoard().possibleMoves(selectedPiece);
			this.updateMoveOptionVisuals();
		}
	}
	
	/**
	 * Show the possible moves in yellow. This is not currently working properly, and needs to be updated.
	 */
	public void updateMoveOptionVisuals() {
		for(Tile t : moveOptions) {
			this.square[t.getX()][t.getY()].setBackground(Color.YELLOW);
		}
		this.setVisible(true);
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
	 * Main method for the GUI.
	 * 
	 * @param args runtime arguments
	 */
	public static void main(String args[]) {
		
		JumpInGUI jumpin = new JumpInGUI();
		
		while(jumpin.running) {
			//Update Board Visuals
			jumpin.updateBoardVisuals();
			//jumpin.getUserMovement();
		    jumpin.game.testGameState(jumpin.game);
		}
		System.out.println("GAME IS WON");
	}

}
