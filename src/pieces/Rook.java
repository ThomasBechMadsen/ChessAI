package pieces;

import java.util.ArrayList;
import java.util.List;

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
	boolean pieceDetected = false;

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
	@Override
	public void calculateThreat(Position pos, Board board) {
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
	}
}



