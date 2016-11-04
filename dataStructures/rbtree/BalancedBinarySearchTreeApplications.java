package dataStructures.rbtree;

interface BalancedBinarySearchTreeApplications {
    abstract int rankOfNode(int key, RBNode root);
    abstract RBNode findNodeHavingRank(int rank, RBNode root);
    abstract int preFixSum(RBNode searchNode, RBNode root);
    abstract int sum(int a, int b);
    abstract int numberOfElementsBetween(int a, int b);
    abstract int average(int a, int b);
    abstract int minInRange(int a, int b);
    abstract int maxInRange(int a, int b);
    
}
