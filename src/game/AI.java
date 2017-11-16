package game;
import java.util.ArrayList;

import pieces.*;

public class AI {

	final int SEARCH_DEPTH = 3;
	
	Board board;
	
	public AI(Board board){
		if(board == null){
			this.board = new Board();
			this.board.generateStandardBoard();
		}
		else{
			this.board = board;
		}
	}
	
	public void playTurn(){
		//Get board from opponent - through WinBoard?
		
		minMax(SEARCH_DEPTH, true);
		
		//Move
	}
	
	int minMax(int depth, boolean maxing){
		//Get all moves
		
		if(depth == 0){
			return evaluateBoard(); 
		}
		
		if(maxing){ //maximizing
			int max = Integer.MIN_VALUE;
			
			//For every move
				//make move
					//Compare to next depth level moves
					//If higher score save move and update max
				//undo move
			//For end
			return max;
		}
		else{ //minimizing
			int min = Integer.MAX_VALUE;
			//Do inverse of max
			
			return min;
		}
	}
	
	
	int evaluateBoard(){
		int score = 0;
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				Piece p = board.getPieceAt(x, y);
				if(p != null){
					if(p.friendly){
						score += p.getBaseValue();//Should be more complex later
					}
					else{
						score -= p.getBaseValue();
					}
				}
			}
		}
		return score;
	}
	
}
