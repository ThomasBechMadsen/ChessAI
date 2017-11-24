package logic;

import java.util.ArrayList;

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
	 public ArrayList<Move> moves;	
	 public Piece[][] board;

	private BoardController boardObject;

	public MoveGenerator(BoardController boardObject){
		this.boardObject = boardObject;
		moves = new ArrayList<>();
		board = boardObject.board.getBoard();
	}

	/**
	 * Første version af generateMoves, den kigger simpelthen alle brikker igennem,
	 *  og laver et træk for hver muligt træk en given brik har
	 *  
	 *  Mulige forbedringer er, at evaluerer hvor brikken står og hvor godt et træk det kunne være og lave en prioritets kø.
	 * @param isWhite
	 */
	
	//TODO Run alpha beta on every move in this method, then save the move with its score. Afterwards select the move with highest score
	public void generateMoves(boolean isWhite){

		// Går igennem brættet og finder brikkerne der måtte være.
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {

				//Check hvis feltet er en brik
				if(board[j][i] != null){
					Piece piece = board[j][i]; // Brikken der kigges på.

					//Kun hvis brikken er spillerens skal der gøres noget.
					if(piece.isWhite == isWhite){
						// Der gåes igennem de mulige træk for brikken
						for(Position pos: piece.possibleMoves(new Position(j,i))){
							//Der bliver genereret et move
							Piece targetPiece = board[pos.x][pos.y];
							Position to = new Position(pos.x, pos.y);
							Position from = new Position(i+1,j+1);
							moves.add(new Move(from, to, piece, targetPiece));
						}
					}
				}
			}
		}
	}
}



