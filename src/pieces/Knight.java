package pieces;

import java.util.ArrayList;

import Utility.Position;
import dataContainers.Board;
import game.BoardController;

public class Knight extends Piece{
	
	public Knight(boolean friendly) {
		super(friendly);
	}
	@Override
	public ArrayList<Position> possibleMoves(Position pos, Board board){

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

	public void knightMove(int x, int y, ArrayList<Position> r, Board board){
		Piece c;
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			c = board.getBoard()[x][y];
			if (c == null) {
				r.add(new Position(x,y));
			}else if(isWhite != c.isWhite)
				r.add(new Position(x,y));
		}
		
	}
	@Override
	public int getBaseValue() {
		return PieceBaseValue.knight;
	}
	@Override
	public int getPositionalValue(int x, int y) {
		if(isWhite){
			return PieceSquareTables.knight[7-x][7-y]; //Should give the opposite positional value
		}
		
		return PieceSquareTables.knight[x][y];
	}
	@Override
	public void calculateThreat(Position pos, Board board) {
		// TODO Auto-generated method stub
		
	}
}
