/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesmanagement;

import java.util.Scanner;

// Main class to run the TV Series Management Application
public class Main {
    public static void main(String[] args) {
        // Create instance of Series class
        Series app = new Series();
        Scanner mainScanner = new Scanner(System.in);
        
        // Display welcome message
        System.out.println("LATEST SERIES - 2025");
        System.out.println("***********");
        System.out.print("Enter (1) to launch menu or any other key exit: ");
        String choice = mainScanner.nextLine();
        
        // Check if user wants to launch the application
        if (!choice.equals("1")) {
            System.out.println("Application exited.");
            mainScanner.close();
            return;
        }
        
        // Main application loop
        boolean running = true;
        while (running) {
            // Display menu
            app.displayMenu();
            String menuChoice = mainScanner.nextLine();
            
            // Process user choice
            switch (menuChoice) {
                case "1":
                    app.CaptureSeries();
                    break;
                case "2":
                    app.SearchSeries();
                    break;
                case "3":
                    app.UpdateSeries();
                    break;
                case "4":
                    app.DeleteSeries();
                    break;
                case "5":
                    app.SeriesReport();
                    break;
                case "6":
                    System.out.println("Thank you for using the TV Series Management Application!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1-6.");
            }
            
            // Add a pause so user can see the results (only if not exiting)
            if (running) {
                System.out.println("\nPress Enter to continue...");
                mainScanner.nextLine();
            }
        }
        
        // Close scanner before exiting
        mainScanner.close();
    }
}
