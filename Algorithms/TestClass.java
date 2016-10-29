package Algorithms;

/* IMPORTANT: Multiple classes and nested static classes are supported */

 
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;


class TestClass {
   public static void main(String args[] ) throws Exception {
       
       Scanner s = new Scanner(System.in);
       int t = 1;
       while(t-- > 0){
       	int p = 12;
       	int n = 3;
       	int k = 3;
       	int[][] arr = {{2,3},{4,2},{8,1}};
       	
       	int last_ind = arr.length - 1;
       	int capacity=k;
       	int time = 0, ind = 0, all_visited=0;
       	boolean flag = true;
       	if((p - arr[last_ind][0]) > arr[0][0]){
       	    for(int i=0;i < n;i++){
       		if(capacity >= arr[i][1]) {
       		    if(flag){
       			time += arr[i][0]; flag = false;
       		    }
       		    else
       			time += arr[i][0]-arr[i-1][0];
       		    
       		    
       		    //System.out.println("i="+i);
       		    capacity -= arr[i][1];
       		    //System.out.println("New Time "+time+" cap"+capacity);
       		    if(capacity == 0 && i+1 < n){
           		    time += arr[i][0];
           		    capacity = k;
           		    flag=true;
           		    //System.out.println("Changed"+time);
   		    } 
       		    if(i+1 == n){
       			time = time + (p - arr[n-1][0]);
       		    }
       		}
       	    
       	    }
       	}
       	else{
       		
       	}
       	System.out.println(time);
       }
      
   }
}

