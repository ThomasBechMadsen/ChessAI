package algo;

import java.util.ArrayList;

import controller.BoardController;
import game.Board;
import game.Program;
import logic.Move;
import logic.MoveGenerator;
import pieces.Piece;

public class AlphaBeta {

	public int moveCounter = 0;
	public int leafCounter = 0;
	ArrayList<Integer> nodesPerDepth;
	int depth1=0, depth2=0,depth3=0,depth4=0,depth0=0, depth5=0,depth6=0,depth7=0, depth8 = 0;
	public Move bMove;
	public boolean isWhite = false;

	BoardController bc = new BoardController();


	public  int[] bestMove(int depth, boolean isWhiteTurn) throws Exception{
		isWhite = isWhiteTurn;
		System.out.println(depth);

		int score = alphaBetaMax(Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
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

	public int alphaBetaMax(int alpha, int beta, int depthLeft) throws Exception{
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
			return Program.b.evaluateBoard();// evaluation;
		}
		ArrayList<Move> moves = MoveGenerator.generateMoves(isWhite, Program.b.getBoard()); 
		for (Move move : moves) {

			if(!bc.execute(move, isWhite)){
				throw new Exception("Illegal move: " + move);
			}
			
			int	score = alphaBetaMin(alpha, beta, depthLeft -1 );
			
			if(!bc.undo(move, isWhite)){
				System.out.println("Illegal undo: " + move);
			}
			
			if(score >= beta){
				return beta;
			}
			if(score > alpha){
				theMove = move;
				alpha = score;
			}

		}
		if(theMove != null){

			System.out.println("The long awaited move: " + theMove);
			bc.execute(theMove, isWhite);
		}
		return alpha;
	}

	public  int alphaBetaMin(int alpha, int beta, int depthLeft) throws Exception {
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


			return Program.b.evaluateBoard();// evaluation;
		}

		ArrayList<Move> moves = MoveGenerator.generateMoves(isWhite, Program.b.getBoard()); 
		for (Move move : moves) {
			
			if(!bc.execute(move, isWhite)){
				throw new Exception("Illegal move: " + move);
			}

			int score = alphaBetaMax(alpha, beta, depthLeft -1 );
			
			if(!bc.undo(move, isWhite)){
				System.out.println("Illegal undo detected!");
			}
			
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
			
			System.out.println("The long awaited move: " + theMove);
			bc.execute(theMove, isWhite);
		}
		return beta;

	}


	
}
