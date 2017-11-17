package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Queen extends Piece{
	
	public Queen(boolean friendly) {
		super(friendly);
		this.type = Type.Queen;
		baseValue = 900;
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
		
		int x,y;

		//Topleft
		x = currentX;
		y = currentY;
		while(true) {
			x--;
			y++;
			if(x < 0 || y>= 8)
				break;

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
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

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
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

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
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

			piece = BoardController.Instance.board.getBoard()[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
				break;
			}
		}
		return moves;
	}

}
