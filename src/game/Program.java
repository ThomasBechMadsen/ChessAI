package game;
import java.util.ArrayList;

import Utility.Position;
import algo.AlphaBeta;
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
		AlphaBeta ab = new AlphaBeta();

	
		System.out.println(ab.bestMove(board, 1));


	}

}
