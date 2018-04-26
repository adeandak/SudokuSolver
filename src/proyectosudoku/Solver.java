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
 *<pre>
 * Clase capaz de resolver tableros de Sudoku representados
 * por arreglos bidimensionales de tipo Integer, pues la
 * implementación gráfica utiliza una JTable (lo que dificulta
 * el uso de una matriz de tipo int.
 * 
 * Los métodos utilizados son capaces de identificar si tiene
 * solucion y encontrar la en caso de que sea única, o encontar
 * una de ellas en caso de que tenga múltiples, de cualquier 
 * tablero de Sudoku de n renglones por n columnas, donde n es
 * un número entero cuadrado. En caso de que no se cumpla alguna
 * de las condiciones se lanza una excepción.
 * 
 * Para el óptimo funcionamiento de esta clase, se requiere
 * aumenrar la memoria del Stack, pues algunos tableros 
 * requieren de más llamadas recursivas que la configuración
 * predeterminada permite, lanzando un StackOverflowException.
 * </pre>
 * @author SLGA
 */
public class Solver {
    private Integer[][] sudoku;
    private boolean[][] input;
    private int size;
    private ConjuntoA<Integer>[][] sets;
    private int sqrSize;
    private int lastCell;
    
    /**
     * 
     * @param mat Sudoku a resolver.
     * @throws proyectosudoku.IncorrectBoardException   En caso de que se reciba un tablero inválido.
     */
    public Solver(Integer[][] mat) throws IncorrectBoardException{
        if(mat==null || mat.length%((int) Math.sqrt(mat.length))!=0 || mat.length!=mat[0].length)
            throw new IncorrectBoardException("Las dimensiones del tablero no son correctas.");
        sudoku=mat;
        size=sudoku.length;
        sqrSize=(int) Math.sqrt(size);
        lastCell=size*size;
        input=new boolean[size][size];
        sets=new ConjuntoA[3][size];
    }
    
    /**
     * 
     * @param mat Siguiente tablero de sudoku a resolver.
     * @throws proyectosudoku.IncorrectBoardException   En caso de que se reciba un tablero inválido.
     */
    public void setSudoku(Integer[][] mat) throws IncorrectBoardException{
        if(mat==null || mat.length%((int) Math.sqrt(mat.length))!=0 || mat.length!=mat[0].length)
            throw new IncorrectBoardException("Las dimensiones del tablero no son correctas.");
        sudoku=mat;
        size=sudoku.length;
        sqrSize=(int)Math.sqrt(size);
        lastCell=size*size;
        input=new boolean[size][size];
        sets=new ConjuntoA[3][size];
    }
    
    /**
     * 
     * @return <ul>
     *  <li> True: si el sudoku no presenta errores identificables inmediatamente como: <ul>
     *      <li> números repetidos en renglones, columnas o regiones de 3x3,
     *      <li> números menores que 1,
     *      <li> números mayores que size (máximo número posible)
     *      </ul>
     *  <li> False: si se presenta al menos uno de los casos anteriores.
     *  </ul>
     */
    private boolean newSudoku(){
        int row;
        int col;
        Integer inf;
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
            if(sudoku[row][col]!=null){
                inf=sudoku[row][col];
                res=inf>0 && inf<=size;
                if(res){
                    res=sets[0][row].agrega(inf);
                    if(res){
                        res=sets[1][col].agrega(inf);
                        if(res){
                            res=sets[2][col/sqrSize + (row/sqrSize)*sqrSize].agrega(inf);    // se divide y se multiplica por el mismo numero para redondear al multiplo de SQR_SIZE mas cercano
                            input[row][col]=true;
                        }
                    }
                }
            }
            i++;
        }
        return res;
    }
    
    /**
     * 
     * @param num   Número a verificar.
     * @param row   Reglón de la celda a verificar.
     * @param col   Columna de la celda a verificar.
     * @return <ul>
     *  <li> True: si num no está presente en alguna de las celdas pertenecientes a:<ul>
     *      <li> el renglón row,
     *      <li> la columna col,
     *      <li> la región de 3x3 correspondiente.
     *      </ul>
     *  <li> False: en caso contrario, num ya está presente en alguna celda.
     *  </ul>
     */
    private boolean isValid(Integer num, int row, int col){
        return !sets[0][row].contiene(num) && !sets[1][col].contiene(num) && !sets[2][col/sqrSize + (row/sqrSize)*sqrSize].contiene(num);
    }
    
    /**
     * 
     * @return <ul>
     *  <li> "NICE TRY"; si el sudoku se resolvió correctamente.
     *  <li> "El tablero está sobrerrestringido o contiene números incorrectos."
     *  </ul>
     */
    public String solve(){
        int i=lastCell;
        int row;
        int col;
        String res="El tablero está sobrerrestringido o contiene números incorrectos.";
        
        if(newSudoku()){
            do{
                i--;
                row=i/size;
                col=i%size;
            }while(input[row][col]);
            if(solve(i))
                res="NICE TRY";
            //print();  //para pruebas
        }
        return res;
    }
    /**
     * 
     * @param num   Índice de la celda a solucionar.
     * @return <ul>
     *  <li> False: si el sudoku está sobrerrestringido.
     *  <li> True: si el sudoku tiene al menos una solución.
     *  </ul>
     */
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
            if(!input[row][col]){   //la celda no está restringida por el usuario
                res=sudoku[row][col];
                if(res==null){  //no se ha intentado ningún número en la celda
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
                if(res>0){  //se encontró un número posible
                    sudoku[row][col]=res;
                    sets[0][row].agrega(res);
                    sets[1][col].agrega(res);
                    sets[2][sec].agrega(res);
                    //print();  //para pruebas
                    return solve(num-1);    //se pasa a la siguiente celda
                }else{  //no hay ningún número posible para la celda dada
                    sudoku[row][col]=null;
                    //print();  //para pruebas
                    return solve(goBack(num+1));    //se debe modificar la celda anterior no restringida
                }
            }
            else    //la celda está restringida
                return solve(num-1);    //se pasa a la siguiente celda
        }
    }
    /**
     * 
     * @param num   El índice de la celda para iniciar la búsqueda
     * @return El índice de la siguiente celda del sudoku no restringida por el usuario
     * @throws NoSuchElementException en caso de recibir un índice mayor al de la última celda
     */
    private int goBack(int num){
        int row=num/size;
        int col=num%size;
        
        if(num<lastCell){
            if(!input[row][col])
                return num;
            else
                return goBack(num+1);
        }
        throw new NoSuchElementException();
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
    
    //métodos para realizar pruebas
    private void print(){
        for(Integer[] c:sudoku)
            System.out.println(Arrays.toString(c));
        System.out.println("\n");
    }
}
