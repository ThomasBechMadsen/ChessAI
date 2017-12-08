package pieces;

import java.util.ArrayList;

import Utility.Position;
import dataContainers.Board;
import game.BoardController;

public class King extends Piece{
	
	public King(boolean friendly) {
		super(friendly);
	}
	
	@Override
	public ArrayList<Position> possibleMoves(Position pos, Board board){

		ArrayList<Position> moves = new ArrayList<Position>() ;
		Piece c;
		int i, j;

		//Top Side
		i = pos.x - 1;
		j = pos.y + 1;
		if (pos.y != 7) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 && i < 8) {
					c = board.getBoard() [i][j];
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
				if (i >= 0 && i < 8) {
					c = board.getBoard() [i][j];
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
			c = board.getBoard() [pos.x - 1][ pos.y];
			if (c == null)
				moves.add(new Position(pos.x - 1, pos.y));
			else if (isWhite != c.isWhite)
				moves.add(new Position(pos.x -1 ,pos.y));
				//r[pos.x - 1][ pos.y] = true;
		}

		//Middle Right
		if(pos.x != 7){
			c = board.getBoard() [pos.x + 1][ pos.y];
			if (c == null)
				moves.add(new Position(pos.x+1,pos.y));
				//r[pos.x + 1][ pos.y] = true;
			else if (isWhite != c.isWhite)
				moves.add(new Position(pos.x + 1, pos.y));
				// r [pos.x + 1][pos.y] = true;
		}

		return moves;
	}

	@Override
	public int getBaseValue() {
		return PieceBaseValue.king;
	}

	@Override
	public int getPositionalValue(int x, int y) {
		if(isWhite){
			return PieceSquareTables.king[7-x][7-y]; //Should give the opposite positional value
		}
		
		return PieceSquareTables.king[x][y];
	}

	@Override
	public void calculateThreat(Position pos, Board board) {
		// TODO Auto-generated method stub
		
	}

}
