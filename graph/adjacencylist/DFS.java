package graph.adjacencylist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


public class DFS {
    public static int _NUM_OF_NODES=5;
    
    public static void main(String[] args) {
	Vertex vertex[]=new Vertex[5];
	
	dfs(vertex);
	
	
    }
    public static void dfs(Vertex[] vertex){
	//Graph Representation
		initializeGraph(vertex);
		
		/*We are using stack to implement BFS
		 * */
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.push(vertex[0]);
		while(!stack.isEmpty()){
		    Vertex u = stack.pop();
		    u.flag = false;
		    Iterator<Vertex> it = u.adjacencyList.iterator();
		    while(it.hasNext()){
			Vertex v = it.next();
			if(v.flag == true) {
			    stack.push(v);
			    v.parent = u;
			    
			}
		    }
		    
		}
		printDFSTree(vertex);
    }
    public static void printDFSTree(Vertex[] vertex){
	System.out.println("Vertex = "+(vertex[0].key) + " parent = null");
	for(int i=1 ;i < _NUM_OF_NODES; i++){
	    System.out.println("Vertex = "+(vertex[i].key) + " parent = "+(vertex[i].parent.key));
	}
    }
    public static void initializeGraph(Vertex[] vertex){
	vertex[0] = new Vertex(1);
	vertex[1] = new Vertex(2);
	vertex[2] = new Vertex(3);
	vertex[3] = new Vertex(4);
	vertex[4] = new Vertex(5);
	
	vertex[0].adjacencyList = new LinkedList<Vertex>();
	vertex[0].adjacencyList.add(vertex[1]);
	vertex[0].adjacencyList.add(vertex[3]);
	
	vertex[1].adjacencyList = new LinkedList<Vertex>();
	vertex[1].adjacencyList.add(vertex[2]);
	vertex[1].adjacencyList.add(vertex[3]);
	
	vertex[2].adjacencyList = new LinkedList<Vertex>();
	
	vertex[3].adjacencyList = new LinkedList<Vertex>();
	vertex[3].adjacencyList.add(vertex[4]);
	
	
	vertex[4].adjacencyList = new LinkedList<Vertex>();
	vertex[4].adjacencyList.add(vertex[1]);
	vertex[4].adjacencyList.add(vertex[2]);
	
   	 
	
    }
     
}
