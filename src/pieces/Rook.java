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
	public ArrayList<Position> possibleMoves(Position pos){

		ArrayList<Position> moves = new ArrayList<Position>();

		Piece piece;
		int i;

		//Right
		i = currentX;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[i][pos.y];
			if(piece == null)
				moves.add(new Position(i, pos.x));
				//moves[i][currentY] = true;
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(i, pos.y));
					//moves[i][currentY] = true;
				break;
			}
		}

		//Left
		i = pos.x;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = BoardController.Instance.board.getBoard()[i][pos.y];
			if(piece == null)
				moves.add(new Position(i, pos.y));
				//moves[i][currentY] = true;
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(i, pos.y));
				break;
			}
		}

		//UP
		i = currentY;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[pos.x][i];
			if(piece == null)
				
				moves.add(new Position(pos.x, i));
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(pos.x, i));
				break;
			}
		}

		//Down
		i = pos.y;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = BoardController.Instance.board.getBoard()[currentX][i];
			if(piece == null)
				moves.add(new Position(pos.x, i));
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(pos.x, i));
				break;
			}
		}

		return moves;

	}


	
	
}
