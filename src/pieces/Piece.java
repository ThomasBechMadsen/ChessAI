package pieces;
import java.util.ArrayList;

import Utility.Position;
import dataContainers.Board;

public abstract class Piece {
	public boolean isWhite;
	public int value;
	public boolean hasAtLeastOneMove;
	
	public Piece(boolean friendly){
		this.isWhite = friendly;
	}
	
	public abstract int getBaseValue();
	
	public abstract int getPositionalValue(int x, int y);
	/*{
		
		if(isWhite){
			return positionalValue[7-x][7-y]; //Should give the opposite positional value
		}
		
		return positionalValue[x][y];
	}*/

	public abstract ArrayList<Position> possibleMoves(Position pos, Board board);
	
	public abstract void calculateThreat(Position pos, Board board);
}
