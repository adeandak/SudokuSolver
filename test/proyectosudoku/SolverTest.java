/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosudoku;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robot
 */
public class SolverTest {
    
    public SolverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setSudoku method, of class Solver.
     */
    @Test
    public void testSetSudoku() throws Exception {
        System.out.println("setSudoku");
        int[][] mat ={
            {0,2,4,0},
            {1,0,0,3},
            {4,0,0,2},
            {0,1,3,0} 
        };
        Solver instance = new Solver(mat);
        instance.setSudoku(mat);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSudoku method, of class Solver.
     */
    @Test
    public void testGetSudoku() throws IncorrectBoardException {
        System.out.println("getSudoku");
        int[][] mat ={
            {0,2,4,0},
            {1,0,0,3},
            {4,0,0,2},
            {0,1,3,0} 
        };
        Solver instance = new Solver(mat);
        int[][] expResult ={
            {0,2,4,0},
            {1,0,0,3},
            {4,0,0,2},
            {0,1,3,0} 
        };
        int[][] result = instance.getSudoku();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of solve method, of class Solver.
     */
    @Test
    public void testSolve() throws IncorrectBoardException {
        System.out.println("solve");
        int[][] mat ={
            {0,2,4,0},
            {1,0,0,3},
            {4,0,0,2},
            {0,1,3,0} 
        };
        Solver instance = new Solver(mat);
        String expResult = "NICE TRY";
        String result = instance.solve();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
