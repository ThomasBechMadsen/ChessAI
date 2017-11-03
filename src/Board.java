import java.util.ArrayList;
import java.util.List;

import Utility.Position;
import pieces.Piece;

public class Board {
	Piece[][] board;
	List<Piece> pieces = new ArrayList<Piece>();
	public Board(){
		board = new Piece[8][8];
		initial();
	}
	
	public boolean move(Piece brik, Position oldPosition, Position newPosition){
		return false;
	}
	
	
	void initial(){
		for(Piece p : pieces){
			if(board[p.position.y][p.position.x] == null)
				board[p.position.y][p.position.x] = p;
		}
	}
	
	void update(List<Piece> pieces){
			for(Piece p : pieces){
				if(board[p.position.y][p.position.x] == null)
					board[p.position.y][p.position.x] = p;
			}
	}
	
}
