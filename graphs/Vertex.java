package graphs;

class Vertex{
    public int key=0;
    public boolean flag=false;
    public Vertex parent;
    public String color;
    public int priority;
    Vertex(int data){
	this.key=data;
	this.flag=true;
	parent = null;
	color = "G";
	priority=123123;
    }
}