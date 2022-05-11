import java.util.LinkedList;
import java.util.Random;

public class Hashing {
    public int U = 32;
    public int b;
    public int n;
    public int[] S;
    //for saving the random H
    public LinkedList<int[][]> hashRandomized;

    Hashing(int[] a) {
        n = a.length;
        b = (int) Math.ceil(Math.log(n) / Math.log(2));
        S = a;
        hashRandomized = new LinkedList<>();
        hashFunction();
    }

    public int[][] randomH() {
        int[][] H = new int[b][U];
        Random random=new Random();
        for(int i=0;i<b;i++){
            for(int j=0;j<U;j++){
                H[i][j]=random.nextInt(2);
            }
        }
      return H;
    }

    // LinkedList<Integer> [] hashTable=new LinkedList[100]  ;

    //will return the index
    public void hashFunction() {
       int [][]H=randomH();
        hashRandomized.add(H);
       for (int i=0;i<n;i++){
           int []x=convertToBinary(S[i]);
           int [] index=multiply(H,x);
       }
    }
    public int[] multiply(int[][] H,int []x){
        int []multiply=new int[H.length];
         int sum=0;
        for(int i=0;i<H.length;i++){
           sum=0;
            for(int j=0;j<H[0].length;j++){
               sum+=H[i][j]*x[j];
            }
            if(sum%2==0)
                multiply[i]=0;
            else
                multiply[i]=1;

        }
       return multiply;
    }
   public int[] convertToBinary(int num){
          //since keys will all be 32-bit integers
       int []bin=new int[32];
       int i=0;
       while(num!=0){
           bin[i++]=num%2;
           num/=2;
       }
       return bin;

   }

}
//expected number of collision =n/m
//e u-bits long. Say the table size M is power of 2, so an index is b-bits long with
//M = 2^b.
//therefore b=ceil((log n)/log2)