package rbtree;

class RBNode{
    RBNode left, right, parent;
    int key, color;     
    
    public RBNode(){
	left = right = parent = null;
	key = color = 0;
    }
    
    public RBNode(int key){
	left = right = parent = null;
	this.key = key;
	color = 0;
    }
}
public class RBTree implements RBTreeFunctions{
    
    static int _RED = 1, _BLACK = 0;
    
    @Override
    public RBNode insertRB(int key, RBNode root) {
	// TODO Auto-generated method stub
	System.out.println("Insert : "+key);
	RBNode node = root;
	
	if(node == null){								//for first node
	    node = new RBNode(key);
	    node.parent = null;
	    node.color  = _BLACK;
	    return node;
	}
	else{
	    boolean flag = false;
	    while(true){
		if(key <= node.key) { if(node.left == null) break; else node = node.left;}
		else { if (node.right == null ) {flag = true;break;} else node = node.right;}
	    }
	    //System.out.println(flag);
	    RBNode newNode = new RBNode(key);
	    
	    if(flag) node.right = newNode;
	    else node.left = newNode;
	    
	    newNode.parent = node;
	    newNode.color  = _RED;
	    
	    // call FIXUP function to maintain property 4 (Red node should have black children)
	    // Double RED problem
	    if(newNode.parent.color == _RED){
		System.out.println("-------FIX UP is called-----------");
		RBNode temp = insertFIXUP(newNode,root);
		if(temp.parent == null) { 
		    temp.color = _BLACK; // fixing property 1 ( root should be BLACK) 
		    return temp;
		}
		else return root;
	    }	
	    else 
		return root;
	}
	 
    }

    private RBNode insertFIXUP(RBNode x, RBNode root){
	while(x.parent != null && x.parent.color == _RED && x.color == _RED){
	    if(x.parent == x.parent.parent.left){			// When x's parent is in left side of x's grand parent
		RBNode uncle = x.parent.parent.right;			 
		if(uncle != null && uncle.color == _RED){				 
		    System.out.println("-------Case 1 applied-----------I");
		    uncle.color = _BLACK; 				// case 1 y(Uncle's color is red
		    x.parent.color = _BLACK; 				// case 1 
		    x.parent.parent.color=_RED;				// case 1 
		    x= x.parent.parent;					// case 1 
		}
		else if(x == x.parent.right && (uncle == null || uncle.color == _BLACK)){
		    System.out.println("-------Case 2 applied-----------I");
		    RBNode y,z;						// case 2 (Uncle is BLACK) 
		    y = x.parent;					// case 2
		    z = x.parent.parent;				// case 2
		    x = zigZagType2(x,y,z);				// case 2
		}
		else if(x == x.parent.left && (uncle == null || uncle.color == _BLACK) && (x.parent.right == null || x.parent.right.color == _BLACK)){
		    System.out.println("-------Case 3 applied-----------I");
		    RBNode y,z;						// case 3 (Sibling is black and Uncle is Black)
		    y = x.parent;					// case 3
		    z = x.parent.parent;				// case 3
		    x = zigZigType2(x,y,z);				// case 3
		}
	    }
	    else{							// When x's parent is in right side of x's grand parent
		 
		RBNode uncle = x.parent.parent.left;			// case 1 y(Uncle's color is red
		if(uncle != null && uncle.color == _RED){				// case 1 
		    System.out.println("-------Case 1 applied-----------II");
		    uncle.color = _BLACK; 				// case 1 
		    x.parent.color = _BLACK; 				// case 1 
		    x.parent.parent.color=_RED;				// case 1 
		    x= x.parent.parent;					// case 1 
		}
		else if(x == x.parent.left && (uncle == null || uncle.color == _BLACK)){
		    System.out.println("-------Case 2 applied-----------II");
		    RBNode y,z;						// case 2 (Uncle is BLACK) 
		    y = x.parent;					// case 2
		    z = x.parent.parent;				// case 2
		    x = zigZagType1(x,y,z);				// case 2
		}
		else if(x == x.parent.right && (uncle == null || uncle.color == _BLACK) && (x.parent.left == null || x.parent.left.color == _BLACK)){
		    System.out.println("-------Case 3 applied-----------II");
		    RBNode y,z;						// case 3 (Sibling is black and Uncle is Black)
		    y = x.parent;					// case 3
		    z = x.parent.parent;				// case 3
		    x = zigZigType1(x,y,z);				// case 3
		    //System.out.println("x= "+x.key);
		}
	    }
	}// while
	return x;
    }
    
    @Override
    public RBNode deleteRB(RBNode node_to_be_deleted, RBNode root) {
	// TODO Auto-generated method stub
	System.out.println("delete : "+node_to_be_deleted.key);
	
	//if(node_to_be_deleted == null) return null;
	//else
	{
	    // Case 1: leaf node and it is RED
	    if(node_to_be_deleted.left == null && node_to_be_deleted.right == null){
		if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = null;
		else node_to_be_deleted.parent.right = null;
		return root;
	    }
	    else{
		// Case 2: node_to_be_deleted has one left or right child
		RBNode inPre = inOrderPredecessor(node_to_be_deleted);
		if(inPre == null){                        		// Case 2: node_to_be_deleted has one right child
		    if(node_to_be_deleted.color == _RED){		// node_to_be_deleted has red color
			
			// side fixing
			if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = node_to_be_deleted.right;
			else node_to_be_deleted.parent.right = node_to_be_deleted.right;
			
			node_to_be_deleted.right.parent = node_to_be_deleted.parent;
			return root;
		    }
		    else if(node_to_be_deleted.color == _BLACK && node_to_be_deleted.right.color == _RED){  // node_to_be_deleted is black and child has red color
			
			// side fixing
			if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = node_to_be_deleted.right;
			else node_to_be_deleted.parent.right = node_to_be_deleted.right;
			
			node_to_be_deleted.right.parent = node_to_be_deleted.parent;
			node_to_be_deleted.right.color = _BLACK;
			return root;
		    }
		    else{		// Double BLACK problem
			if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = node_to_be_deleted.right;
			else node_to_be_deleted.parent.right = node_to_be_deleted.right;
			
			node_to_be_deleted.right.parent = node_to_be_deleted.parent;
			
			
			RBNode temp =doubleBlackFIXUP(node_to_be_deleted.right,node_to_be_deleted.parent); 
			if(temp.parent == null ) { temp.color = _BLACK;return temp;}
			else return root;
			
		    }
		}
		else if(inPre.parent == node_to_be_deleted && node_to_be_deleted.right == null){     	// Case 2: node_to_be_deleted has one left child
		    
		    if(node_to_be_deleted.color == _RED){		// node_to_be_deleted has red color
			
			// side fixing
			if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = inPre;
			else node_to_be_deleted.parent.right = inPre;
			
			inPre.parent = node_to_be_deleted.parent;
			inPre.color  = _BLACK;
			return root;
		    }
		    else if(node_to_be_deleted.color == _BLACK && inPre.color == _RED){  // node_to_be_deleted is black and child has red color
			
			// side fixing
			if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = inPre;
			else node_to_be_deleted.parent.right = inPre;
			
			inPre.parent = node_to_be_deleted.parent;
			inPre.color = _BLACK;
			return root;
		    }
		    else{		// Double BLACK problem   (node_to_be_deleted.color == _BLACK && inPre.color = _BLACK)
			if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = inPre;
			else node_to_be_deleted.parent.right = inPre;
			
			inPre.parent = node_to_be_deleted.parent;
			//System.out.println(inPre.key+""+inPre.parent.key+" "+inPre.parent.left.key);
			RBNode temp =doubleBlackFIXUP(inPre,node_to_be_deleted.parent); 
			if(temp.parent == null ) { temp.color = _BLACK;return temp;}
			else return root;
		    }
		}
		else{ 							// Case 3 : more than one child
		    node_to_be_deleted.key = inPre.key;
		    return deleteRB(inPre, root);
		}
	    }
	}
	 
    }
    
    private RBNode doubleBlackFIXUP(RBNode drbNode, RBNode  parent){
	System.out.println("delete fix up is called..");
	if(parent == null) return drbNode;
	
	RBNode sibling = parent.left == drbNode ? parent.right : parent.left;
	
	//System.out.println(sibling.key);
	// Case 1
	if(sibling == null){  // sibling is black
	    if(parent.color == _RED){				// parent color is red
		parent.color  =  _BLACK; 
	    }
	    else{						// parent color is black
		parent.color  =  _BLACK; 
		// Again double red problem arises
		return doubleBlackFIXUP(parent, parent.parent);
	    }
	}
	else if(sibling.color == _RED){				// Case 1 : Uncle is RED
	    System.out.println("Case 1 is called..");
	    if(sibling.parent.left == sibling){
		drbNode = zigZigInverseType1(drbNode, parent, sibling,true);
	    }
	    else{
		drbNode = zigZigInverseType2(drbNode, parent, sibling,true);
	    }
	}
	// Case 2
	if(sibling.color == _BLACK && ((sibling.left == null && sibling.right == null) || (sibling.left.color == _BLACK && sibling.right.color == _BLACK))){
	    System.out.println("Case 2 is called..");
	    if(parent.color == _RED){				// parent color is red
		parent.color  =  _BLACK; sibling.color = _RED;
	    }
	    else{						// parent color is black
		parent.color  =  _BLACK; sibling.color = _RED;
		// Again double red problem arises
		return doubleBlackFIXUP(parent, parent.parent);
	    }
	}
	// Case 3
	else {
	    System.out.println("Case 3 is called..");
	    if(sibling.parent.left == sibling){
		 
		drbNode = zigZigInverseType1(drbNode, parent, sibling, false);
	    }
	    else{
		drbNode = zigZigInverseType2(drbNode, parent, sibling, false);
	    }
	}
	return drbNode;
    }
    
    private RBNode zigZigInverseType1(RBNode x, RBNode parent, RBNode sibling, boolean case1or3){
	System.out.println("called Type 1");
	
	sibling.parent = parent.parent;
	sibling.color  = parent.color;
	
	//side fixing
	if(parent.parent != null && parent.parent.left == parent) parent.parent.left = sibling;
	else if(parent.parent != null)parent.parent.right = sibling;
	
	RBNode t1;
	t1 = sibling.right;
	
	sibling.right = parent; 
	parent.parent = sibling;
	parent.color  = case1or3 == true ? _RED : _BLACK;
	parent.left   = t1;
	if(t1 != null) t1.parent = parent;
	return sibling;
		
    }
    private RBNode zigZigInverseType2(RBNode x, RBNode parent, RBNode sibling, boolean case1or3){
	System.out.println("called Type 2");
	sibling.parent = parent.parent;
	sibling.color  = parent.color;
	
	//side fixing
	if(parent.parent != null && parent.parent.left == parent) parent.parent.left = sibling;
	else if(parent.parent != null)parent.parent.right = sibling;
		
	RBNode t1;
	t1 = sibling.left;
	
	sibling.left = parent; 
	parent.parent = sibling;
	parent.color  = case1or3 == true ? _RED : _BLACK;
	parent.right   = t1;
	if(t1 != null) t1.parent=parent;
	return sibling;
		
    }
    private RBNode inOrderPredecessor(RBNode node){
	if(node.left==null)
	    return null;
	node=node.left;
	while(node.right !=null)
	    node=node.right;
	return node;
    }
   
    @Override
    public RBNode searchRB(int key, RBNode root) {
	// TODO Auto-generated method stub
	if(root == null) return null;
	else if(root.key == key)
	    return root;
	else if(key <= root.key) return searchRB(key,root.left);
	else return searchRB(key,root.right);
	 
    }

    @Override
    public int sizeRB(RBNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return 0;
	return 1+sizeRB(root.left)+sizeRB(root.right);
    }
    

    @Override
    public boolean isEmpty(RBNode root) {
	// TODO Auto-generated method stub
	return root == null;
    }

    @Override
    public void makeEmpty(RBNode root) {
	// TODO Auto-generated method stub
	root = null;
    }

    @Override
    public int whichRotation(RBNode x, RBNode y, RBNode z) {
	// TODO Auto-generated method stub
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
    public RBNode zigZagType1(RBNode x, RBNode y, RBNode z) {
	// TODO Auto-generated method stub
	RBNode t2,t3;
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
	
	//update colors
	x.color=_BLACK; z.color=_RED; y.color=_RED;
	return x;
    }
    
    @Override
    public RBNode zigZagType2(RBNode x, RBNode y, RBNode z) {
	// TODO Auto-generated method stub
	RBNode t3,t4;
	t3 = x.left;t4 = x.right;
	x.left=y; x.parent= z.parent;
	
	if(z.parent!= null && z.parent.left == z)
	    z.parent.left=x;
	else if(z.parent != null)
	    z.parent.right=x;
	
	y.parent=x;
	x.right = z; z.parent=x;
	
	y.right=t3;
	z.left=t4;
	
	if(t3 != null) t3.parent=y;
	if(t4 != null) t4.parent=z;
	
	 //update colors
	x.color=_BLACK; z.color=_RED; y.color=_RED;
	return x;
    }
    @Override
    public RBNode zigZigType1(RBNode x, RBNode y, RBNode z) {
	// TODO Auto-generated method stub
	RBNode t2=y.left;
	y.left=z;
	y.parent=z.parent;
	 
	if(z.parent!= null && z.parent.left == z)
	    y.parent.left=y;
	else if(z.parent != null)
	    y.parent.right=y;
		 
	z.parent=y;
	z.right=t2;
	 
	if(t2 != null) t2.parent=z;
	//System.out.println();
	//update colors
	x.parent.color=_BLACK;
	z.color=_RED;
	return y;
   }

    

    @Override
    public RBNode zigZigType2(RBNode x, RBNode y, RBNode z) {
	// TODO Auto-generated method stub
	RBNode t3=y.right;
	y.right = z; y.parent=z.parent;
	 
	if(z.parent!= null && z.parent.left == z)
	    y.parent.left=y;
	else if(z.parent != null)
	    y.parent.right=y;
	 
	 
	z.parent=y;
	z.left=t3;
	 
	if(t3 != null) t3.parent=z;
	 
	 //update colors
	x.parent.color = _BLACK;
	z.color = _RED;
	return y;
	
    }

    @Override
    public void preOrder(RBNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return;
	System.out.println(root.key);
	inOrder(root.left);	
	inOrder(root.right);
    }

    @Override
    public void inOrder(RBNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return;
	
	inOrder(root.left);	
	
	System.out.println(root.key+" color:"+ (root.color == 0 ? "B":"R"));
	inOrder(root.right);
    }
    
}
