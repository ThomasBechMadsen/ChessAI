package game;

import java.util.ArrayList;

import Utility.Position;
import dataContainers.Board;
import dataContainers.Move;
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
	public static  ArrayList<Move> generateMoves(boolean isWhite, Board b){
		ArrayList<Move> moves = new ArrayList<>();
		Piece[][] board = b.getBoard();

		// G�r igennem br�ttet og finder brikkerne der m�tte v�re.
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {

				//Check hvis feltet er en brik
				if(board[j][i] != null){
					Piece piece = board[j][i]; // Brikken der kigges p�.

					//Kun hvis brikken er spillerens skal der g�res noget.
					if(piece.isWhite == isWhite){
						// Der g�es igennem de mulige tr�k for brikken
						for(Position pos: piece.possibleMoves(new Position(j,i),b)){
							//	System.out.println("MoveGenerator brik farve check: " + piece.isWhite);
							//Der bliver genereret et move

							Position to = new Position(pos.x, pos.y);
							Position from = new Position(j,i);
							Move move = new Move(from, to);
//							//		System.out.println("MoveGenerator " + move);
							
							// move ordering
							if(move.getTarget() != null  ){
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
}



