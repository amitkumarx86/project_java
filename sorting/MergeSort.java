package sorting;

import java.util.Scanner;

public class MergeSort {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 5;//in.nextInt();
        int[] ar = {5,4,3,2,1};//new int[n];
        //for(int i=0 ;i<n;i++) ar[i] = in.nextInt();
        MergeSort(ar,0,ar.length-1);
        for(int a : ar)
            System.out.println(a+" ");
        in.close();
}
    public static void MergeSort(int[] ar, int p, int r){
	if(p < r){
	    int q = (p+r)/2;
	    MergeSort(ar,p,q);
	    MergeSort(ar,q+1,r);
	    //MergeCoreman(ar,p,q,r);
	    mergeMurli(ar,p,q,r);
	}
    }
    public static void mergeMurli(int[] ar,int p,int q, int r){
	
	int i = p,j = q+1,a=0;
	//System.out.println("j ="+j + " r="+r);
	int[] B = new int[r+1];
	while(i <= q && j <= r){
	    if(ar[i] <= ar[j]) B[a++] = ar[i++]; // use if(ar[i] >= ar[j]) for non-increasing order
	    else B[a++] = ar[j++];
	}
	while(i <= q){ B[a++] = ar[i++];}
	while(j <= r){ B[a++] = ar[j++];}
	a=0;i=p;
	while(i<=r) ar[i++] = B[a++];
	
    }
    public static void MergeCoreman(int[] ar,int p,int q, int r){
	//System.out.println("p = "+p+" q="+q+" r="+r);
	int n1 = q - p + 1;
	int n2 = r - q ;
	//System.out.println("n1 = "+n1+" n2="+n2);
	
	int[] L = new int[n1+1];
	int[] R = new int[n2+1];
	for(int i=0;i<n1;i++) L[i] = ar[p+i];
	for(int i=0;i<n2;i++) R[i] = ar[q+i+1];
	L[n1] = Integer.MAX_VALUE;
	R[n2] = Integer.MAX_VALUE;
	/*System.out.println("---------------");
	for(int a:L) System.out.println(a);
	System.out.println("---------------");
	for(int a:R) System.out.println(a);
	System.out.println("---------------");
	*/
	int i = 0 , j = 0;
	for(int k = p ; k <= r ; k++){
	    if(L[i] <= R[j]){ar[k] = L[i];i++;}
	    else{ ar[k] = R[j]; j++;}
	}
	//for(int i1=0;i1<=r;i1++)System.out.println(ar[i1]);
	
    }
}
