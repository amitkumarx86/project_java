package sorting;

import java.util.Arrays;

public class QuickSortWithDeterministicPivot {
    
    
    public void qSort(int[] a, int l, int r)
    {
         if(l<r){
            int k = FindRankDeterministic(a,l,r);
            qSort(a, l, k-1);
            qSort(a, k+1, r);
         }
     }
     public int FindRankDeterministic(int[] a,int i, int j){
	 int p = goodPivot(a,i,j);
	 int k = Partition(a, i, j, p);
	 return k;
	}
     
     private  int Partition(int[] a, int l, int r,int p)
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
     public int goodPivot(int[] a,int i, int j){
	 int noBlocks=(j-i+1)/5;
	 int remain = (j-i+1)%5;
	 for(int k=0;k<noBlocks;k++){
	     int[] temp = new int[5];
	     for(int l=i+k*5,ind=0;l<(i+k*5+5);l++,ind++) temp[ind]=a[l];
	     Arrays.sort(temp);
	     for(int l=i+k*5,ind=0;l<(i+k*5+5);l++,ind++) a[l]=temp[ind];
	 }
	 if(remain > 0){
	     int[] temp = new int[remain];
	     for(int l=i+5*noBlocks,t=0;l<=j;l++,t++) temp[t]=a[l];
	     Arrays.sort(temp);
	     for(int l=i+5*noBlocks,t=0;l<=j;l++,t++) a[l]=temp[t];	
	 }
	 int loc=i;
	 for(int k=0,n=0;n<noBlocks;n++,k=n*5,loc++){
	     a[loc] = a[(2*i+2*k+5)/2] + a[loc]- (a[(2*i+2*k+5)/2] = a[loc]);
	 }
	 
	 if(remain > 0)a[loc] = a[(i+5*noBlocks+j)/2] + a[loc]- (a[(i+5*noBlocks+j)/2] = a[loc]);	
	 
	 
	 int[] temp = new int[loc+1];
	 
	 for(int k=i;k<loc+1;k++) temp[k]=a[k];
	 Arrays.sort(temp);
	 for(int k=i;k<loc+1;k++) a[k]=temp[k];    
	 return (i+loc)/2;
     }
     public void printArray(int[] a){
	 for(int i:a) System.out.print(i+" ");
	 System.out.println();
     }
     
     public static void main(String[] args)
     {
         RandMizedQuickSortVersion1 qsort = new RandMizedQuickSortVersion1();
         int[] array = {-1,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,-1,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,2,3,4,5,6,7,8,9,2,3,4,5,6,6,7,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,3,4,5,6,2,3,4,5,6,8,9,2,-12313,3,32132132,4,5,6,2,3,4,5,6};
         
         System.out.print("Original  Array : ");
         for(int i=0; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
         System.out.println();
         int length = array.length;
         //qsort.qSort(array, 0, length-1);
         QuickSortWithDeterministicPivot q = new QuickSortWithDeterministicPivot();
         q.qSort(array, 0, array.length-1);
         System.out.print("Sorted  Array   : ");
         for(int i=0; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
     }
}
