package graph.adjacencylist;

 import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
 
class Edge{
    int weight;
    Vertex u,v;
    Edge(Vertex u,Vertex v,int weight){this.weight = weight; this.u = u; this.v = v;}
}
public class Krushkal {
    	public static int _NUM_OF_NODES=5;
    	public static int _NUM_OF_EDGES=7;
    	public static void main(String[] args) {
	
	Edge[] edge = new Edge[_NUM_OF_EDGES];
	/*We are using PrirityQueue to implement Prim's MST
	 * Complexity : (V + E ) * logV using binary heap
	 * Complexity : E + Vlogv using fibonacci heap cause decrease key is constant in it 
	 * */
	
	// Used edge list representation
	initializeGraph(edge);
	
	// Sort array in non-decreasing Order
	Arrays.sort(edge, new Comparator<Edge>() {
	        @Override
	        public int compare(Edge o1, Edge o2) {
	            return o1.weight - o2.weight; // or whatever property you want to sort
	        }
	    });
	
	
	// declare three arrays 
	/*
	 * 1. for having vertex -> color mapping
	 * 2. for how many nodes are mapped to one color
	 * 3. for given color code pointer to list of nodes
	 * */
	int[] vertex_color_mapping = new int[5];
	int[] vertex_count_color   = new int[5];
	Vertex[] color_to_vertex_pointer = new Vertex[5];
	
	initializeArrays(vertex_color_mapping,vertex_count_color,color_to_vertex_pointer);
	
	//Final Edge list
	Edge[] mst = new Edge[_NUM_OF_EDGES];
	int mst_i=0;
	
	System.out.println("Minimum Spanning Tree..");
	for(int i=0;i<_NUM_OF_EDGES;i++) {
	    if(cycleCheck(edge[i].u,edge[i].v,vertex_color_mapping,vertex_count_color,color_to_vertex_pointer)){
		mst[mst_i++]=new Edge(edge[i].u,edge[i].v,edge[i].weight);
	    }
	    //else : it will create a cycle
	
	}
	
	printKrushkalMST(mst);
    }
    
    private static void initializeArrays(int[] vertex_color_mapping, int[] vertex_count_color,
	    Vertex[] color_to_vertex_pointer) {
	// TODO Auto-generated method stub
	vertex_color_mapping[0]=1;
	vertex_color_mapping[1]=2;
	vertex_color_mapping[2]=3;
	vertex_color_mapping[3]=4;
	vertex_color_mapping[4]=5;
	
	vertex_count_color[0]  =1;
	vertex_count_color[1]  =1;
	vertex_count_color[2]  =1;
	vertex_count_color[3]  =1;
	vertex_count_color[4]  =1;
	
	color_to_vertex_pointer[0] = new Vertex(1);
	color_to_vertex_pointer[1] = new Vertex(2);
	color_to_vertex_pointer[2] = new Vertex(3);
	color_to_vertex_pointer[3] = new Vertex(4);
	color_to_vertex_pointer[4] = new Vertex(5);
	
	color_to_vertex_pointer[0].adjacencyList = new LinkedList<Vertex>();
	color_to_vertex_pointer[1].adjacencyList = new LinkedList<Vertex>();
	color_to_vertex_pointer[2].adjacencyList = new LinkedList<Vertex>();
	color_to_vertex_pointer[3].adjacencyList = new LinkedList<Vertex>();
	color_to_vertex_pointer[4].adjacencyList = new LinkedList<Vertex>();
	
	color_to_vertex_pointer[0].adjacencyList.add(new Vertex(1));
	color_to_vertex_pointer[1].adjacencyList.add(new Vertex(2));
	color_to_vertex_pointer[2].adjacencyList.add(new Vertex(3));
	color_to_vertex_pointer[3].adjacencyList.add(new Vertex(4));
	color_to_vertex_pointer[4].adjacencyList.add(new Vertex(5));
	
    }
    private static void printKrushkalMST(Edge[] edge) {
	for(int i=0;i < _NUM_OF_NODES-1;i++) 
	    System.out.println(edge[i].u.key+" "+edge[i].v.key+" "+edge[i].weight);
    }
    private static boolean cycleCheck(Vertex u, Vertex v, int[] vertex_color_mapping, 
	    int[] vertex_count_color, Vertex[] color_to_vertex_pointer) {
	
	int color_u = vertex_color_mapping[u.key-1]-1;
	int color_v = vertex_color_mapping[v.key-1]-1;
	//System.out.println(u.key+" "+(color_u+1)+" "+v.key+" "+(color_v+1));
	
	if(color_u != color_v) {
	    //We need to change all three above arrays to merge
	    //System.out.println("if condition");
	    //System.out.println(vertex_count_color[color_u]+" "+vertex_count_color[color_v]);
	    if(vertex_count_color[color_u] <= vertex_count_color[color_v]){
		// Count reduction
		vertex_count_color[color_v]  += color_to_vertex_pointer[color_u].adjacencyList.size();
		vertex_count_color[color_u]  -= color_to_vertex_pointer[color_u].adjacencyList.size();
		vertex_color_mapping[u.key-1] = color_v+1; // color change
		vertex_color_mapping[color_u] = color_v+1;
		//merge
		Iterator<Vertex> it = color_to_vertex_pointer[color_u].adjacencyList.iterator();
		while(it.hasNext())color_to_vertex_pointer[color_v].adjacencyList.addFirst(it.next()); 				color_to_vertex_pointer[u.key-1].adjacencyList=null;
		//System.out.println("Color change");
		//System.out.println(vertex_color_mapping[u.key-1]+" "+vertex_color_mapping[v.key-1]);
			    
	    }
	    else {
		// Count reduction
		vertex_count_color[color_u]  += color_to_vertex_pointer[color_v].adjacencyList.size();
		vertex_count_color[color_v]  -= color_to_vertex_pointer[color_v].adjacencyList.size();
		vertex_color_mapping[v.key-1] = color_u+1; // color change
		vertex_color_mapping[color_v] = color_u+1;
		//merge
		Iterator<Vertex> it = color_to_vertex_pointer[color_v].adjacencyList.iterator();
		while(it.hasNext())color_to_vertex_pointer[color_u].adjacencyList.addFirst(it.next()); 				color_to_vertex_pointer[u.key-1].adjacencyList=null;
		//System.out.println("Color change case 2");
		//System.out.println(vertex_color_mapping[v.key-1]+" "+vertex_color_mapping[u.key-1]);
						
	    }
		
	    return true;
	    }
	else 
	    return false; // do nothing as the edge is forming a cycle
    }
     
    private static void initializeGraph(Edge[] edge){
	Vertex v1 = new Vertex(1);
	Vertex v2 = new Vertex(2);
	Vertex v3 = new Vertex(3);
	Vertex v4 = new Vertex(4);
	Vertex v5 = new Vertex(5);
	
	//edge list
	edge[0] = new Edge(v1,v2,3);
	edge[1] = new Edge(v2,v3,9);
	edge[2] = new Edge(v1,v4,4);
	edge[3] = new Edge(v2,v4,4);
	edge[4] = new Edge(v5,v2,3);
	edge[5] = new Edge(v5,v3,1);
	edge[6] = new Edge(v4,v5,6);
 
	
       }
}


