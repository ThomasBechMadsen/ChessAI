package algo;

import java.util.ArrayList;

import controller.BoardController;
import game.Board;
import logic.Move;
import logic.MoveGenerator;
import pieces.Piece;

public class AlphaBeta {

	 public int moveCounter = 0;
	 public int leafCounter = 0;
	 ArrayList<Integer> nodesPerDepth;
	 int depth1=0, depth2=0,depth3=0,depth4=0,depth0=0, depth5=0,depth6=0,depth7=0, depth8 = 0;

	BoardController bc = new BoardController(null);
	static int score = 0;

	public  int[] bestMove(Board board, int depth){
System.out.println(depth);

		score = alphaBetaMax(Integer.MIN_VALUE, Integer.MAX_VALUE, board, depth);
		System.out.println("depth0 " +depth0);
		System.out.println("depth1 " +depth1);
		System.out.println("depth2 " +depth2);
		System.out.println("depth3 " +depth3);
		System.out.println("depth4 " +depth4);
		System.out.println("depth5 " +depth5);
		System.out.println("depth6 " +depth6);
		System.out.println("depth7 " +depth7);
		System.out.println("depth8 " +depth8);

		return new int[] {moveCounter, leafCounter};
	}

	public int alphaBetaMax(int alpha, int beta, Board board, int depthLeft){
		if(depthLeft == 8){
			depth8++;
		}
		if(depthLeft == 7){
			depth7++;
		}
		if(depthLeft == 6){
			depth6++;
		}
		if(depthLeft == 5){
			depth5++;
		}
		if(depthLeft == 4){
			depth4++;
		}
		if(depthLeft == 3){
			depth3++;
		}
		if(depthLeft == 2){
			depth2++;
		}
		if(depthLeft == 1){
			depth1++;
		}
		
	moveCounter++;
		//System.out.println(depthLeft + " maximizer " + " counter = " + counter++);

		// Denne if kan gøres mere specifik, returnere -uendelig hvis dette er mate, 0 hvis remis;
		if(depthLeft == 0 ){
			leafCounter++;
			return board.evaluateBoard();// evaluation;
		}
		ArrayList<Move> moves = MoveGenerator.generateMoves(true, board.getBoard()); 
		for (Move move : moves) {
//			System.out.println(" Farve check i alphabetaMmax() " +move.getMovingPiece().isWhite);

			// Der laves en kopi af brættet til at lave trækket
			Board newBoard =  board.clone();

			bc.setBoard(newBoard.getBoard());
			//	System.out.println(newBoard.getBoard());
			// Brikken flyttes
			bc.setPlayerTurn(true);
			bc.selectChessPiece(move.getFrom().x, move.getFrom().y);
			bc.moveChessPiece(move.getTo(), move.getFrom());
//			bc.printBoard();
			// Scoren findes igennem Minimizeren

			score = alphaBetaMin(alpha, beta, newBoard, depthLeft -1 );
			if(score >= beta){
				return beta;
			}
			if(score > alpha){
				alpha = score;
			}
//			  if (alpha >= beta) {
//				  System.out.println("cut off");
//				  break;
//			 
//			  }
		}

		return alpha;
	}

	public  int alphaBetaMin(int alpha, int beta, Board board, int depthLeft) {
		if(depthLeft == 8){
			depth8++;
		}
		if(depthLeft == 7){
			depth7++;
		}
		if(depthLeft == 6){
			depth6++;
		}
		if(depthLeft == 5){
			depth5++;
		}
		if(depthLeft == 4){
			depth4++;
		}
		if(depthLeft == 3){
			depth3++;
		}
		if(depthLeft == 2){
			depth2++;
		}
		if(depthLeft == 1){
			depth1++;
		}
		moveCounter++;

		// Denne if kan gøres mere specifik, returnere -uendelig hvis dette er mate, 0 hvis remis;
		//System.out.println(depthLeft + " minimizer  counter =  " + counter++);
		if(depthLeft == 0){
			leafCounter++;
			return board.evaluateBoard();// evaluation;
		}

		ArrayList<Move> moves = MoveGenerator.generateMoves(false, board.getBoard()); 
		for (Move move : moves) {
//			System.out.print(move);
//			System.out.println(" Farve check i alphabetaMin() " +move.getMovingPiece().isWhite);
			// Der laves en kopi af brættet til at lave trækket
			Board newBoard = board.clone(); 
			bc.setBoard(newBoard.getBoard());
			// 	System.out.println(newBoard.getBoard());
			// Brikken flyttes
			bc.setPlayerTurn(false);
			bc.selectChessPiece(move.getFrom().x, move.getFrom().y);
			bc.moveChessPiece(move.getTo(), move.getFrom());
//			bc.printBoard();

			// Scoren findes igennem Minimizeren

			score = alphaBetaMax(alpha, beta, newBoard, depthLeft -1 );
			if(score <= alpha){
				return alpha;
			}
			if(score < beta){
				beta = score;
			}
			//  if (alpha >= beta) break;
		}

		return beta;

	}


	//	
}
