package sorting;

import java.util.*;

public class countingSort {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++) ar[i]=in.nextInt();
        countNumbers(ar);
        in.close();
    }
    public static void printArr(int[] ar){for(int l : ar) System.out.print(l+" "); System.out.println();}
    public static void countNumbers(int[] ar){
        int max=ar[0];
        for(int i=1;i<ar.length;i++){if(max<ar[i]) max=ar[i];}
        int[] temp = new int[max+1];
        for(int i=0;i<ar.length;i++)
            ++temp[ar[i]]; 
        int[] out = new int[ar.length];
        //printArr(temp);
        for(int i=1;i<temp.length;i++) temp[i] = temp[i] + temp[i-1];
        //printArr(temp);
        for(int i=0;i<ar.length;i++){
            out[temp[ar[i]]-1] = ar[i];
            --temp[ar[i]];
        }
        for(int i=0;i<ar.length;i++){
            System.out.print(out[i]+" ");
        }
    }
}
