import java.util.ArrayList;
import java.util.Arrays;

public class Nsquare {
    int result[];
    int H[][]; // Zeros and ones
    boolean exist[];
    int b;
    int n;
    Hashing hashing;

    public Nsquare(Hashing hash) {
        this.hashing = hash;
        n=hashing.n;
        result=new int[n*n];
        exist = new boolean[n*n];
        b = (int) Math.floor(Math.log(Math.pow(n,2)) / Math.log(2));
        H = new int[b][hashing.U];
        hashFunction();
    }
    public void hashFunction() {
        int[]S=hashing.S;
        H = hashing.randomH(b);
        boolean hashed;
        do{
            hashed=true;
            for(int i=0;i<n;i++) {

                   // System.out.println("henaa" + n);
                    int[] x = hashing.convertToBinary(S[i]);
                    int[] indexBinary = hashing.multiply(H, x);
                    int index = hashing.convertToDecimal(indexBinary);
                  //  System.out.println("hhhhh" + S[i]);
                    if (exist[index]) {
                        //the same index exist
                        //collision ->so call again random the H and get another index
                        H = hashing.randomH(b);
                        hashed = false;
                        Arrays.fill(exist, false);
                        Arrays.fill(result, 0);
                       // System.out.println("colision!!" + hashed);
                        break;
                    } else {
                        result[index] = S[i];
                        exist[index] = true;
                    }
            }
        }while(!hashed);
      //  print();
    }

    public int noOfHashFuns(){
        return hashing.noCollision;
    }
    public void print(){
        for(int i=0;i<n*n;i++){
            if(exist[i]){
                System.out.println("Number " + result[i] + "  in index : "+ i);
            }
        }
        System.out.println("Number to re-build the hash table in the case of collision = "+ hashing.noCollision );

    }
    public void lookUp(int value){
        boolean flag =false;
        for(int i=0;i<n*n;i++){
            if(value==result[i]){
                System.out.println(value +" Found  at index "+i);
                flag=true;
                break;
            }
        }
        if(!flag)
            System.out.println(value+ " Not found !!");
    }


}
