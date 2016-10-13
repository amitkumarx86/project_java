package rbtree;


public class RBTreeTest {
    public static void main(String[] args) {
	System.out.println("rb started..");
	RBTree rb = new RBTree();
	
	//rb Insertion
	RBNode root = null;
	int arr[] = {21,26,30,9,4,14,28,18,15,10,2,3,7};
	
	for(int i=0;i<arr.length;i++){
	    root = rb.insertRB(arr[i], root);
	}
	 
	rb.inOrder(root);
	System.out.println("Root : "+root.key+ " color : "+(root.color == 0 ? "B":"R"));
	/*
	
	/*
	
	root = rb.insertRB(4, root,null);
	root = rb.insertRB(14, root,null);
	root = rb.insertRB(28, root,null);
	root = rb.insertRB(18, root,null);
	root = rb.insertRB(15, root,null);
	root = rb.insertRB(10, root,null);
	root = rb.insertRB(2, root,null);
	root = rb.insertRB(3, root,null);
	root = rb.insertRB(7, root,null);
	root = rb.insertRB(6, root,null);
	
	/*
	System.out.println("Root : "+ root.data);
	RBNode node_to_be_deleted = rb.searchRB(14,root);
	root = rb.deleteRB(node_to_be_deleted, root);
	rb.inOrder(root);
	System.out.println("\nRoot : "+ root.data);
	*/
	 
	
	 
    }
}
