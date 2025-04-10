package icetasks3;

import java.util.Scanner;

public class ICETASKS3 {
     public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       System.out.print("Student Name: ");
       String studentname = input.nextLine();
       System.out.print("Student Surname: ");
       String studentsurname = input.nextLine();
       System.out.print("Student username: ");
       String studentusername = input.nextLine();
       System.out.print("Enter assignment Mark>> ");
       int assignmentmark = input.nextInt();
       System.out.print("Enter test Mark>> ");
       double testmark = input.nextDouble();
       System.out.print("Enter ICE Task Mark>> ");
       double icetaskmark = input.nextDouble();
       System.out.println("******************************");
       System.out.println("Students Details ");
       System.out.println("******************************");
       System.out.println("Name                     : Boipelo");
       System.out.println("Surname                  : Nxumalo");
       System.out.println("Username                 : ST10489163@rcconnect.edu.za");
       System.out.println("Assignment Mark          : 55");
       System.out.println("Test Mark                : 60");
       System.out.println("ICE Task Mark            : 90");
       System.out.println("Final Average Mark       : 63.");
       System.out.println("******************************");
       
    }
    
}
