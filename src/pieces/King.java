package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class King extends Piece{

	
	public King(boolean friendly) {
		super(friendly);
		this.type = Type.KING;
		baseValue = 10000;
	}
	
	@Override
	public ArrayList<Position> possibleMoves(){

		ArrayList<Position> moves = new ArrayList<Position>() ;
		Piece c;
		int i, j;

		//Top Side
		i = currentX - 1;
		j = currentY + 1;
		if (currentY != 7) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 || i < 8) {
					c = BoardController.Instance.board.getBoard() [i][j];
					if (c == null)
						moves.add(new Position(i, j));
					else if (isWhite != c.isWhite)
						moves.add(new Position(i, j));
				}
				i++;
			}
		}

		//Top Side
		i = currentX - 1;
		j = currentY - 1;
		if (currentY != 0) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 || i < 8) {
					c = BoardController.Instance.board.getBoard() [i][j];
					if (c == null)
						moves.add(new Position(i, j));
					else if (isWhite != c.isWhite)
						moves.add(new Position(i, j));
				}
				i++;
			}
		}


		//Middle Left
		if(currentX != 0){
			c = BoardController.Instance.board.getBoard() [currentX - 1][ currentY];
			if (c == null)
				moves.add(new Position(currentX - 1, currentY));
			else if (isWhite != c.isWhite)
				moves.add(new Position(currentX -1 ,currentY));
				//r[currentX - 1][ currentY] = true;
		}

		//Middle Right
		if(currentX != 7){
			c = BoardController.Instance.board.getBoard() [currentX + 1][ currentY];
			if (c == null)
				moves.add(new Position(currentX+1,currentY));
				//r[currentX + 1][ currentY] = true;
			else if (isWhite != c.isWhite)
				moves.add(new Position(currentX + 1, currentY));
				// r [currentX + 1][currentY] = true;
		}

		return moves;
	}

}
