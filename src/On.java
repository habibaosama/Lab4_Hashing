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
   // private int[] result;
    int H[][];
    //for saving the random H
    public LinkedList<int[][]> hashRandomized;
    Hashing hashing;
    Nsquare[] hashTable;
    int[][][]  hashfuns;
    int[][] finalHashTable;
    public  On(Hashing hashingObj) {
        this.hashing = hashingObj;
        n = hashing.n;
        b = (int) Math.floor(Math.log(n) / Math.log(2));
        hashRandomized = new LinkedList<>();
        exist=new boolean[n];
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
        H = hashing.randomH(b);
        hashRandomized.add(H);

       for (int i = 0; i < n; i++) {
            int[] x = hashing.convertToBinary(S[i]);
            int[] indexBinary = hashing.multiply(H, x);
            int index=hashing.convertToDecimal(indexBinary);
            h[index].add(S[i]);
            exist[i]=true;
         //   System.out.println(index+"colli"+h[index]);
        }
       int sum=0;
        for (int i = 0; i < n; i++) {
            if (h[i] != null) {
                sum += (h[i].size() * h[i].size());
            }
        }
        System.out.println("Space Occupied ="+sum);

        int p=0;

        finalHashTable = new int[n][];
        hashfuns=new int[n][][];
        for (int i = 0; i < n; i++) {
            if (h[i] != null && h[i].size() != 0) {
                Integer[] arr = new Integer[h[i].size()];
                arr = h[i].toArray(arr);
                int[] a= new int[h[i].size()];
                for(int j=0;j<h[i].size();j++){
                    a[j]=arr[j];
                //    System.out.println("hena "+a[j]);
                }
                hashing.noCollision++;
                int[] x = hashing.convertToBinary(a[0]);
                int[] indexBinary = hashing.multiply(H, x);
                int index=hashing.convertToDecimal(indexBinary);
                Hashing kk=new Hashing(a);
                Nsquare subTable = new Nsquare(kk);

                finalHashTable[i] = subTable.result;
                hashfuns[index]=subTable.H;
                hashing.noCollision+=subTable.noOfHashFuns();
            }else{
                finalHashTable[i]=null;
            }

        }
    }

    public void lookUp(int v){
        int[] x = hashing.convertToBinary(v);
        int[] indexBinary = hashing.multiply(H, x);
        int index=hashing.convertToDecimal(indexBinary);
        int[] indBinary;
        if(exist[index]==true){
            //System.out.println("Found!! at the first hashtable at index "+index);
            if(hashfuns[index]!=null){
                indBinary= hashing.multiply(hashfuns[index], x);
                int ind=hashing.convertToDecimal(indBinary);
                int[] r=finalHashTable[index];
                if(r[ind]==v){
                    System.out.println("Found!! at the index "+ind+" of second hashtable of the "+index+" index of the first hashtable");
                }else{
                    System.out.println("Not Found!!");
                }
            }else{
                System.out.println("Not Found!!");
            }
        }else{
            System.out.println("Not Found!!");
        }
    }
    public int noOfHashFuns(){
        return hashing.noCollision;
    }

    public void print(){

        for(int i=0;i<hashTable.length;i++) {
           if(finalHashTable[i]!=null) {
               System.out.println("////////////////////////////////////////////////");
              // System.out.println(i+"hiiiiii");
               for (int j = 0; j < finalHashTable[i].length; j++) {
                   if(finalHashTable[i][j]!=0) {
                     //  System.out.println(finalHashTable[i][j]);
                       System.out.println("Number " + finalHashTable[i][j] + " in index : " + j + " of second hashtable of the " + i + " index of the first hashtable");
                   }
               }

           }
        }
        System.out.println("No. of collision that occurred= "+ hashing.noCollision );
    }

}

