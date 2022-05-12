public class Main {
    public static void main(String[] args){
        int []s={1,2,3,5,6,7,8,9};
        Hashing hashing = new Hashing(s);
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
