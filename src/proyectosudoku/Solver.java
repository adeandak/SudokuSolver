/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosudoku;

import auxiliares.ConjuntoA;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author eschu
 */
public class Solver {
    private Integer[][] sudoku;
    private int[][] input;
    private int size;
    private ConjuntoA<Integer>[][] sets;
    private int sqrSize;
    private int lastCell;
    private final Integer NO_VALUE=0;
    
    public Solver(Integer[][] mat){
        sudoku=mat;
        size=sudoku.length;
        sqrSize=(int)Math.sqrt(size);
        lastCell=size*size;
        input=new int[size][size];
        sets=new ConjuntoA[3][size];
    }
    
    private boolean newSudoku(){
        int row;
        int col;
        boolean res=true;
        int i=0;
        
        for(int j=0; j<size; j++){
            sets[0][j]=new ConjuntoA();
            sets[1][j]=new ConjuntoA();
            sets[2][j]=new ConjuntoA();
        }
        while(res && i<size*size){
            row=i/size;
            col=i%size;
            if(!sudoku[row][col].equals(NO_VALUE)){
                res=sets[0][row].agrega(sudoku[row][col]);
                res=res && sets[1][col].agrega(sudoku[row][col]);
                res=res && sets[2][col/sqrSize + (row/sqrSize)*sqrSize].agrega(sudoku[row][col]);    // se divide y se multiplica por el mismo numero para redondear al multiplo de SQR_SIZE mas cercano
                input[row][col]=1;
            }
            i++;
        }
        return res;
    }
    
    private boolean isValid(Integer num, int row, int col){
        return !sets[0][row].contiene(num) && !sets[1][col].contiene(num) && !sets[2][col/sqrSize + (row/sqrSize)*sqrSize].contiene(num);
    }
    
    public String solve(){
        int i=lastCell;
        int row;
        int col;
        String res="ERROR";
        if(newSudoku()){
            do{
                i--;
                row=i/size;
                col=i%size;
            }while(input[row][col]==1);
            if(solve(i))
                res="NICE TRY";
            print();
        }
        return res;
    }
    private boolean solve(int num){
        int row=num/size;
        int col=num%size;
        int sec=col/sqrSize + (row/sqrSize)*sqrSize;
        Integer res;
        
        if(num<0)
            return true;
        else if(num>=lastCell)
            return false;
        else{
            if(input[row][col]!=1){
                res=sudoku[row][col];
                if(res.equals(NO_VALUE)){
                    res=size;
                }else{
                    sets[0][row].quita(res);
                    sets[1][col].quita(res);
                    sets[2][sec].quita(res);
                    res--;
                }
                while(res>0 && !isValid(res, row, col)){
                    res--;
                }
                if(res>0){
                    sudoku[row][col]=res;
                    sets[0][row].agrega(res);
                    sets[1][col].agrega(res);
                    sets[2][sec].agrega(res);
                    //print();
                    return solve(num-1);
                }else{
                    sudoku[row][col]=NO_VALUE;
                    //print();
                    return solve(goBack(num+1));
                }
            }
            else
                return solve(num-1);
        }
    }
    private int goBack(int num){
        int row=num/size;
        int col=num%size;
        
        if(num<lastCell){
            if(input[row][col]!=1)
                return num;
            else
                return goBack(num+1);
        }
        throw new NoSuchElementException("Valio barriga, señor Vergas.");
    }
    
    
    
    
    
    @Override
    public String toString(){
        StringBuilder cad=new StringBuilder();
        for(Integer[] c:sudoku){
            cad.append(Arrays.toString(c));
            cad.append("\n");
        }
        cad.deleteCharAt(cad.length()-1);
        return cad.toString();
    }
    
    private void print(){
        for(Integer[] c:sudoku)
            System.out.println(Arrays.toString(c));
        System.out.println("\n");
    }
}
