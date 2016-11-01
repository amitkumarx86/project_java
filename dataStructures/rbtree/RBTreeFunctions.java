package dataStructures.rbtree;

public interface RBTreeFunctions {
    abstract RBNode insertRB(int x, RBNode root);
    abstract RBNode deleteRB(RBNode node_to_be_deleted,RBNode root);
    abstract RBNode searchRB(int data,RBNode root);
    abstract int sizeRB(RBNode root);
    abstract boolean isEmpty(RBNode root);
    abstract void makeEmpty(RBNode root);
    abstract int whichRotation(RBNode x, RBNode y, RBNode z);
    abstract RBNode zigZagType1(RBNode x, RBNode y, RBNode z);
    abstract RBNode zigZigType1(RBNode x, RBNode y, RBNode z);
    abstract RBNode zigZagType2(RBNode x, RBNode y, RBNode z);
    abstract RBNode zigZigType2(RBNode x, RBNode y, RBNode z);
    abstract void preOrder(RBNode root);
    abstract void inOrder(RBNode root);    
}
