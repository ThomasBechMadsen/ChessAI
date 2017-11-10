package pieces;

import java.util.ArrayList;
import Utility.Position;

public abstract class Piece {
	public boolean friendly;
	protected int baseValue;
	public int value;
	
	public Piece(boolean friendly){
		this.friendly = friendly;
	}
	
	public int getBaseValue() {
		return baseValue;
	}
}
