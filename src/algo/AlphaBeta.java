package algo;

import controller.BoardController;
import game.Board;
import logic.Move;
import logic.MoveGenerator;
import pieces.Piece;

public class AlphaBeta {

	static int counter = 0;

	static BoardController bc = new BoardController(null);
	static int score = 0;

	public  int bestMove(Board board, int depth){
	
		
		score = alphaBetaMax(Integer.MIN_VALUE, Integer.MAX_VALUE, board, depth);
		return score;
	}

	public static int alphaBetaMax(int alpha, int beta, Board board, int depthLeft){

		//System.out.println(depthLeft + " maximizer " + " counter = " + counter++);

		// Denne if kan gøres mere specifik, returnere -uendelig hvis dette er mate, 0 hvis remis;
		if(depthLeft == 0){
			
			return board.evaluateBoard();// evaluation;
		}

		for (Move moves : MoveGenerator.generateMoves(true, board.getBoard())) {
			System.out.println(moves);
			
			// Der laves en kopi af brættet til at lave trækket
			Board newBoard =  board.clone();
			
			bc.setBoard(newBoard.getBoard());
			
			// Brikken flyttes
			bc.selectChessPiece(moves.getFrom().x, moves.getFrom().y);
			bc.moveChessPiece(moves.getTo(), moves.getFrom());
			bc.printBoard();
			// Scoren findes igennem Minimizeren
			score = alphaBetaMin(alpha, beta, newBoard, depthLeft -1 );
			System.out.println(score);
			if(score >= beta){
				return beta;
			}
			if(score > alpha){
				alpha = score;
			}
		}
		return alpha;
	}

	public static int alphaBetaMin(int alpha, int beta, Board board, int depthLeft) {
		// Denne if kan gøres mere specifik, returnere -uendelig hvis dette er mate, 0 hvis remis;
		//System.out.println(depthLeft + " minimizer  counter =  " + counter++);
		if(depthLeft == 0 ){
			
			return board.evaluateBoard();// evaluation;
		}

		//				System.out.println(MoveGenerator.generateMoves(false, board.getBoard()).size());
		for (Move moves : MoveGenerator.generateMoves(false, board.getBoard())) {
			
			// Der laves en kopi af brættet til at lave trækket
			Board newBoard = board.clone(); 
	//		bc.setBoard(board);
			// Brikken flyttes
			bc.selectChessPiece(moves.getFrom().y+1, moves.getFrom().x+1);
			bc.moveChessPiece(moves.getTo(), moves.getFrom());

			// Scoren findes igennem Minimizeren
			score = alphaBetaMax(alpha, beta, newBoard, depthLeft -1 );
			if(score <= alpha){
				return alpha;
			}
			if(score < beta){
				beta = score;
			}
		}
		return beta;

	}



	//	
	//	int alphaBetaMax( int alpha, int beta, int depthleft ) {
	//		   if ( depthleft == 0 ) return evaluate();
	//		   for ( all moves) {
	//		      score = alphaBetaMin( alpha, beta, depthleft - 1 );
	//		      if( score >= beta )
	//		         return beta;   // fail hard beta-cutoff
	//		      if( score > alpha )
	//		         alpha = score; // alpha acts like max in MiniMax
	//		   }
	//		   return alpha;
	//		}
	//		 
	//		int alphaBetaMin( int alpha, int beta, int depthleft ) {
	//		   if ( depthleft == 0 ) return -evaluate();
	//		   for ( all moves) {
	//		      score = alphaBetaMax( alpha, beta, depthleft - 1 );
	//		      if( score <= alpha )
	//		         return alpha; // fail hard alpha-cutoff
	//		      if( score < beta )
	//		         beta = score; // beta acts like min in MiniMax
	//		   }
	//		   return beta;

	//	int bestMove(Node node){
	//		if(node.isLeaf()){
	//			System.out.println("leaf :" + node.value);
	//			return node.value;
	//		}
	//
	//		if(node.isMax){
	//			while(node.alpha < node.beta){
	//				for(Node child : node.getChildren()){
	//					if(node.alpha > node.beta)
	//						break;
	//					child.alpha = node.alpha;
	//					child.beta = node.beta;
	//					int v = bestMove(child);
	//					if (v > node.alpha){
	//						node.alpha = v;
	//						node.bestMove = child;
	//					}
	//				}
	//				return node.alpha;
	//			}
	//		}
	//		else{
	//			while(node.alpha < node.beta){
	//				for(Node child : node.getChildren()){
	//					if(node.alpha > node.beta)
	//						break;
	//					child.alpha = node.alpha;
	//					child.beta = node.beta;
	//					int v = bestMove(child);
	//					if (v < node.beta){
	//						node.beta = v;
	//						node.bestMove = child;
	//					}
	//				}
	//				return node.beta;
	//			}
	//		}
	//		return 0;
	//	}
	//	
}
