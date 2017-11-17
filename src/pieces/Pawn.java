package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Pawn extends Piece{
	
	public Pawn(boolean friendly) {
		super(friendly);
		baseValue = 100;
		this.type = Type.Pawn;
	}

	@Override
	public ArrayList<Position> possibleMoves(){
		ArrayList<Position> moves = new ArrayList<Position>();
		int[] e = BoardController.Instance.enPassantMove;
		Piece c1, c2;

		// White team move
		if (isWhite) {
			//Diagonal Left Move
			if(currentX != 0 && currentY != 7){
				if (e [0] == currentX - 1 && e [1] == currentY + 1) 
					moves.add(new Position(currentX -1, currentY+1));
					//moves [currentX - 1][ currentY + 1] = true;

				c1 = BoardController.Instance.board.getBoard()[currentX -1][ currentY +1];
				if(c1 != null && !c1.isWhite){
					moves.add(new Position(currentX -1, currentY+1));
					//moves [currentX -1][currentY +1] = true;
				}
			}

			//Diagonal Right Move
			if(currentX != 7 && currentY != 7){

				if (e [0] == currentX + 1 && e [1] == currentY + 1) 
					moves.add(new Position(currentX +1, currentY+1));
					//moves [currentX + 1][ currentY + 1] = true;
				
				c1 = BoardController.Instance.board.getBoard()[currentX +1][ currentY +1];
				if(c1 != null && !c1.isWhite){
					moves.add(new Position(currentX +1, currentY+1));
					//moves [currentX + 1][ currentY +1] = true;
				}
			}

			//Middel 
			if (currentY != 7) {
				c1 = BoardController.Instance.board .getBoard()[currentX][ currentY + 1];
				if (c1 == null)
					moves.add(new Position(currentX, currentY+1));
					//moves [currentX][ currentY + 1] = true;
			}

			//Middle on first move
			if (currentY == 1) {
				c1 = BoardController.Instance.board .getBoard()[currentX][ currentY + 1];
				c2 = BoardController.Instance.board.getBoard() [currentX][ currentY + 2];
				if (c1 == null & c2 == null)
					moves.add(new Position(currentX , currentY+2));
					//moves [currentX][currentY + 2] = true;
			}
		}
			
		// Black team move
		else {
			//Diagonal Left Move
			if(currentX != 0 && currentY != 0){
				if (e [0] == currentX - 1 && e [1] == currentY - 1) 
					moves.add(new Position(currentX -1, currentY-1));
					//moves [currentX - 1][ currentY - 1] = true;
				
				c1 = BoardController.Instance.board.getBoard()[currentX -1][ currentY -1];
				if(c1 != null && c1.isWhite){
					moves.add(new Position(currentX -1, currentY-1));
					//moves [currentX -1][ currentY -1] = true;
				}
			}

			//Diagonal Right Move
			if(currentX != 7 && currentY != 0){
				if (e [0] == currentX + 1 && e [1] == currentY - 1) 
					moves.add(new Position(currentX +1, currentY-1));
					//moves [currentX + 1][ currentY - 1] = true;
				c1 = BoardController.Instance.board.getBoard()[currentX +1][ currentY -1];
				if(c1 != null && c1.isWhite){
					moves.add(new Position(currentX +1, currentY-1));
					//moves [currentX + 1][ currentY -1] = true;
				}
			}

			//Middel 
			if (currentY != 0) {
				c1 = BoardController.Instance.board.getBoard() [currentX][ currentY - 1];
				if (c1 == null)
					moves.add(new Position(currentX , currentY-1));
					//moves [currentX][ currentY - 1] = true;
			}

			//Middle on first move
			if (currentY == 6) {
				c1 = BoardController.Instance.board.getBoard() [currentX][ currentY - 1];
				c2 = BoardController.Instance.board.getBoard() [currentX][ currentY - 2];
				if (c1 == null & c2 == null)
					moves.add(new Position(currentX, currentY-2));
					//moves [currentX][ currentY - 2] = true;
			}
		}
		return moves;
	}
}
