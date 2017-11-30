package algo;

public class AlgoTest {

	static Node n = new Node(null,1);
	int[] numbers = {8,7,2,9,1,6,2,4,1,1,3,5,3,9,2,6,5,2,1,2,3,9,7,2,9,6,4};

	
	public void initializeNodes(){
		addChildren(3, n);
		System.out.println(n.toString());
		int i = 0;
		for(Node child : n.children){
			addChildren(3, child);
			System.out.println(child.toString());
			for(Node gChild : child.getChildren()){
				gChild.addChild(new Node(gChild, numbers[i], child.getId()+1));
				i++;
				gChild.addChild(new Node(gChild, numbers[i], child.getId()+1));
				i++;
				gChild.addChild(new Node(gChild, numbers[i], child.getId()+1));
				i++;
				System.out.println(gChild.toString());
				for(Node ggChild : gChild.getChildren())
					System.out.println(ggChild.toString());
			}
		}
	}
	
	public void addChildren( int tal, Node parrent){
		for(int i = 0; i< tal; i++)
			parrent.addChild(new Node(parrent,parrent.getId()+1));
	}
	
	
	public static void main(String[] args) {
		AlphaBeta algo = new AlphaBeta();
		AlgoTest t = new AlgoTest();
		t.initializeNodes();
		//System.out.println("\nBestMove:   "+algo.bestMove(n));
		Node bestMove = n;
		while(bestMove != null ){
			System.out.println(bestMove);
			bestMove = bestMove.bestMove;
		}
		
	}
	
}
