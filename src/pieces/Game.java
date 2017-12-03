package pieces;

import java.util.Scanner;
import Utility.Position;
import algo.AlphaBeta;
import controller.BoardController;
import game.Board;
import game.Program;
import logic.Move;

public class Game {
	BoardController bc; 
	Scanner scanner;
	AlphaBeta ab;
	
	int x1, y1, x2, y2;;
	
	public Game(){
		Program.b = new Board();
		Program.b.generateStandardBoard();
		bc = new BoardController();
		scanner = new Scanner(System.in);
		ab = new AlphaBeta();
	}
	
	public void startGame(){
		while(true){
			//TODO: Check kings
			
			
			bc.printBoard();
			if(Program.playerTurn){
				System.out.print("Vælg et brik: ");
				x1 = getXInput();
				y1 = getYInput();
				System.out.print("Hvor skal den rykkes: ");
				x2 = getXInput();
				y2 = getYInput();
				Move m = new Move(new Position(x1,y1), new Position(x2,y2), Program.b.getPieceAt(x1, y1), Program.b.getPieceAt(x2, y2));
				try {
					bc.execute(m, Program.playerTurn);
					Program.playerTurn = !Program.playerTurn;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					long temp = System.currentTimeMillis();
					System.out.println(Program.playerTurn);
					ab.bestMove(6, Program.playerTurn);
					System.out.println(System.currentTimeMillis() - temp);
					Program.playerTurn = !Program.playerTurn;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
				
		}
	}
	
	
	/**
	 *  sørger for at man kan indtaste positioner på en rigtig bræt system fx e4 og konvertere til array indekser
	 */
	int getXInput(){
		String pos= scanner.next();
		int x = 8;
		switch(pos.substring(0, 1).toLowerCase()){
			case("a"): x -= 1; break;
			case("b"): x -= 2; break;
			case("c"): x -= 3; break;
			case("d"): x -= 4; break;
			case("e"): x -= 5; break;
			case("f"): x -= 6; break;
			case("g"): x -= 7; break;
			case("h"): x -= 8; break;
			default: {
				System.out.println("Ugyldigt input");break;				
			}
		}
		return x;
	}
	
	int getYInput(){
		String pos= scanner.next();
		int y = -1;
		try{
			y = Integer.parseInt(pos.replaceAll(" ", ""))-1;
			if(y > 8 || y < 1){
				System.out.println("ugyldig input");
			}
		} catch (Exception e){
			System.out.println("ugyldig input");
		}
		return y;
	}
	
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
	}

}
