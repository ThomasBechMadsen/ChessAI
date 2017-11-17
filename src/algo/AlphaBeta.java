package algo;


public class AlphaBeta {

	int bestMove(Node node){
		if(node.isLeaf()){
			System.out.println("leaf :" + node.value);
			return node.value;
		}

		if(node.isMax){
			while(node.alpha < node.beta){
				for(Node child : node.getChildren()){
					if(node.alpha > node.beta)
						break;
					child.alpha = node.alpha;
					child.beta = node.beta;
					int v = bestMove(child);
					if (v > node.alpha){
						node.alpha = v;
						node.bestMove = child;
					}
				}
				return node.alpha;
			}
		}
		else{
			while(node.alpha < node.beta){
				for(Node child : node.getChildren()){
					if(node.alpha > node.beta)
						break;
					child.alpha = node.alpha;
					child.beta = node.beta;
					int v = bestMove(child);
					if (v < node.beta){
						node.beta = v;
						node.bestMove = child;
					}
				}
				return node.beta;
			}
		}
		return 0;
	}
	
}
