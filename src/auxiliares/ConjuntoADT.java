/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import java.util.Iterator;

/**
 *
 * @author robot
 * @param <T>
 */
public interface ConjuntoADT<T> extends Iterable<T> {
    public int getCardinalidad();
    @Override
    public Iterator<T> iterator();
    public boolean contiene(T dato);
    public boolean agrega(T dato);
    public T quita(T dato);
    public ConjuntoADT<T> union(ConjuntoADT<T> otro);
    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro);
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro);
    @Override
    public String toString();
    @Override
    public boolean equals(Object otro);
}
