
import java.util.Scanner;
import java.math.BigInteger;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create Scanner object to read user input.
        Scanner scanner = new Scanner(System.in);
        
        // Request and obtain the two numbers we want to multiply.
        System.out.println("Enter two equal-length numbers that you want to multiply.");
        System.out.print("First number: ");
        String firstNumber = scanner.nextLine();
        System.out.print("Second number: ");
        String secondNumber = scanner.nextLine();
        
        System.out.println("");
        
        System.out.println("The product is: " + karatsubaMultiply(firstNumber, secondNumber));
    }
    
    public static BigInteger karatsubaMultiply(String s1, String s2) {
        // If both are not same length, pad the beginning of the shortest with 0's until it's the same length as the longest.
        if (s1.length() > s2.length()) {
            int difference = s1.length() - s2.length();
            for (int i = 1; i <= difference; i++) {
                s2 = "0" + s2;
            }
        }
        
        if (s2.length() > s1.length()) {
            int difference = s2.length() - s1.length();
            for (int i = 1; i <= difference; i++) {
                s1 = "0" + s1;
            }
        }
        
        // Calculate the length of the strings (both are now ensured to be same length).
        int n = s1.length();
        
        // Convert both strings to BigInteger.
        BigInteger firstNumber = new BigInteger(s1);
        BigInteger secondNumber = new BigInteger(s2);
        
        // Base case: two 1-digit numbers.
        if (n == 1) {
            // Do simple multiplication of the two 1-digit numbers.
            return firstNumber.multiply(secondNumber);
        }
        
        // Recursively compute the product of the two numbers.
        boolean oddLength = (n % 2 > 0);
        
        if (oddLength) {
            String a = s1.substring(0, (n / 2) + 1);
            String b = s1.substring((n / 2) + 1, n);
            String c = s2.substring(0, (n / 2) + 1);
            String d = s2.substring((n / 2) + 1, n); // substring(inclusive, exclusive)
            
            BigInteger ac = karatsubaMultiply(a, c);
            BigInteger bd = karatsubaMultiply(b, d);
            BigInteger gauss1 = karatsubaMultiply((new BigInteger(a)).add(new BigInteger(b)).toString(), (new BigInteger(c)).add(new BigInteger(d)).toString());
            BigInteger gauss2 = gauss1.subtract(ac).subtract(bd);
            
            BigInteger ten = new BigInteger("10");
            
            return (ac.multiply(ten.pow(n - 1))).add(gauss2.multiply(ten.pow((n - 1) / 2))).add(bd);
        } else {
            String a = s1.substring(0, n / 2);
            String b = s1.substring(n / 2, n);
            String c = s2.substring(0, n / 2);
            String d = s2.substring(n / 2, n);
            
            BigInteger ac = karatsubaMultiply(a, c);
            BigInteger bd = karatsubaMultiply(b, d);
            BigInteger gauss1 = karatsubaMultiply((new BigInteger(a)).add(new BigInteger(b)).toString(), (new BigInteger(c)).add(new BigInteger(d)).toString());
            BigInteger gauss2 = gauss1.subtract(ac).subtract(bd);
            
            BigInteger ten = new BigInteger("10");
            
            return (ac.multiply(ten.pow(n))).add(gauss2.multiply(ten.pow(n / 2))).add(bd);
        }   
    }
}
