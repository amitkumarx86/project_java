package dataStructures.rbtree;

public class AppBSTTest {
    public static void main(String[] args) {
	RBTree rb = new ApplicationOfBBST();
	 
	
	//rb Insertion
	RBNode root = null;
	int arr[] = {1,2,3,4,5,6,7,8,9,10};//,28,30,26,21,34,11,12,14,15,43,44,55,66,77,100};//6,7,8,9,10,28,30,26,21};
	
	System.out.print("Insert : "); 
	for(int i=0;i<arr.length;i++)
	    root = rb.insertRB(arr[i], root);
	     
	
	printRB(root,rb);
	
	ApplicationOfBBST bst = new ApplicationOfBBST();
	
	// find rank of a node
	//System.out.println("Rank of 1 "+bst.rankOfNode(1, root));
	
	// find node for rank
	//RBNode a=null;	 
	//System.out.println("15 is rank of = "+" "+ ((a=bst.findNodeHavingRank(15, root)) == null ? "No element" : a.key));
	
	// prefix Sum
	RBNode searchNode  = rb.searchRB(9, root);
	System.out.println("\nPrefix of 9 is "+bst.preFixSum(searchNode, root));
	
	
	int[] delArr = {1,6};
	RBNode node_to_be_deleted = null;
	
	
	 
	for(int i=0;i<delArr.length ; i++){
	    node_to_be_deleted = rb.searchRB(delArr[i], root);
	    bst.decreaseNumberOfNodes(node_to_be_deleted);
	    bst.decreaseSum(node_to_be_deleted);
	    root = rb.deleteRB(node_to_be_deleted, root);
	   // printRB(root,rb);
	}
	
	printRB(root,rb);
	
	
	// prefix Sum
	searchNode  = rb.searchRB(9, root);
	System.out.println("\nPrefix of 9 is "+bst.preFixSum(searchNode, root));
	//System.out.println((a=bst.findNodeHavingRank(15, root)) == null ? "No element" : a.key);
	 
    }

    private static void printRB(RBNode root, RBTree rb) {
	// TODO Auto-generated method stub
	if(root == null) return;
	System.out.println();
	rb.inOrder(root);
	System.out.println("Root : "+root.key+ " color : "+(root.color == 0 ? "B":"R"));
    }
}
