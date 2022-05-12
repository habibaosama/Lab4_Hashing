import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int []s={1,2,3,5,6,7,8,9,44,12,15,22};
        s = Arrays.stream(s).distinct().toArray();
        Hashing hashing = new Hashing(s);
        //Nsquare hash=new Nsquare(hashing);

        On hash=new On(hashing);
        hash.print();
        hash.lookUp(6);
        hash.lookUp(4);
        hash.lookUp(19);
        hash.lookUp(9);
        hash.lookUp(5);
        hash.lookUp(8);
        hash.lookUp(7);
        hash.lookUp(3);
        hash.lookUp(2);
        hash.lookUp(1);
    }
}
