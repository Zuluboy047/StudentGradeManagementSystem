/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tvseriesmanagement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class SeriesTest {
    
    private Series series;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;
    
    @BeforeEach
    public void setUp() {
        series = new Series();
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testIsValidAgeWithValidNumber() {
        System.out.println("isValidAge with valid number");
        String age = "12";
        boolean result = series.isValidAge(age);
        assertTrue(result, "Valid age should return true");
    }

    @Test
    public void testIsValidAgeWithInvalidNumber() {
        System.out.println("isValidAge with invalid number");
        String age = "20";
        boolean result = series.isValidAge(age);
        assertFalse(result, "Age out of range should return false");
    }

    @Test
    public void testIsValidAgeWithBoundaryValues() {
        System.out.println("isValidAge with boundary values");
        
        assertTrue(series.isValidAge("2"), "Minimum age should be valid");
        assertTrue(series.isValidAge("18"), "Maximum age should be valid");
        assertFalse(series.isValidAge("1"), "Below minimum age should be invalid");
        assertFalse(series.isValidAge("19"), "Above maximum age should be invalid");
    }

    @Test
    public void testIsValidAgeWithNonNumeric() {
        System.out.println("isValidAge with non-numeric input");
        String age = "abc";
        boolean result = series.isValidAge(age);
        assertFalse(result, "Non-numeric age should return false");
    }

    @Test
    public void testIsValidAgeWithEmptyString() {
        System.out.println("isValidAge with empty string");
        String age = "";
        boolean result = series.isValidAge(age);
        assertFalse(result, "Empty string should return false");
    }

    @Test
    public void testDisplayMenu() {
        System.out.println("displayMenu");
        series.displayMenu();
        String output = outputStream.toString();
        assertTrue(output.contains("LATEST SERIES - 2025"));
        assertTrue(output.contains("Capture a new series"));
        assertTrue(output.contains("Search for a series"));
        assertTrue(output.contains("Update series"));
        assertTrue(output.contains("Delete a series"));
        assertTrue(output.contains("Print series report"));
        assertTrue(output.contains("Exit Application"));
    }

    @Test
    public void testCaptureSeriesValidInput() {
        System.out.println("CaptureSeries with valid input");
        
        // Simulate user input
        String input = "S001\nTest Series\n12\n24\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.CaptureSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Capture New Series"));
        assertTrue(output.contains("successfully saved"));
        
        // Verify the series was added to the list
        assertEquals(1, series.seriesList.size());
        SeriesModel capturedSeries = series.seriesList.get(0);
        assertEquals("S001", capturedSeries.SeriesId);
        assertEquals("Test Series", capturedSeries.SeriesName);
        assertEquals("12", capturedSeries.SeriesAge);
        assertEquals("24", capturedSeries.SeriesNumberOfEpisodes);
    }

    @Test
    public void testCaptureSeriesWithInvalidAgeRetry() {
        System.out.println("CaptureSeries with invalid age then valid age");
        
        // Simulate user input: invalid age first, then valid age
        String input = "S002\nAnother Series\n20\n15\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.CaptureSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid age restriction"));
        assertTrue(output.contains("successfully saved"));
        
        assertEquals(1, series.seriesList.size());
        SeriesModel capturedSeries = series.seriesList.get(0);
        assertEquals("15", capturedSeries.SeriesAge); // Should use the valid age
    }

    @Test
    public void testSearchSeriesFound() {
        System.out.println("SearchSeries - series found");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Test Series 1", "12", "24"));
        series.seriesList.add(new SeriesModel("S002", "Test Series 2", "16", "36"));
        
        // Simulate search for existing series
        String input = "S002\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.SearchSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Series Found"));
        assertTrue(output.contains("S002"));
        assertTrue(output.contains("Test Series 2"));
        assertTrue(output.contains("16"));
        assertTrue(output.contains("36"));
    }

    @Test
    public void testSearchSeriesNotFound() {
        System.out.println("SearchSeries - series not found");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Test Series", "12", "24"));
        
        // Simulate search for non-existing series
        String input = "S999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.SearchSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("No series found"));
        assertTrue(output.contains("S999"));
    }

    @Test
    public void testUpdateSeriesFound() {
        System.out.println("UpdateSeries - series found and updated");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Old Name", "12", "24"));
        
        // Simulate update with new values
        String input = "S001\nNew Name\n16\n48\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.UpdateSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Series updated successfully"));
        
        SeriesModel updatedSeries = series.seriesList.get(0);
        assertEquals("New Name", updatedSeries.SeriesName);
        assertEquals("16", updatedSeries.SeriesAge);
        assertEquals("48", updatedSeries.SeriesNumberOfEpisodes);
    }

    @Test
    public void testUpdateSeriesNotFound() {
        System.out.println("UpdateSeries - series not found");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Test Series", "12", "24"));
        
        // Simulate update for non-existing series
        String input = "S999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.UpdateSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("No series found"));
        assertTrue(output.contains("S999"));
    }

    @Test
    public void testDeleteSeriesWithConfirmation() {
        System.out.println("DeleteSeries - with confirmation");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Test Series", "12", "24"));
        series.seriesList.add(new SeriesModel("S002", "Another Series", "16", "36"));
        
        // Simulate delete with confirmation
        String input = "S001\nyes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.DeleteSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Series deleted successfully"));
        assertEquals(1, series.seriesList.size());
        assertEquals("S002", series.seriesList.get(0).SeriesId);
    }

    @Test
    public void testDeleteSeriesWithCancellation() {
        System.out.println("DeleteSeries - with cancellation");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Test Series", "12", "24"));
        
        // Simulate delete with cancellation
        String input = "S001\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.DeleteSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Deletion cancelled"));
        assertEquals(1, series.seriesList.size()); // Series should still exist
    }

    @Test
    public void testDeleteSeriesNotFound() {
        System.out.println("DeleteSeries - series not found");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Test Series", "12", "24"));
        
        // Simulate delete for non-existing series
        String input = "S999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.DeleteSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("No series found"));
        assertEquals(1, series.seriesList.size()); // Original series should still exist
    }

    @Test
    public void testSeriesReportWithData() {
        System.out.println("SeriesReport - with data");
        
        // Add test data
        series.seriesList.add(new SeriesModel("S001", "Series One", "12", "24"));
        series.seriesList.add(new SeriesModel("S002", "Series Two", "16", "36"));
        
        series.SeriesReport();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Series Report - 2025"));
        assertTrue(output.contains("Total Series: 2"));
        assertTrue(output.contains("S001"));
        assertTrue(output.contains("Series One"));
        assertTrue(output.contains("S002"));
        assertTrue(output.contains("Series Two"));
    }

    @Test
    public void testSeriesReportEmpty() {
        System.out.println("SeriesReport - empty list");
        
        series.SeriesReport();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Series Report - 2025"));
        assertTrue(output.contains("Total Series: 0"));
        assertTrue(output.contains("No series available"));
    }

    @Test
    public void testExitSeriesApplication() {
        System.out.println("ExitSeriesApplication");
        
        // This test just verifies the method doesn't throw exceptions
        // We can't actually test System.exit() in a unit test
        assertDoesNotThrow(() -> {
            // Create a new instance to avoid closing the shared scanner
            Series testSeries = new Series();
            testSeries.ExitSeriesApplication();
        });
    }

    // Helper method to test the SeriesModel class
    @Test
    public void testSeriesModelConstructor() {
        System.out.println("SeriesModel constructor");
        
        SeriesModel model = new SeriesModel("S001", "Test Series", "12", "24");
        
        assertEquals("S001", model.SeriesId);
        assertEquals("Test Series", model.SeriesName);
        assertEquals("12", model.SeriesAge);
        assertEquals("24", model.SeriesNumberOfEpisodes);
    }
}