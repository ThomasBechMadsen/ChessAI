package game;
import java.util.ArrayList;

import pieces.Piece;

public class Program {

	
	
	public static void main(String[] args) {
		//Initialize board
		Board board = new Board();
		AI ai = new AI(board);
		
		while(true){ // Game loop
			ai.playTurn();
			//Wait for opponent
		}
	}

}
