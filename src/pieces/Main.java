package pieces;

import java.util.Scanner;

import Utility.Position;
import algo.AlphaBeta;
import controller.BoardController;
import game.Board;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		board.generateStandardBoard();
		BoardController b = new BoardController(board.getBoard());
		b.board.generateStandardBoard();
		Scanner input = new Scanner(System.in);
		AlphaBeta ab = new AlphaBeta();
		
		int x, y;
		b.printBoard();
		while(true){
			if(b.isWhiteTurn){
				System.out.println("Score: "+b.board.evaluateBoard());
				System.out.println("Vælg et brik");
				System.out.print("kolone: ");
				x = input.nextInt();
				System.out.print("række: ");
				y = input.nextInt();
				if(x>8 || y>8){
					x = 8; y=8 ;
				}
				b.selectChessPiece(x-1, y-1);
				Position oldPos = new Position(x-1, y-1);
				if(b.getSelectedPiece() != null){
					System.out.println("\nHvor skal den rykkes");
					System.out.print("kolone: ");
					x = input.nextInt();
					System.out.print("række: ");
					y = input.nextInt();
					if(x>8 || y>8){
						x = 8; y=8 ;
					}
					Position newPos = new Position(x-1, y-1);
					b.moveChessPiece(newPos, oldPos);
				}
			}
			else{
				ab.bestMove(b.board, 2, b.isWhiteTurn);
			}
			
			b.setPlayerTurn(!b.isWhiteTurn);
			//for(Piece p : b.activeChessPieces)
				//System.out.print(p.getType() +" - ");
			b.printBoard();
		}	
	}

}
