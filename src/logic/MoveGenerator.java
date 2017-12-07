package logic;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import controller.BoardController;
import game.Board;
import pieces.Piece;

/**
 * Klasse der har til ansvar at beregne mulige træk for brikker
 * @author KimdR
 *
 */
public class MoveGenerator {

	/**
	 * Første version af generateMoves, den kigger simpelthen alle brikker igennem,
	 *  og laver et træk for hver muligt træk en given brik har
	 *  
	 *  Mulige forbedringer er, at evaluerer hvor brikken står og hvor godt et træk det kunne være og lave en prioritets kø.
	 * @param isWhite
	 */

	//TODO Run alpha beta on every move in this method, then save the move with its score. Afterwards select the move with highest score
	public static  ArrayList<Move> generateMoves(boolean isWhite, Board b){
		ArrayList<Move> moves = new ArrayList<>();
		Piece[][] board = b.getBoard();
		
		if((isWhite && b.isWhiteCheck) || (!isWhite && b.isBlackCheck)){
		     moves = avoidCheckMoves(isWhite, b);
		     return moves;
		}
			
		

		// Går igennem brættet og finder brikkerne der måtte være.
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {

				//Check hvis feltet er en brik
				if(board[j][i] != null){
					Piece piece = board[j][i]; // Brikken der kigges på.

					//Kun hvis brikken er spillerens skal der gøres noget.
					if(piece.isWhite == isWhite){
						// Der gåes igennem de mulige træk for brikken
						for(Position pos: piece.possibleMoves(new Position(j,i),b)){
							//	System.out.println("MoveGenerator brik farve check: " + piece.isWhite);
							//Der bliver genereret et move
							Piece targetPiece = board[pos.x][pos.y];

							Position to = new Position(pos.x, pos.y);
							Position from = new Position(j,i);
							Move move = new Move(from, to, piece, targetPiece);
//							//		System.out.println("MoveGenerator " + move);
							
							// move ordering
							if(targetPiece != null  ){
								moves.add(0, move);
							}else{
								moves.add(move);
							}
						}
					}
				}
			}
		}
		return moves;
	}

	private static ArrayList<Move> avoidCheckMoves(boolean isWhite, Board b) {
		System.out.println("avoiding");
		ArrayList<Move> moves = new ArrayList<>();
		Position kingPos = null;
		if(isWhite){
			kingPos = b.whiteKing;
		}else{
			kingPos = b.blackKing;
		}
		Piece king = b.getPieceAt(b.whiteKing.x, b.whiteKing.y);
		for (Position pos : king.possibleMoves(kingPos, b)) {
			Move move = new Move(kingPos, pos, king, b.getPieceAt(pos.x, pos.y));
			moves.add(move);
		}
		
		return moves;
	}
}



