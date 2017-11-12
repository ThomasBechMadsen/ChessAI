package pieces;

import java.util.Scanner;

import controller.BoardController;

public class Main {

	public static void main(String[] args) {
		BoardController b = new BoardController();
		Scanner input = new Scanner(System.in);
		b.printBoard();
		int x, y;
		while(true){
			System.out.println("Vælg et brik");
			System.out.print("række: ");
			y = input.nextInt();
			System.out.print("kolone: ");
			x = input.nextInt();
			b.selectChessPiece(x-1, y-1);
			if(b.getSelectedPiece() != null && b.getSelectedPiece().hasAtLeastOneMove){
				System.out.println("Hvor skal den rykkes");
				System.out.print("række: ");
				y = input.nextInt();
				System.out.print("kolone: ");
				x = input.nextInt();
				b.moveChessPiece(x-1, y-1);
			}
			b.printBoard();
		}		
	}

}
