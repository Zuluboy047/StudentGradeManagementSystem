package icetask4;
import java.util.Scanner;
public class ICETASK4 {
    
    public static double calculateAverage(double iceMark, double examMark, double testMark) {
        return (iceMark + examMark + testMark);
    }
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter ICE Mark: ");
        double iceMark = scanner.nextDouble();
        System.out.print("Enter Exam Mark: ");
        double examMark = scanner.nextDouble();
        System.out.print("Enter Test Mark: ");
        double testMark = scanner.nextDouble();
        
        double average = calculateAverage(iceMark, examMark, testMark);
        System.out.printf("The average mark is: %.2f%n", average);
    }
    
}
