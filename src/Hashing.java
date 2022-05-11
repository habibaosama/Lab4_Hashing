import java.util.LinkedList;
import java.util.Random;

public class Hashing {
    private int U = 32;
    private int b;
    private int n;
    private int[] S;
    private boolean[] exist;
    private int[]result;
    //for saving the random H
    public LinkedList<int[][]> hashRandomized;

   public  Hashing(int[] a) {
        n = a.length;
        b = (int) Math.ceil(Math.log(n) / Math.log(2));
        S = a;
        hashRandomized = new LinkedList<>();
        //since it is n^2
        exist=new boolean[n*n];
        result=new int [n*n];
        hashFunction();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void hashFunction() {
        int[][] H = randomH();
        hashRandomized.add(H);
        for (int i = 0; i < n; i++) {
            int[] x = convertToBinary(S[i]);
            int[] indexBinary = multiply(H, x);
            int index=convertToDecimal(indexBinary);
            if(exist[index]){
                //the same index exist
                //collision ->so call again random the H and get another index
                H = randomH();
                hashRandomized.add(H);
            }else{
                result[index]=S[i];
                exist[index]=true;
            }
        }
    }

    private int[][] randomH() {
        int[][] H = new int[b][U];
        Random random = new Random();
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < U; j++) {
                H[i][j] = random.nextInt(2);
            }
        }
       /* System.out.println("H");
        for(int i=0;i<b;i++){
            for(int j=0;j<U;j++){
                System.out.print(H[i][j]+" ");
            }
            System.out.println();
        }*/
        return H;
    }
    private int convertToDecimal(int[] bin){
        int val=0;
        for(int i=0;i < bin.length;i++)
            val+=bin[i]*Math.pow(2,i);
        return val;
    }
    public int[] multiply(int[][] H, int[] x) {
        int[] multiply = new int[H.length];
        int sum = 0;
        for (int i = 0; i < H.length; i++) {
            sum = 0;
            for (int j = 0; j < H[0].length; j++) {
                sum += H[i][j] * x[j];
            }
            if (sum % 2 == 0)
                multiply[i] = 0;
            else
                multiply[i] = 1;

        }
      /* System.out.println("multiply");
        for(int i=0;i< H.length;i++)
            System.out.print(multiply[i] +" ");*/

        return multiply;
    }
    public int[] convertToBinary(int num) {
        //since keys will all be 32-bit integers
        System.out.println("binary " + num);
        int[] bin = new int[32];
        int i = 0;
        while (num != 0) {
            bin[i++] = num % 2;
            num /= 2;
        }
       /* for(int j=0;j<i;j++ )
              System.out.print(bin[j]+ " ");
        System.out.println();*/
        return bin;
    }
    public void print(){
       for(int i=0;i<n*n;i++)
           System.out.println(result[i]);
    }
}
//expected number of collision =n/m
//e u-bits long. Say the table size M is power of 2, so an index is b-bits long with
//M = 2^b.
//therefore b=ceil((log n)/log2)