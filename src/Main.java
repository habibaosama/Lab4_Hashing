public class Main {
    public static void main(String[] args){
        int []s={1,2,3,5,6,7,8,9};
        Hashing hashing = new Hashing(s);
        Nsquare pHashing = new Nsquare(hashing);
        pHashing.hashFunction();

    }

}