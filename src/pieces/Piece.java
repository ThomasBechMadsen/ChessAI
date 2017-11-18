package pieces;
import java.util.ArrayList;

import Utility.Position;

public abstract class Piece {
	public boolean isWhite;
	protected int baseValue;
	public int value;
	public int currentX;
	public int currentY;
	public Type type;
	public boolean hasAtLeastOneMove;
	
	public Piece(boolean friendly){
		this.isWhite = friendly;
	}
	
	public Type getType(){
		return type;
	}
	
	public int getBaseValue() {
		return baseValue;
	}
	
	
	
	public ArrayList<Position> possibleMoves(Position pos){
		return new ArrayList<Position>();
	}
}
