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

	public static boolean playerTurn = true; //White always starts
	public static Board b;

	public static void main(String[] args) {
//		//Initialize board
		b = new Board();
		b.generateStandardBoard();
 		
		BoardController bc = new BoardController();
	//	new BoardPersistence().loadBoard(bc);
		
		AlphaBeta ab = new AlphaBeta();


		
		while(!bc.isGameOver()){
			bc.printBoard();
			try {
				ab.bestMove(5, playerTurn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			bc.printBoard();
			playerTurn = !playerTurn;
		}

	}
}
