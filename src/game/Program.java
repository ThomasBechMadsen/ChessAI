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

		bc.printBoard();
		
		while(!bc.isGameOver){
			long startTime = System.currentTimeMillis();
			ab.bestMove(bc.board, 4,bc.isWhiteTurn);
			System.out.println(System.currentTimeMillis() -startTime);
			if(bc.isWhiteTurn){
				bc.setPlayerTurn(false);
			}else{
				bc.setPlayerTurn(true);
				
			}
			bc.printBoard();
		}

	}
}
