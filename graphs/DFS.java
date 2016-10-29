package graphs;

import java.util.Stack;


public class DFS {
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
	/*We are using stack to implement BFS
	 * */
	Stack<Vertex> stack = new Stack<Vertex>();
	vertex[0].color="Y";
	stack.push(vertex[0]);
	while(!stack.isEmpty()){
	    Vertex u = stack.pop();
	   
	    if(u.color != "B"){
		    u.color = "B";
        	    for(int v1=0;v1 < _NUM_OF_NODES;v1++){
        		if(graph[u.key][v1] != 0 && vertex[v1].color != "B"){
        		    vertex[v1].color="Y";
        		    vertex[v1].parent=u;
        		    stack.push(vertex[v1]);
        		}
        	    }
            }
	    else
		{
			if(stack.size() != 0)
			    stack.pop();
		}
	}
	System.out.println("Vertex = "+(vertex[0].key+1) + " parent = null");
	for(int i=1 ;i < _NUM_OF_NODES; i++){
	    System.out.println("Vertex = "+(vertex[i].key+1) + " parent = "+(vertex[i].parent.key+1));
	}
    }
    
    
     
}
