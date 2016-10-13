package avlTree;



class AVLNode
{    
    AVLNode left, right, parent;
    int key;
    int height;

    /* Constructor */
    public AVLNode()
    {
        left = right = parent = null;
        key = height = 0 ;
    }
    /* Constructor */
    public AVLNode(int n)
    {
	left = right = parent = null;
        key = n; height = 0 ;
    }     
}
public class AVLTree implements AVLFunctions{

    
    @Override
    public AVLNode insertAVL(int key, AVLNode root) {
	AVLNode node = root;
	// TODO Auto-generated method stub
	if(node == null){
	    node = new AVLNode(key);
	    node.height=0;
	    node.parent=null;
	    return node;
	}
	AVLNode parnt = null;
	while(true){
	    if(key <= node.key){
		node.height= Math.max(AVLHeight(node.left)+1, AVLHeight(node.right))+1; 
		parnt=node; node=node.left;
		if(node == null){
		    node = new AVLNode(key);
		    node.height=0;
		    node.parent=parnt;
		    parnt.left=node;
		    break;
		}
	    }
	    else{
		node.height= Math.max(AVLHeight(node.left), AVLHeight(node.right)+1)+1; 
		parnt=node;node=node.right;
		if(node == null){
		    node = new AVLNode(key);
		    node.height=0;
		    node.parent=parnt;
		    parnt.right=node;
		    break;
		}
	    }
	}
	
	//Balance tree
	node = avlBalanceAfterInsertion(node);
	//System.out.println("return :"+node.key+" " +node.parent.key);
	return node.parent == null ? node : root;
    }
    private AVLNode avlBalanceAfterInsertion(AVLNode t){
	AVLNode x,y,z=null;
	while(t.parent.parent != null){
	    x=t;
	    y=t.parent;
	    z=t.parent.parent;
	    int rotationType = whichRotation(x,y,z);
	    //System.out.println(Math.abs(AVLHeight( z.left )- AVLHeight( z.right )));
	    if(Math.abs(AVLHeight( z.left )- AVLHeight( z.right )) > 1){
		//System.out.println("\nRotation = "+rotationType);
		if(rotationType == 0){
		    return zigZagType1(x,y,z);
		}
		else if(rotationType == 1){
		    return zigZagType2(x,y,z);
		}
		else if(rotationType == 2){
		    return zigZigType1(x,y,z);
		}
		else{
		    return zigZigType2(x,y,z);
		}
	    }
	    t=t.parent;
	}//while close
	//System.out.println("\nNo ratation required.");
	return t;
    }
    
    
    @Override
    public AVLNode deleteAVL(AVLNode node_to_be_deleted,AVLNode root) {
	// TODO Auto-generated method stub
	
	if(node_to_be_deleted == null) // node not found
	    return root;
	else{
	    // case 1: node_to_be_deleted is a leaf node
	    if(node_to_be_deleted.left == null && node_to_be_deleted.right == null){
		return delete_AVL_Leaf(node_to_be_deleted,root);
	    }
	    // case 2: node_to_be_deleted has a left child, but no right child
	    else if(node_to_be_deleted.left != null && node_to_be_deleted.right == null){
		return deleteAVL_with_Left_Child_and_No_Right_Child(node_to_be_deleted,root);
	    }
	    // case 3: node_to_be_deleted has no left child but has a right child
	    else if(node_to_be_deleted.left == null && node_to_be_deleted.right != null){
		return deleteAVL_no_left_child_one_right_child(node_to_be_deleted,root);
	    }
	    // case 4: node has both child
	    else{
		return deleteAVL_both_children(node_to_be_deleted,root);
	    }
	}
	
    }
    
    private AVLNode deleteAVL_both_children(AVLNode node_to_be_deleted, AVLNode root){
	System.out.println(node_to_be_deleted.key);
	AVLNode inPre = inOrderPredecessor(node_to_be_deleted);
	AVLNode temp = inPre.parent;
	while(temp != node_to_be_deleted){
	    
	    temp = temp.parent;
	}
	node_to_be_deleted.key = inPre.key;
	return deleteAVL(inPre, root);
	
    }
    private AVLNode deleteAVL_no_left_child_one_right_child(AVLNode node_to_be_deleted, AVLNode root){
	if(node_to_be_deleted.parent == null)
	    return null;
	else{
	    // find inorder predecessor
	    AVLNode inPre = node_to_be_deleted.right;
	    //Only one left child
	    
	    inPre.parent = node_to_be_deleted.parent;
	    if(node_to_be_deleted.parent!=null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left=inPre;
	    else if(node_to_be_deleted.parent !=null ) node_to_be_deleted.parent.right= inPre;
	    //Update height of parent
		
	    node_to_be_deleted.parent.height = Math.max(AVLHeight(node_to_be_deleted.parent.left), AVLHeight(node_to_be_deleted.parent.right)) + 1;
	    //balance AVL
	    AVLNode node = node_to_be_deleted.parent;
	    AVLNode temp = balanceAVL(node,null,root);
	    
	    while(temp.parent !=null ) temp = balanceAVL(temp,null,root);
	    return root;
	    
	}
    }
    private AVLNode deleteAVL_with_Left_Child_and_No_Right_Child(AVLNode node_to_be_deleted, AVLNode root){
	if(node_to_be_deleted.parent == null)
	    return null;
	else{
	    //System.out.println("Node to be deleted = "+node_to_be_deleted.key);
	    // find inorder predecessor
	    AVLNode inPre = node_to_be_deleted.left;
	    //Only one left child
	    
	    inPre.parent = node_to_be_deleted.parent;
	    if(node_to_be_deleted.parent!=null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left=inPre;
	    else if(node_to_be_deleted.parent !=null ) node_to_be_deleted.parent.right= inPre;
	    //Update height of parent
	    node_to_be_deleted.parent.height = Math.max(AVLHeight(node_to_be_deleted.parent.left), AVLHeight(node_to_be_deleted.parent.right)) + 1;
	    //balance AVL
	    AVLNode node = node_to_be_deleted.parent;
	    AVLNode temp = balanceAVL(node,null,root);
	    
	    while(temp.parent !=null ) temp = balanceAVL(temp,null,root);
	    return root;
	    
	}
    }
    
    private AVLNode delete_AVL_Leaf(AVLNode node_to_be_deleted, AVLNode root){
	if(node_to_be_deleted.parent == null)
	    return null;
	else{
	    
	    if(node_to_be_deleted.parent!=null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left=null;
	    else if(node_to_be_deleted.parent !=null ) node_to_be_deleted.parent.right= null;
	    
	    //Update height of parent
	    node_to_be_deleted.parent.height = Math.max(AVLHeight(node_to_be_deleted.parent.left), AVLHeight(node_to_be_deleted.parent.right)) + 1;
	    
	    //balance AVL
	    AVLNode node = node_to_be_deleted.parent;
	    AVLNode temp = balanceAVL(node,null,root);
	   
	    while(temp.parent !=null ) temp = balanceAVL(temp,null,root);
	    return root;
	}// if main block
	    
    }//method close
    private AVLNode balanceAVL(AVLNode node, AVLNode prevNode, AVLNode root){
	  
	 while(node.parent != null && !checkImbalance(node)) { prevNode = node; node = node.parent;}
	 if(node.parent == null)   // root is reached and no height imbalance
	     return root;
	 else{
	     	// decide the rotation
		if(node.left == prevNode){
		    //System.out.println("imbalance at"+node.key+" "+node.left.key);
		    AVLNode x,y,z;
		    z = node;
		    y = node.right;
		    x = AVLHeight(y.left) > AVLHeight(y.right) ? y.left: y.right; 
		    System.out.println("x="+x.key+ ", y="+y.key+", z="+z.key);
		    return rotationDecideRoutine(x,y,z);
		}
		else{
		    AVLNode x,y,z;
		    z = node;
		    y = node.left;
		    x = AVLHeight(y.left) > AVLHeight(y.right) ? y.left: y.right; 
		    System.out.println("x="+x.key+ ", y="+y.key+", z="+z.key);
		    return rotationDecideRoutine(x,y,z);
		}
	    }
    }
    
    // method to decide which rotation to take and do it in case of deletion
    private AVLNode rotationDecideRoutine(AVLNode x,AVLNode y,AVLNode z){
	int rotationType = whichRotation(x, y, z);
	if(rotationType == 0){
	    return zigZagType1(x,y,z);
	}
	else if(rotationType == 1){
	    return zigZagType2(x,y,z);
	}
	else if(rotationType == 2){
	    return zigZigType1(x,y,z);
	}
	else{
	    return zigZigType2(x,y,z);
	}
    }//function close
    
    
    private boolean checkImbalance(AVLNode node){
	if(Math.abs(AVLHeight(node.left)-AVLHeight(node.right))>1)
	    return true;
	return false;
    }
    private AVLNode inOrderPredecessor(AVLNode node){
	if(node.left==null)
	    return null;
	node=node.left;
	while(node.right !=null)
	    node=node.right;
	return node;
    }
    
    @Override
    public AVLNode searchAVL(int key,AVLNode root) {
	// TODO Auto-generated method stub
	 
	if(root == null)
	    return null;
	if(key == root.key)
	    return root;
	else if(key <= root.key)
	    return searchAVL(key,root.left);
	else
	    return searchAVL(key,root.right);
	
    }

    @Override
    public int sizeAVL(AVLNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return 0;
	return 1+sizeAVL(root.left)+sizeAVL(root.right);
	
    }

    @Override
    public boolean isEmpty(AVLNode root) {
	// TODO Auto-generated method stub
	return root == null;
    }
    
    @Override
    public void makeEmpty(AVLNode root){
	root = null;
    }
    
    @Override
    public int AVLHeight(AVLNode node){
	return node == null ? -1 : node.height;
    }
    
    

    @Override
    public int whichRotation(AVLNode x,AVLNode y,AVLNode z){
	if(y.left != null && z.right != null && (y.left.key == x.key && z.right.key == y.key))
	    return 0;
	else if(y.right != null && z.left != null && (y.right.key == x.key && z.left.key == y.key))
	    return 1;
	else if(y.right != null && z.right != null && (y.right.key == x.key && z.right.key == y.key))
	    return 2;
	else
	    return 3;
    }
    @Override
    public AVLNode zigZagType1(AVLNode x, AVLNode y, AVLNode z) {
	// TODO Auto-generated method stub
	//System.out.println(x.key+" "+y.key+" "+z.key);
	AVLNode t2,t3;
	t2 = x.left;t3 = x.right;
	x.parent= z.parent;
	
	if(z.parent!= null && z.parent.left == z)
	    x.parent.left=x;
	else if(z.parent != null)
	    x.parent.right=x;
	
	    
	x.left=z;z.parent=x;
	x.right=y; y.parent=x;
	
	z.right=t2;
	y.left=t3;
	
	if(t3 != null) t3.parent=y;
	if(t2 != null) t2.parent=z;
	
	//update heights
	z.height=Math.max( AVLHeight( z.left ), AVLHeight( z.right ) ) + 1;
	y.height=Math.max( AVLHeight( y.left ), AVLHeight( y.right ) ) + 1;
	x.height=Math.max( AVLHeight( x.left ), AVLHeight( x.right ) ) + 1;
	return x;
    }
    
    @Override
    public AVLNode zigZagType2(AVLNode x, AVLNode y, AVLNode z) {
	// TODO Auto-generated method stub
	//System.out.println(x.key+" "+y.key+" "+z.key);
	AVLNode t3,t4;
	t3 = x.left;t4 = x.right;
	x.left=y; x.parent= z.parent;
	
	if(z.parent!= null && z.parent.left == z)
	    x.parent.left=x;
	else if(z.parent != null)
	    x.parent.right=x;
	
	y.parent=x;
	x.right=z; z.parent=x;
	
	y.right=t3;
	z.left=t4;
	
	if(t3 != null) t3.parent=y;
	if(t4 != null) t4.parent=z;
	
	//update heights
	z.height=Math.max( AVLHeight( z.left ), AVLHeight( z.right ) ) + 1;
	y.height=Math.max( AVLHeight( y.left ), AVLHeight( y.right ) ) + 1;
	x.height=Math.max( AVLHeight( x.left ), AVLHeight( x.right ) ) + 1;
	return x;
    }
    @Override
    public AVLNode zigZigType1(AVLNode x, AVLNode y, AVLNode z) {
	//System.out.println(x.key+" "+y.key+" "+z.key);
	// TODO Auto-generated method stub
	 AVLNode t2=y.left;
	 y.left=z;
	 y.parent=z.parent;
	 
	 if(z.parent!= null && z.parent.left == z)
	     y.parent.left=y;
	 else if(z.parent != null)
	     y.parent.right=y;
		 
	 z.parent=y;
	 z.right=t2;
	 
	 if(t2 != null) t2.parent=z;
		
	 //update heights
	 z.height=Math.max( AVLHeight( z.left ), AVLHeight( z.right ) ) + 1;
	 y.height=Math.max( AVLHeight( y.left ), AVLHeight( y.right ) ) + 1;
	 return y;
    }

    @Override
    public AVLNode zigZigType2(AVLNode x, AVLNode y, AVLNode z) {
	 // TODO Auto-generated method stub
	 AVLNode t3=y.right;
	 y.right=z;y.parent=z.parent;
	 
	 if(z.parent!= null && z.parent.left == z)
	    y.parent.left=y;
	 else if(z.parent != null)
	    y.parent.right=y;
	 
	 
	 z.parent=y;
	 z.left=t3;
	 
	 if(t3 != null) t3.parent=z;
	 
	 //update heights
	 z.height=Math.max( AVLHeight( z.left ), AVLHeight( z.right ) ) + 1;
	 y.height=Math.max( AVLHeight( y.left ), AVLHeight( y.right ) ) + 1;
	 // inOrder(y);
	 return y;
    }

    @Override
    public void preOrder(AVLNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return;
	System.out.println(root.key);
	inOrder(root.left);	
	inOrder(root.right);
    }

    @Override
    public void inOrder(AVLNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return;
	inOrder(root.left);
	System.out.print(root.key+" ");
	inOrder(root.right);
	 
    }
}