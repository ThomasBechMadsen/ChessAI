package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Knight extends Piece{

	public int[][] positionalValue = {
			{-2,2,7,9,9,7,2,-2},
			{1,4,12,13,13,12,4,1},
			{5,11,18,19,19,18,11,5},
			{3,10,14,14,14,14,10,3},
			{0,5,8,9,9,8,5,0},
			{-3,1,3,4,4,3,1,-3},
			{-5,-3,-1,0,0,-1,-3,-5},
			{-7,-5,-4,-2,-2,-4,-5,-7}
	};
	
	public Knight(boolean friendly) {
		super(friendly);
		baseValue = 300;
		super.setPositionalValue(positionalValue);
		this.type = Type.Knight;
	}
	@Override
	public ArrayList<Position> possibleMoves(Position pos, Piece[][] board){

		ArrayList<Position> moves = new ArrayList<Position>();

		// Upleft
		knightMove(pos.x -1, pos.y +2, moves,board); 		
		// UpRight
		knightMove(pos.x +1, pos.y +2, moves,board); 		
		// RightUp
		knightMove(pos.x +2, pos.y +1, moves,board); 		
		// RightDown
		knightMove(pos.x +2, pos.y -1, moves,board); 		
		// LeftUp
		knightMove(pos.x -2, pos.y +1, moves,board); 		
		// LeftDown
		knightMove(pos.x -2, pos.y -1, moves,board); 
		// DownLeft
		knightMove(pos.x -1, pos.y -2, moves,board); 		
		// DownRight
		knightMove(pos.x +1, pos.y -2, moves,board); 

		return moves;
	}

	public void knightMove(int x, int y, ArrayList<Position> r, Piece[][] board){
		Piece c;
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			c = board[x][y];
			if (c == null) {
				r.add(new Position(x,y));
			}else if(isWhite != c.isWhite)
				r.add(new Position(x,y));
		}
		
	}
}
