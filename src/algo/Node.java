package algo;

import java.util.ArrayList;

import game.Board;

public class Node {

	public int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;
	public Node bestMove = null;
	public boolean isMax = true;
	public Node parrent;
	public Board board;
	private int id;

	ArrayList<Node> children = new ArrayList<Node>();
	int value = 0;
	public Node(Node parrent, int value, int id){
		this.parrent = parrent;
		this.id = id;
		if(parrent!=null)
			this.isMax = !parrent.isMax;
		this.value = value;
	}
	
	public Node(Node parrent, int id){
		this.parrent = parrent;
		this.id = id;
		if(parrent!=null)
			this.isMax = !parrent.isMax;
	}
	
	public boolean isLeaf() {
		if (children.isEmpty())
			return true;
		return false;
	}
	
	
	public void addChild(Node child){
		children.add(child);
	}
	
	public ArrayList<Node> getChildren(){
		if (children.isEmpty())
			return null;	
		return children;
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public String toString(){
		if(isLeaf())
			return String.format("id: %d - Leaf - Value: %d ",id , value);
		else if(isMax)
			return String.format("id: %d - Max - Alpha: %d - Beta: %d ",id,  alpha, beta);
		else return 
				String.format("id: %d - Min - Alpha: %d - Beta: %d - Min", id, alpha, beta);
	}

	public int getId() {
		return id;
	}
	
}
