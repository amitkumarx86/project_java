package dataStructures.rbtree;

public class AppBSTTest {
    public static void main(String[] args) {
	RBTree rb = new ApplicationOfBBST();
	 
	
	//rb Insertion
	RBNode root = null;
	int arr[] = {1,2,3,4,5,6};//,7,8,9,10,28,30,26,21,34,11,12,14,15};//6,7,8,9,10,28,30,26,21};
	
	System.out.print("Insert : "); 
	for(int i=0;i<arr.length;i++){
	    root = rb.insertRB(arr[i], root);
	    //rb.inOrder(root);
	}
	
	System.out.println();
	rb.inOrder(root);
	System.out.println("Root : "+root.key+ " color : "+(root.color == 0 ? "B":"R"));
	
	ApplicationOfBBST bst = new ApplicationOfBBST();
	System.out.println(bst.rankOfNode(1, root));
	
	// find node with rank
	RBNode a=null;
	System.out.println((a=bst.findNodeHavingRank(6, root)) == null ? "No element" : a.key);
	
	int[] delArr = {1};
	RBNode node_to_be_deleted = null;
	System.out.println();
	System.out.print("Delete : ");
	for(int i=0;i<delArr.length ; i++){
	    node_to_be_deleted = rb.searchRB(delArr[i], root);
	    System.out.println(node_to_be_deleted.key);
	    root = rb.deleteRB(node_to_be_deleted, root);
	}
	System.out.println();
	rb.inOrder(root);
	System.out.println("Root : "+root.key+ " color : "+(root.color == 0 ? "B":"R"));
    }
}
