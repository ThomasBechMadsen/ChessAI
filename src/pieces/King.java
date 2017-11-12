package pieces;

import controller.BoardController;

public class King extends Piece{

	
	public King(boolean friendly) {
		super(friendly);
		this.type = Type.KING;
		baseValue = 10000;
	}
	
	@Override
	public boolean[][] possibleMoves(){

		boolean[][] r = new boolean[8][ 8];
		Piece c;
		int i, j;

		//Top Side
		i = currentX - 1;
		j = currentY + 1;
		if (currentY != 7) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 || i < 8) {
					c = BoardController.Instance.chessPieces [i][j];
					if (c == null)
						r [i][j] = true;
					else if (friendly != c.friendly)
						r [i][j] = true;
				}
				i++;
			}
		}

		//Top Side
		i = currentX - 1;
		j = currentY - 1;
		if (currentY != 0) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 || i < 8) {
					c = BoardController.Instance.chessPieces [i][j];
					if (c == null)
						r [i][j] = true;
					else if (friendly != c.friendly)
						r [i][j] = true;
				}
				i++;
			}
		}


		//Middle Left
		if(currentX != 0){
			c = BoardController.Instance.chessPieces [currentX - 1][ currentY];
			if (c == null)
				r [currentX - 1][currentY] = true;
			else if (friendly != friendly)
				r[currentX - 1][ currentY] = true;
		}

		//Middle Right
		if(currentX != 7){
			c = BoardController.Instance.chessPieces [currentX + 1][ currentY];
			if (c == null)
				r[currentX + 1][ currentY] = true;
			else if (friendly != c.friendly)
				r [currentX + 1][currentY] = true;
		}

		return r;
	}

}
