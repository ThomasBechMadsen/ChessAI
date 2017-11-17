package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Rook extends Piece{

	public Rook(boolean friendly) {
		super(friendly);
		this.type = Type.Rook;
		baseValue = 500;
	}

	@Override
	public ArrayList<Position> possibleMoves(){

		ArrayList<Position> moves = new ArrayList<Position>();

		Piece piece;
		int i;

		//Right
		i = currentX;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[i][currentY];
			if(piece == null)
				moves.add(new Position(i, currentY));
				//moves[i][currentY] = true;
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(i, currentY));
					//moves[i][currentY] = true;
				break;
			}
		}

		//Left
		i = currentX;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = BoardController.Instance.board.getBoard()[i][currentY];
			if(piece == null)
				moves.add(new Position(i, currentY));
				//moves[i][currentY] = true;
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(i, currentY));
				break;
			}
		}

		//UP
		i = currentY;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[currentX][i];
			if(piece == null)
				
				moves.add(new Position(currentX, i));
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(currentX, i));
				break;
			}
		}

		//Down
		i = currentY;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = BoardController.Instance.board.getBoard()[currentX][i];
			if(piece == null)
				moves.add(new Position(currentX, i));
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(currentX, i));
				break;
			}
		}

		return moves;

	}


	
	
}
