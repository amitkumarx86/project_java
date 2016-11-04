package dataStructures.rbtree;

public class Sample {
    public static void main(String[] args) {
	int i=20,j=23,k=6;
	int count=0;
        for(long i1 = i; i1<= j ; i1++){
            long m = i1,i2=0;
            while(m != 0){
                i2 = i2*10 + m%10;
                m=m/10;
                System.out.println("asdf");
            }
            if((long)Math.abs(i1-i2)%k == 0) count++;
        }
        System.out.println(count);
    }
}
