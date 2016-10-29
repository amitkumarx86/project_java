package dataStructures;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueues {
    public static void main(String[] args) {
	PriorityQueues q = new PriorityQueues();
	int ar[] = {10,11,2,3,4,5,6,8};
	q.minHeap(ar);
	q.maxHeap(ar);
    }
    
    private void minHeap(int[] ar) {
	PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	int i=0;
	while(i < ar.length) queue.add(ar[i++]);
	
	System.out.println(queue.peek());
	 
    }
    private void maxHeap(int[] ar) {
	PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
	int i=0;
	while(i < ar.length) queue.add(ar[i++]);
	
	System.out.println(queue.peek());
    }
}
