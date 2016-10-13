package Algorithms;
/*
 * These problems are of Aj-Ai with below variations
 * */
public class MaxProblems {
    public static void main(String[] args){
	//int[] a = {10,2,3,4,5,5,6,1,7,-8};
	int[] a = {1,3,2,12,10};
	//maxAbsAiAj(a); // absolute diff b/w ai-aj
	//maxAjAi(a); // max diff b/w aj and ai where i < j
	//maxAjAiInl(a,2); 
	/* if you have bought i share then you should sell it after at least l days
			  * or when you want to sell share j then you should have shares bought on at least before l days
    			 */
	maxAjAiIn_at_least_l(a,2);

    }
    public static void maxAjAiIn_at_least_l(int[] a,int l){
	int i=0,max=0;
	for(int j=1;j<a.length;j++){
	    if(j>l+i) i++;
	    System.out.println("max="+max+" j="+j+" and i="+i);
	    System.out.println(a[j]-a[i]);
	    if(a[j]-a[i] > max) max = a[j]-a[i];
	    if(a[i] > a[j]) i = j;  // j-l th elem is new elem when l slides
	    //System.out.println("max="+max+" i="+i);
	}
	System.out.println(max);
    }
    public static void maxAjAiInl(int[] a,int l){
	int i=0,max=a[a.length-1]-a[0];
	for(int j=l+1;j<a.length;j++){
	    System.out.println("max="+max+" j="+j+" and i="+i);
	    if(a[j]-a[i] > max) max = a[j]-a[i];
	    if(a[i] > a[j-l]) i = j-l;  // j-l th elem is new elem when l slides
	    //System.out.println("max="+max+" i="+i);
	}
	System.out.println(max);
    }
    public static void maxAjAi(int[] a){
	int i=0,max=0;
	for(int j=1;j<a.length;j++){
	    if(a[j]-a[i] > max) max = a[j]-a[i];
	    if(a[j] < a[i]) i = j;
	}
	System.out.println(max);
    }
    public static void maxAbsAiAj(int[] a){
	int min=a[0],max=a[0];
	for(int i=1;i<a.length;i++){
	    if(min > a[i]) min = a[i];
	    if(max < a[i]) max = a[i];
	}
	System.out.println("min = "+min+" max = "+max+" abs(aj-ai)="+Math.abs(max-min));
    }
}
