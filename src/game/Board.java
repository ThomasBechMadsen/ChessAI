package game;

import Utility.Position;
import pieces.*;

public class Board {
	private Piece[][] board;
	public boolean isWhiteTurn = true;
	public boolean isMate = false;

	
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
	}
	
	public int evaluateBoard(){
		int score = 0;
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Piece p = board[col][row];
				if(p != null){
					if(p.isWhite){
						score += (p.getBaseValue() + p.getPositionalValue(row,col));
						//System.out.println("Score white "  +p.type+ " :"+(p.getBaseValue() + p.getPositionalValue(row,col)));
					}
					else{
						//Should get opposite positional value
						score -= (p.getBaseValue() + p.getPositionalValue(row,col));
						//System.out.println("Score black " +p.type+ " :"+(p.getBaseValue() + p.getPositionalValue(row,col)));
					}
				}
			}
		}
		//System.out.println(score);
		return score;
	}

	public boolean getIsMate() {
		// TODO Auto-generated method stub N�r der er skat mat skal denne s�ttes
		return isMate;
	}
	public Board clone(){
		Board clonedBoard = new Board();
		Piece[][] newBoard = new Piece[8][8];
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard.length; j++) {
				newBoard[j][i] = this.getBoard()[j][i];
			}
		}
		clonedBoard.setBoard(newBoard);
		return clonedBoard;
		
	}



	
}
