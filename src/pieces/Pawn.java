package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Pawn extends Piece{
	
	public int[][] positionalValue = {
			{0,0,0,0,0,0,0,0},
			{7,7,13,23,26,13,7,7},
			{-2,-2,4,12,15,4,-2,-2},
			{-3,-3,2,9,11,2,-3,-3},
			{-4,-4,0,6,8,0,-4,-4},
			{-4,-4,0,4,6,0,-4,-4},
			{-1,-1,1,5,6,1,-1,-1},
			{0,0,0,0,0,0,0,0}
	};
	
	public Pawn(boolean friendly) {
		super(friendly);
		baseValue = 100;
		this.type = Type.Pawn;
		super.setPositionalValue(positionalValue);
	}

	@Override
	public ArrayList<Position> possibleMoves(Position pos,Piece[][] board){
	//	System.out.println("DEBUG PAWN start xy = " + pos);
		ArrayList<Position> moves = new ArrayList<Position>();
		Piece c1, c2;

		// White team move
		if (isWhite) {
			//Diagonal Left Move
			if(pos.x != 0 && pos.y != 7){
				
				c1 = board[pos.x -1][ pos.y +1];
				if(c1 != null && !c1.isWhite){
					moves.add(new Position(pos.x -1, pos.y+1));
					//moves [pos.x -1][pos.y +1] = true;
				}
			}

			//Diagonal Right Move
			if(pos.x != 7 && pos.y != 7){
				
				c1 = board[pos.x +1][ pos.y +1];
				if(c1 != null && !c1.isWhite){
					moves.add(new Position(pos.x +1, pos.y+1));
					//moves [pos.x + 1][ pos.y +1] = true;
				}
			}

			//Middel 
			if (pos.y != 7) {
				c1 = board[pos.x][ pos.y + 1];
				if (c1 == null)
					moves.add(new Position(pos.x, pos.y+1));
					//moves [pos.x][ pos.y + 1] = true;
			}

			//Middle on first move
			if (pos.y == 1) {
				c1 = board[pos.x][ pos.y + 1];
				c2 = board [pos.x][ pos.y + 2];
				if (c1 == null & c2 == null)
					moves.add(new Position(pos.x , pos.y+2));
					//moves [pos.x][pos.y + 2] = true;
			}
		}
			
		// Black team move
		else {
			//Diagonal Left Move
			if(pos.x != 0 && pos.y != 0){
				
				c1 = board[pos.x -1][ pos.y -1];
				if(c1 != null && c1.isWhite){
					moves.add(new Position(pos.x -1, pos.y-1));
					//moves [pos.x -1][ pos.y -1] = true;
				}
			}

			//Diagonal Right Move
			if(pos.x != 7 && pos.y != 0){

				c1 = board[pos.x +1][ pos.y -1];
				if(c1 != null && c1.isWhite){
					moves.add(new Position(pos.x +1, pos.y-1));
					//moves [pos.x + 1][ pos.y -1] = true;
				}
			}

			//Middel 
			if (pos.y != 0) {
				c1 = board [pos.x][ pos.y - 1];
				if (c1 == null)
					moves.add(new Position(pos.x , pos.y-1));
					//moves [pos.x][ pos.y - 1] = true;
			}

			//Middle on first move
			if (pos.y == 6) {
				c1 = board [pos.x][ pos.y - 1];
				c2 = board [pos.x][ pos.y - 2];
				if (c1 == null & c2 == null)
					moves.add(new Position(pos.x, pos.y-2));
					//moves [pos.x][ pos.y - 2] = true;
			}
		}
		return moves;
	}
}
