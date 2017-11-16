package controller;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import game.Board;
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
	private ArrayList<Position> allowedMoves;

	public int[] enPassantMove = {-1,-1};

	public List<Piece> activeChessPieces;

	public Board board = new Board();
	private Piece selectedPiece;
	public boolean isWhiteTurn;

	public BoardController(Piece[][] board){
		Instance = this;
		activeChessPieces = new ArrayList<Piece>();
		this.board.setBoard(board);
		isWhiteTurn = true;
	}

	public BoardController(){
		Instance = this;
		activeChessPieces = new ArrayList<Piece>();
		isWhiteTurn = true;
	}

	public void selectChessPiece(int x, int y){
		// return if selected is outside board
		if(x < 0 || x > 7 || y < 0 || y > 7)
			return;
		
		// return if the player selects an empty position
		if (board.getBoard() [x][y] == null){
			System.out.println("pladsen er tom");
			return;
		}
		
		// return if the player selects the wrong color
		if (board.getBoard() [x][y].isWhite != isWhiteTurn){
			System.out.println("Det er modstanderens tur");
			return;
		}
		// get the possible moves for the chess piece
		allowedMoves = board.getBoard()[x][y].possibleMoves();
		
		// return if the chesspiece moves is empty
		if (allowedMoves.isEmpty()){
			System.out.printf("%s(%d,%d) er valgt men har ingen mulige træk\n",board.getBoard() [x][y].getType(),y+1,x+1);
			return;
		}
		
		selectedPiece = board.getBoard() [x][y];
		System.out.printf("%s(%d,%d) er markeret  \n", board.getBoard() [x][y].getType(), y+1, x+1 );
		
	}
	
	public Piece getSelectedPiece(){
		return selectedPiece;
	}

	public boolean moveChessPiece(int x, int y){
		// if outside the board then return
		if(x < 0 || x > 7 || y < 0 || y > 7){
			System.out.println("Træk ikke muligt!\n");
			return false;
		}

		boolean moveAllowed = false ;
		
		// check if the move is allowed
		if (selectedPiece == null )
			return false;
		
		for(Position p : allowedMoves)
			if(p.x == x && p.y == y)
				moveAllowed = true;
		
		
		if (moveAllowed){ //allowedMoves[x][y]) {
			System.out.printf("%s(%d,%d) rykkes til (%d,%d)\n",selectedPiece.getType(),selectedPiece.currentY+1, selectedPiece.currentX+1,y+1,x+1);
			Piece p = board.getBoard() [x][y];
			// tjek hvis modstanderens brik
			if(p != null && p.isWhite != isWhiteTurn){
				//tjek hvis modstanderens brik er kongen
				if(p.type == Type.KING){
					// End the game
					endGame();
					return false;
				}
				//fjern modstanderen brik
				activeChessPieces.remove(p);
			}

			if (x == enPassantMove [0] && y == enPassantMove [1]) {
				if (isWhiteTurn) 
					p = board.getBoard() [x][ y - 1];
 				else 
					p = board.getBoard() [x][y + 1];
				activeChessPieces.remove(p);
			}

			enPassantMove [0] = -1;
			enPassantMove [1] = -1;
			
			if (selectedPiece.type == Type.Pawn ) {
				// Promotion rules
				if (y == 7) { // if white pawn reach top replace with white Queen
					activeChessPieces.remove(p);
					board.getBoard()[selectedPiece.currentX][selectedPiece.currentY] = null;
					spawnChessPiece ( x, y, new Queen(true));
					selectedPiece = board.getBoard() [x][y];
				} else if(y == 0){ // if black pawn reach top replace with black Queen
					activeChessPieces.remove(p);
					board.getBoard()[selectedPiece.currentX][selectedPiece.currentY] = null;
					spawnChessPiece ( x, y, new Queen(false));
					selectedPiece = board.getBoard()[x][y];
				}

				// En passant Rules
				if (selectedPiece.currentY == 1 && y == 3) {
					enPassantMove [0] = x;
					enPassantMove [1] = y - 1;
				}else if (selectedPiece.currentY == 6 && y == 4) {
					enPassantMove [0] = x;
					enPassantMove [1] = y + 1;
				}
			}

			board.getBoard() [selectedPiece.currentX][ selectedPiece.currentY] = null;
			selectedPiece.setPosition (x, y);
			board.getBoard() [x][y] = selectedPiece;
			isWhiteTurn = !isWhiteTurn;

			selectedPiece = null;
			return true;
		}
		else{
			System.out.println("Træk ikke muligt!\n");
			selectedPiece = null;
			return false;
		}
	}

	public void spawnChessPiece(int x, int y, Piece piece){
		board.getBoard() [x][y] = piece;
		board.getBoard() [x][y].setPosition (x, y);
		activeChessPieces.add(piece);
	}
	
	private void endGame(){
		if(isWhiteTurn){
			System.out.println("White Wins!");
		}else{
			System.out.println("Black Wins!");
		}
		activeChessPieces.removeAll(activeChessPieces);
	}
	
	public void printBoard(){
		for(int i = -1; i < board.getBoard().length; i++){
			if(i>-1 && i<8)
				System.out.printf("%d: ",(i+1));
			for(int j = 0 ; j < board.getBoard()[0].length ; j++){
				if(i == -1)
					System.out.printf("	%d	|  ",(j+1));
				else if(board.getBoard()[j][i] != null && i>-1 && i < 8){
					if(board.getBoard()[j][i].isWhite)
						System.out.print("W");
					else
						System.out.print("B");
					System.out.printf("%s	|  ", board.getBoard()[j][i].type);
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
