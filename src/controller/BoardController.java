package controller;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import game.Board;
import game.Program;
import logic.Move;
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

	public boolean isGameOver;

	public BoardController(){
		Instance = this;
		activeChessPieces = new ArrayList<Piece>();
	}

	public boolean execute(Move move, boolean isWhite){
		 //Check if this piece can be moved
		if(!legalPiece(move.getFrom().x, move.getFrom().y, isWhite)){
			return false;
		}
		if(!legalMove(move)){
			return false;
		}
		move(move);
		System.out.println("Execute");
		printBoard();
		return true;
	}
	
	public boolean undo(Move move, boolean isWhite){
		if(!legalPiece(move.getTo().x, move.getTo().y, isWhite)){
			return false;
		}
		
		reverseMove(move);
		System.out.println("Undo:");
		printBoard();
		return true;
	}
	
	private boolean legalPiece(int posX, int posY, boolean isWhite){
		// return if selected is outside board
		if(posX < 0 || posX > 7 || posY < 0 || posY > 7){
			System.out.println();
			return false;
		}
		
		// return if the player selects an empty position
		if (Program.b.getBoard()[posX][posY] == null){
			System.out.println("No piece found on this position");
			return false;
		}
		
		// return if the player is moving the wrong color
		if (Program.b.getBoard() [posX][posY].isWhite != isWhite){
			System.out.println("Selected piece is wrong colour");
			return false;
		}
		return true;
	}
	
	private boolean legalMove(Move move){
		// If outside the board then return
		//QUESTION: Will this ever be possible due to the pieces own possibleMoves method?
		Position newPos = move.getTo();
		if(newPos.x < 0 || newPos.x > 7 || newPos.y < 0 || newPos.y > 7){
			System.out.println("Moving out of bounds");
			return false;
		}
		
		if(move.getTarget() != null && move.getMovingPiece().isWhite == move.getTarget().isWhite){
			System.out.println("Defeating friendly piece");
			return false;
		}

		if(!move.getMovingPiece().possibleMoves(move.getFrom(), Program.b.getBoard()).contains(newPos)){
			System.out.println("Move not part of possible moves");
			return false;
		}
		return true;
	}
	
	private void move(Move move){
		if(move.getTarget() != null){
			if(move.getTarget() instanceof King){
				endGame();
				return;
			}
			activeChessPieces.remove(move.getTarget());
		}
		
		if(move.getMovingPiece() instanceof Pawn){
			
			//TODO: Insert enPassant move
			
			//Check pawn promotion
			if(move.getTo().y == 7){
				spawnChessPiece ( move.getFrom().x, move.getFrom().y, new Queen(true));
			}
			else if(move.getTo().y == 0){
				spawnChessPiece ( move.getFrom().x, move.getFrom().y, new Queen(false));
			}
		}
		
		Program.b.move(move.getFrom(), move.getTo());
	}
	
	private void reverseMove(Move move){
		//Undo promotion
		if(move.getMovingPiece() instanceof Pawn){
			if(move.getTo().y == 7){
				spawnChessPiece ( move.getTo().x, move.getTo().y, move.getMovingPiece());
			}
			else if(move.getTo().y == 0){
				spawnChessPiece ( move.getTo().x, move.getTo().y, move.getMovingPiece());
			}
		}
		
		//Undo transformation
		Program.b.move(move.getTo(), move.getFrom());
		
		//Restore enemy
		spawnChessPiece ( move.getTo().x, move.getTo().y, move.getTarget());
	}
	
	/**
	 * Metode der flytter brikkerne p� br�ttet.
	 * @param newPos er positionen som brikken flyttes til
	 * @param oldPos er positionen som brikken rykkes fra
	 * @return
	 */
	/*private boolean moveChessPiece(Position newPos, Position oldPos){
		// if outside the board then return
		if(newPos.x < 0 || newPos.x > 7 || newPos.y < 0 || newPos.y > 7){
		//	System.out.println("Træk ikke muligt!\n");
			return false;
		}

		boolean moveAllowed = false ;
		
		// check if the move is allowed
		if (selectedPiece == null )
			return false;
		
		for(Position p : allowedMoves)
			if(p.x == newPos.x && p.y == newPos.y)
				moveAllowed = true;
		
		
		if (moveAllowed){ //allowedMoves[x][y]) {
	//		System.out.printf("%s(%d,%d) rykkes til (%d,%d)\n",selectedPiece.getType(),oldPos.x+1, oldPos.y+1,newPos.x+1, newPos.y+1);
			Piece p = board.getBoard() [newPos.x][newPos.y];
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

			if (newPos.x == enPassantMove [0] && newPos.y == enPassantMove [1]) {
				if (isWhiteTurn) 
					p = board.getBoard() [newPos.x][ newPos.y - 1];
 				else 
					p = board.getBoard() [newPos.x][newPos.y + 1];
				activeChessPieces.remove(p);
			}

			enPassantMove [0] = -1;
			enPassantMove [1] = -1;
			
			if (selectedPiece.type == Type.Pawn ) {
				// Promotion rules
				if (newPos.y == 7) { // if white pawn reach top replace with white Queen
					activeChessPieces.remove(p);
					board.getBoard()[oldPos.x][oldPos.y] = null;
					spawnChessPiece ( newPos.x, newPos.y, new Queen(true));
					selectedPiece = board.getBoard() [newPos.x][newPos.y];
				} else if(newPos.y == 0){ // if black pawn reach top replace with black Queen
					activeChessPieces.remove(p);
					board.getBoard()[oldPos.x][oldPos.y] = null;
					spawnChessPiece ( newPos.x, newPos.y, new Queen(false));
					selectedPiece = board.getBoard()[newPos.x][newPos.y];
				}

				// En passant Rules
				if (oldPos.x == 1 && newPos.y == 3) {
					enPassantMove [0] = newPos.x;
					enPassantMove [1] = newPos.y - 1;
				}else if (oldPos.y == 6 && newPos.y == 4) {
					enPassantMove [0] = newPos.x;
					enPassantMove [1] = newPos.y + 1;
				}
			}

			board.getBoard() [oldPos.x][ oldPos.y] = null;
			
			board.getBoard() [newPos.x][newPos.y] = selectedPiece;
		

			selectedPiece = null;
			return true;
		}
		else{
			//System.out.println("Træk ikke muligt!\n");
			selectedPiece = null;
			return false;
		}
	}*/

	public void spawnChessPiece(int x, int y, Piece piece){
		Program.b.getBoard() [x][y] = piece;
		activeChessPieces.add(piece);
	}
	
	private void endGame(){
		System.out.println("Game Over!");
		activeChessPieces.removeAll(activeChessPieces);
	}
	
	public void printBoard(){
		for(int i = -1; i < Program.b.getBoard().length; i++){
			if(i>-1 && i<8)
				System.out.printf("%d: ",(i+1));
			for(int j = 0 ; j < Program.b.getBoard()[0].length ; j++){
				if(i == -1)
					System.out.printf("	%c	|  ",('a'+j));
				else if(Program.b.getBoard()[j][i] != null && i>-1 && i < 8){
					if(Program.b.getBoard()[j][i].isWhite)
						System.out.print("W");
					else
						System.out.print("B");
					System.out.printf("%s	|  ", Program.b.getBoard()[j][i].type);
				}
				else
					System.out.print("------	|  ");
			}
			System.out.println();
		}
	}

}
