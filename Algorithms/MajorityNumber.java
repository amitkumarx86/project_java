package Algorithms;

import java.util.Arrays;

public class MajorityNumber {
    public static void main(String[] args) {
	int[] ar= {7,2,3,4,5,7,7,9,9,6,9,9,9,9};
	countMajorityNumber(ar);
    }
    public static void countMajorityNumber(int[] ar){
	int count=0;
	int[] A=new int[ar.length];
	int[] B=new int[ar.length];
	Arrays.fill(A, -123);
	Arrays.fill(B, -123);
	int top1=-1,top2=-1;
	for(int i=0;i<ar.length;i++){
	    if(top1 == -1) {
		A[++top1] = ar[i];
	    	continue;}
	    if(top2 == -1){ 
		B[++top2] = ar[i];
	    	continue;
	    	}
	    boolean flag=false;
	    for(int k=0;k<A.length;k++){
		if(A[k] == ar[i]){
		    flag=true;break;
		}
		if(A[k] == -123) break;
	    }
	    if(flag) A[++top1] = ar[i];
	    boolean flag2=false;
	    for(int k=0;k<B.length;k++){
		if(B[k] == ar[i]){
		    flag2=true;break;
		}
		if(A[k] == -123) break;
	    }
	    if(flag2) B[++top2] = ar[i];
	    if(!flag && !flag2){
		--top1;--top2;
	    }
	     
	    
	}
	
	int number=-123;
	for(int i=0;i<A.length;i++) {if(A[i] == -123) break; 
        	boolean flag=false;
        	int count1=0;
        	number=A[i];
        	for(int j=0;j<ar.length;j++){
        	    if(number==ar[j]) count1++;
        	    if(count1 >= (ar.length/3 + 1)) {flag=true;break;}
        	}
        	if(flag) {break;}
        	
	}
	int number1=-123;
	for(int i=0;i<B.length;i++) {if(B[i] == -123) break; 
         	boolean flag=false;
        	int count1=0;
        	number1=B[i];
        	for(int j=0;j<ar.length;j++){
        	    if(number1==ar[j]) count1++;
        	    if(count1 >= (ar.length/3 + 1)) {flag=true;break;}
        	}
        	if(flag) {break;}

	}
	if(number == number1)System.out.println(number); 
	else if(number != -123 || number1 != -123){
	    System.out.println(number); 
	    System.out.println(number1); 
	}
	
    }
    private static void printArray(int[] a) {
	// TODO Auto-generated method stub
	for(int i : a) System.out.print(i+" ");
        System.out.println();
    }
}
