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
	
	public List<Piece> activeChessPieces;

	public BoardController(){
		Instance = this;
		activeChessPieces = new ArrayList<Piece>();
	}

	public void execute(Move move, boolean isWhite) throws Exception{
		 //Check if this piece can be moved
		legalPiece(move.getFrom().x, move.getFrom().y, isWhite);
		legalMove(move);
		move(move);
		//System.out.println("Execute");
		//printBoard();
	}
	
	public void undo(Move move, boolean isWhite) throws Exception{
		legalPiece(move.getTo().x, move.getTo().y, isWhite);
		reverseMove(move);
		//System.out.println("Undo:");
		//printBoard();
	}
	
	private void legalPiece(int posX, int posY, boolean isWhite) throws Exception{
		// return if selected is outside board
		if(posX < 0 || posX > 7 || posY < 0 || posY > 7){
			throw new Exception("Piece out of bounds!");
		}
		
		// return if the player selects an empty position
		if (Program.b.getBoard()[posX][posY] == null){
			throw new Exception("No piece found on this position (" + posX + "," + posY + ")");
		}
		
		// return if the player is moving the wrong color
		if (Program.b.getBoard() [posX][posY].isWhite != isWhite){
			System.out.println("pos x = " + posX + " posy= "  + posY );
			printBoard();
			System.exit(1);
			throw new Exception("Selected piece is wrong colour");
		}
	}
	
	private void legalMove(Move move) throws Exception{
		// If outside the board then return
		Position newPos = move.getTo();
		if(newPos.x < 0 || newPos.x > 7 || newPos.y < 0 || newPos.y > 7){
			throw new Exception("Moving out of bounds");
		}
		
		if(move.getTarget() != null && move.getMovingPiece().isWhite == move.getTarget().isWhite){
			throw new Exception("Defeating friendly piece");
		}

		if(!move.getMovingPiece().possibleMoves(move.getFrom(), Program.b).contains(newPos)){
			throw new Exception("Move not part of possible moves");
		}
	}
	
	private void move(Move move){
		if(move.getTarget() != null){
			activeChessPieces.remove(move.getTarget());
		}
		
		if(move.getMovingPiece() instanceof Pawn){
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
	
	public void spawnChessPiece(int x, int y, Piece piece){
		Program.b.getBoard() [x][y] = piece;
		activeChessPieces.add(piece);
	}
	
	public boolean isGameOver(){
		boolean whiteKingFound = false;
		boolean blackKingFound = false;
		for(int i = 8; i >= 0; i--){
			for(int j = 8 ; j > 0 ; j--){
				if(Program.b.getBoard()[j-1][i-1] instanceof King && Program.b.getBoard()[j-1][i-1].isWhite){
					whiteKingFound = true;
				}
				if(Program.b.getBoard()[j-1][i-1] instanceof King && !Program.b.getBoard()[j-1][i-1].isWhite){
					blackKingFound = true;
				}
				if(whiteKingFound && blackKingFound){
					return false;
				}
			}
		}
		return true;
	}
	
	public void printBoard(){
		for(int i = 8; i >= 0; i--){
			if(i>0)
				System.out.printf("%d: ",(i));
			for(int j = 8 ; j > 0 ; j--){
				if(i == 0)
					System.out.printf("	%c	|  ",('i'-j));
				else if(Program.b.getBoard()[j-1][i-1] != null && i>=0  && i <= 8){
					if(Program.b.getBoard()[j-1][i-1].isWhite)
						System.out.printf(" %s	|  ", Program.b.getBoard()[j-1][i-1].type.toString().toUpperCase());
					else
						System.out.printf(" %s	|  ", Program.b.getBoard()[j-1][i-1].type.toString().toLowerCase());
				}
				else
					System.out.print("------	|  ");
			}
			System.out.println();
		}	
	}
}
