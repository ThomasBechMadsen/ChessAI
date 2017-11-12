package pieces;

public abstract class Piece {
	public boolean friendly;
	protected int baseValue;
	public int value;
	public int currentX;
	public int currentY;
	public Type type;
	public boolean hasAtLeastOneMove;
	
	public Piece(boolean friendly){
		this.friendly = friendly;
	}
	
	public Type getType(){
		return type;
	}
	
	public int getBaseValue() {
		return baseValue;
	}
	
	public void setPosition(int x, int y){
		currentX = x;
		currentY = y;
	}
	
	public boolean[][] possibleMoves(){
		return new boolean[8][8];
	}
}
