package sorting;



public class RandMizedQuickSortVersion1 {
    
    
    public void qSort(int[] a, int l, int r)
    {
         if(l<r){
            int k = FindRankRandom(a,l,r,(r-l+1)/2);
            qSort(a, l, k-1);
            qSort(a, k+1, r);
         }
         else 
		return;
     }
     public int FindRankRandom(int[] a,int i, int j, int rank){
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
	     k=FindRankRandom(a,k+1,j,rank);
	 return k;
     }
     
     private  int Pivot(int minimum,int maximum){
	return (minimum + (int)(Math.random()*(maximum - minimum))); 
     }
     
     private  int partition(int[] a, int l, int r,int p)
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
         qsort.qSort(array, 0, length-1);
         System.out.print("Sorted  Array   : ");
         for(int i=0; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
     }
}
