package controller;

import java.util.ArrayList;
import java.util.List;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import pieces.Type;

public class BoardController {

	public static BoardController Instance;
	private boolean[][] allowedMoves;

	public int[] enPassantMove = {-1,-1};

	private List<Piece> activeChessPieces;

	public Piece[][] chessPieces;
	private Piece selectedPiece;
	public boolean isWhiteTurn;

	public BoardController(){
		Instance = this;
		isWhiteTurn = true;
		spawnAllChessPieces();
		System.out.println("Spil er startet.");
	}

	public void selectChessPiece(int x, int y){
		if (chessPieces [x][y] == null){
			System.out.println("pladsen er tom");
			return;
		}
		if (chessPieces [x][y].friendly != isWhiteTurn){
			System.out.println("Det er modstanderens tur");
			return;
		}
		allowedMoves = chessPieces [x][y].possibleMoves ();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (allowedMoves [i][j])
					chessPieces[x][y].hasAtLeastOneMove = true;
		if (!chessPieces[x][y].hasAtLeastOneMove){
			System.out.printf("%s(%d,%d) er valgt men har ingen mulige træk\n",chessPieces [x][y].getType(),y+1,x+1);
			return;
		}
			
		selectedPiece = chessPieces [x][y];
		System.out.printf("%s(%d,%d) er markeret  \n", chessPieces [x][y].getType(), y+1, x+1 );
	}
	
	public Piece getSelectedPiece(){
		return selectedPiece;
	}


	public void moveChessPiece(int x, int y){
		
		if ( selectedPiece != null && allowedMoves[x][y]) {
			System.out.printf("%s(%d,%d) rykkes til (%d,%d)\n",selectedPiece.getType(),selectedPiece.currentY+1, selectedPiece.currentX+1,y+1,x+1);
			Piece c = chessPieces [x][y];
			if(c != null && c.friendly != isWhiteTurn){

				//If the king is hit
				if(c.type == Type.KING){
					// End the game
					endGame();
					return;
				}
				activeChessPieces.remove(c);
			}

			if (x == enPassantMove [0] && y == enPassantMove [1]) {
				if (isWhiteTurn) 
					c = chessPieces [x][ y - 1];
 				else 
					c = chessPieces [x][y + 1];
				activeChessPieces.remove(c);
			}

			enPassantMove [0] = -1;
			enPassantMove [1] = -1;
			if (selectedPiece.type == Type.Pawn ) {
				if (y == 7) { // if white pawn reach top replace with white Queen
					activeChessPieces.remove (selectedPiece);
					spawnChessPiece ( x, y, new Queen(true));
					selectedPiece = chessPieces [x][y];
				} else if(y == 0){ // if black pawn reach top replace with black Queen
					activeChessPieces.remove (selectedPiece);
					spawnChessPiece ( x, y, new Queen(false));
					selectedPiece = chessPieces [x][y];
				}

				if (selectedPiece.currentY == 1 && y == 3) {
					enPassantMove [0] = x;
					enPassantMove [1] = y - 1;
				}else if (selectedPiece.currentY == 6 && y == 4) {
					enPassantMove [0] = x;
					enPassantMove [1] = y + 1;
				}
			}

			chessPieces [selectedPiece.currentX][ selectedPiece.currentY] = null;
			selectedPiece.setPosition (x, y);
			chessPieces [x][y] = selectedPiece;
			isWhiteTurn = !isWhiteTurn;

		}
		else
			System.out.println("Træk ikke muligt!\n");
		selectedPiece = null;

	}
		
	private void spawnChessPiece(int x, int y, Piece piece){
		chessPieces [x][y] = piece;
		chessPieces [x][y].setPosition (x, y);
		activeChessPieces.add(piece);
	}

	private void spawnAllChessPieces(){
		activeChessPieces = new ArrayList<Piece>();
		chessPieces = new Piece[8][8];

		//Generate friendly pieces
		spawnChessPiece(0, 0, new Rook(true));
		spawnChessPiece(1, 0, new Knight(true));
		spawnChessPiece(2, 0, new Bishop(true));
		spawnChessPiece(3, 0, new Queen(true));
		spawnChessPiece(4, 0, new King(true));
		spawnChessPiece(5, 0, new Bishop(true));
		spawnChessPiece(6, 0, new Knight(true));
		spawnChessPiece(7, 0, new Rook(true));
		
		//Generate enemy pieces
		spawnChessPiece(0, 7, new Rook(false));
		spawnChessPiece(1, 7, new Knight(false));
		spawnChessPiece(2, 7, new Bishop(false));
		spawnChessPiece(3, 7, new Queen(false));
		spawnChessPiece(4, 7, new King(false));
		spawnChessPiece(5, 7, new Bishop(false));
		spawnChessPiece(6, 7, new Knight(false));
		spawnChessPiece(7, 7, new Rook(false));
		
		//Generate pawns
		for(int i = 0; i < 8; i++){
			spawnChessPiece(i, 1, new Pawn(true));
			spawnChessPiece(i, 6, new Pawn(false));
		}
	}

	private void endGame(){
		if(isWhiteTurn){
			System.out.println("White Wins!");
		}else{
			System.out.println("Black Wins!");
		}


		for(Piece p : activeChessPieces)
			activeChessPieces.remove(p);
		
		isWhiteTurn = true;
		spawnAllChessPieces ();
	}
	
	public void printBoard(){
		for(int i = -1; i < chessPieces.length; i++){
			if(i>-1 && i<8)
				System.out.printf("%d: ",(i+1));
			for(int j = 0 ; j < chessPieces[0].length ; j++){
				if(i == -1)
					System.out.printf("	%d	|  ",(j+1));
				else if(chessPieces[j][i] != null && i>-1 && i < 8){
					if(chessPieces[j][i].friendly)
						System.out.print("W");
					else
						System.out.print("B");
					System.out.printf("%s	|  ", chessPieces[j][i].type);
				}
				else
					System.out.print("------	|  ");
			}
			System.out.println();
		}
		System.out.println("\n");
		if(isWhiteTurn)
			System.out.println("Det er WHITE's tur");
		else
			System.out.println("Det er BLACK's tur");		
	}

}
