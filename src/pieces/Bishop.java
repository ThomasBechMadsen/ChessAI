package pieces;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import controller.BoardController;

public class Bishop extends Piece{

	protected int[][] positionalValue = {
			{2,3,4,4,4,4,3,2},
			{4,7,7,7,7,7,7,4},
			{3,5,6,6,6,6,5,3},
			{3,5,7,7,7,7,5,3},
			{4,5,6,8,8,6,5,4},
			{4,5,-2,-2,5,5,4},
			{5,5,5,3,3,5,5,5},
			{0,0,0,0,0,0,0,0}
	};
	
	public Bishop(boolean friendly) {
		super(friendly);
		this.type = Type.Bishop;
		baseValue = 300;
	}
	
	@Override
	public ArrayList<Position> possibleMoves(Position pos){
		
		ArrayList<Position> moves = new ArrayList<Position>() ;
		Piece piece;
		int x,y;

		//Topleft
		x = pos.x;
		y = pos.y;
		while(true) {
			x--;
			y++;
			if(x < 0 || y>= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
				break;
			}
		}

		//TopRight
		x = pos.x;
		y = pos.y;
		while(true) {
			x++;
			y++;
			if(x >= 8 || y >= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
					break;
			}
		}
		
		//Down Left
		x = pos.x;
		y = pos.y;
		while(true) {
			x--;
			y--;
			if(x < 0 || y < 0)
				break;

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
				break;
			}
		}
		//Down Right
		x = pos.x;
		y = pos.y;
		while(true) {
			x++;
			y--;
			if(x >= 8 || y < 0)
				break;

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
				break;
			}
		}
		return moves;
	}
}
