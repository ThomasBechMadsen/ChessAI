package pieces;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import dataContainers.Board;
import game.BoardController;

public class Bishop extends Piece{
	
	public Bishop(boolean friendly) {
		super(friendly);
	}
	
	@Override
	public ArrayList<Position> possibleMoves(Position pos,Board board){
		
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

			piece = board.getBoard()[x][y];
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

			piece = board.getBoard()[x][y];
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

			piece = board.getBoard()[x][y];
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

			piece = board.getBoard()[x][y];
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

	@Override
	public int getBaseValue() {
		return PieceBaseValue.bishop;
	}

	@Override
	public int getPositionalValue(int x, int y) {
		if(isWhite){
			return PieceSquareTables.bishop[7-x][7-y]; //Should give the opposite positional value
		}
		
		return PieceSquareTables.bishop[x][y];
	}

	@Override
	public void calculateThreat(Position pos, Board board) {
		// TODO Auto-generated method stub
		
	}
}
