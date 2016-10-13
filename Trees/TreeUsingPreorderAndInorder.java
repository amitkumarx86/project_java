package Trees;

//Java program to construct a tree using inorder and preorder traversal

/* A binary tree node has data, pointer to left child
and a pointer to right child */
class Node 
{
 char data;
 int level;
 Node left, right;

 Node(char item) 
 {
     data = item;
     level=0;
     left = right = null;
 }
}

public class TreeUsingPreorderAndInorder 
{
     Node root;
     static int preIndex = 0;
     static int postIndex = 0;
     public TreeUsingPreorderAndInorder(int len){postIndex=len-1;}
     /* Recursive function to construct binary of size len from
        Inorder traversal in[] and Preorder traversal pre[].
        Initial values of inStrt and inEnd should be 0 and len -1.  
        */
     Node buildTree(char in[], char pre[], int inStrt, int inEnd) 
     {
         
         if (inStrt > inEnd) 
             return null;
    
         /* Pick current node from Preorder traversal using preIndex
            and increment preIndex */
         Node tNode = new Node(pre[preIndex++]);
    
         /* If this node has no children then return */
         if (inStrt == inEnd)
             return tNode;
    
         /* Else find the index of this node in Inorder traversal */
         int inIndex = search(in, inStrt, inEnd, tNode.data);
    
         /* Using index in Inorder traversal, construct left and
            right subtrees */
         tNode.left = buildTree(in, pre, inStrt, inIndex - 1);
         tNode.right = buildTree(in, pre, inIndex + 1, inEnd);
    
         return tNode;
     }
    Node buildTreePost(char[] in, char[] post, int inStrt, int inEnd){
	
        if(inStrt > inEnd)
    		return null;
        /* Pick current node from Preorder traversal using preIndex
        and increment preIndex */
        
        Node tNode = new Node(post[postIndex--]);
        
        /* If this node has no children then return */
        if(inStrt == inEnd)
    	return tNode;
        int inIndex = search(in,inStrt, inEnd, tNode.data);
        
        /* Using index in Inorder traversal, construct right and
        left subtrees */
        tNode.right = buildTreePost(in, post, inIndex+1, inEnd);
        tNode.left = buildTreePost(in, post, inStrt, inIndex-1);
        
        
        return tNode;
    }
     /* UTILITY FUNCTIONS */
      
     /* Function to find index of value in arr[start...end]
      The function assumes that value is present in in[] */
     int search(char arr[], int strt, int end, char value) 
     {
         int i;
         for (i = strt; i <= end; i++) 
         {
             if (arr[i] == value)
                 return i;
         }
         return i;
     }

     /* This funtcion is here just to test buildTree() */
     void printInorder(Node node) 
     {
         if (node == null)
             return;
    
         /* first recur on left child */
         printInorder(node.left);
    
         /* then print the data of node */
         System.out.print(node.data + " ");
    
         /* now recur on right child */
         printInorder(node.right);
     }
     /* This funtcion is here just to test buildTree() */
     void printPreorder(Node node) 
     {
         if (node == null)
             return;
    
         /* first print the data of node */
         System.out.print(node.data + " ");
    
         /* then recur on left child */
         printPreorder(node.left);
         /* and recur on right child */
         printPreorder(node.right);
     }
     /* This funtcion is here just to test buildTree() */
     void printPostorder(Node node) 
     {
         if (node == null)
             return;
    
         /* first recur on left child */
         printPostorder(node.left);
         /* and recur on right child */
         printPostorder(node.right);
         /* now print the data of node */
         System.out.print(node.data + " ");
    
     }
     // driver program to test above functions
     public static void main(String args[]) 
     {
         
         char in[] = new char[]{'D', 'B', 'E', 'A', 'F', 'C'};
         char pre[] = new char[]{'A', 'B', 'D', 'E', 'C', 'F'};
         char post[] = new char[]{'D', 'E', 'B', 'F', 'C', 'A'};
         TreeUsingPreorderAndInorder tree = new TreeUsingPreorderAndInorder(in.length);
         int len = in.length;
         Node root = tree.buildTree(in, pre, 0, len - 1);
         //Node root = tree.buildTreePost(in, post, 0, len - 1);
         // building the tree by printing inorder traversal
         System.out.println("Inorder traversal of constructed tree is : ");
         tree.printInorder(root);
         // Calculate height of the tree
         TreeMethods hTree = new TreeMethods();
         System.out.println();
         //System.out.println("height of the tree is = "+hTree.treeHeight(root));
         hTree.treeLevel(root,0);
         //Spiral traversal
         hTree.spiralTraversal(root);
         System.out.println();
         hTree.optimizedSpiral(root);
     }
}
