package pieces;
import java.util.ArrayList;

import Utility.Position;
import game.Board;

public abstract class Piece {
	public boolean isWhite;
	protected int baseValue;
	protected int[][] positionalValue;
	public int value;
	//public int currentX;
	//public int currentY;
	public Type type;
	public boolean hasAtLeastOneMove;
	
	public Piece(boolean friendly){
		this.isWhite = friendly;
	}
	
	public int getBaseValue(){
		return baseValue;
	}
	
	public int getPositionalValue(int x, int y){
		/**try{
			positionalValue[x][y] = 3;
			positionalValue[7-x][7-y] = 3;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(this.type);
			return 3;
		}*/
		
		if(isWhite){
			return positionalValue[7-x][7-y]; //Should give the opposite positional value
		}
		
		return positionalValue[x][y];
	}
	
	public void setPositionalValue(int[][] value){
		this.positionalValue = value;
	}
	
	public Type getType(){
		return type;
	}
	
	
	public ArrayList<Position> possibleMoves(Position pos, Board board){
		return new ArrayList<Position>();
	}
	
	public void calculateThreat(Position pos, Board board){
		
	}
}
