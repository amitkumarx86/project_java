package dataStructures.avlTree;


public class AVLTest {
    public static void main(String[] args) {
	System.out.println("AVL started..");
	AVLTree avl = new AVLTree();
	//AVL Insertion
	AVLNode root = avl.insertAVL(21, null);	
	root = avl.insertAVL(26, root);
	root = avl.insertAVL(30, root);
	root = avl.insertAVL(9, root);
	root = avl.insertAVL(4, root);
	root = avl.insertAVL(14, root);
	root = avl.insertAVL(28, root);
	root = avl.insertAVL(18, root);
	root = avl.insertAVL(15, root);
	root = avl.insertAVL(10, root);
	root = avl.insertAVL(2, root);
	root = avl.insertAVL(3, root);
	root = avl.insertAVL(7, root);
	root = avl.insertAVL(6, root);
	System.out.println("Root : "+ root.key);
	AVLNode node_to_be_deleted = avl.searchAVL(14,root);
	root = avl.deleteAVL(node_to_be_deleted, root);
	avl.inOrder(root);
	System.out.println("\nRoot : "+ root.key);
	
	System.out.println("Root : "+ root.key);
	node_to_be_deleted = avl.searchAVL(4,root);
	root = avl.deleteAVL(node_to_be_deleted, root);
	avl.inOrder(root);
	System.out.println("\nRoot : "+ root.key);
	
	//AVL Deletion
	
	
	/*
	avl.insertAVL(40, root);
	System.out.println(root.height);
	
	avl.insertAVL(45, root);
	System.out.println(root.height);
	
	avl.inOrder(root);
	System.out.println(root.height);
	*/
    }
}
