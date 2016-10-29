package graph.adjacencylist;

import java.util.LinkedList;


class Vertex{
    public int key=0;
    public boolean flag=false;
    public Vertex parent;
    
    public int priority;
    public int discovery_time , finish_time;
    public Vertex nextNode;
    public LinkedList<Vertex> adjacencyList;
    public int weight;
    public Vertex mainReference;
    Vertex(int data){
	key=data;
	flag=true;
	parent = null;
	priority=123123;
	discovery_time = 0;
	finish_time = 0;
	nextNode=null;
	adjacencyList = null;
	weight=0;
	mainReference = null;
    }
    Vertex(){}
}