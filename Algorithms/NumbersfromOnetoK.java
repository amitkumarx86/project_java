package Algorithms;

public class NumbersfromOnetoK {
    public static void main(String[] args) {
	NumbersfromOnetoK m = new NumbersfromOnetoK();
	m.findSubArrayWith_1_to_K_Numbers();
    }

    private void findSubArrayWith_1_to_K_Numbers(){
	//int[] arr={10,6,3,7,6,4,2,8,6,1,2,7,7,1,2,3,4,7,3,4,7,7,7,1,2,9,9,9,9,9,9};
	int arr[] = {1, 2, 3, 4,4, 5, 6, 1, 7, 1, 2, 4, 5, 3, 6, 7, 10};
	int k=4,strtInd=0,endInd=0,digCount=0;
	int[] kArr=new int[k+1];
        /*
	 * First find the sub array in which 1 to k number exist, it may 
	 * not be the minimal
	 * */
	for(int i=0;i<arr.length;i++){  
	    if(arr[i] <=k && arr[i] >=1){
		if(kArr[arr[i]]==0) digCount++;    // count of digits from 1...k
		kArr[arr[i]]++;
		if(digCount == k) {endInd=i;break;}
	    }
	}
	
	int min_len=endInd-strtInd+1; // initially we let this is min 
        int tempInd=endInd;
	int i=strtInd;
	
        while(tempInd<arr.length){
	    if(arr[i]<=k){
		kArr[arr[i]]--;
		if(kArr[arr[i]] == 0){
		    kArr[arr[i]]++;
                    // calculating new minimum
		    if(tempInd-i+1 < min_len){
			min_len=tempInd-i+1;
			strtInd=i;endInd=tempInd;
		    }
		    if(tempInd < arr.length)
			tempInd++;
                    // skipping nos which are not in range 1..k
		    while(tempInd < arr.length && arr[tempInd] > k)tempInd++;
		    // if no is in 1..k then increment its count in kArray
                    if(tempInd < arr.length)
			kArr[arr[tempInd]]++;
		}
		else
		    	i++; // increase i when the dig count in not reduced
	    }
	    else
		i++; // increase i if no is out of range 1..k
	    
	}
        //print start and end index of sub array
	System.out.println(strtInd+" "+(endInd)); // final answer
    }
    private void printArray(int[] arr){
	for(int a : arr) System.out.print(a+" ");
	System.out.println();
    }
}
