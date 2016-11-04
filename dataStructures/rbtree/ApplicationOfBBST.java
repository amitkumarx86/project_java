package dataStructures.rbtree;

class ApplicationOfBBST extends RBTree implements BalancedBinarySearchTreeApplications{
    private static boolean printFlag = false;
    
    
    public void decreaseNumberOfNodes(RBNode node_to_be_deleted) {
   	
   	if(node_to_be_deleted.parent != null) {
   	    	node_to_be_deleted.number_of_nodes -=1;
           	node_to_be_deleted = node_to_be_deleted.parent;
           	while(node_to_be_deleted.parent!=null) {
           	    node_to_be_deleted.number_of_nodes -= 1;
           	    node_to_be_deleted=node_to_be_deleted.parent;
           	}
   	}
       }
       public void decreaseSum(RBNode node_to_be_deleted) {
      	// TODO Auto-generated method stub
   	int deleteKey = node_to_be_deleted.key;
   	if(node_to_be_deleted.parent != null) {
           	if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.leftSum -= node_to_be_deleted.key;
           	else node_to_be_deleted.parent.rightSum -= node_to_be_deleted.key;
           	 
           	node_to_be_deleted = node_to_be_deleted.parent;
           	
           	while(node_to_be_deleted.parent!=null) {
           	    if(node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.leftSum -= deleteKey;
           	    else node_to_be_deleted.parent.rightSum -= deleteKey;
           	    node_to_be_deleted=node_to_be_deleted.parent;
           	}
   	}
       }
    @Override
    public RBNode findNodeHavingRank(int rank, RBNode root) {
	// TODO Auto-generated method stub
	RBNode node = root;
	
	while(node != null) {	    
		if((node.right == null ? 0 : node.right.number_of_nodes) + 1 == rank)
		    return node;
		else if(rank < (node.right == null ? 0 : node.right.number_of_nodes) + 1)
		    node=node.right;
		else {
		    rank -= 1+(node.right == null ? 0 : node.right.number_of_nodes) ; 
		    node=node.left;
		}
    	}
	return null;
    }
    @Override
    public int preFixSum(RBNode searchNode, RBNode root) {
	// TODO Auto-generated method stub
	RBNode node = root;
	int preSum = 0 ;
	while(node != null) {	    
		if(node.equals(searchNode)) 
		    return preSum + node.leftSum;
		else if(node.key >= searchNode.key) 
		    node = node.left;
		else {
		    preSum += node.leftSum + node.key;
		    node = node.right;
		}
	}
	return 0;
	 
    }
    @Override
    public int sum(int a, int b) {
	// TODO Auto-generated method stub
	return 0;
    }
    @Override
    public int numberOfElementsBetween(int a, int b) {
	// TODO Auto-generated method stub
	return 0;
    }
    @Override
    public int average(int a, int b) {
	// TODO Auto-generated method stub
	return 0;
    }
    @Override
    public int minInRange(int a, int b) {
	// TODO Auto-generated method stub
	return 0;
    }
    @Override
    public int maxInRange(int a, int b) {
	// TODO Auto-generated method stub
	return 0;
    }
    @Override
    public int rankOfNode(int key, RBNode root) {
	if(root.key == key) {
	    if(root.right != null)
		return root.right.number_of_nodes + 1 ;
	    else
		return 1;
	}
	RBNode node = root;
	int rank=0;
	while(node != null){
	    if(node.key == key) {
		if(node.right != null)
		    return rank + node.right.number_of_nodes + 1 ;
		else
		    return rank += 1;
	    }
	    else if(node.key > key) {
		if(node.right != null) rank += node.right.number_of_nodes + 1;
		node=node.left;
	    }
	    else
		node=node.right;
	}	    
	return rank; // rank = 0 in case element is not present
    }
    public RBNode insertRB(int key, RBNode root) {
	// TODO Auto-generated method stub
	System.out.print(key+" ");
	RBNode node = root;
	
	if(node == null){								//for first node
	    node = new RBNode(key);
	    node.parent = null;
	    node.color  = _BLACK;
	    node.number_of_nodes = 1;
	    return node;
	}
	else{
	    boolean flag = false;
	    while(true){
		if(key <= node.key) { 
		    if(node.left == null) {
			node.number_of_nodes += 1;
			node.leftSum += key;
			break;
		    } 
		    else { 
			node.number_of_nodes += 1;
			node.leftSum += key;
			node = node.left;
		    }
		}
		else { 
		    if (node.right == null ) {
			node.number_of_nodes += 1; 
			node.rightSum += key;
			flag = true;
			break;
		    } 
		    else { 
			node.number_of_nodes += 1 ;
			node.rightSum += key;
			node = node.right;
		    }
		}
	    }
	    //System.out.println(flag);
	    RBNode newNode = new RBNode(key);
	    
	    if(flag) node.right = newNode;
	    else node.left = newNode;
	    
	    newNode.parent = node;
	    newNode.color  = _RED;
	    
	    // Initialize #Nodes in new Node
	    newNode.number_of_nodes = 1;
	    
	    // call FIXUP function to maintain property 4 (Red node should have black children)
	    // Double RED problem
	    if(newNode.parent.color == _RED){
		
		// Debugging 
		if(printFlag)
		    System.out.println("-------Insert FIX UP is called-----------");

		// Insert Fixup Routine 
		RBNode temp = insertFIXUP(newNode,root);
		if(temp.parent == null) { 
		    temp.color = _BLACK; // fixing property 1 ( root should be BLACK) 
		    return temp;
		}
		else {
		    while(temp.parent!= null) temp = temp.parent;
		    return temp;
		}
	    }	
	    else 
		return root;
	}
	 
    }
    protected RBNode insertFIXUP(RBNode x, RBNode root){
	while(x.parent != null && x.parent.color == _RED && x.color == _RED){  
	    if(x.parent == x.parent.parent.left){			
		// When x's parent is in left side of x's grand parent
		RBNode uncle = x.parent.parent.right;			 
		if(uncle != null && uncle.color == _RED){
		    // Debugging 
		    if(printFlag)
			System.out.println("-------Case 1 applied-----------I");
		    
		    uncle.color = _BLACK; 				// case 1 y(Uncle's color is red
		    x.parent.color = _BLACK; 				// case 1 
		    x.parent.parent.color=_RED;				// case 1 
		    x= x.parent.parent;					// case 1
		}
		else if(x == x.parent.right && (uncle == null || uncle.color == _BLACK)){
		    // Debugging 
		    if(printFlag)
			System.out.println("-------Case 2 applied-----------I");
		    
		    RBNode y,z;						// case 2 (Uncle is BLACK) 
		    y = x.parent;					// case 2
		    z = x.parent.parent;				// case 2
		    x = zigZagType2(x,y,z);				// case 2
		}
		else if(x == x.parent.left && (uncle == null || uncle.color == _BLACK) && (x.parent.right == null || x.parent.right.color == _BLACK)){
		    // Debugging 
		    if(printFlag)
			System.out.println("-------Case 3 applied-----------I");
		    
		    RBNode y,z;						// case 3 
		    							// (Sibling is black and Uncle is Black)
		    y = x.parent;					// case 3
		    z = x.parent.parent;				// case 3
		    x = zigZigType2(x,y,z);				// case 3
		    
		}
	    }
	    else{							
		// When x's parent is in right side of x's grand parent
		RBNode uncle = x.parent.parent.left;			// case 1 y(Uncle's color is red
		if(uncle != null && uncle.color == _RED){		// case 1 
		    // Debugging 
		    if(printFlag)
			System.out.println("-------Case 1 applied-----------II");
		    
		    uncle.color = _BLACK; 				// case 1 
		    x.parent.color = _BLACK; 				// case 1 
		    x.parent.parent.color=_RED;				// case 1 
		    x= x.parent.parent;					// case 1
		   
		}
		else if(x == x.parent.left && (uncle == null || uncle.color == _BLACK)){
		    // Debugging 
		    if(printFlag)
			System.out.println("-------Case 2 applied-----------II");
		    
		    RBNode y,z;						// case 2 (Uncle is BLACK) 
		    y = x.parent;					// case 2
		    z = x.parent.parent;				// case 2
		    x = zigZagType1(x,y,z);				// case 2
		   
		}
		else if(x == x.parent.right && (uncle == null || uncle.color == _BLACK) && (x.parent.left == null || x.parent.left.color == _BLACK)){
		    // Debugging 
		    if(printFlag)
			System.out.println("-------Case 3 applied-----------II");
		    
		    RBNode y,z;						// case 3 
		    							// (Sibling is black and Uncle is Black)
		    y = x.parent;					// case 3
		    z = x.parent.parent;				// case 3
		    x = zigZigType1(x,y,z);				// case 3
		    
		}
	    }
	}// while
	return x;
    }
   
    public RBNode deleteRB(RBNode node_to_be_deleted, RBNode root) {
	// TODO Auto-generated method stub
	//System.out.println("Delete "+node_to_be_deleted.key+" \n");
	{
	    // Case 1: leaf node and it is RED
	    if(node_to_be_deleted.color == _RED && node_to_be_deleted.left == null && node_to_be_deleted.right == null){
		if(node_to_be_deleted.parent != null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = null;
		else if(node_to_be_deleted.parent != null) node_to_be_deleted.parent.right = null;
		return root;
	    }
	    // leaf node is BLACK node
	    else if(node_to_be_deleted.color == _BLACK && node_to_be_deleted.left == null && node_to_be_deleted.right == null){
		 
		if(node_to_be_deleted.parent != null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = null;
		else if(node_to_be_deleted.parent != null) node_to_be_deleted.parent.right = null;
		
		// Only one node left
		if(node_to_be_deleted.parent == null)
		    return null;
		
		// Debugging
		if(printFlag)
		    System.out.println("Fix Up is called");
		
		RBNode temp = doubleBlackFIXUP(null,node_to_be_deleted.parent); 
		 
		if(temp.parent == null ) { temp.color = _BLACK;return temp;}
		else {
		    while(temp.parent != null) temp=temp.parent;
		    return temp;}
	    }
	    else{
		// Case 2: node_to_be_deleted has one left or right child
		
		RBNode inPre = inOrderPredecessor(node_to_be_deleted);
		if(inPre == null){                        		// Case 2: node_to_be_deleted has one right child
		    
		    if(node_to_be_deleted.parent!= null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = node_to_be_deleted.right;
		    else if(node_to_be_deleted.parent!= null) node_to_be_deleted.parent.right = node_to_be_deleted.right;
		    
		    node_to_be_deleted.right.parent = node_to_be_deleted.parent;
		    node_to_be_deleted.right.color = _BLACK;
		    
		    if(node_to_be_deleted == root)
			return node_to_be_deleted.right;
		    else
			return root;
		}
		else if(inPre.parent == node_to_be_deleted && node_to_be_deleted.right == null){     	// Case 2: node_to_be_deleted has one left child
		     
		    //node to be deleted is BLACK and left child is RED
		    if(node_to_be_deleted.parent != null && node_to_be_deleted.parent.left == node_to_be_deleted) node_to_be_deleted.parent.left = inPre;
		    else if(node_to_be_deleted.parent != null) node_to_be_deleted.parent.right = inPre;
			
		    if(node_to_be_deleted.parent == null){
			    inPre.parent = null; inPre.color = _BLACK; return inPre;
		    }
		    inPre.parent = node_to_be_deleted.parent;
		    inPre.color = _BLACK;
		    return root;
		}
		else{ 							// Case 3 : more than one child
		    
		    if(printFlag)
			System.out.println("InOrder Predecessor is found "+inPre.key);
		    node_to_be_deleted.key = inPre.key;
		    return deleteRB(inPre, root);
		}
	    }
	}
    }
    protected RBNode doubleBlackFIXUP(RBNode drbNode, RBNode  parent){
	// drbNode is node on which double black problem is called
	
	// if parent is null then just return drbNode
	if(parent == null) return drbNode;
	
	// decide which is sibling
	RBNode sibling = parent.left == drbNode ? parent.right : parent.left;
	
	// Case 1: when sibling is red
	if(sibling.color == _RED){				// Case 1 : Uncle is RED
	    
	    // when sibling is in left side of its parent
	    if(sibling.parent.left == sibling){
		if(printFlag)
		    System.out.println("\n----------Case 1 is called---------I");
		drbNode = zigZigInverseType1(drbNode, parent, sibling,true);
		sibling = parent.left;
	    }
	    // when sibling is in right side of its parent
	    else{
		if(printFlag)
		    System.out.println("\n----------Case 1 is called---------II");
		drbNode = zigZigInverseType2(drbNode, parent, sibling,true);
		sibling = parent.right;
		 
	    }
	}
	// Case 2 (When sibling's both children are black or both are null)
	//System.out.println(sibling.key);
	if(sibling.color == _BLACK && ((sibling.left == null && sibling.right == null) || ((sibling.left != null ? sibling.left.color : _BLACK) == _BLACK && sibling.right.color == _BLACK))){
	   
	    if(parent.color == _RED){				// parent color is red	
		if(printFlag)
		    System.out.println("\n----------Case 2 is called---------I");
		parent.color  =  _BLACK; sibling.color = _RED;
		drbNode = parent;
	    }
	    else{						// parent color is black
		if(printFlag)
		    System.out.println("\n----------Case 2 is called---------II");
		parent.color  =  _BLACK; sibling.color = _RED;
		// Again double red problem arises
		return doubleBlackFIXUP(parent, parent.parent);
	    }
	}
	// Case 3 (When sibling's one child is Red)
	if (sibling.color == _BLACK && ((sibling.right!=null && sibling.right.color == _RED) || (sibling.left!=null && sibling.left.color == _RED))){
	    // sibling to be in left side 
	    
	    if(sibling.parent.left == sibling){
		if(printFlag)
			System.out.println("\n----------Case 3 is called---------I");
		
		if((sibling.right == null ? _BLACK : sibling.right.color) == _RED){
		     
		    RBNode e = sibling.right; e.parent = sibling.parent;
		    RBNode z = e.left; 
		    sibling.right = z;
		    if(sibling.parent != null && sibling.parent.left == sibling) sibling.parent.left = e;
		    else if(sibling.parent!=null) sibling.parent.right = e;
		    sibling.parent = e;
		    e.left = sibling;
		     
		    // Number of nodes update
		    if(sibling.parent != null) {
			sibling.parent.number_of_nodes -= sibling.number_of_nodes;
			sibling.parent.leftSum         -= sibling.key + sibling.leftSum + sibling.rightSum;
		    }
		    if(z != null) {
			z.parent = sibling;
			//Number of nodes update
			e.number_of_nodes       -= z.number_of_nodes;
			sibling.number_of_nodes += z.number_of_nodes;
			
			e.leftSum               -= z.key + z.leftSum + z.rightSum;
			sibling.rightSum        += z.key + z.leftSum + z.rightSum;
		    }
		    //Number of nodes Update
		    e.number_of_nodes += sibling.number_of_nodes;
		    e.leftSum         += sibling.key + sibling.leftSum + sibling.rightSum;
		    
		    if(e.parent != null) {
			e.parent.number_of_nodes += e.number_of_nodes;
			e.parent.leftSum         += e.key + e.leftSum + e.rightSum;
		    }
		    
		    e.color = sibling.color + e.color - (sibling.color = e.color);
		    sibling = e;
		   
		}
		//case 4
		
		if(sibling.left != null && sibling.left.color == _RED) {
		    if(printFlag)
			System.out.println("\n----------Case 3.1 is called---------I");
		    drbNode = zigZigInverseType1(drbNode, parent,sibling,false);
		}
		    
	    }
	    //case 3 for sibling to be in right side
	    else{
		if((sibling.left == null ? _BLACK : sibling.left.color) == _RED){
		    if(printFlag)
			System.out.println("\n----------Case 3 is called---------II");
		    
		    RBNode d = sibling.left; d.parent = sibling.parent;
		    RBNode w = d.right; 
		    sibling.left = w;
		    if(sibling.parent != null && sibling.parent.left == sibling) sibling.parent.left = d;
		    else if(sibling.parent!=null) sibling.parent.right = d;
		    sibling.parent = d;
		    d.right = sibling;
		   
		    // Number of nodes and sum update
		    if(sibling.parent != null) {
			sibling.parent.number_of_nodes -= sibling.number_of_nodes;
			sibling.parent.rightSum        -= sibling.key + sibling.leftSum + sibling.rightSum;
		    }
		    sibling.number_of_nodes -= d.number_of_nodes;
		    sibling.leftSum         -= d.key + d.leftSum + d.rightSum;
		    
		    if(w != null) {
			w.parent = sibling;
			//Number of nodes update
			d.number_of_nodes       -= w.number_of_nodes;
			sibling.number_of_nodes += w.number_of_nodes;
			
			d.rightSum              -= w.key + w.leftSum + w.rightSum;
			sibling.leftSum         += w.key + w.leftSum + w.rightSum;
		    }
		    //Number of nodes Update
		    d.number_of_nodes += sibling.number_of_nodes;
		    d.rightSum        += sibling.key + sibling.leftSum + sibling.rightSum;
		    
		    if(d.parent != null) {
			d.parent.number_of_nodes += d.number_of_nodes;
			d.parent.rightSum        += d.key + d.leftSum + d.rightSum;
		    }
		    d.color = sibling.color + d.color - (sibling.color = d.color);
		    sibling = d;
		    
		     
		}
		//case 4
		if(sibling.right != null && sibling.right.color == _RED) {
		    if(printFlag)
			System.out.println("\n----------Case 3.1 is called---------II");
		    drbNode = zigZigInverseType2(drbNode, parent,sibling,false);
		}
	    }
	}	
	return drbNode;
    }
    
    // This is used in delete fix up
    protected RBNode zigZigInverseType1(RBNode x, RBNode parent, RBNode sibling, boolean case1or3){
	if(printFlag)
	    System.out.println("\ncalled : zigZigInverseType1");
	/*
	 * if case1or3 flag is true
	 * 	When Sibling/Uncle is in left Side of parent of x(new node after deletion)
	 * else
	 * 	When Sibling/Uncle is black and right child is RED
	 */
	
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
	
	// Update number of nodes
	parent.number_of_nodes -= sibling.number_of_nodes;
	// Update sum
	parent.leftSum         -= sibling.key + sibling.leftSum + sibling.rightSum;
	
	if(t1 != null) {
	    t1.parent = parent;
	    // Update number of nodes
	    sibling.number_of_nodes  -= t1.number_of_nodes;
	    parent.number_of_nodes   += t1.number_of_nodes;
	    // Update sum
	    sibling.rightSum         -= t1.key + t1.leftSum + t1.rightSum;
	    parent.leftSum           += t1.key + t1.leftSum + t1.rightSum;
	}
	// here parent variable is parent of x not sibling
	// Update number of nodes
	sibling.number_of_nodes += parent.number_of_nodes;
	// Update sum
	sibling.rightSum        += parent.key + parent.leftSum + parent.rightSum;
	
	// in case == 1 flag is true else false
	if(!case1or3)
	    if(sibling.left != null ) sibling.left.color = _BLACK;
	
	return sibling;
		
    }
    // This is used in delete fix up
    protected RBNode zigZigInverseType2(RBNode x, RBNode parent, RBNode sibling, boolean case1or3){
	if(printFlag)
	    System.out.println("\ncalled : zigZigInverseType2");
	/*
	 * if case1or3 flag is true
	 * 	When Sibling/Uncle is in right Side of parent of x(new node after deletion)
	 * else
	 * 	When Sibling/Uncle is black and right child is RED
	 */
	 
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
	
	// Update number of nodes
	parent.number_of_nodes -= sibling.number_of_nodes;
	// Update sum
	parent.rightSum        -= sibling.key + sibling.leftSum + sibling.rightSum;
	
	if(t1 != null) {
	    t1.parent=parent;
	    // Update number of nodes
	    sibling.number_of_nodes -= t1.number_of_nodes;
	    parent.number_of_nodes  += t1.number_of_nodes;
	    // Update sum
	    sibling.leftSum         -= t1.key + t1.leftSum + t1.rightSum;
	    parent.rightSum         += t1.key + t1.leftSum + t1.rightSum;
	}
	// here parent variable is parent of x not sibling
	// Update number of nodes
	sibling.number_of_nodes += parent.number_of_nodes;
	// Update sum
	sibling.leftSum         += parent.key + parent.leftSum + parent.rightSum;
	
	if(!case1or3)
	    if(sibling.right != null ) sibling.right.color = _BLACK;
	
	
	
	return sibling;
		
    }
    public RBNode zigZagType1(RBNode x, RBNode y, RBNode z) {
	// TODO When x is in left of z 
	if(printFlag)
	System.out.println("zigzag1");
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
	
	// Z's right child is y, y's left child is x	
	
	// Number of Node Update
	z.number_of_nodes -= y.number_of_nodes;
	y.number_of_nodes -= x.number_of_nodes;
		
	// Update sum
	z.rightSum         -= (y.key + y.leftSum + y.rightSum);
	y.leftSum        -= (x.key + x.leftSum + x.rightSum);

	
	if(t3 != null) {
	    t3.parent=y;
	    // Number of Node Update
	    y.number_of_nodes += t3.number_of_nodes;
	    x.number_of_nodes -= t3.number_of_nodes;
	    // Update sum
	    x.rightSum        -= t3.key + t3.rightSum + t3.leftSum;
	    y.leftSum         += t3.key + t3.rightSum + t3.leftSum;
	    
	}
	if(t2 != null) {
	    t2.parent=z;
	    // Number of Node Update
	    z.number_of_nodes += t2.number_of_nodes;
	    x.number_of_nodes -= t2.number_of_nodes;
	    // Update sum
	    x.leftSum         -= t2.key + t2.rightSum + t2.leftSum;
	    z.rightSum        += t2.key + t2.rightSum + t2.leftSum;
	}

	//update colors
	x.color=_BLACK; z.color=_RED; y.color=_RED;
	
	//update number of nodes
	x.number_of_nodes  += z.number_of_nodes + y.number_of_nodes;
	// Update sum
	x.rightSum         += (y.key + y.leftSum + y.rightSum);
	x.leftSum          += (z.key + z.leftSum + z.rightSum);
	
	return x;
    }
    public RBNode zigZagType2(RBNode x, RBNode y, RBNode z) {
	// TODO When x is in right of z 
	if(printFlag)
	System.out.println("zigzag2");
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
	
	// Z's left child is y, y's right child is x	
	
	// Number of Node Update
	z.number_of_nodes -= y.number_of_nodes;
	y.number_of_nodes -= x.number_of_nodes;
	
	// Update sum
	z.leftSum         -= (y.key + y.leftSum + y.rightSum);
	y.rightSum        -= (x.key + x.leftSum + x.rightSum);
	
	if(t3 != null) {
	    t3.parent=y;
	    //update number of nodes
	    y.number_of_nodes += t3.number_of_nodes;
	    x.number_of_nodes -= t3.number_of_nodes;
	    // Update sum
	    x.leftSum         -= t3.key + t3.rightSum + t3.leftSum;
	    y.rightSum        += t3.key + t3.rightSum + t3.leftSum;
	    
	}
	if(t4 != null) {
	    t4.parent=z;
	    //update number of nodes
	    z.number_of_nodes += t4.number_of_nodes;
	    x.number_of_nodes -= t4.number_of_nodes;
	    // Update sum
	    x.rightSum        -= t4.key + t4.rightSum + t4.leftSum;
	    z.leftSum         += t4.key + t4.rightSum + t4.leftSum;
	}
	
	 //update colors
	x.color=_BLACK; z.color=_RED; y.color=_RED;
	//update number of nodes
	x.number_of_nodes += z.number_of_nodes + y.number_of_nodes;
	// Update sum
	x.leftSum         += (y.key + y.leftSum + y.rightSum);
	x.rightSum        += (z.key + z.leftSum + z.rightSum);
	
	return x;
    }
    public RBNode zigZigType1(RBNode x, RBNode y, RBNode z) {
   	// TODO When x is in right of z 
	if(printFlag)
	System.out.println("zigzig1");
   	RBNode t2=y.left;
   	y.left=z;
   	y.parent=z.parent;
   	 
   	if(z.parent!= null && z.parent.left == z)
   	    y.parent.left=y;
   	else if(z.parent != null)
   	    y.parent.right=y;
   		 
   	z.parent=y;
   	z.right=t2;
   	//update number of nodes
   	z.number_of_nodes -= y.number_of_nodes; 
   	// Update sum
   	z.rightSum        -= (y.key + y.leftSum + y.rightSum);
   	
   	if(t2 != null) {
   	    t2.parent=z;
   	    //update number of nodes
   	    y.number_of_nodes -= t2.number_of_nodes;
   	    z.number_of_nodes += t2.number_of_nodes;
   	    // Update sum
   	    y.leftSum         -= t2.key + t2.rightSum + t2.leftSum;
	    z.rightSum        += t2.key + t2.rightSum + t2.leftSum;
   	}
   	//System.out.println();
   	//update colors
   	x.parent.color=_BLACK;
   	z.color=_RED;
   	//update number of nodes
   	y.number_of_nodes += z.number_of_nodes;
   	// Update sum
   	y.leftSum         += (z.key + z.leftSum + z.rightSum);
   	
   	return y;
      }
    public RBNode zigZigType2(RBNode x, RBNode y, RBNode z) {
	// TODO When x is in left of z 
	if(printFlag)
	System.out.println("zigzig2");
	RBNode t3=y.right;
	y.right = z; y.parent=z.parent;
	 
	if(z.parent!= null && z.parent.left == z)
	    y.parent.left=y;
	else if(z.parent != null)
	    y.parent.right=y;
	 
	 
	z.parent=y;
	z.left=t3;
	//update number of nodes
	z.number_of_nodes -= y.number_of_nodes; 
	// Update sum
	z.leftSum         -= (y.key + y.leftSum + y.rightSum);
	
	if(t3 != null) {
	    t3.parent=z;
	    //update number of nodes
	    y.number_of_nodes -= t3.number_of_nodes;
   	    z.number_of_nodes += t3.number_of_nodes;
   	    // Update sum
   	    y.rightSum         -= t3.key + t3.rightSum + t3.leftSum;
	    z.leftSum          += t3.key + t3.rightSum + t3.leftSum;
	}
	 
	 //update colors
	x.parent.color = _BLACK;
	z.color = _RED;
	//update number of nodes
	y.number_of_nodes  += z.number_of_nodes;
	// Update sum
	y.rightSum         += (z.key + z.leftSum + z.rightSum);
	return y;
	
    }
    public void inOrder(RBNode root) {
	// TODO Auto-generated method stub
	if(root == null)
	    return;
	inOrder(root.left);	
	System.out.println(root.key+" =>"+" leftSum : "+root.leftSum+" ,rightSum : "+root.rightSum+" ,color:"+ (root.color == 0 ? "B":"R")+" ,#nodes : "+(root.number_of_nodes)+" ,leftChild : "+ 
			  (root.left != null ? root.left.key : "n/a")+" ,rightChild : "+ (root.right != null ? root.right.key : "n/a"));
	inOrder(root.right);
    }
}