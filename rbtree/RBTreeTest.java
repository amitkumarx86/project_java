package rbtree;


public class RBTreeTest {
    public static void main(String[] args) {
	System.out.println("rb started..");
	RBTree rb = new RBTree();
	
	//rb Insertion
	RBNode root = null;
	int arr[] = {1,2,3,4,5,6,7,8,9,10,28,30,26,21};
	//int arr[] = {21,26,30,9,4,14,28,18,15,10,2,3,7};
	
	for(int i=0;i<arr.length;i++){
	    root = rb.insertRB(arr[i], root);
	}
	 
	rb.inOrder(root);
	System.out.println("Root : "+root.key+ " color : "+(root.color == 0 ? "B":"R"));
	
	 
	 
	
	RBNode node_to_be_deleted = rb.searchRB(28,root);
	
	
	root = rb.deleteRB(node_to_be_deleted, root);
	node_to_be_deleted = rb.searchRB(30,root);
	root = rb.deleteRB(node_to_be_deleted, root);
	node_to_be_deleted = rb.searchRB(26,root);
	root = rb.deleteRB(node_to_be_deleted, root);
	node_to_be_deleted = rb.searchRB(21,root);
	root = rb.deleteRB(node_to_be_deleted, root);
	rb.inOrder(root);
	System.out.println("\nRoot : "+ root.key);
	
	 
	
	 
    }
}
