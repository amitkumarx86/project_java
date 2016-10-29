package graphs;

import java.util.Comparator;
import java.util.PriorityQueue;



public class DIJEKSTRA {
 public static int _NUM_OF_NODES=5;
    
    public static void main(String[] args) {
	int graph[][]={ {0,3,0,4,0},
			{0,0,9,2,0},
			{0,0,0,0,0},
			{0,0,0,0,6},
			{0,3,1,0,0},
			};
	Vertex vertex[]=new Vertex[5];
	
	for(int u=0;u<5;u++){
	    vertex[u]= new Vertex(u);
	} 
	/*We are using Queue to implement BFS
	 * */
	PriorityQueue<Vertex> heap = new PriorityQueue<Vertex>(5, new Comparator<Vertex>() {
	        public int compare(Vertex u, Vertex v) {
	            if( u.priority == v.priority ) return 0;
	            else if(u.priority > v.priority) return 1;
	            else return -1;
	        }
	    });
	
	vertex[0].color = "Y";
	vertex[0].priority = 0;
	heap.add(vertex[0]);
	while(!heap.isEmpty()){
	    Vertex u = heap.poll();
	    u.color="B";
	    for(int v1=0; v1 < _NUM_OF_NODES;v1++){
		if(graph[u.key][v1] != 0 && vertex[v1].color == "G"){
		    vertex[v1].priority = u.priority + graph[u.key][v1];
		    vertex[v1].color="Y";
		    vertex[v1].parent=u;
		    heap.add(vertex[v1]);
		}
		else if(graph[u.key][v1] != 0 && vertex[v1].color == "Y" && 
			vertex[v1].priority > (u.priority + graph[u.key][v1])){
		    //decrease key
		    heap.remove(vertex[v1]);
		    heap.add(vertex[v1]);
		    
		    vertex[v1].priority = u.priority + graph[u.key][v1];
		    vertex[v1].parent = u;
		}
	    }
	}
	
	System.out.println("Tree");
	System.out.println("Vertex = "+(vertex[0].key+1) + " parent = null"+" priority : "+0);
	for(int i=1 ;i < _NUM_OF_NODES; i++){
	    System.out.println("Vertex = "+(vertex[i].key+1) + " parent = "+(vertex[i].parent.key+1)+"    priority : "+vertex[i].priority);
	}
    }
}

