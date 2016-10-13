package sorting;

 
public class RandomizedQuickSort {
    
     
    public void qSort(int[] a, int l, int r)
    {
         if(l<r){
            //int p = l;
            //a[r] = a[p] + a[r] - (a[p]=a[r]);
            int q = Partition(a,l,r);
            printArray(a, 0, a.length-1);
            qSort(a, l, q-1);
            qSort(a, q+1, r);
            
         }
     }
    public void printArray(int[] a,int l,int r){
	 for(int i=l;i<=r;i++) System.out.print(a[i]+" ");
	 System.out.println();
    }
     private int Pivot(int minimum,int maximum){
	return (minimum + (int)(Math.random()*(maximum - minimum))); 
     }
 
     private int Partition(int[] a, int p, int r)
     {
         int x = a[r];
         int i = p-1;
         for(int j=p; j<=r-1; j++)
         {
             if(a[j]<=x) // use if(a[j] >=x) for non-increasing order 
             {
                 i++;a[j]=a[i]+a[j]-(a[i]=a[j]);
             }	
         }
         a[i+1] = a[r] + a[i+1] - (a[r] = a[i+1]);
         return (++i);
     }
     public  int PartitionForPivotAtIndexZero(int[] a, int p, int r)
     {
	 
	 int x = a[p];
         int i = p-1;
         for(int j=p+1; j<=r; j++)
         {
             if(a[j]<=x) // use if(a[j] >=x) for non-increasing order 
             {
                 i++;a[j]=a[i]+a[j]-(a[i]=a[j]);
             }	
         }
         a[i+1] = a[r] + a[i+1] - (a[r] = a[i+1]);
         return (++i);
     }
     
     public static void main(String[] args)
     {
         RandomizedQuickSort qsort = new RandomizedQuickSort();
         int[] array = {1, 3, 9, 8, 2, 7, 5};

         /*
         System.out.print("Original  Array : ");
         for(int i=0; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
         System.out.println();
         */
         int length = array.length;
         
         qsort.qSort(array, 0, length-1);
         /*System.out.print("Sorted  Array : ");
         for(int i=0; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
         */
         /*
         int i=0;
         for(i=0; i<array.length;i++)
         {
             if(array[i] == array[finalPivot])
        	 break;
             System.out.print(array[i] + " ");
         }
         System.out.println();
         i++;
         for(; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
         System.out.println();*/
         for(int i=0; i<array.length;i++)
         {
             System.out.print(array[i] + " ");
         }
        
         //System.out.println("finalP = "+finalPivot);
     }
}
