package pieces;

import controller.BoardController;

public class Rook extends Piece{

	public Rook(boolean friendly) {
		super(friendly);
		this.type = Type.Rook;
		baseValue = 500;
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

		return moves;

	}


	
	
}
