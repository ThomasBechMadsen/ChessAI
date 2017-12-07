package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controller.BoardController;
import game.Board;
import game.Program;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class BoardPersistence {

	public void loadBoard(){
		Piece[][] board = Program.b.getBoard();

		try {
			BufferedReader br =  new BufferedReader(new FileReader("board.txt"));
			String line = "";
			int rows = 0;
			while(((line = br.readLine()) != null)){
				if(line.length() > 8){
					System.err.println("Indholdet af filen er ikke et 8x8 skakbræt");
					break;
				}
				System.out.println(line);
				if(rows <8){
					for(int i = 0; i< 8; i++){
						switch (line.charAt(i)) {
						case 'p' : Piece pawnEnemy = new Pawn(false); 
						board[i][rows]=pawnEnemy;
						break;
						case 'P' :	Piece pawn = new Pawn(true); 
						board[i][rows]=pawn;
						break;
						case 'r' :Piece rookEnemy = new Rook(false); 
						board[i][rows]=rookEnemy;
						break;
						case 'R' :Piece rook = new Rook(true); 
						board[i][rows]=rook;	
						break;
						case 'b' :Piece bishopEnemy = new Bishop(false); 
						board[i][rows]=bishopEnemy;
						break;
						case 'B' :	Piece bishop = new Bishop(true); 
						board[i][rows]=bishop;
						break;
						case 'n' :Piece knightEnemy = new Knight(false); 
						board[i][rows]=knightEnemy;
						break;
						case 'N' :	Piece knight = new Knight(true); 
						board[i][rows]=knight;
						break;
						case 'q' :Piece queenEnemy = new Queen(false); 
						board[i][rows]=queenEnemy;
						break;
						case 'Q' :	Piece queen = new Queen(true); 
						board[i][rows]=queen;
						break;
						case 'k' :Piece kingEnemy = new King(false); 
						Program.b.blackKing = new Position(i, rows);
						board[i][rows]=kingEnemy;
						break;
						case 'K' :	Piece king = new King(true); 
						board[i][rows]=king;
						Program.b.whiteKing = new Position(i, rows);
						break;

						default:
							break;
						}   
					}
				}
				rows++;
				if(line.equalsIgnoreCase("white")){
					Program.playerTurn = true;
				}else if(line.equalsIgnoreCase("black")){
					Program.playerTurn = false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


Program.b.calculateThread();
		

	}
}
