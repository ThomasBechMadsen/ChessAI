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

		boolean[][] threat;
		if(this.isWhite){
			threat = board.blackThreat;
		}else{
			threat = board.whiteThreat;

		}

		Position[] positions = getPositions(pos);



		for (Position position : positions) {
			if(position.x >= 0 && position.x <8 && position.y >=0 && position.y <7){ // Check for out of boundt

				Piece p = board.getPieceAt(position.x, position.y); // gemmer feltet 
				if(p != null){ // hvis feltet ikke er tom, og det er modstander brik, så tilføj
					if(p.isWhite != isWhite){

						moves.add(position);

					}
				}else{

					moves.add(position); // hvis det er tomt, så tilføj det
				}
			}

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

	public ArrayList<Position> detectThreat(Position pos, Board board){

		ArrayList<Position> sources = new ArrayList<>();

		Position kingPos;
		if(isWhite){
			kingPos = board.whiteKing;
		}
		else{
			kingPos = board.blackKing;
		}

		for(int y = 0 ; y < 8; y++){
			for (int x = 0; x < 8; x++) {
				Piece p = board.getBoard()[x][y];
				if(p != null){
					if(p.isWhite != this.isWhite);
					sources.addAll(p.possibleMoves(new Position(x, y), board));
				}
			}
		}

		ArrayList<Position> threat = new ArrayList();
		for (Position position : sources) {
			if(position.equals(kingPos)){
				threat.add(position);
			}
		}
		return threat;

	}
	public Position[] getPositions(Position pos){
		Position downRight = new Position(pos.x-1, pos.y-1);
		Position downMiddle= new Position(pos.x, pos.y-1);
		Position downLeft= new Position(pos.x+1, pos.y-1);
		Position right = new Position(pos.x-1, pos.y);;
		Position left= new Position(pos.x+1, pos.y-1);
		Position upRight= new Position(pos.x-1, pos.y+1);
		Position upMiddle = new Position(pos.x, pos.y+1);
		Position upLeft = new Position(pos.x+1, pos.y+1);
		Position[] positions = {downLeft,downRight, downMiddle, right, left, upLeft, upMiddle, upRight};
		return positions;
	}
}

