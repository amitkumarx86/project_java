package Algorithms;

import java.util.Arrays;

/*
 * Find two such elements such as ai+aj=ak
 * Complexity is O(n^2logn)
 * This is open problem as there is no proof that we can do best than it or worst than it
 * */
public class FindNumberswhoseSumIsAlsoInArray {
    public static void main(String[] args){
	int[] a={1,3,12,7,3,8,4,6,6};
	Arrays.sort(a);
	
	for(int i=0;i<a.length;i++){
	    //System.out.println("x="+a[i]);
	    FindSumOfNumber.findSumOfNumber(a,a[i]);
	}
    }
}
