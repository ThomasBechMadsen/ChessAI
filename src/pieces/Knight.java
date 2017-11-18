package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Knight extends Piece{

	public Knight(boolean friendly) {
		super(friendly);
		baseValue = 300;
		this.type = Type.Knight;
	}
	@Override
	public ArrayList<Position> possibleMoves(Position pos){

		ArrayList<Position> moves = new ArrayList<Position>();

		// Upleft
		knightMove(pos.x -1, pos.y +2, moves); 		
		// UpRight
		knightMove(pos.x +1, pos.y +2, moves); 		
		// RightUp
		knightMove(pos.x +2, pos.y +1, moves); 		
		// RightDown
		knightMove(pos.x +2, pos.y -1, moves); 		
		// LeftUp
		knightMove(pos.x -2, pos.y +1, moves); 		
		// LeftDown
		knightMove(pos.x -2, pos.y -1, moves); 
		// DownLeft
		knightMove(pos.x -1, pos.y -2, moves); 		
		// DownRight
		knightMove(pos.x +1, pos.y -2, moves); 

		return moves;
	}

	public void knightMove(int x, int y, ArrayList<Position> r){
		Piece c;
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			c = BoardController.Instance.board.getBoard()[x][y];
			if (c == null) {
				r.add(new Position(x,y));
			}else if(isWhite != c.isWhite)
				r.add(new Position(x,y));
		}
		
	}
}
