package pieces;

import java.util.Scanner;

import Utility.Position;
import controller.BoardController;
import game.Board;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		board.generateStandardBoard();
		BoardController b = new BoardController(board.getBoard() );
		b.board.generateStandardBoard();
		Scanner input = new Scanner(System.in);

		int x, y;
		Position pos = new Position(2,5);
		b.selectChessPiece(2,5);
		Position newPos = new Position(3, 7);
		b.moveChessPiece(newPos, pos);
		//b.selectChessPiece(1, 6);
		b.printBoard();
		while(true){
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
				Position newNewPos = new Position(x-1, y-1);
				b.moveChessPiece(newNewPos, oldPos);
			}
			//for(Piece p : b.activeChessPieces)
				//System.out.print(p.getType() +" - ");
			b.printBoard();
		}	
	}

}
