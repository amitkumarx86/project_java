package Heap;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

 

public class HeapAssignment_1 {
    public static void main(String[] args) throws IOException {
	createNumber();
	printKMin(100);
    }
    static void printKMin(int k) throws IOException{
	HeapMaxMethods h = new HeapMaxMethods();
	DataInputStream reader = new DataInputStream(new FileInputStream("/home/amitkumarx86/Desktop/headNumber.txt"));
	int[] ar = new int[k];
	int i=0;
	while(i < k) ar[i++]=reader.readInt();
	h.buildHeap(ar, ar.length);
	while(reader.available()>0){
	    int number=reader.readInt();
	    if(ar[0] > number){
		ar[0] = number;
		h.top_to_bottom(ar , 0,ar.length);
	    }
	}
	for(int a:ar) System.out.print(a+" ");
	System.out.println();
	reader.close();
    }
    static void createNumber() throws IOException{
	DataOutputStream writer = new DataOutputStream(new FileOutputStream("/home/amitkumarx86/Desktop/headNumber.txt"));
	for(int i=0;i<1000;i++){
	    int t = (int)(Math.random()*50);
	    writer.writeInt(t);
	}
	writer.close();
   }
}
