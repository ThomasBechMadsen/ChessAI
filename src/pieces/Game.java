package pieces;

import java.util.Scanner;
import Utility.Position;
import algo.AlphaBeta;
import controller.BoardController;
import game.Board;

public class Game {
	Board board;
	BoardController bc; 
	Scanner scanner;
	AlphaBeta ab;
	
	int x = -1, y = -1;
	
	public Game(){
		board = new Board();
		board.generateStandardBoard();
		bc = new BoardController(board.getBoard());
		scanner = new Scanner(System.in);
		ab = new AlphaBeta();
	}
	
	public void startGame(){
		while(true){
			bc.printBoard();
			if(bc.isWhiteTurn){
				System.out.print("Vælg et brik: ");
				getInput();
				bc.selectChessPiece(x, y);
				Position oldPos = new Position(x, y);
				if(bc.getSelectedPiece() != null){
					System.out.print("Hvor skal den rykkes: ");
					getInput();
					try{
						Position newPos = new Position(x, y);
						if(bc.moveChessPiece(newPos, oldPos))
							bc.setPlayerTurn(!bc.isWhiteTurn);
						else
							System.out.println("Træk ikke muligt");
						//bc.printBoardR();
					}catch(Exception e){
						e.printStackTrace();
					}	
				}
			}else{
				ab.bestMove(bc.board, 2, bc.isWhiteTurn);
				bc.setPlayerTurn(!bc.isWhiteTurn);
				//bc.printBoardR();
			}	
			if(bc.isGameOver){
				bc.setPlayerTurn(true);
			}
				
		}
	}
	
	
	/**
	 *  sørger for at man kan indtaste positioner på en rigtig bræt system fx e4 og konvertere til array indekser
	 */
	void getInput(){
		String pos= scanner.next();
		x = 8;
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
		try{
			y = Integer.parseInt(pos.substring(1, 2))-1;
			if(y > 8 || y < 1){
				System.out.println("ugyldig input");
			}
		} catch (Exception e){
			System.out.println("ugyldig input");
		}
	}
	
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
	}

}
