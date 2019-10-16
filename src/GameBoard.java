import java.util.ArrayList;

public class GameBoard {
	private Tile[][] tiles;
	private ArrayList<GamePiece> boardpieces;
	
	public GameBoard(ArrayList<GamePiece> p) {
		boardpieces = p;
		tiles = new Tile[5][5];
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				tiles[i][j] = new Tile(j,i);						
			}
		}
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				for(int piece = 0; piece<8; piece ++) {
					if(boardpieces.get(piece).getX() == j && boardpieces.get(piece).getY() == i) {
						tiles[i][j].setFull();
						tiles[i][j].setOnTop(boardpieces.get(piece));
						if(boardpieces.get(piece).getFox() && boardpieces.get(piece).getUpDown()) {
							tiles[i+1][j].setFull();
							tiles[i+1][j].setOnTop(boardpieces.get(piece));
						}
						else if(boardpieces.get(piece).getFox() && !boardpieces.get(piece).getUpDown()) {
							tiles[i][j+1].setFull();
							tiles[i][j+1].setOnTop(boardpieces.get(piece));
						}
					}
				}
			}
		}
	}
	public void printBoard() {
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				tiles[i][j].printTile();
			}
			System.out.println("");
		}
	}
}
