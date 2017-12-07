package game;
import java.util.ArrayList;
import java.util.Scanner;

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
	static BoardController bc;
	static AlphaBeta ab;
	
	public static void main(String[] args) {
		b = new Board();
		b.generateStandardBoard();
		bc = new BoardController();	
		ab = new AlphaBeta();

		//playAIvsAI();
		playHumanVsAI(true);
	}
	
	static void playAIvsAI(){
		while(!bc.isGameOver()){
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

	static void playHumanVsAI(boolean playerIsWhite){
		bc.printBoard();
		while(!bc.isGameOver()){
			
			if(playerTurn == playerIsWhite){
				while(true){
					try{
						bc.execute(getPlayerMove(), playerTurn);
						break;
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			else{
				try {
					ab.bestMove(5, playerTurn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}	
			}
			bc.printBoard();
			playerTurn = !playerTurn;
		}
	}
	
	static Move getPlayerMove(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose a piece to move: ");
		String coords1 = sc.nextLine();
		coords1.trim();
		int x1 = xCoord(coords1.charAt(0));
		int y1 = yCoord(coords1.charAt(1));
		
		System.out.println("Choose destination: ");
		String coords2 = sc.nextLine();
		coords2.trim();
		int x2 = xCoord(coords2.charAt(0));
		int y2 = yCoord(coords2.charAt(1));
		
		return new Move(new Position(x1, y1), new Position(x2, y2),
						b.getPieceAt(x1, y1), b.getPieceAt(x2, y2));
	}
	
	static int xCoord(char in){
		switch(in){
			case 'a': return 7;
			case 'b': return 6;
			case 'c': return 5;
			case 'd': return 4;
			case 'e': return 3;
			case 'f': return 2;
			case 'g': return 1;
			case 'h': return 0;
		}
		return -1;
	}
	
	static int yCoord(char in){
		try{
			return Integer.parseInt(Character.toString(in))-1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
}