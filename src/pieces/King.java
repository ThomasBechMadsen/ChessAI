package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class King extends Piece{

	protected int[][] positionalValue = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
	};
	
	public King(boolean friendly) {
		super(friendly);
		this.type = Type.KING;
		baseValue = 10000;
	}
	
	@Override
	public ArrayList<Position> possibleMoves(Position pos){

		ArrayList<Position> moves = new ArrayList<Position>() ;
		Piece c;
		int i, j;

		//Top Side
		i = pos.x - 1;
		j = pos.y + 1;
		if (pos.y != 7) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 && i < 8) {
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
		i = pos.x - 1;
		j = pos.y - 1;
		if (pos.y != 0) {
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
		if(pos.x != 0){
			c = BoardController.Instance.board.getBoard() [pos.x - 1][ pos.y];
			if (c == null)
				moves.add(new Position(pos.x - 1, pos.y));
			else if (isWhite != c.isWhite)
				moves.add(new Position(pos.x -1 ,pos.y));
				//r[pos.x - 1][ pos.y] = true;
		}

		//Middle Right
		if(pos.x != 7){
			c = BoardController.Instance.board.getBoard() [pos.x + 1][ pos.y];
			if (c == null)
				moves.add(new Position(pos.x+1,pos.y));
				//r[pos.x + 1][ pos.y] = true;
			else if (isWhite != c.isWhite)
				moves.add(new Position(pos.x + 1, pos.y));
				// r [pos.x + 1][pos.y] = true;
		}

		return moves;
	}

}
