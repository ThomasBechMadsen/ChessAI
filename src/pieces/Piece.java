package pieces;
import java.util.ArrayList;

import Utility.Position;

public abstract class Piece {
	public boolean isWhite;
	protected int baseValue;
	protected int[][] positionalValue;
	public int value;
	public int currentX;
	public int currentY;
	public Type type;
	public boolean hasAtLeastOneMove;
	
	public Piece(boolean friendly){
		this.isWhite = friendly;
	}
	
	public int getBaseValue(){
		return baseValue;
	}
	
	public int getPositionalValue(){
		if(!isWhite){
			return positionalValue[7-currentX][7-currentY]; //Should give the opposite positional value
		}
		return positionalValue[currentX][currentY];
	}
	
	public Type getType(){
		return type;
	}
	
	public ArrayList<Position> possibleMoves(Position pos){
		return new ArrayList<Position>();
	}
}
