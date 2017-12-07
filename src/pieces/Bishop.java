package pieces;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import controller.BoardController;
import game.Board;

public class Bishop extends Piece{

	public int[][] positionalValue = {
			{2,3,4,4,4,4,3,2},
			{4,7,7,7,7,7,7,4},
			{3,5,6,6,6,6,5,3},
			{3,5,7,7,7,7,5,3},
			{4,5,6,8,8,6,5,4},
			{4,5,5,-2,-2,5,5,4},
			{5,5,5,3,3,5,5,5},
			{0,0,0,0,0,0,0,0}
	};
	
	public Bishop(boolean friendly) {
		super(friendly);
		this.type = Type.Bishop;
		baseValue = 300;
		super.setPositionalValue(positionalValue);
	}
	
	@Override
	public ArrayList<Position> possibleMoves(Position pos,Board board){
		
		ArrayList<Position> moves = new ArrayList<Position>() ;
		Piece piece;
		int x,y;

		//Topleft
		x = pos.x;
		y = pos.y;
		while(true) {
			x--;
			y++;
			if(x < 0 || y>= 8)
				break;

			piece = board.getBoard()[x][y];
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

			piece = board.getBoard()[x][y];
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

			piece = board.getBoard()[x][y];
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

			piece = board.getBoard()[x][y];
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
	
	@Override
	public void calculateThreat(Position pos, Board board){

		boolean possiblePin = false;
		Piece possiblePinnedPiece = null;
		boolean[][] threat;
		List<Piece> pinned;


		if(this.isWhite){
			threat = board.whiteThreat;
			pinned = board.blackPinned;
		}
		else{
			threat = board.blackThreat;
			pinned = board.whitePinned;
		}



		//down right
		int y = pos.y;
		int x = pos.x;
		for ( x = pos.x+1; x < 8; x++) {
			y--;
			if(x > 7 || y < 0){
				break;
			}
			Piece p = board.getBoard()[x][y];
			if(!possiblePin){
				threat[x][y]= true;
				if(p != null){
					possiblePinnedPiece = p;
					threat[x][y]= true;
					possiblePin = true;
				}
			}else{
				threat[x][y] = false;
				if(p != null){
					if(p.type == Type.KING){
						pinned.add(possiblePinnedPiece);
						break;
					}
					else{
						break;
					}
				}
			}
		}
		//up right
		possiblePin = false;
		y = pos.y;
		for (x = pos.x+1; x < 8; x++) {
			y++;
			if(x > 7 || y > 7){
				break;
			}
			Piece p = board.getBoard()[x][y];
			if(!possiblePin){
				threat[x][y]= true;
				if(p != null){
					possiblePinnedPiece = p;
					threat[x][y]= true;
					possiblePin = true;
				}
			}else{
				threat[x][y] = false;
				if(p != null){
					if(p.type == Type.KING){
						pinned.add(possiblePinnedPiece);
						break;
					}
					else{
						break;
					}
				}
			}
		}
		//up left
		possiblePin = false;
		x = pos.x;
		for (y = pos.y+1; y < 8; y++) {
			x--;
			if(x < 0 || y > 7){
				break;
			}
			Piece p = board.getBoard()[x][y];
			if(!possiblePin){
				threat[x][y]= true;
				if(p != null){
					possiblePinnedPiece = p;
					threat[x][y]= true;
					possiblePin = true;
				}
			}else{
				threat[x][y] = false;
				if(p != null){
					if(p.type == Type.KING){
						pinned.add(possiblePinnedPiece);
						break;
					}
					else{
						break;
					}
				}
			}
		}
		//left down
		possiblePin = false;
		x = pos.x;
		for (y = pos.y-1; y >
		0; y--) {
			x--;
			if(x < 0 || y < 0){
				break;
			}
			Piece p = board.getBoard()[x][y];
			if(!possiblePin){
				threat[x][y]= true;
				if(p != null){
					possiblePinnedPiece = p;
					threat[x][y]= true;
					possiblePin = true;
				}
			}else{
				threat[x][y] = false;
				if(p != null){
					if(p.type == Type.KING){
						pinned.add(possiblePinnedPiece);
						break;
					}
					else{
						break;
					}
				}
			}
		}

		
	}
}
