/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosudoku;

/**
 *
 * @author robot
 */
public class ProyectoSudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IncorrectBoardException {
        Solver s;
        

        int[][] board ={
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };
    
/*
        Integer[][] solution={
            {8, 1, 2, 7, 5, 3, 6, 4, 9},
            {9, 4, 3, 6, 8, 2, 1, 7, 5},
            {6, 7, 5, 4, 9, 1, 2, 8, 3},
            {1, 5, 4, 2, 3, 7, 8, 9, 6},
            {3 ,6 ,9 ,8, 4, 5, 7, 2, 1},
            {2 ,8 ,7 ,1 ,6 ,9 ,5, 3, 4},
            {5 ,2 ,1 ,9 ,7 ,4 ,3 ,6 ,8},
            {4 ,3 ,8 ,5 ,2 ,6 ,9 ,1 ,7},
            {7 ,9 ,6 ,3 ,1 ,8 ,4 ,5 ,2}
        };
    */

/*
        int [][]board ={
            {0,2,4,0},
            {1,0,0,3},
            {4,0,0,2},
            {0,1,3,0} 
        };
    */

int[][] board2 ={
{ 7, 0, 0, 1, 0, 8, 0, 0, 0 },
{ 0, 9, 0, 0, 0, 0, 0, 3, 2 },
{ 0, 0, 0, 0, 0, 5, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 1, 0, 0 },
{ 9, 6, 0, 0, 2, 0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 8, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{ 0, 0, 5, 0, 0, 1, 0, 0, 0 },
{ 3, 2, 0, 0, 0, 0, 0, 0, 6 }
};

        s=new Solver(board2);
        System.out.println(s.toString());
        System.out.println("\n");
        System.out.println(s.solve());
        System.out.println("\n");
        System.out.println(s.toString());
    } 
    
}
