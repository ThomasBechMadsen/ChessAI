package dataContainers;

import Utility.Position;
import game.Program;
import pieces.Piece;

public class Move {

	private Position from;
	private Position to;
	private Piece movingPiece;
	private Piece target;
	private int Score;

	
	public Move(Position from, Position to){
		this.from = from;
		this.to = to;
		if(Program.b.getPieceAt(from.x, from.y) != null){
			movingPiece = Program.b.getPieceAt(from.x, from.y);
		}
		if(Program.b.getPieceAt(to.x, to.y) != null){
			target = Program.b.getPieceAt(to.x, to.y);
		}
	}
	
	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public Piece getTarget() {
		return target;
	}

	public int getScore() {
		return Score;
	}
 
	public String toString(){
		String targetString = "";
		if(target != null){
			targetString = " and beats " + target; 
		}
		else{
			targetString = " which is empty";
		}
		return movingPiece.getClass().getSimpleName() +" moves from " + from + " to " + to + targetString;
	}

}
