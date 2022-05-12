import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class On {
    private int U = 32 ;
    private int b;
    private int n;
    private int[] S;
    private boolean[] exist;
    private int[]result;
    //for saving the random H
    public LinkedList<int[][]> hashRandomized;
    Hashing hashing;
    Nsquare[] hashTable;
    int[][] subHashTable;
    public  On(Hashing hashingObj) {
        this.hashing = hashingObj;
        n = hashing.n;
        b = (int) Math.ceil(Math.log(n) / Math.log(2));
        hashRandomized = new LinkedList<>();
        exist=new boolean[n];
        result=new int [n];
        hashFunction();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void hashFunction() {
        hashTable = new Nsquare[n];
        int[]S=hashing.S;
        ArrayList<Integer>[]  h=new ArrayList[n];
        for (int i = 0; i < n; i++) {
            h[i] = new ArrayList<Integer>();
        }
        int[][] H = hashing.randomH(b);
        hashRandomized.add(H);
       for (int i = 0; i < n; i++) {
            int[] x = hashing.convertToBinary(S[i]);
            int[] indexBinary = hashing.multiply(H, x);
            int index=hashing.convertToDecimal(indexBinary);
            if(exist[index]){
                //the same index exist
                //collision ->so call again random the H and get another index
                h[index].add(S[i]);
                System.out.println("colli"+h[index]);
            }else {
                result[index] = S[i];
                exist[index] = true;
            }
        }


        subHashTable = new int[hashTable.length][n*n];
        for (int i = 0; i < n; i++) {
            if (h[i] != null && h[i].size() != 0) {
                Integer[] arr = new Integer[h[i].size()];
                arr = h[i].toArray(arr);
                int[] a= new int[h[i].size()];
                for(int j=0;j<h[i].size();j++){
                    a[j]=arr[j];
                    System.out.println("hena "+a[j]);
                }

                Hashing kk=new Hashing(a);
                Nsquare subTable = new Nsquare(kk);
                // System.out.println(subTable.getCollisionNum());
                subHashTable[i] = subTable.result;
            }else{
                subHashTable[i]=null;
            }
        }
    }




    public void print(){
        System.out.println("\n");
        for(int i=0;i<n;i++)
            System.out.println(result[i]);

        System.out.println("////////////////////////////////////////////////");

        for(int i=0;i<hashTable.length;i++) {
           if(subHashTable[i]!=null) {
               for (int j = 0; j < subHashTable[i].length; j++) {
                   System.out.println(subHashTable[i][j]);
               }
               System.out.println("////////////////////////////////////////////////");
           }
        }
    }

}

