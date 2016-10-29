package graphs;

import java.util.LinkedList;
import java.util.Queue;


public class BFS {
    public static int _NUM_OF_NODES=5;
    
    public static void main(String[] args) {
	int graph[][]={ {0,1,0,1,0},
			{0,0,1,1,0},
			{0,0,0,0,0},
			{0,0,0,0,1},
			{0,1,1,0,0},
			};
	
	
	
	Vertex vertex[]=new Vertex[5];
	
	for(int u=0;u<5;u++){
	    vertex[u]= new Vertex(u);
	} 
	/*We are using Queue to implement BFS
	 * */
	Queue<Vertex> queue = new LinkedList<Vertex>();
	vertex[0].flag=false;
	queue.add(vertex[0]);
	while(!queue.isEmpty()){
	    Vertex u = queue.poll();
	    for(int v1=0;v1 < _NUM_OF_NODES;v1++){
		if(graph[u.key][v1] != 0 && vertex[v1].flag == true){
		    vertex[v1].flag=false;
		    vertex[v1].parent=u;
		    queue.add(vertex[v1]);
		}
	    }
	}
	System.out.println("Vertex = "+(vertex[0].key+1) + " parent = null");
	for(int i=1 ;i < _NUM_OF_NODES; i++){
	    System.out.println("Vertex = "+(vertex[i].key+1) + " parent = "+(vertex[i].parent.key+1));
	}
    }
}
