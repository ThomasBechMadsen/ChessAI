package pieces;

import java.util.ArrayList;
import Utility.Position;

public abstract class Piece {
	public boolean friendly;
	private int baseValue = 0; //readonly
	public int value;
	public ArrayList<Position> moves;
	
	public Piece(boolean friendly){
		this.friendly = friendly;
	}
	
	public int getBaseValue() {
		return baseValue;
	}
}
