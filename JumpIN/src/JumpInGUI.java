import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JumpInGUI extends JFrame implements ActionListener{
	private Game game;
	
	private JMenuItem puzzle0, puzzle1, puzzle2;
	private JMenu puzzleMenu;
	
	private boolean running;
    private int puzzlenumber;
    
    private static JButton[][] square;
    
    private GamePiece selectedpiece;
    private ArrayList<Tile> moveoptions;
	
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
		puzzle0.addActionListener(this);
		
		puzzle1 = new JMenuItem("puzzle 1");
		puzzle1.addActionListener(this);
		
		puzzle2 = new JMenuItem("puzzle 2");
		puzzle2.addActionListener(this);
		
		puzzleMenu.add(puzzle0);
		puzzleMenu.add(puzzle1);
		puzzleMenu.add(puzzle2);
		
		setVisible(true);
		
		//Waits For a puzzle selection to set up board
		running = false;
		puzzlenumber = -1;
						
		while(!running) {
			System.out.print("");
			if(puzzlenumber >= 0 && puzzlenumber < InfoBook.COUNT_BOARDS) running = true;
		}
				
		
		//Button board: Related to GameBoard and game 
		square = new JButton[5][5];
		JPanel p = new JPanel(new GridLayout(5,5));
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if((i == j && i == 0) || (i == j && i == 4) || (i == j && i == 2) || (i == 0 && j == 4) || (i == 0 && j == 4) || (i == 4 && j == 0)) {
					square[i][j] = new JButton();
					square[i][j].setBackground(Color.BLACK);
					square[i][j].addActionListener(this);
					p.add(square[i][j]);
				}
				else {
					square[i][j] = new JButton();
					square[i][j].setBackground(Color.GREEN);
					square[i][j].addActionListener(this);
					p.add(square[i][j]);
				}				
			}
		}
		add(p, BorderLayout.CENTER);
		
		this.setVisible(true);
		
		game = new Game(puzzlenumber);				
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		//Puzzle Selection Menu Items
		if(event.getSource() == puzzle0) {
			puzzlenumber = 0;
			puzzleMenu.setEnabled(false);
		}
		else if(event.getSource() == puzzle1) {
			puzzlenumber = 1;
			puzzleMenu.setEnabled(false);
		}
		else if(event.getSource() == puzzle2) {
			puzzlenumber = 2;
			puzzleMenu.setEnabled(false);
		}
		//Grid Board Buttons
		else{	
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if(event.getSource().equals(square[i][j])) {
						System.out.println("i: "+ i + ", j: " + j);
						//If the tile on the Board is occupied by a game piece
						if(!game.getGameBoard().getTile(i, j).isEmpty()) {
							selectedpiece = game.getGameBoard().getTile(i, j).getOnTop();
							
							
							//Bunny
							if(selectedpiece instanceof Bunny ) {							
								if(selectedpiece.getName() == "Bunny1") {
									System.out.print(selectedpiece.canMove());
									System.out.println("Bunny1");
								}
								else if(selectedpiece.getName() == "Bunny2") {
									System.out.print(selectedpiece.canMove());
									System.out.println("Bunny2");
								}
								else if(selectedpiece.getName() == "Bunny3") {
									System.out.print(selectedpiece.canMoveFromSpot(i,j));
									System.out.println("Bunny3");
								}
							//Mushroom	-> Probably don't need this
							}
							else if(selectedpiece instanceof Mushroom) {
								System.out.println("Mushroom");
							}
							//Fox
							else if(selectedpiece instanceof Fox) {								
								if(selectedpiece.getName() == "Fox1") {
									//moveoptions = game.getGameBoard().foxMoveAvailable(false,selectedpiece.getX(),selectedpiece.getY());
									//updateMoveOptionVisuals();
									
									System.out.println("Fox1");
									
									/*if(game.getGameBoard().getTile(i, j).getOnTop().canMove()) {
										System.out.println("Fox1 Can Move");
									}*/
								}
								else if(selectedpiece.getName() == "Fox2") {
									System.out.println("Fox2");
									//moveoptions = game.getGameBoard().foxMoveAvailable(true,selectedpiece.getX(),selectedpiece.getY());
									//updateMoveOptionVisuals();
								}
							}
						}
					}
				}
			}
		}
	}
	public void updateMoveOptionVisuals() {
		for(int i = 0; i < game.getGameBoard().SIZE; i++) {
			for(int j = 0; j < game.getGameBoard().SIZE; j++) {
				for(int k = 0; k<moveoptions.size(); k++) {
					if(moveoptions.get(k) == game.getGameBoard().getTile(i, j)) {
						square[i][j].setBackground(Color.YELLOW);
					}
				}
			}	
		}			
	}
	
	public void updateBoardVisuals() {
		for(int i = 0; i < game.getGameBoard().SIZE; i++) {
			for(int j = 0; j < game.getGameBoard().SIZE; j++) {
				if(!game.getGameBoard().getTile(i, j).isEmpty()) {
					if(game.getGameBoard().getTile(i, j).getOnTop() instanceof Bunny) {							
						square[i][j].setBackground(Color.GRAY);
					}
					else if(game.getGameBoard().getTile(i, j).getOnTop() instanceof Mushroom) {
						square[i][j].setBackground(Color.RED);
					}
					else if(game.getGameBoard().getTile(i, j).getOnTop() instanceof Fox) {
						square[i][j].setBackground(Color.ORANGE);
					}
				}
			}
		}
	}
	
	/*public void getUserMovement() {
		
	}*/
	
	
	public static void main(String args[]) {
		
		JumpInGUI jumpin = new JumpInGUI();
		
		while(jumpin.running) {
			
			//Update Board Visuals
			jumpin.updateBoardVisuals();
			//jumpin.getUserMovement();
			
		    
		    /*for(int i = 0; i < jumpin.pieces.size(); i++) {
		    	 System.out.print(i);
		    	 
		    	 System.out.println(": " + jumpin.pieces.get(i).getName() + " at (" + 
		    	 jumpin.pieces.get(i).getX() +", "+ jumpin.pieces.get(i).getY() +")");
		    	 
		    }*/
		    
		    /*int move_piece = -1;
		    while(move_piece < 0 || move_piece >= jumpin.getPieces().size()) {
		    	System.out.println("Enter the piece you wish to move.");
		    	move_piece = input.nextInt();  // Read user input
		    }
		    
		    System.out.println("Enter the direction you wish to move.");
		    System.out.println("0: up \n1: right \n2: down \n3: left");
		    int direction = input.nextInt(); // Read user input direction8*/
		    
		    //jumpin.game.getGameBoard().movePiece(jumpin.game.getPieces().get(move_piece).getX(), jumpin.game.getPieces().get(move_piece).getY(), direction);
			
		    //At the end of each iteration, the game state must be tested.*/
		    jumpin.game.testGameState(jumpin.game);
		}	
		System.out.println("GAME IS WON");
		//game.input.close();
	}
	

}
