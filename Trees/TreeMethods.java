package Trees;

import java.util.Stack;

public class TreeMethods {
    static int levelCount=-1;
    static int flip=1;
    int treeHeight(Node head){
	if(head == null)
	    return 0;
	if(head.right == null && head.left==null)
	    return 0;
	
	return Math.max(treeHeight(head.left),treeHeight(head.right))+1;
    }
    void treeLevel(Node node,int l){
	if (node == null)
            return ;
   
        /* first print the data of node */
	node.level=l;
        //System.out.print(node.data + " ");   
        /* then recur on left child */
        //levelCount++;
        //System.out.println(node.level);
        treeLevel(node.left,1+l);
        /* and recur on right child */
        treeLevel(node.right,1+l);
        
    }
    void spiralTraversal(Node node){
        int h = treeHeight(node);
        int i;
  
        /* ltr -> left to right. If this variable is set then the
           given label is transversed from left to right */
        boolean ltr = true;
        for (i = 0; i <= h; i++) 
        {
            //System.out.println("calling");
            printGivenLevel(node, i, ltr);
  
            /*Revert ltr to traverse next level in opposite order*/
            ltr = !ltr;
        }
    }
    /* Print nodes at a given level */
    void printGivenLevel(Node node, int level, boolean ltr) 
    {
        if (node == null) 
            return;
        if (level == 0) 
            System.out.print(node.data + " ");
        else if (level > 0) 
        {
            if (ltr != false) 
            {
                printGivenLevel(node.left, level - 1, ltr);
                printGivenLevel(node.right, level - 1, ltr);
            } 
            else
            {
                printGivenLevel(node.right, level - 1, ltr);
                printGivenLevel(node.left, level - 1, ltr);
            }
        }
    }
    void optimizedSpiral(Node node){
	/*
	 * this method takes O(n) time and O(n) Space,
	 * We are using two stacks, insert one element into one stack and its child into another stack
	 * Pop 1 elem from one stack and print it.. repeat this whole process till both STACKs are empty 
	 * */
	if(node == null){
	    return ;
	}
	Stack<Node> s1 = new Stack<Node>();
	Stack<Node> s2 = new Stack<Node>();
	s1.push(node);
	while(!s1.empty() || !s2.empty()){
	    while(!s1.empty()){
		Node n = s1.pop();
		System.out.print(n.data+" ");
		
		if(n.left != null)
		    s2.push(n.left);
		if(n.right != null)
		    s2.push(n.right);
	    }
	    while(!s2.empty()){
		Node n = s2.pop();
		System.out.print(n.data+" ");
		if(n.right != null)
		    s1.push(n.right);
		if(n.left != null)
		    s1.push(n.left);
		
	    }
	}
    }
}




















