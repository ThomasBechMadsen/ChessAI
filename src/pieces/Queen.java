package pieces;

import controller.BoardController;

public class Queen extends Piece{
	
	public Queen(boolean friendly) {
		super(friendly);
		this.type = Type.Queen;
		baseValue = 900;
	}
	@Override
	public boolean[][] possibleMoves(){

		boolean[][] moves = new boolean[8][8];

		Piece piece;
		int i;

		//Right
		i = currentX;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = BoardController.Instance.chessPieces[i][currentY];
			if(piece == null)
				moves[i][currentY] = true;
			else{
				if(piece.friendly != friendly)
					moves[i][currentY] = true;
				break;
			}
		}

		//Left
		i = currentX;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = BoardController.Instance.chessPieces[i][currentY];
			if(piece == null)
				moves[i][currentY] = true;
			else{
				if(piece.friendly != friendly)
					moves[i][currentY] = true;
				break;
			}
		}

		//UP
		i = currentY;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = BoardController.Instance.chessPieces[currentX][i];
			if(piece == null)
				moves[currentX][i] = true;
			else{
				if(piece.friendly != friendly)
					moves[currentX][i] = true;
				break;
			}
		}

		//Down
		i = currentY;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = BoardController.Instance.chessPieces[currentX][i];
			if(piece == null)
				moves[currentX][i] = true;
			else{
				if(piece.friendly != friendly)
					moves[currentX][i] = true;
				break;
			}
		}
		
		//  diagonal moves (Bishop Moves)
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
