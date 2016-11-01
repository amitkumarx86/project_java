package dataStructures.avlTree;

public interface AVLFunctions {
    abstract AVLNode insertAVL(int x, AVLNode root);
    abstract AVLNode deleteAVL(AVLNode node_to_be_deleted,AVLNode root);
    abstract AVLNode searchAVL(int data,AVLNode root);
    abstract int sizeAVL(AVLNode root);
    abstract boolean isEmpty(AVLNode root);
    abstract void makeEmpty(AVLNode root);
    abstract int AVLHeight(AVLNode node);
    abstract int whichRotation(AVLNode x, AVLNode y, AVLNode z);
    abstract AVLNode zigZagType1(AVLNode x, AVLNode y, AVLNode z);
    abstract AVLNode zigZigType1(AVLNode x, AVLNode y, AVLNode z);
    abstract AVLNode zigZagType2(AVLNode x, AVLNode y, AVLNode z);
    abstract AVLNode zigZigType2(AVLNode x, AVLNode y, AVLNode z);
    abstract void preOrder(AVLNode root);
    abstract void inOrder(AVLNode root);    
}
