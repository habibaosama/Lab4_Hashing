import java.util.LinkedList;
import java.util.Random;

public class Hashing {
    public int U = 32;
    public int b;
    public int n;
    public int[] S;
    public LinkedList<int[][]> hash;

    Hashing(int[] a) {
        n = a.length;
        b = (int) Math.ceil(Math.log(n) / Math.log(2));
        S = a;
        hash = new LinkedList<>();
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
       
    }


}
//expected number of collision =n/m
//e u-bits long. Say the table size M is power of 2, so an index is b-bits long with
//M = 2^b.
//therefore b=ceil((log n)/log2)