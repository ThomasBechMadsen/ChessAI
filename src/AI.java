import java.util.ArrayList;

import pieces.Piece;

public class AI {

	Board board;
	
	public AI(Board board){
		if(board == null){
			this.board = board.generateStandardBoard();
		}
		else{
			this.board = board;
		}
	}
	
	public void playTurn(){
		//Get board from opponent - through WinBoard?
		//Get pieces
		ArrayList<Piece> pieces = getActivePieces();
		//Generate moves
		generateMoves(board);
		//MinMax to victory!
		
		//Move
	}
	
	void generateMoves(ArrayList<Piece> pieces){
		
	}
	
	/**
	 * Returns all 'living' pieces on the board
	 */
	public ArrayList<Piece> getActivePieces(Board board){
		ArrayList<Piece> result;
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				if(board.board[x][y] != null){
					result.add(board[x][y]);
				}
			}
		}
		return result;
	}
}
