/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tvseriescode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class SeriesTest {
    
    public SeriesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of CaptureSeries method, of class Series.
     */
    @Test
    public void testCaptureSeries() {
        System.out.println("CaptureSeries");
        Series instance = new Series();
        instance.CaptureSeries();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SearchSeries method, of class Series.
     */
    @Test
    public void testSearchSeries() {
        System.out.println("SearchSeries");
        Series instance = new Series();
        instance.SearchSeries();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdateSeries method, of class Series.
     */
    @Test
    public void testUpdateSeries() {
        System.out.println("UpdateSeries");
        Series instance = new Series();
        instance.UpdateSeries();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DeleteSeries method, of class Series.
     */
    @Test
    public void testDeleteSeries() {
        System.out.println("DeleteSeries");
        Series instance = new Series();
        instance.DeleteSeries();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SeriesReport method, of class Series.
     */
    @Test
    public void testSeriesReport() {
        System.out.println("SeriesReport");
        Series instance = new Series();
        instance.SeriesReport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ExitSeriesApplication method, of class Series.
     */
    @Test
    public void testExitSeriesApplication() {
        System.out.println("ExitSeriesApplication");
        Series instance = new Series();
        instance.ExitSeriesApplication();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidAge method, of class Series.
     */
    @Test
    public void testIsValidAge() {
        System.out.println("isValidAge");
        String age = "";
        Series instance = new Series();
        boolean expResult = false;
        boolean result = instance.isValidAge(age);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayMenu method, of class Series.
     */
    @Test
    public void testDisplayMenu() {
        System.out.println("displayMenu");
        Series instance = new Series();
        instance.displayMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
