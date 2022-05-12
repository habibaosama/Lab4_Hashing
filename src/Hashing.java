import java.util.Random;

public class Hashing {
    static final int U = 32;
     int n;
     int[] S;
     int noCollision;
     Hashing(int[] s){
         this.n=s.length;
         this.S=s;
         this.noCollision=-1;
     }
     //generate random H
    public int[][] randomH(int [][]H) {
        //int[][] H = new int[b][U];
        Random random = new Random();
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < U; j++) {
                H[i][j] = random.nextInt(2);
            }
        }
        return H;
    }
    public int convertToDecimal(int[] bin){
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
        return bin;
    }




}
