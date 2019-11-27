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
	
	private JMenuItem addFoxPieceV, addFoxPieceH,addBunnyPiece ,addMushroomPiece,removePiece;
	private JMenu addPieceMenu, removePieceMenu, savePuzzle;
	
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
		
		//Menu Items
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		addPieceMenu = new JMenu("Add Piece");
		removePieceMenu = new JMenu("Remove Piece");
		savePuzzle = new JMenu("Save Puzzle");
		
		menuBar.add(addPieceMenu);
		menuBar.add(removePieceMenu);
		menuBar.add(savePuzzle);
		
		//Add Piece JMenu
		addBunnyPiece = new JMenuItem("Standard Bunny");
		addBunnyPiece.addActionListener(e -> {
			currentPieceAdding = 1;
			System.out.println(currentPieceAdding);
		});
		
		addFoxPieceH = new JMenuItem("Horizontal Fox");
		addFoxPieceH.addActionListener(e -> {
			currentPieceAdding = 2;
			System.out.println(currentPieceAdding);
		});
		
		addFoxPieceV = new JMenuItem("Vertical Fox");
		addFoxPieceV.addActionListener(e -> {
			currentPieceAdding = 3;
			System.out.println(currentPieceAdding);
		});
		
		addMushroomPiece = new JMenuItem("Standard Mushroom");
		addMushroomPiece.addActionListener(e -> {
			currentPieceAdding = 4;
			System.out.println(currentPieceAdding);
		});
		
		addPieceMenu.add(addBunnyPiece);
		addPieceMenu.add(addFoxPieceH);
		addPieceMenu.add(addFoxPieceV);
		addPieceMenu.add(addMushroomPiece);
		
		//Remove Piece JMenu
		removePiece = new JMenuItem("Remove");
		removePiece.addActionListener(e -> {
			currentPieceAdding = 0;
			System.out.println(currentPieceAdding);
		});
		
		removePieceMenu.add(removePiece);
		
		//Save Menu
		
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
				/*if(remainingFoxPieces >0) {
					game.getGameBoard().placePiece(new Fox("Fox",b.getX(), b.getY(), boolean d));
				}*/
				System.out.println("Add Fox to be made");
			}
			//adding a Mushroom
			else if(currentPieceAdding == 4) {
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
				if(game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop() instanceof Bunny) {
					remainingBunnyPieces +=1;
				}
				else if(game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop() instanceof Mushroom) {
					remainingMushroomPieces +=1;
				}
				else if(game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop() instanceof Mushroom) {
					remainingFoxPieces += 1;
				}
				
				game.getGameBoard().removePiece(b.getX(), b.getY());
				System.out.println("Piece Should be removed");
			}
				
			this.selectedPiece = game.getGameBoard().getTile(b.getX(), b.getY()).getOnTop();
		}
		updateBoardVisuals();
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

}