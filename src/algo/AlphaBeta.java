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
	public Move bMove;;

	BoardController bc = new BoardController(null);


	public  int[] bestMove(Board board, int depth){
		System.out.println(depth);

		int score = 	 alphaBetaMax(Integer.MIN_VALUE, Integer.MAX_VALUE, board, depth);
		//		System.out.println("depth 8 " + leafCounter);
		//		System.out.println("depth 7 " + depth1);
		//		System.out.println("depth 6 " + depth2);
		//		System.out.println("depth 5 " + depth3);
		//		System.out.println("depth 4 " + depth4);
		//		System.out.println("depth 3 " + depth5);
		//		System.out.println("depth 2 " + depth6);
		//		System.out.println("depth 1 " + depth7);
		//		System.out.println("depth 0 " + depth8);
		//		System.out.println("Bedste  " + score);
		return new int[] {moveCounter, leafCounter};
	}

	public int alphaBetaMax(int alpha, int beta, Board board, int depthLeft){
		Move theMove = null;

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
		if(depthLeft == 0 ){
			leafCounter++;
			return board.evaluateBoard();// evaluation;
		}
		ArrayList<Move> moves = MoveGenerator.generateMoves(true, board.getBoard()); 
		for (Move move : moves) {

			// Der laves en kopi af brættet til at lave trækket
			Board newBoard =  board.clone();

			bc.setBoard(newBoard.getBoard());

			// Brikken flyttes
			
			bc.setPlayerTurn(true);
			bc.selectChessPiece(move.getFrom().x, move.getFrom().y);
			bc.moveChessPiece(move.getTo(), move.getFrom());
			
			// Scoren findes igennem Minimizeren



			int	score = alphaBetaMin(alpha, beta, newBoard, depthLeft -1 );
			if(score >= beta){
				return beta;
			}
			if(score > alpha){
				theMove = move;
				alpha = score;
			}

		}
		if(theMove != null){

			bc.setBoard(board.getBoard());
			bc.setPlayerTurn(true);
			bc.selectChessPiece(theMove.getFrom().x, theMove.getFrom().y);
			bc.moveChessPiece(theMove.getTo(), theMove.getFrom());
		}
		return alpha;
	}

	public  int alphaBetaMin(int alpha, int beta, Board board, int depthLeft) {
		Move theMove = null;
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


		// Denne if kan gøres mere specifik, returnere -uendelig hvis dette er mate, 0 hvis remis;
		//System.out.println(depthLeft + " minimizer  counter =  " + counter++);
		if(depthLeft == 0){
			leafCounter++;


			return board.evaluateBoard();// evaluation;
		}

		ArrayList<Move> moves = MoveGenerator.generateMoves(false, board.getBoard()); 
		for (Move move : moves) {

			// Der laves en kopi af brættet til at lave trækket
			Board newBoard = board.clone(); 
			bc.setBoard(newBoard.getBoard());

			// Brikken flyttes
			bc.setPlayerTurn(false);

			bc.selectChessPiece(move.getFrom().x, move.getFrom().y);
			bc.moveChessPiece(move.getTo(), move.getFrom());

			// Scoren findes igennem Minimizeren

			int score = alphaBetaMax(alpha, beta, newBoard, depthLeft -1 );
			if(score <= alpha){

				return alpha;
			}
			if(score < beta){
				theMove = move;
				beta = score;
			}
			//  if (alpha >= beta) break;
		}
		if(theMove != null){
			
			bc.setPlayerTurn(false);
			bc.setBoard(board.getBoard());
			bc.selectChessPiece(theMove.getFrom().x, theMove.getFrom().y);
			bc.moveChessPiece(theMove.getTo(), theMove.getFrom());	
		

		}
		return beta;

	}


	
}
