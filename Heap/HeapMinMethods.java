package Heap;

public class HeapMinMethods {
    // works in O(n) time
    void buildHeap(int[] ar,int size){
	for(int i=(ar.length-1)/2 ; i>=0;i--)
	    top_to_bottom(ar,i,size);
    }
    void insert(int ar[],int item){
	
    }
    void increaseKey(int ar[] ,int key, int pos,int size){
	System.out.println("Increase : ar["+key+"]="+ar[pos]);
	if(pos > ar.length)
	    return;
	ar[pos]=key;
	top_to_bottom(ar, pos,size);
    }
    void decreaseKey(int ar[] ,int key, int pos,int size){
	System.out.println("Decrease : ar["+key+"]="+ar[pos]);
	if(pos > size)
	    return;
	ar[pos]=key;
	bottom_to_Top(ar, pos);
    }
    void bottom_to_Top(int[] ar,int i){
	while(i >=0 && ar[(i-1)/2] > ar[i]){
	    ar[(i-1)/2] = ar[i]+ar[(i-1)/2] - (ar[i]=ar[(i-1)/2]); // swapping
	    i=(i-1)/2;
	}
    }
    void top_to_bottom(int ar[] , int i,int n){
	while((2*i+1) < n){
	    int l = 2*i+1, r = 2*i+2,m=0;
	    if(r<n && ar[r] < ar[l])
		m=r;
	    else
		m=l;
	    
	    if(ar[i] > ar[m])
		ar[i]=ar[m]+ar[i]-(ar[m]=ar[i]);
	    else
		break;
	    i=m;
	}
    }
    int extractMin(int ar[],int size){
	ar[0]=ar[size-1]+ar[0]-(ar[size-1]=ar[0]);
	//System.out.print(ar[size]+" ");
	System.out.print(ar[size-1]+" " );
	top_to_bottom(ar, 0,size-1);
	//System.out.println("After");
	return size-1;
    }
    void sort(int ar[], int size){
	int i=size;
	while(i > 0){
	    extractMin(ar,i);
	    i--;
	}
    }
}

