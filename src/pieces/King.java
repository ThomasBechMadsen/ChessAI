package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;
import game.Board;

public class King extends Piece{

	public int[][] positionalValue = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
	};


	public King(boolean friendly) {
		super(friendly);
		this.type = Type.KING;
		baseValue = 10000;
		super.setPositionalValue(positionalValue);

	}

	@Override
	public ArrayList<Position> possibleMoves(Position pos, Board board){

		ArrayList<Position> moves = new ArrayList<Position>() ;
		Piece c;
		boolean[][] threat;
		if(this.isWhite){
			threat = board.blackThreat;
		}else{
			threat = board.whiteThreat;

		}

		int i, j;

		//Top Side
		i = pos.x - 1;
		j = pos.y + 1;
		if (pos.y != 7) {
			for (int k = 0; k < 3; k++) {
				if(threat[i][j]){
					break;
				}
				else if (i >= 0 && i < 8) {
					c = board.getBoard() [i][j];
					if (c == null)
						moves.add(new Position(i, j));
					else if (isWhite != c.isWhite)
						moves.add(new Position(i, j));
				}
				i++;
			}
		}

		//Top Side
		i = pos.x - 1;
		j = pos.y - 1;
		if (pos.y != 0) {
			for (int k = 0; k < 3; k++) {
				if (i >= 0 && i < 8) {
					if(threat[i][j]){
						break;
					}
					c = board.getBoard() [i][j];
					if (c == null)
						moves.add(new Position(i, j));
					else if (isWhite != c.isWhite)
						moves.add(new Position(i, j));
				}
				i++;
			}
		}


		//Middle Left
		if(pos.x != 0){

			c = board.getBoard() [pos.x - 1][ pos.y];
			if(threat[pos.x-1][pos.y]){

			}else if (c == null){
				moves.add(new Position(pos.x - 1, pos.y));
			}else if (isWhite != c.isWhite){
				moves.add(new Position(pos.x -1 ,pos.y));
			}
			//r[pos.x - 1][ pos.y] = true;
		}

		//Middle Right
		if(pos.x != 7){
			c = board.getBoard() [pos.x + 1][ pos.y];
			if(threat[pos.x+1][pos.y]){

			}
			else if (c == null){
				moves.add(new Position(pos.x+1,pos.y));
				//r[pos.x + 1][ pos.y] = true;
			}else if (isWhite != c.isWhite)
				moves.add(new Position(pos.x + 1, pos.y));
			// r [pos.x + 1][pos.y] = true;
		}

		return moves;
	}

	@Override
	public void calculateThreat(Position pos, Board board){

		boolean[][] threat;
		if(this.isWhite){
			threat = board.whiteThreat;
		}
		else{
			threat = board.blackThreat;
		}

		int x = pos.x;
		int y = pos.y;

		for(y = pos.y-1; y < pos.y+2; y++){
			for (x = pos.x-1; x < pos.x +2; x++) {

				if(x >= 0 && x<8 && y>= 0 && y<8){
					threat[x][y] = true;
				}
			}
		}
		threat[pos.x][pos.y] = false;


	}
}