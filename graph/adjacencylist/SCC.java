package graph.adjacencylist;


import java.util.Iterator;
import java.util.LinkedList;



/*
 * Strongly connected component
 * Sort nodes based on their finishing times and then apply DFS on G transpose
 * */

class TopoLogicalSort{
     
    public void topoLogicalSort(Vertex[] vertex){
	System.out.println("Sorting nodes according to their finish time");
	for(int i=1;i<vertex.length ;i++){
	    int j = i-1;
	    int key = vertex[i].finish_time;
	    Vertex key_vertex = vertex[i];
	    while(j>=0 && vertex[j].finish_time < key){
		vertex[j+1] = vertex[j];
		j--;
	    }
	    vertex[j+1] = key_vertex;
	}
	
	for(int i=0;i<vertex.length ;i++){
	    System.out.println("Vertex = "+(vertex[i].key) + " parent = "+(vertex[i].parent == null ? "null " : vertex[i].parent.key)+" d = "+vertex[i].discovery_time+" f = "+vertex[i].finish_time);
	}
    }
}
public class SCC {
    public static int _NUM_OF_NODES=5;
    public static int time = 0;
    
    public static void DFS(Vertex[] vertex){
	
	vertex[0].discovery_time = time;
	for(int i=0;i < _NUM_OF_NODES ; i++) {if (vertex[i].flag)DFS_VISIT(vertex[i]);}
    }
    
    public static void DFS_VISIT(Vertex u){
	time++;
	//System.out.print(u.key+" "+u.flag+" ");
	u.flag = false; 
	u.discovery_time = time;
	Iterator<Vertex> it = u.adjacencyList.iterator();
	
	while(it.hasNext()){
	    Vertex v = it.next();
	    //System.out.print(v.flag+" ");
	    if(v.flag){
		v.parent = u;
		v.flag   = false;
		System.out.print(v.key+" ");
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
    public static Vertex[] graphTranspose(Vertex[] vertex){
	Vertex[] tempVertex = new Vertex[_NUM_OF_NODES];
	for(int i=0;i<vertex.length;i++) {
	    tempVertex[i] = new Vertex(vertex[i].key);
	    tempVertex[i].adjacencyList = new LinkedList<Vertex>();
	}
	
	for(int i=0;i < vertex.length;i++){
	    Vertex u = vertex[i];
	    Iterator<Vertex> it = u.adjacencyList.iterator();
	    while(it.hasNext()){
		Vertex v = it.next();
		tempVertex[v.key-1].adjacencyList.add(tempVertex[u.key-1]);
	    }
	     
	}
	
	return tempVertex;
    }
    public static void main(String[] args) {
	Vertex vertex[]=new Vertex[5];
	//Graph Representation
	initializeGraph(vertex);
	// Compute G transpose
	Vertex[] graphTranspose;
	graphTranspose = graphTranspose(vertex);
	
	DFS(vertex);
	printDFSTree(vertex);

	// topological sort
	TopoLogicalSort t = new TopoLogicalSort();
	t.topoLogicalSort(vertex);
	
	for(int i=0;i<graphTranspose.length;i++) graphTranspose[i].flag=true;
	
	for(int i=0;i<vertex.length;i++){
	    if(graphTranspose[vertex[i].key-1].flag)
		   System.out.print((graphTranspose[vertex[i].key-1].key)+" ");
	    
	    DFS_VISIT(graphTranspose[vertex[i].key-1]);
	    
	    System.out.println();
	    
	}
	
    }
    public static void printGraph(Vertex[] vertex){
	for(int i=0;i<vertex.length;i++){
	    Iterator<Vertex> it = vertex[i].adjacencyList.iterator();
	    System.out.println(vertex[i].key+" ");
	    while(it.hasNext()){
		System.out.print(it.next().key+" ");
	    }
	    System.out.println();
	}
    }
}
