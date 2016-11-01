package sorting;

public class NSquareSorting {
    public static void main(String[] args) {
	int[] a = {5,4,3,2,1,10};
	// 0 means desc and 1 means asc
	//insertionSort(a,1); 
	/*
	 * Number of comparisons in insertion sort is n*(n-1)/2
	 * Shifts = n*(n-1)/2
	 * */
	
	//bubbleSort(a,1); 
	/*
	 * Number of comparisons in bubble sort is n*(n-1)
	 * Swaps = n-1
	 * */
	
	selectionSort(a,1);
	/*
	 * Number of comparisons in selection sort is n*(n-1)/2
	 * Swaps = n-1
	 * */
	
    }
    public static void insertionSort(int[] a,int order){
	for(int i=1;i<a.length;i++){
	    int j=i-1;
	    int key = a[i];
	    boolean cond= order == 1 ? a[j] > key : a[j] < key;
	    while(j>=0 && cond){
		a[j+1] = a[j];
		cond= order == 1 ? a[j] > key : a[j] < key;
		j--;
	    }
	    a[j+1] = key;
	}
	
    }
    public static void bubbleSort(int[] a, int order){
	for(int i=0;i<a.length;i++){
	    for(int j=0;j<a.length;j++){
		/* compare the adjacent elements */ 
		if(order == 1 ? a[j-1] > a[j] : a[j-1] < a[j]) 
		    /* swap them */
		    a[j-1]=a[j]+a[j-1]-(a[j]=a[j-1]);
	    }
	}	
	printArray(a);
    }
    public static void selectionSort(int[] a, int order){
	for(int i=0;i<a.length;i++){
	    /* set current element as minimum*/
	    int loc = i;
	    /* check the element to be minimum */
	    for(int j = i+1 ; j<a.length;j++){
		/*Order 1: increasing order
		 * Order 0: decreasing order
		 * */
		if(order == 1 ? a[i] > a[j] : a[i] < a[j]) {loc = j;}
	    }
	    /* swap the minimum element with the current element*/
	    if(loc != i) a[i]=a[loc]+a[i]-(a[loc]=a[i]);
	}
	printArray(a);
    }
    public static void printArray(int[] a){
	for(int i=0;i<a.length;i++)
	    System.out.print(a[i]+" ");
	System.out.println();
    }
}
