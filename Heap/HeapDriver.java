package Heap;

public class HeapDriver {
    public static void main(String[] args) {
	HeapMaxMethods heap = new HeapMaxMethods();
	int ar[] = {34,19,53,64,54,73,35,7,17,20,};
	int size=ar.length;
	heap.buildHeap(ar,size);
	printHeap(ar);
	heap.sort(ar,size);
    }
    static void printHeap(int[] ar){
	for(int i:ar)
	    System.out.print(i + " " );
	System.out.println();
    }
}
