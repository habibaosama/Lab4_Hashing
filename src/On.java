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
    int H[][];
    //for saving the random H
    public LinkedList<int[][]> hashRandomized;
    Hashing hashing;
    Nsquare[] hashTable;
    int[][][]  hashfuns;
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
        H = hashing.randomH(b);
        hashRandomized.add(H);
        int y=0;
       for (int i = 0; i < n; i++) {
            int[] x = hashing.convertToBinary(S[i]);
            int[] indexBinary = hashing.multiply(H, x);
            int index=hashing.convertToDecimal(indexBinary);
            if(exist[index]){
                //the same index exist
                //collision ->so call again random the H and get another index
                h[index].add(S[i]);
                System.out.println(index+"colli"+h[index]);
            }else {
                result[index] = S[i];
                exist[index] = true;
            }
        }
       int sum=0;
        for (int i = 0; i < n; i++) {
            if (h[i].size()>=1) {
                sum += (h[i].size() * h[i].size());
            }else{
                sum+=1;
            }
        }
        System.out.println("sum="+sum);
        for (int i = 0; i < n; i++) {
            if(h[i].size()!=0){
                y++;
            }
        }
        System.out.println("hashfuns"+y);
        int p=0;
        subHashTable = new int[hashTable.length][n*n];
        hashfuns=new int[n][][];
        for (int i = 0; i < n; i++) {
            if (h[i] != null && h[i].size() != 0) {
                Integer[] arr = new Integer[h[i].size()];
                arr = h[i].toArray(arr);
                int[] a= new int[h[i].size()];
                for(int j=0;j<h[i].size();j++){
                    a[j]=arr[j];
                    System.out.println("hena "+a[j]);
                }
                int[] x = hashing.convertToBinary(a[0]);
                int[] indexBinary = hashing.multiply(H, x);
                int index=hashing.convertToDecimal(indexBinary);
                Hashing kk=new Hashing(a);
                Nsquare subTable = new Nsquare(kk);
                // System.out.println(subTable.getCollisionNum());
                //////////////////////////3mlt comment////////////////////
                subHashTable[i] = subTable.result;
                hashfuns[index]=subTable.H;
            }else{
                subHashTable[i]=null;
            }

        }
    }

    public void lookUp(int v){
        int[] x = hashing.convertToBinary(v);
        int[] indexBinary = hashing.multiply(H, x);
        int index=hashing.convertToDecimal(indexBinary);
        int[] indBinary;
        if(result[index]==v){
            System.out.println("Found!! at the first hashtable at index "+index);
        }else{
            if(hashfuns[index]!=null){
               // System.out.println(hashfuns[index].toString());
                indBinary= hashing.multiply(hashfuns[index], x);
                int ind=hashing.convertToDecimal(indBinary);
                int[] r=subHashTable[index];
                if(r[ind]==v){
                    System.out.println("Found!! at the index "+ind+" of second hashtable of the "+index+" index of the first hashtable");
                }else{
                    System.out.println("Not Found!!");
                }
            }else{
                System.out.println("Not Found!!");
            }
        }
    }


    public void print(){
        System.out.println("\n");
        for(int i=0;i<n;i++)
            System.out.println(result[i]);



        for(int i=0;i<hashTable.length;i++) {

           if(subHashTable[i]!=null) {
               System.out.println("////////////////////////////////////////////////");
               System.out.println(i+"hiiiiii");
               for (int j = 0; j < subHashTable[i].length; j++) {
                   System.out.println(subHashTable[i][j]);
               }

           }
        }
    }

}

