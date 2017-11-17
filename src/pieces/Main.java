package pieces;

import java.util.Scanner;

import controller.BoardController;

public class Main {

	public static void main(String[] args) {
		BoardController b = new BoardController();
		Scanner input = new Scanner(System.in);

		int x, y;
		
		b.selectChessPiece(2, 5);
		b.moveChessPiece(3, 7);
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
			if(b.getSelectedPiece() != null){
				System.out.println("\nHvor skal den rykkes");
				System.out.print("kolone: ");
				x = input.nextInt();
				System.out.print("række: ");
				y = input.nextInt();
				if(x>8 || y>8){
					x = 8; y=8 ;
				}
				
				b.moveChessPiece(x-1, y-1);
			}
			//for(Piece p : b.activeChessPieces)
				//System.out.print(p.getType() +" - ");
			b.printBoard();
		}	
	}

}
