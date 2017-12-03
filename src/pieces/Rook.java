package pieces;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;
import game.Board;

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
	public ArrayList<Position> possibleMoves(Position pos,Board board){

		ArrayList<Position> moves = new ArrayList<Position>();

		Piece piece;
		int i;

		//Right
		i = pos.x;
		while(true){
			i++;
			if (i >= 8)
				break;

			piece = board.getBoard()[i][pos.y];
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

			piece = board.getBoard()[i][pos.y];
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

			piece = board.getBoard()[pos.x][i];
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

			piece = board.getBoard()[pos.x][i];
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

	public void calculateThread(Position pos, Board board) {
		boolean[][] threat;
		if(this.isWhite){
			threat = board.whiteThreat;
		}
		else{
			threat = board.blackThreat;
		}

		int posY = pos.y;
		int posX = pos.x;

		//right
		for(int x = posX+1 ;x<8;x++){
			addThreatHorizontal(board, threat, posY, x);
		}

		//Left
		for(int x = posX-1; x >=0; x--){
			addThreatHorizontal(board,threat, posY,x);
		}

		//Up
		for(int y = posY+1; y < 8; y++){
			addThreatHorizontal(board,threat, y,posX);
		}
		//Down
		//Left
		for(int y = posY-1; y >=0; y--){
			addThreatHorizontal(board,threat, y, posX);
			System.out.println(y);
		}

	}

	private void addThreatHorizontal(Board board, boolean[][] threat, int y, int x) {
		Piece target =board.getBoard()[x][y];

		if(target == null){
			threat[x][y] = true;
		}else if(board.getBoard()[x][y] != null) {
			System.out.println(board.getBoard()[x][y].type);
			if(target.isWhite != isWhite){
				threat[x][y] = true;
				return;
			}else{
				return;
		} 

	}
}


}
