package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;

public class Rook extends Piece{

	protected int[][] positionalValue = {
			{9,9,11,10,11,9,9,9},
			{4,6,7,9,9,7,6,4},
			{9,10,10,11,11,10,10,9},
			{8,8,8,9,9,8,8,8},
			{6,6,5,6,6,5,6,6},
			{4,5,5,5,5,5,5,4},
			{3,4,4,6,6,4,4,3},
			{0,0,0,0,0,0,0,0}
	};
	
	public Rook(boolean friendly) {
		super(friendly);
		this.type = Type.Rook;
		baseValue = 500;
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

			piece = board[i][pos.y];
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

		return moves;

	}


	
	
}
