package Algorithms;
import java.util.Arrays;
/*
 * This program is finding two elements in the array whose sum is equal to x
 * Complexity is O(nlogn)
 * */

public class FindSumOfNumber {
    public static void main(String[] args){
	int x = 15;
	int[] a = {1,2,3,4,5,6,7,1,4,10,5};
	Arrays.sort(a);
	findSumOfNumber(a,x);
    }
    public static void findSumOfNumber(int[] a,int x){
	
	for(int i=0;i<a.length;i++){
	    //1 3 3 4 6 7 12
	    int aj = Arrays.binarySearch(a, x-a[i]);
	    if (aj > 0 && aj != i){System.out.println("Numbers are ai="+a[i]+" and aj="+a[aj]);break;}
	}
    }
}
