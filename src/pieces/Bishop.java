package pieces;

import controller.BoardController;

public class Bishop extends Piece{

	public Bishop(boolean friendly) {
		super(friendly);
		this.type = Type.Bishop;
		baseValue = 300;
	}
	
	@Override
	public boolean[][] possibleMoves(){
		
		boolean[][] moves = new boolean[8][8];
		Piece piece;
		int x,y;

		//Topleft
		x = currentX;
		y = currentY;
		while(true) {
			x--;
			y++;
			if(x < 0 || y>= 8)
				break;

			piece = BoardController.Instance.chessPieces[x][y];
			if(piece == null)
				moves [x][y] = true;
			else{
				if(friendly != piece.friendly)
					moves[x][y] = true;
				break;
			}
		}

		//TopRight
		x = currentX;
		y = currentY;
		while(true) {
			x++;
			y++;
			if(x >= 8 || y >= 8)
				break;

			piece = BoardController.Instance.chessPieces[x][y];
			if(piece == null)
				moves [x][y] = true;
			else{
				if(friendly != piece.friendly)
					moves[x][y] = true;
					break;
			}
		}
		
		//Down Left
		x = currentX;
		y = currentY;
		while(true) {
			x--;
			y--;
			if(x < 0 || y < 0)
				break;

			piece = BoardController.Instance.chessPieces[x][y];
			if(piece == null)
				moves [x][y] = true;
			else{
				if(friendly != piece.friendly)
					moves[x][y] = true;
				break;
			}
		}
		//Down Right
		x = currentX;
		y = currentY;
		while(true) {
			x++;
			y--;
			if(x >= 8 || y < 0)
				break;

			piece = BoardController.Instance.chessPieces[x][y];
			if(piece == null)
				moves [x][y] = true;
			else{
				if(friendly != piece.friendly)
					moves[x][y] = true;
				break;
			}
		}
		return moves;
	}
}
