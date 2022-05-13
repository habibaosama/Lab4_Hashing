import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class On {
    private int U = 32 ;
    private int b;
    private int n;
    private int[] colli;
    private boolean[] exist;
    private boolean[][] ex;
    int H[][];
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
        ex=new boolean[n][];
        finalHashTable = new int[n][];
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

        //Put every element who has the same index in the same arraylist
        // to be sent to the first method late.
        for (int i = 0; i < n; i++) {
            int[] x = hashing.convertToBinary(S[i]);
            int[] indexBinary = hashing.multiply(H, x);
            int index=hashing.convertToDecimal(indexBinary);
            h[index].add(S[i]);
            exist[i]=true;
        }

        //Printing the total occupied space by the second method.
        int sum=0;
        for (int i = 0; i < n; i++) {
            if (h[i] != null) {
                sum += (h[i].size() * h[i].size());
            }
        }
        System.out.println("Space Occupied ="+sum);

        int p=0;
        colli=new int[n];
        hashfuns=new int[n][][];

        for (int i = 0; i < n; i++) {

            if (h[i] != null && h[i].size() != 0) {

                Integer[] arr = new Integer[h[i].size()];
                arr = h[i].toArray(arr);
                int[] a= new int[h[i].size()];

                for(int j=0;j<h[i].size();j++){//converting the arraylist to an array to be sent to the first method.
                    a[j]=arr[j];
                }

                hashing.noCollision++;

                int[] x = hashing.convertToBinary(a[0]);
                int[] indexBinary = hashing.multiply(H, x);
                int index=hashing.convertToDecimal(indexBinary);

                //Each array (elements of the same index) is sent to the first method.
                Hashing kk=new Hashing(a);
                Nsquare subTable = new Nsquare(kk);

                ex[i]=subTable.exist;
                finalHashTable[i] = subTable.result;//The resulted table is saved in this array.
                colli[i]=subTable.noOfHashFuns();//No of rebuilt hash
                hashfuns[index]=subTable.H;//storing the hash functions used.
                hashing.noCollision+=subTable.noOfHashFuns();//Total rebuilt hash for all tables.

            }else{
                finalHashTable[i]=null;
            }

        }
    }



    public void lookUp(int v){//looking for an element to see if it is the hash table or not.

        int[] x = hashing.convertToBinary(v);
        int[] indexBinary = hashing.multiply(H, x);
        int index=hashing.convertToDecimal(indexBinary);
        int[] indBinary;

        if(exist[index]==true){//Check if the index of the element  entered was already added or not.

            if(hashfuns[index]!=null){
                indBinary= hashing.multiply(hashfuns[index], x);//get the hash function used for this element previously.
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

    public int noOfHashFuns(){//printing the no.of rebuilt hash functions.
        return hashing.noCollision;
    }

    public void print(){//printing the resulted hash table.

        for(int i=0;i<hashTable.length;i++) {
            if(finalHashTable[i]!=null) {
                System.out.println("////////////////////////////////////////////////");
                System.out.println("In table "+i+" :");
                for (int j = 0; j < finalHashTable[i].length; j++) {
                    if(ex[i][j]==true) {
                        System.out.println("Number " + finalHashTable[i][j] + " in index : " + j);
                    }
                }
                System.out.println("No. of Re-built Hash = "+colli[i]);

            }
        }
        System.out.println("------> Total no. of Re-built Hash of all the tables = "+ noOfHashFuns() );
    }

}

