package logic;

import Utility.Position;
import pieces.Piece;

public class Move {

	private Position from;
	private Position to;
	private Piece movingPiece;
	private Piece target;
	private int Score;

	
	public Move(Position from, Position to, Piece movingPiece, Piece target){
		this.from = from;
		this.to = to;
		this.movingPiece = movingPiece;
		this.target = target;
	}
	
	public Position getFrom() {
		return from;
	}



	public void setFrom(Position from) {
		this.from = from;
	}



	public Position getTo() {
		return to;
	}



	public void setTo(Position to) {
		this.to = to;
	}



	public Piece getMovingPiece() {
		return movingPiece;
	}



	public void setMovingPiece(Piece movingPiece) {
		this.movingPiece = movingPiece;
	}



	public Piece getTarget() {
		return target;
	}



	public void setTarget(Piece target) {
		this.target = target;
	}



	public int getScore() {
		return Score;
	}



	public void setScore(int score) {
		Score = score;
	}

 
	public String toString(){
		String targetString = "";
		if(target != null){
			targetString = " and beats " + target; 
		}
		else{
			targetString = " which is empty";
		}
		return movingPiece.type +" moves from " + from + " to " + to + targetString;
	}

}
