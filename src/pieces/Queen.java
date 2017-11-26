package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Queen extends Piece{
	
	public int[][] positionalValue = {
			{2,3,4,3,4,3,3,2},
			{2,3,4,4,4,4,3,2},
			{3,4,4,4,4,4,4,3},
			{3,3,4,4,4,4,3,3},
			{2,3,3,4,4,3,3,2},
			{2,2,2,3,3,2,2,2},
			{2,2,2,2,2,2,2,2},
			{0,0,0,0,0,0,0,0}
	};
	
	public Queen(boolean friendly) {
		super(friendly);
		this.type = Type.Queen;
		baseValue = 900;
		super.setPositionalValue(positionalValue);
	}
	@Override
	public ArrayList<Position> possibleMoves(Position pos,Piece[][] board){


		ArrayList<Position> moves = new ArrayList<Position>();

		Piece piece;
		int i;

		//Right
		i = pos.x;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = board[i][pos.y];
			if(piece == null)
				moves.add(new Position(i, pos.y));
				//moves[i][pos.y] = true;
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(i, pos.y));
					//moves[i][pos.y] = true;
				break;
			}
		}

		//Left
		i = pos.x;
		while(true){
			i--;
			if (i < 0)
				break;

			piece = board[i][pos.y];
			if(piece == null)
				moves.add(new Position(i, pos.y));
				//moves[i][pos.y] = true;
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(i, pos.y));
				break;
			}
		}

		//UP
		i = pos.y;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = board[pos.x][i];
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

			piece = board[pos.x][i];
			if(piece == null)
				moves.add(new Position(pos.x, i));
			else{
				if(piece.isWhite != isWhite)
					moves.add(new Position(pos.x, i));
				break;
			}
		}
		
		int x,y;

		//Topleft
		x = pos.x;
		y = pos.y;
		while(true) {
			x--;
			y++;
			if(x < 0 || y>= 8)
				break;

			piece = board[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
				break;
			}
		}

		//TopRight
		x = pos.x;
		y = pos.y;
		while(true) {
			x++;
			y++;
			if(x >= 8 || y >= 8)
				break;

			piece = board[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
					break;
			}
		}
		
		//Down Left
		x = pos.x;
		y = pos.y;
		while(true) {
			x--;
			y--;
			if(x < 0 || y < 0)
				break;

			piece = board[x][y];
			if(piece == null)
				moves.add(new Position(x, y));
			else{
				if(isWhite != piece.isWhite)
					moves.add(new Position(x, y));
				break;
			}
		}
		//Down Right
		x = pos.x;
		y = pos.y;
		while(true) {
			x++;
			y--;
			if(x >= 8 || y < 0)
				break;

			piece = board[x][y];
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
