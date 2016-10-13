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
	
	//selectionSort(a,1);
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
	    for(int j=1;j<a.length;j++){
		if(order == 1 ? a[j-1] > a[j] : a[j-1] < a[j]) a[j-1]=a[j]+a[j-1]-(a[j]=a[j-1]);
	    }
	}
	printArray(a);
    }
    public static void selectionSort(int[] a, int order){
	for(int i=0;i<a.length;i++){
	    int max=a[0],loc=0;
	    for(int j=0;j<a.length-i;j++){
		// below logic changes the order of sort
		if(order == 1 ? a[j] > max : a[j] < max) {max = a[j];loc=j;}
	    }
	    a[a.length-i-1]=a[loc]+a[a.length-i-1]-(a[loc]=a[a.length-i-1]);
	}
	printArray(a);
    }
    public static void printArray(int[] a){
	for(int i=0;i<a.length;i++)
	    System.out.print(a[i]+" ");
	System.out.println();
    }
}
