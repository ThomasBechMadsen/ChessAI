package pieces;

import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import controller.BoardController;
import game.Board;
import game.Program;

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

			piece = board.getBoard()[i][pos.y];
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

	//TODO SKAL REFAKTURERES HVIS DER BLIVER TID, der kan samles noget kode til en metode der kaldes igen og igen
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



		int y = pos.y;
		int dY = pos.y+1;
		possiblePin = false;
		//right
		for (int x = pos.x+1; x < 8; x++) {
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
			//
			//		
		}
		//left
		possiblePin = false;
		for (int x = pos.x-1; x >= 0; x--) {

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
		//up
		int	x = pos.x;
		possiblePin = false;
		for ( y = pos.y+1; y < 8; y++) {

			Piece	p = board.getBoard()[x][y];
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
		//down
		possiblePin = false;
		for (y = pos.y-1; y >= 0; y--) {

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
		//down right
		possiblePin = false;
		y = pos.y;
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

