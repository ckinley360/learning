
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        List<String> myList = new List<>();
        System.out.println(myList.contains("hello"));
        myList.add("hello");
        System.out.println(myList.contains("hello"));
    }
}
