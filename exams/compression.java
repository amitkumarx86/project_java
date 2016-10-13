package exams;

import java.util.Arrays;

public class compression {
    public static void main(String args[]){
	//System.out.println(compression("z101a12b5c1a1a35c41d1z101"));
	int arr[] = {4,2,7,3};
	SortUsingNumberOf1(arr);
    }
    public static void SortUsingNumberOf1(int arr[]){
	Arrays.sort(arr);
	int a[][]= new int[arr.length][2];
	for(int i=0;i<arr.length;i++){
	    //System.out.println(Integer.bitCount(arr[i]));
	    a[i][0]=arr[i];
	    a[i][1]=Integer.bitCount(arr[i]);
	}
	for(int i=1;i<arr.length;i++){
	    int j=i-1;
	    int key=a[i][1];
	    int value=a[i][0];
	    while(j>=0 && a[j][1] > key){
		a[j+1][1]=a[j][1];
		a[j+1][0]=a[j][0];
		j--;
	    }
	    a[j+1][1]=key;
	    a[j+1][0]=value;
	}
	for(int i=0;i<arr.length;i++) System.out.println(a[i][0]+ " ");
    }
    public static String compression(String s){
	int arr[] = new int[26];
	for(int j=0;j<26;j++)
	    arr[j]=0;
	int i=0;
	while(i<s.length()){
	    //System.out.println(s.charAt(i));
	    int c = (int)(s.charAt(i)-'a');
	    i++;
	    int digit=0;
	    while(i<s.length() && (int)s.charAt(i) < 97){
		digit=digit*10+(s.charAt(i)-'0');
		//System.out.println(" as"+s.charAt(i));
		i++;
	    	
	    }
	    //System.out.println(digit);
	    arr[c]=arr[c]+digit;
	    
	}
	for(int j=0;j<26;j++){
	    if(arr[j]!=0)
		System.out.print((char)(97+j)+""+arr[j]);
	}
	return "";
    }
}
