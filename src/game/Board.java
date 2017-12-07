package game;

import java.util.ArrayList;

import Utility.Position;
import controller.BoardController;
import pieces.*;

public class Board {
	private Piece[][] board;
	public boolean isWhiteTurn = true;
	public boolean isWhiteCheck = false;
	public boolean isBlackCheck = false;
	public Position whiteKing;
	public Position blackKing;
	boolean blackCheck = false;
	boolean whiteCheck = false;
	public ArrayList<Piece> whitePinned = new ArrayList<>();
	public ArrayList<Piece> blackPinned = new ArrayList<>();
	
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
		whiteKing = new Position(3, 0);
		board[4][0] = new Queen(true);
		board[5][0] = new Bishop(true);
		board[6][0] = new Knight(true);
		board[7][0] = new Rook(true);

		//Generate enemy pieces
		board[0][7] = new Rook(false);
		board[1][7] = new Knight(false);
		board[2][7] = new Bishop(false);
		board[3][7] = new King(false);
		blackKing = new Position(3, 7);
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

	public int evaluateBoard(){
		int score = 0;
		int friend = 0;
		int enemy = 0;
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Piece p = board[col][row];
				if(p != null){
					if(p.isWhite == Program.playerTurn){
						friend = friend + (p.getBaseValue() + p.getPositionalValue(row,col));
						//System.out.println("Score white "  +p.type+ " :"+(p.getBaseValue() + p.getPositionalValue(row,col)));
					}
					else{
						//Should get opposite positional value
						enemy = enemy + (p.getBaseValue() + p.getPositionalValue(row,col));
						//System.out.println("Score black " +p.type+ " :"+(p.getBaseValue() + p.getPositionalValue(row,col)));
					}
				}
			}
		}
		//		System.out.println("Friend score " + friend);
		//		System.out.println("Enemy score " + enemy);
		score = friend - enemy;
		return score;
	}

	public void calculateThreat(){
		whitePinned = new ArrayList<>();
		blackPinned = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j <	 8; j++) {
				Piece p = board[j][i];
				if(p!= null){
					p.calculateThreat(new Position(j,i), this);
				}
			}

		}
		if(blackThreat[whiteKing.x][whiteKing.y]){
			whiteCheck = true;
		
		}
		if(whiteThreat[blackKing.x][blackKing.y]){
			blackCheck = true;
		}
	
	}


}
