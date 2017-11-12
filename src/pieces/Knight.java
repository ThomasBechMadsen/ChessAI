package pieces;

import controller.BoardController;

public class Knight extends Piece{

	public Knight(boolean friendly) {
		super(friendly);
		baseValue = 300;
		this.type = Type.Knight;
	}
	@Override
	public boolean[][] possibleMoves(){

		boolean[][] moves = new boolean[8][8];

		// Upleft
		knightMove(currentX -1, currentY +2, moves); 		
		// UpRight
		knightMove(currentX +1, currentY +2, moves); 		
		// RightUp
		knightMove(currentX +2, currentY +1, moves); 		
		// RightDown
		knightMove(currentX +2, currentY -1, moves); 		
		// LeftUp
		knightMove(currentX -2, currentY +1, moves); 		
		// LeftDown
		knightMove(currentX -2, currentY -1, moves); 
		// DownLeft
		knightMove(currentX -1, currentY -2, moves); 		
		// DownRight
		knightMove(currentX +1, currentY -2, moves); 

		return moves;
	}

	public void knightMove(int x, int y, boolean[][] r){
		Piece c;
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			c = BoardController.Instance.chessPieces [x][y];
			if (c == null) {
				r [x][y] = true;
			}else if(friendly != c.friendly)
				r[x][y]=true;
		}
		
	}
}
