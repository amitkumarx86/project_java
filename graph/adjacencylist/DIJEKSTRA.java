package graph.adjacencylist;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;



public class DIJEKSTRA {
 public static int _NUM_OF_NODES=5;
    
    public static void main(String[] args) {
	Vertex[] vertex = new Vertex[5];
	
	/*We are using PrirityQueue to implement Dijkstra's Shortest Path Problem
	 * Complexity : (V + E ) * logV using binary heap
	 * Complexity : E + Vlogv using fibonacci heap cause decrease key is constant in it 
	 * */
	
	
	initializeGraph(vertex);
	
	PriorityQueue<Vertex> heap = new PriorityQueue<Vertex>(5, new Comparator<Vertex>() {
	        public int compare(Vertex u, Vertex v) {
	            if( u.priority == v.priority ) return 0;
	            else if(u.priority > v.priority) return 1;
	            else return -1;
	        }
	    });
	
	vertex[0].flag = false;
	vertex[0].priority = 0;
	heap.add(vertex[0]);
	
	while(!heap.isEmpty()){
	    Vertex u = heap.poll();
	    u.flag=false;
	    if (u.mainReference != null ) u.mainReference.flag = false;
	    
	    Iterator<Vertex> it = u.adjacencyList.iterator();
	    while(it.hasNext()){
		Vertex v = it.next();
		if(v.mainReference.flag){
		    v.mainReference.priority = u.priority + v.weight;
		    v.mainReference.flag=false;
		    v.mainReference.parent=u;
		    heap.add(v.mainReference);
		     
		}
		else if(!v.mainReference.flag  && 
			v.mainReference.priority > (u.priority + v.weight)){
		    //decrease key
		    heap.remove(v.mainReference);
		    heap.add(v.mainReference);
		    v.mainReference.priority = u.priority + v.weight;
		    v.mainReference.parent = u;
		     
		}
	    }
	}
	printDijkstra(vertex);
    }
    
    public static void printDijkstra(Vertex[] vertex){
	System.out.println("Tree");
	System.out.println("Vertex = "+(vertex[0].key) + " parent = null"+" priority : "+0);
	for(int i=1 ;i < _NUM_OF_NODES; i++){
	    System.out.println("Vertex = "+(vertex[i].key) + " parent = "+(vertex[i].parent.key)+"    priority : "+vertex[i].priority);
	}
    }
    public static void initializeGraph(Vertex[] vertex){
	vertex[0] = new Vertex(1);
	vertex[1] = new Vertex(2);
	vertex[2] = new Vertex(3);
	vertex[3] = new Vertex(4);
	vertex[4] = new Vertex(5);
	
	vertex[0].adjacencyList = new LinkedList<Vertex>();
	Vertex v1 = new Vertex(2);
	v1.weight = 3;
	v1.mainReference = vertex[1];
	vertex[0].adjacencyList.add(v1);
	
	Vertex v2 =  new Vertex(4);
	v2.weight = 4;
	v2.mainReference = vertex[3];
	vertex[0].adjacencyList.add(v2);
	
	
	vertex[1].adjacencyList = new LinkedList<Vertex>();
	Vertex v3 =  new Vertex(3);
	v3.weight = 9;
	v3.mainReference = vertex[2];
	vertex[1].adjacencyList.add(v3);
	Vertex v4 =  new Vertex(4);
	v4.weight = 4;
	v4.mainReference = vertex[3];
	vertex[1].adjacencyList.add(v4);
	
	vertex[2].adjacencyList = new LinkedList<Vertex>();
	
	vertex[3].adjacencyList = new LinkedList<Vertex>();
	Vertex v5 =  new Vertex(5);
	v5.weight = 6;
	v5.mainReference = vertex[4];
	vertex[3].adjacencyList.add(v5);
	
	vertex[4].adjacencyList = new LinkedList<Vertex>();
	Vertex v6 =  new Vertex(2);
	v6.weight = 3;
	v6.mainReference = vertex[1];
	vertex[4].adjacencyList.add(v6);
	Vertex v7 =  new Vertex(3);
	v7.weight = 1;
	v7.mainReference = vertex[2];
	vertex[4].adjacencyList.add(v7);
	 
       }
}

