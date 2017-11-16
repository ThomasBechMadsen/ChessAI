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
	ArrayList<Move> moves;	
	Piece[][] board;

	private BoardController boardObject;

	public MoveGenerator(BoardController boardObject){
		this.boardObject = boardObject;
		moves = new ArrayList<>();
		board = boardObject.chessPieces;
	}

/**
 * F�rste version af generateMoves, den kigger simpelthen alle brikker igennem,
 *  og laver et tr�k for hver muligt tr�k en given brik har
 *  
 *  Mulige forbedringer er, at evaluerer hvor brikken st�r og hvor godt et tr�k det kunne v�re og lave en prioritets k�.
 * @param isWhite
 */
	public void generateMoves(boolean isWhite){

		// G�r igennem br�ttet og finder brikkerne der m�tte v�re.
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 7; j++) {
				
				//Check hvis feltet er en brik
				if(board[j][i] != null){
					Piece piece = board[j][i]; // Brikken der kigges p�.
				
					//Kun hvis brikken er spillerens skal der g�res noget.
					if(piece.friendly){
						// Der g�es igennem de mulige tr�k for brikken
						for(int k = 0; k < 8; k++){
							for (int l = 0; l < 7; l++) {
								if(piece.possibleMoves()[j][i]){
									//Der bliver genereret et move
		                          Piece targetPiece = board[j][i];
                                  Position to = new Position(l, k);
                                  Position from = new Position(j,i);
                                  moves.add(new Move(from, to, piece, targetPiece));
								}
							}
						}
					}
				}
			}
			
		}
	}
}



