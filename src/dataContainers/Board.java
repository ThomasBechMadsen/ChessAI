package dataContainers;

import Utility.Position;
import game.Program;
import pieces.*;

public class Board {
	private Piece[][] board;
	public boolean isWhiteTurn = true;
	public boolean isWhiteCheck = false;
	public boolean isBlackCheck = false;

	public boolean[][] whiteThreat = {
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false}
	};

	public boolean[][] blackThreat = {
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false}
	};
	
	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public Board(){
		board = new Piece[8][8];
	}
	
	public void move(Position oldPosition, Position newPosition){
		board[newPosition.x][newPosition.y] = board[oldPosition.x][oldPosition.y];
		board[oldPosition.x][oldPosition.y] = null;
	}
	
	public Piece getPieceAt(int x, int y){
		return board[x][y];
	}
	
	public void generateStandardBoard(){
		Piece[][] board = new Piece[8][8];
		//Generate friendly pieces
		board[0][0] = new Rook(true);
		board[1][0] = new Knight(true);
		board[2][0] = new Bishop(true);
		board[3][0] = new King(true);
		board[4][0] = new Queen(true);
		board[5][0] = new Bishop(true);
		board[6][0] = new Knight(true);
		board[7][0] = new Rook(true);
		
		//Generate enemy pieces
		board[0][7] = new Rook(false);
		board[1][7] = new Knight(false);
		board[2][7] = new Bishop(false);
		board[3][7] = new King(false);
		board[4][7] = new Queen(false);
		board[5][7] = new Bishop(false);
		board[6][7] = new Knight(false);
		board[7][7] = new Rook(false);
		
		//Generate pawns
		for(int i = 0; i < 8; i++){
			board[i][1] = new Pawn(true);
			board[i][6] = new Pawn(false);
		}
		
		this.board = board;
	}

	public void printBoard(){
		for(int i = 8; i >= 0; i--){
			if(i>0)
				System.out.printf("%d: ",(i));
			for(int j = 8 ; j > 0 ; j--){
				if(i == 0)
					System.out.printf("	%c	|  ",('i'-j));
				else if(Program.b.getBoard()[j-1][i-1] != null && i>=0  && i <= 8){
					if(Program.b.getBoard()[j-1][i-1].isWhite)
						System.out.printf(" %s	|  ", Program.b.getBoard()[j-1][i-1].getClass().getSimpleName().toUpperCase());
					else
						System.out.printf(" %s	|  ", Program.b.getBoard()[j-1][i-1].getClass().getSimpleName().toLowerCase());
				}
				else
					System.out.print("------	|  ");
			}
			System.out.println();
		}	
	}
}
