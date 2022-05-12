import java.util.Arrays;

public class Nsquare {
    int result[];
    int H[][]; // Zeros and ones
    boolean exist[];
    int b;
    int n;
    Hashing hashing;

    public Nsquare(Hashing hashingObj) {
        this.hashing = hashingObj;
        n=hashing.n;
        result = new int[n*n];
        exist = new boolean[n*n];
         b = (int) Math.floor(Math.log(Math.pow(n,2)) / Math.log(2));
        H = new int[b][hashing.U];
    }
    public void hashFunction() {
        int[]S=hashing.S;
         H = hashing.randomH(H);
        boolean hashed;
        do{
            hashed=true;
            for(int i=0;i<n;i++) {
                int[] x = hashing.convertToBinary(S[i]);
                int[] indexBinary =hashing.multiply(H, x);
                int index = hashing.convertToDecimal(indexBinary);
                if (exist[index]) {
                    //the same index exist
                    //collision ->so call again random the H and get another index
                    System.out.println("collision");
                    H =hashing.randomH(H);
                    hashed = false;
                    Arrays.fill(exist, false);
                    break;
                } else {
                    result[index] = S[i];
                    exist[index] = true;
                }
            }
        }while(!hashed);
        print();
    }
    public void print(){
        for(int i=0;i<n*n;i++)
            System.out.print(result[i]);
    }


}