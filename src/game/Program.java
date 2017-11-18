package game;
import java.util.ArrayList;

import controller.BoardController;
import logic.Move;
import logic.MoveGenerator;
import pieces.Piece;

public class Program {

	
	
	public static void main(String[] args) {
		//Initialize board
		Board board = new Board();
		board.generateStandardBoard();
		BoardController bc = new BoardController(board.getBoard());
		
		
		bc.printBoard();
		MoveGenerator mg = new MoveGenerator(bc);
		
		
		
		mg.generateMoves(bc.isWhiteTurn);
		System.out.println(mg.moves.size());
	
	for(Move move : mg.moves){
		System.out.println(move);
	}
	}
	

}
