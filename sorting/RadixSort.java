package sorting;

public class RadixSort {
    public static void main(String[] args) {
	int[] ar= {124,560,670,789,528,154,111};
	radixSort(ar);
    }
    public static void radixSort(int[] ar){
	int k=3,l=0;
	while(k-- > 0)
	{
	    int output[] = new int[ar.length];
	    int count[]  = new int[10];
	    for(int i=0;i<ar.length;i++){
		count[(ar[i]/((int)Math.pow(10,l)))%10]=count[(ar[i]/((int)Math.pow(10,l)))%10]+1;
	    }
	    for(int i=0;i<count.length;i++){
		System.out.print(count[i]+" ");
	    }
	    for(int i=1;i<count.length;i++)count[i] += count[i-1];
	    for(int i=ar.length-1;i>=0;i--) {
		output[count[(ar[i]/((int)Math.pow(10,l)))%10]-1]=ar[i];
		count[(ar[i]/((int)Math.pow(10,l)))%10]--;
	    }
	    System.out.println();
	    for(int i=0;i<count.length;i++){
		System.out.print(count[i]+" ");
	    }
	    for(int i=0;i<ar.length;i++) ar[i]=output[i];
	l++;
	}
	System.out.println();
	for(int a:ar) System.out.println(a);
    }
}
    

