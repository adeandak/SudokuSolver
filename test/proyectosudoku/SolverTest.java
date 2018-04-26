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
        Integer[][] mat ={
            {null,2,4,null},
            {1,null,null,3},
            {4,null,null,2},
            {null,1,3,null} 
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
        Integer[][] mat ={
            {null,2,4,null},
            {1,null,null,3},
            {4,null,null,2},
            {null,1,3,null} 
        };
        Solver instance = new Solver(mat);
        Integer[][] expResult = {
            {null,2,4,null},
            {1,null,null,3},
            {4,null,null,2},
            {null,1,3,null} 
        };
        Integer[][] result = instance.getSudoku();
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
        Integer[][] mat ={
            {null,2,4,null},
            {1,null,null,3},
            {4,null,null,2},
            {null,1,3,null} 
        };
        Solver instance = new Solver(mat);
        String expResult = "NICE TRY";
        String result = instance.solve();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
