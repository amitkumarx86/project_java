package Algorithms;

import java.util.ArrayList;

public class MaxSubArray{
    public static void main(String args[]) {
	//int[] arr = {5,6,2,20,-14,-16,1,-53,-22,10};
	int[] arr={1,2,10,6,3,1,2,7,6,4,2,8,6,1,7,3,1,2,3,4};
	//maxSubArray(arr);
	//maxSubArrayofLengthL(arr,3);
	//minSubArraySumAtLeastS(arr,35);
	
    }
    public static void printArray(int[] ar){ for(int a : ar) System.out.print(a+" "); System.out.println();}
    public static void printArrayIndex(int[] ar,int l,int r){ 
	System.out.print("l="+l+" r="+r+" : ");
	printArray(ar);
	System.out.print("len="+(r-l+1)+" : ");
	for(int i=l;i<=r;i++) System.out.print(ar[i]+" "); System.out.println();}
    
        public static void maxSubArray(int[] arr){
	int max=0,sum=0;
	for(int i=0;i<arr.length;i++){
	    sum +=arr[i];
	    if(sum > max) max = sum;
	    if(sum < 0) sum=0;
	}
	ArrayList<Integer> ar = new ArrayList<Integer>();
	sum=0;
	for(int i=0;i<arr.length;i++){
	    ar.add(arr[i]);
	    sum +=arr[i];
	    if(sum == max) break;
	    if(sum < 0) {
		sum=0;
	    	ar.clear();
	    }
	}
	System.out.println(max);
	System.out.println(ar);
    }
    public static void maxSubArrayofLengthL(int[] ar,int l){
	int sum=0,max=0;
	for(int i=0;i<ar.length;i++){
	    if(i<=l-1) {sum+=ar[i];if(sum > max) max=sum; if(sum<0) sum=0;}
	    else{
		sum = sum-ar[i-l];
		sum = sum+ar[i];
		if(sum > max) max = sum;
		if(sum<0) sum=0;
	    }
	}
	System.out.println(max);
    }
    public static void minSubArraySumAtLeastS(int[] ar,int S){
	int max=0,sum=0;
	for(int i=0;i<ar.length;i++){
	    sum+=ar[i];
	    if(sum > max) max = sum;
	    if(sum<0) sum=0;
	}
	System.out.println(max);
    }
}
