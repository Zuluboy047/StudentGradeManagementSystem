/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tvseriesmanagement;

import java.util.ArrayList;
import java.util.Scanner;


// Main series management class
public class Series {
    // Make seriesList public for testing access
    public ArrayList<SeriesModel> seriesList;
    private Scanner scanner;
    
    // Constructor
    public Series() {
        seriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Method to capture a new series
    public void CaptureSeries() {
        System.out.println("\n=== Capture New Series ===");
        
        System.out.print("Enter Series ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Series Name: ");
        String name = scanner.nextLine();
        
        String age;
        while (true) {
            System.out.print("Enter Age Restriction (2-18): ");
            age = scanner.nextLine();
            
            // Validate age restriction
            if (isValidAge(age)) {
                break;
            } else {
                System.out.println("Invalid age restriction! Please enter a number between 2-18.");
            }
        }
        
        System.out.print("Enter Number of Episodes: ");
        String episodes = scanner.nextLine();
        
        // Create new series and add to list
        SeriesModel newSeries = new SeriesModel(id, name, age, episodes);
        seriesList.add(newSeries);
        
        System.out.println("Series details successfully saved!");
    }
    
    // Method to search for a series
    public void SearchSeries() {
        System.out.println("\n=== Search Series ===");
        System.out.print("Enter Series ID to search: ");
        String searchId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(searchId)) {
                System.out.println("Series Found:");
                System.out.println("ID: " + series.SeriesId);
                System.out.println("Name: " + series.SeriesName);
                System.out.println("Age Restriction: " + series.SeriesAge);
                System.out.println("Episodes: " + series.SeriesNumberOfEpisodes);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("No series found with ID: " + searchId);
        }
    }
    
    // Method to update a series
    public void UpdateSeries() {
        System.out.println("\n=== Update Series ===");
        System.out.print("Enter Series ID to update: ");
        String updateId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(updateId)) {
                System.out.println("Current Series Details:");
                System.out.println("ID: " + series.SeriesId);
                System.out.println("Name: " + series.SeriesName);
                System.out.println("Age Restriction: " + series.SeriesAge);
                System.out.println("Episodes: " + series.SeriesNumberOfEpisodes);
                
                System.out.println("\nEnter new details:");
                System.out.print("New Series Name: ");
                series.SeriesName = scanner.nextLine();
                
                String age;
                while (true) {
                    System.out.print("New Age Restriction (2-18): ");
                    age = scanner.nextLine();
                    if (isValidAge(age)) {
                        series.SeriesAge = age;
                        break;
                    } else {
                        System.out.println("Invalid age restriction! Please enter a number between 2-18.");
                    }
                }
                
                System.out.print("New Number of Episodes: ");
                series.SeriesNumberOfEpisodes = scanner.nextLine();
                
                System.out.println("Series updated successfully!");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("No series found with ID: " + updateId);
        }
    }
    
    // Method to delete a series
    public void DeleteSeries() {
        System.out.println("\n=== Delete Series ===");
        System.out.print("Enter Series ID to delete: ");
        String deleteId = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).SeriesId.equals(deleteId)) {
                System.out.println("Series Found:");
                System.out.println("ID: " + seriesList.get(i).SeriesId);
                System.out.println("Name: " + seriesList.get(i).SeriesName);
                
                System.out.print("Are you sure you want to delete this series? (yes/no): ");
                String confirmation = scanner.nextLine();
                
                if (confirmation.equalsIgnoreCase("yes")) {
                    seriesList.remove(i);
                    System.out.println("Series deleted successfully!");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("No series found with ID: " + deleteId);
        }
    }
    
    // Method to display series report
    public void SeriesReport() {
        System.out.println("\n=== Series Report - 2025 ===");
        System.out.println("Total Series: " + seriesList.size());
        System.out.println("-----------------------------");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
        } else {
            for (SeriesModel series : seriesList) {
                System.out.println("ID: " + series.SeriesId);
                System.out.println("Name: " + series.SeriesName);
                System.out.println("Age Restriction: " + series.SeriesAge);
                System.out.println("Episodes: " + series.SeriesNumberOfEpisodes);
                System.out.println("-----------------------------");
            }
        }
    }
    
    // Method to exit application
    public void ExitSeriesApplication() {
        System.out.println("Thank you for using the TV Series Management Application!");
        scanner.close();
        System.exit(0);
    }
    
    // Helper method to validate age restriction (made public for testing)
    public boolean isValidAge(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Method to display menu
    public void displayMenu() {
        System.out.println("\nLATEST SERIES - 2025");
        System.out.println("***********");
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series.");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
        System.out.print("Enter your choice (1-6): ");
    }
}