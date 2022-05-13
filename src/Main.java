import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
       /* int []s={1,2,3,5,6,7,8,9,44,12,15,22};
        s = Arrays.stream(s).distinct().toArray();
        Hashing hashing = new Hashing(s);
        Nsquare hash = new Nsquare(hashing);
        int n=input.nextInt();
        hash.lookUp(n);*/
        boolean flag = true;

        while (flag) {
            System.out.println("Choose number: ");
            System.out.println("1- O(n^2) space");
            System.out.println("2- O(n) space");
            System.out.println("3- exit");

            int n = input.nextInt();
            switch (n) {
                case 1: {
                    System.out.println("enter size of array:");
                    int x= input.nextInt();
                    int []s=new int[x];
                    System.out.println("enter the array");
                    for ( int i=0;i<x;i++)
                        s[i]=input.nextInt();
                    s = Arrays.stream(s).distinct().toArray();
                    Hashing hashing = new Hashing(s);
                    Nsquare hash = new Nsquare(hashing);
                    hash.print();
                }
                break;
                case 2: {
                    System.out.println("enter size of array:");
                    int x= input.nextInt();
                    int []s=new int[x];
                    System.out.println("enter the array");
                    for ( int i=0;i<x;i++)
                        s[i]=input.nextInt();

                    s = Arrays.stream(s).distinct().toArray();
                    Hashing hashing = new Hashing(s);
                    On hash = new On(hashing);
                    hash.print();
                }break;
                case 3:
                    flag=false;break;

                default:
                    System.out.println("Enter valid number!!!!!!!");
            }

        }

        //Hashing hashing = new Hashing(s);
       /* On hash = new On(hashing);
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
        hash.lookUp(1);*/

    }


}
