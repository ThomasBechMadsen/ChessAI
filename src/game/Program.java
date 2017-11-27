package game;
import java.util.ArrayList;

import Utility.BoardPersistence;
import Utility.Position;
import algo.AlphaBeta;
import controller.BoardController;
import logic.Move;
import logic.MoveGenerator;
import pieces.Piece;

public class Program {



	public static void main(String[] args) {
//		//Initialize board
		Board b = new Board();
		b.generateStandardBoard();
 		
		BoardController bc = new BoardController(b.getBoard());
	//	new BoardPersistence().loadBoard(bc);
		
		AlphaBeta ab = new AlphaBeta();

		
		long timeStart = System.currentTimeMillis();
		int[] counters = ab.bestMove(bc.board, 8);
			System.out.println(counters[0]);
			System.out.println(counters[1]);
			
			double timeElapsed = ((double)(System.currentTimeMillis() -timeStart)/1000);
System.out.println(timeElapsed);

	}
}
