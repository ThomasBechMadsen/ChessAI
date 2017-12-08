package pieces;

import java.util.ArrayList;

import Utility.Position;
import dataContainers.Board;
import game.BoardController;

public class Pawn extends Piece{
	
	public Pawn(boolean friendly) {
		super(friendly);
	}

	@Override
	public ArrayList<Position> possibleMoves(Position pos,Board board){
	//	System.out.println("DEBUG PAWN start xy = " + pos);
		ArrayList<Position> moves = new ArrayList<Position>();
	//	int[] e = BoardController.Instance.enPassantMove;
		Piece c1, c2;

		// White team move
		if (isWhite) {
			//Diagonal Left Move
			if(pos.x != 0 && pos.y != 7){
//				if (e [0] == pos.x - 1 && e [1] == pos.y + 1) 
//					moves.add(new Position(pos.x -1, pos.y+1));
					//moves [pos.x - 1][ pos.y + 1] = true;

				c1 = board.getBoard()[pos.x -1][ pos.y +1];
				if(c1 != null && !c1.isWhite){
					moves.add(new Position(pos.x -1, pos.y+1));
					//moves [pos.x -1][pos.y +1] = true;
				}
			}

			//Diagonal Right Move
			if(pos.x != 7 && pos.y != 7){

//				if (e [0] == pos.x + 1 && e [1] == pos.y + 1) 
//					moves.add(new Position(pos.x +1, pos.y+1));
					//moves [pos.x + 1][ pos.y + 1] = true;
				
				c1 = board.getBoard()[pos.x +1][ pos.y +1];
				if(c1 != null && !c1.isWhite){
					moves.add(new Position(pos.x +1, pos.y+1));
					//moves [pos.x + 1][ pos.y +1] = true;
				}
			}

			//Middel 
			if (pos.y != 7) {
				c1 = board.getBoard()[pos.x][ pos.y + 1];
				if (c1 == null)
					moves.add(new Position(pos.x, pos.y+1));
					//moves [pos.x][ pos.y + 1] = true;
			}

			//Middle on first move
			if (pos.y == 1) {
				c1 = board.getBoard()[pos.x][ pos.y + 1];
				c2 = board.getBoard() [pos.x][ pos.y + 2];
				if (c1 == null & c2 == null)
					moves.add(new Position(pos.x , pos.y+2));
					//moves [pos.x][pos.y + 2] = true;
			}
		}
			
		// Black team move
		else {
			//Diagonal Left Move
			if(pos.x != 0 && pos.y != 0){
//				if (e [0] == pos.x - 1 && e [1] == pos.y - 1) 
//					moves.add(new Position(pos.x -1, pos.y-1));
					//moves [pos.x - 1][ pos.y - 1] = true;
				
				c1 = board.getBoard()[pos.x -1][ pos.y -1];
				if(c1 != null && c1.isWhite){
					moves.add(new Position(pos.x -1, pos.y-1));
					//moves [pos.x -1][ pos.y -1] = true;
				}
			}

			//Diagonal Right Move
			if(pos.x != 7 && pos.y != 0){
//				if (e [0] == pos.x + 1 && e [1] == pos.y - 1) 
//					moves.add(new Position(pos.x +1, pos.y-1));
					//moves [pos.x + 1][ pos.y - 1] = true;
				c1 = board.getBoard()[pos.x +1][ pos.y -1];
				if(c1 != null && c1.isWhite){
					moves.add(new Position(pos.x +1, pos.y-1));
					//moves [pos.x + 1][ pos.y -1] = true;
				}
			}

			//Middel 
			if (pos.y != 0) {
				c1 = board.getBoard() [pos.x][ pos.y - 1];
				if (c1 == null)
					moves.add(new Position(pos.x , pos.y-1));
					//moves [pos.x][ pos.y - 1] = true;
			}

			//Middle on first move
			if (pos.y == 6) {
				c1 = board.getBoard() [pos.x][ pos.y - 1];
				c2 = board.getBoard() [pos.x][ pos.y - 2];
				if (c1 == null & c2 == null)
					moves.add(new Position(pos.x, pos.y-2));
					//moves [pos.x][ pos.y - 2] = true;
			}
		}
		return moves;
	}
	
	@Override
	public void calculateThreat(Position pos, Board b){
	    if(pos.x > 0){
	    //	if(board)
	    	
	    }
		
		
	}

	@Override
	public int getBaseValue() {
		return PieceBaseValue.pawn;
	}

	@Override
	public int getPositionalValue(int x, int y) {
		if(isWhite){
			return PieceSquareTables.pawn[7-x][7-y]; //Should give the opposite positional value
		}
		
		return PieceSquareTables.pawn[x][y];
	}
}
