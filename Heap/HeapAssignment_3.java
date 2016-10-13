package Heap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HeapAssignment_3 {
    static void createNumber() throws IOException{
   	DataOutputStream writer = new DataOutputStream(new FileOutputStream("/home/amitkumarx86/Desktop/headNumber.txt"));
   	for(int i=0;i<1000;i++){
   	    int t = (int)(Math.random()*100);
   	    writer.writeInt(t);
   	}
   	writer.close();
      }

    static void printMin(int k) throws IOException {
	// TODO Auto-generated method stub
	int[] ar = new int[2*k];
	int n = ar.length;
	DataInputStream reader = new DataInputStream(new FileInputStream("/home/amitkumarx86/Desktop/headNumber.txt"));
	int i=0;
	while(i < 2*k) ar[i++]=reader.readInt();
	
	int index = FindRankRandom(ar,0,n-1,n/2);
	int j=index+1;
	while(reader.available() > 0){
	    j=index+1;
	    while(reader.available() > 0 && j < 2*k){ar[j++] = reader.readInt();}
	    index = FindRankRandom(ar,0,n-1,(j-k));
	}
	printArray(ar,k);
	reader.close();
    }
    static void printArray(int[] ar,int k){
	for(int i=0;i<k;i++) System.out.print(ar[i]+" ");
	System.out.println();
    }
    public static int FindRankRandom(int[] a,int i, int j, int rank){
	 int k,p;
	 p=Pivot(i,j);
	 k=partition(a,i,j,p);
	 if((j-k)==rank)
		return k;
	 else if(rank > (j-k))
	 {
		k=FindRankRandom(a,i,k-1,rank-j + k-1);
	 }
	 else	
	     return k;
	 
	 return k;
    }
    
    private  static int Pivot(int minimum,int maximum){
	return (minimum + (int)(Math.random()*(maximum - minimum))); 
    }
    
    private  static int partition(int[] a, int l, int r,int p)
    {
	a[r] = a[p] + a[r] - (a[p]=a[r]);
        int x = a[r];
        int i = l-1;
        for(int j=l; j<=r-1; j++)
        {
            if(a[j]<=x) // use if(a[j] >=x) for non-increasing order 
            {
                i++;a[j]=a[i]+a[j]-(a[i]=a[j]);
            }	
        }
        a[i+1] = a[r] + a[i+1] - (a[r] = a[i+1]);
        return (++i);
    }
    public static void main(String[] args) throws IOException {
	createNumber();
	printMin(100);
    }
    
}

