package logic;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;
import game.Board;
import pieces.Piece;

/**
 * Klasse der har til ansvar at beregne mulige tr�k for brikker
 * @author KimdR
 *
 */
public class MoveGenerator {

	/**
	 * F�rste version af generateMoves, den kigger simpelthen alle brikker igennem,
	 *  og laver et tr�k for hver muligt tr�k en given brik har
	 *  
	 *  Mulige forbedringer er, at evaluerer hvor brikken st�r og hvor godt et tr�k det kunne v�re og lave en prioritets k�.
	 * @param isWhite
	 */
	
	//TODO Run alpha beta on every move in this method, then save the move with its score. Afterwards select the move with highest score
	public static  ArrayList<Move> generateMoves(boolean isWhite, Piece[][] board){
		ArrayList<Move> moves = new ArrayList<>();

		// G�r igennem br�ttet og finder brikkerne der m�tte v�re.
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {

				//Check hvis feltet er en brik
				if(board[j][i] != null){
					Piece piece = board[j][i]; // Brikken der kigges p�.
			
					//Kun hvis brikken er spillerens skal der g�res noget.
					if(piece.isWhite == isWhite){
						// Der g�es igennem de mulige tr�k for brikken
						for(Position pos: piece.possibleMoves(new Position(j,i),board)){
							//Der bliver genereret et move
							Piece targetPiece = board[pos.x][pos.y];
							
							Position to = new Position(pos.x, pos.y);
							Position from = new Position(j,i);
							Move move = new Move(from, to, piece, targetPiece);
						//	System.out.println(move);
							moves.add(move);
						}
					}
			
				}
			
			}
			
		}
		return moves;
	}
}



