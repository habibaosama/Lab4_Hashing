import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //int []s={1,2,3,5,6,7,8,9,44,12,15,22};
        // s = Arrays.stream(s).distinct().toArray();
        Hashing hashing=null;
        Nsquare hash1 = null;
        On hash2=null;
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Choose number: ");
            System.out.println("1- O(n^2) space");
            System.out.println("2- O(n) space");
            System.out.println("3- Look Up");
            System.out.println("4- exit");
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
                    hashing = new Hashing(s);
                    hash1 = new Nsquare(hashing);
                    hash1.print();
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
                    hashing = new Hashing(s);
                    hash2 = new On(hashing);
                    hash2.print();
                }break;
                case 3:{

                        System.out.println("Enter the method you want to look up in:  -Enter 1 for method 1 :::: -Enter 2 for method 2");
                        int x= input.nextInt();
                        if(x==1){
                            System.out.println("Enter the number you want to look for:");
                            int y= input.nextInt();
                            if(hash1!=null) {
                                hash1.lookUp(y);
                            }else{
                                System.out.println("Enter the array first!!!");
                            }
                        }else if(x==2){
                            System.out.println("Enter the number you want to look for:");
                            int y= input.nextInt();
                            if(hash2!=null) {
                                hash2.lookUp(y);
                            }else{
                                System.out.println("Enter the array first!!!");
                            }
                        }else{
                            System.out.println("Wrong Entry!!!");
                        }
                    }break;

                case 4:

                    flag=false;break;
                default:
                    System.out.println("Enter valid number!!!!!!!");
            }

        }

        //Hashing hashing = new Hashing(s);
        //Nsquare hash=new Nsquare(hashing);

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

    /*public static int[] convertToArray(String s) {
        int[] arr = new int[s.length()];
        String[] str = s.split(", ");
        if (s.length() != 1 || !(str[0].isEmpty())) {

            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
        }
        return arr;
    }*/
}
