package graph.adjacencylist;

import java.util.Iterator;
import java.util.LinkedList;



public class RecursiveDFS {
    public static int _NUM_OF_NODES=5;
    public static int time = 0;
    
    public static void DFS(Vertex[] vertex){
	//Graph Representation
	initializeGraph(vertex);
	
	vertex[0].discovery_time = time;
	for(int i=0;i < _NUM_OF_NODES ; i++) {if (vertex[i].flag)DFS_VISIT(vertex[i]);}
    }
    
    public static void DFS_VISIT(Vertex u){
	time++;
	u.flag = false;
	u.discovery_time = time;
	Iterator<Vertex> it = u.adjacencyList.iterator();
	while(it.hasNext()){
	    Vertex v = it.next();
	    if(v.flag){
		v.parent = u;
		v.flag   = false;
		DFS_VISIT(v);
	    }
	}
	time++;
	u.finish_time = time;
    }
    
    public static void printDFSTree(Vertex[] vertex){
	System.out.println("Vertex = "+(vertex[0].key) + " parent = null"+" d = "+vertex[0].discovery_time+" f = "+vertex[0].finish_time);
	for(int i=1 ;i < _NUM_OF_NODES; i++){
	    System.out.println("Vertex = "+(vertex[i].key) + " parent = "+(vertex[i].parent.key)+" d = "+vertex[i].discovery_time+" f = "+vertex[i].finish_time);
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
    
    public static void main(String[] args) {
	Vertex vertex[]=new Vertex[5];
	DFS(vertex);
	printDFSTree(vertex);
	
    }
}
