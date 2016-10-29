package graph.adjacencylist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class BFS {
    public static int _NUM_OF_NODES=5;
    
    public static void main(String[] args) {
	
	Vertex vertex[]=new Vertex[5];

	//Graph Representation
	initializeGraph(vertex);
	
	/*We are using Queue to implement BFS
	 * */
	Queue<Vertex> queue = new LinkedList<Vertex>();
	queue.add(vertex[0]);
	while(!queue.isEmpty()){
	    Vertex u = queue.poll();
	    u.flag=false;
	    Iterator<Vertex> it = u.adjacencyList.iterator();
	    while(it.hasNext()){
		Vertex v = it.next();
		if(v.flag){ 
		    queue.add(v);
		    v.parent = u;
		    v.flag   = false;
		    System.out.print(v.key+" ");
		}
	    }
	}
	printBFSTree(vertex);
    }
    
    public static void printBFSTree(Vertex[] vertex){
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
