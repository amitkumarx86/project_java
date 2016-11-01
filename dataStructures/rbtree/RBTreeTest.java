package dataStructures.rbtree;


public class RBTreeTest {
    public static void main(String[] args) {
	
	RBTree rb = new RBTree();
	
	//rb Insertion
	RBNode root = null;
	int arr[] = {1,2,3,4,5,6,7,8,9,10,28,30,26,21,34,11,12,14,15};//6,7,8,9,10,28,30,26,21};
	
	System.out.print("Insert : "); 
	for(int i=0;i<arr.length;i++)
	    root = rb.insertRB(arr[i], root);
	
	System.out.println();
	rb.inOrder(root);
	System.out.println("Root : "+root.key+ " color : "+(root.color == 0 ? "B":"R"));
	
	int[] delArr = {10,5,6,21,30,34,11,12};//,14,15,1,2,3,4,9,28};
	RBNode node_to_be_deleted = null;
	System.out.print("Delete : ");
	for(int i=0;i<delArr.length ; i++){
	    node_to_be_deleted = rb.searchRB(delArr[i], root);
	    root = rb.deleteRB(node_to_be_deleted, root);
	}
	System.out.println();
	
	rb.inOrder(root);
	if(root != null){
	    System.out.println("\nRoot : "+ root.key);
	    System.out.println("Total Nodes:"+rb.sizeRB(root));
	}
	 
	
	 
    }
}
