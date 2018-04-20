/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author robot
 */
public class IteradorArreglo<T> implements Iterator<T> {
    private T[] coleccion;
    private int total;
    private int actual;
    
    public IteradorArreglo(T[] coleccion, int total) {
        this.coleccion=coleccion;
        this.total=total;
        actual=0;
    }
    
    @Override
    public boolean hasNext(){
        return actual<total;
    }
    @Override
    public T next(){
        if(hasNext()){
            T res=coleccion[actual];
            actual++;
            return res;
        }
        throw new NoSuchElementException();
    }
}
