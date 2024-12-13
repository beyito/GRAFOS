/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author cobu-
 */
public class GrafoPesadoDirigido<G extends Comparable<G>> extends GrafoPesadoNoDirigido<G> {

    @Override
    public void insertarArista(G verticeOrigen, G verticeDestino, double peso) {
        if (existeAdyacencia(verticeOrigen, verticeDestino)) {
            throw new IllegalArgumentException("Ya existe esa arista");
        }
        int nroDelVerticeOrigen = nroVertice(verticeOrigen);
        int nroDelVerticeDestino = nroVertice(verticeDestino);
        //List<Integer> para GRAFO NO PESADO

        List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(nroDelVerticeOrigen);
        AdyacenteConPeso adyDelOrigen = new AdyacenteConPeso(nroDelVerticeDestino, peso);
        adyacentesDelOrigen.add(adyDelOrigen);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public void eliminarArista(G verticeOrigen, G verticeDestino) {
        if (existeAdyacencia(verticeOrigen, verticeDestino)) {
            int nroVerticeOrigen = nroVertice(verticeOrigen);
            int nroVerticeDestino = nroVertice(verticeDestino);
            List<AdyacenteConPeso> adyacenciaOrigen = listaDeAdyacencias.get(nroVerticeOrigen);
            AdyacenteConPeso adySinPeso = new AdyacenteConPeso(nroVerticeDestino);
            adyacenciaOrigen.remove(adySinPeso);
        }

    }
public double flujoMaximo(){
    FordFulkerson<G> fordFulkerson = new FordFulkerson<>(this);
    return fordFulkerson.getFlujoMaximo();
}
    public List<G> caminoCosotoMinimo(G verticeOrigen, G verticeDestino) {
        Dijkstra<G> caminoCosto = new Dijkstra<>(this, verticeOrigen, verticeDestino);
        return caminoCosto.caminoCostoMinimo();
    }

    public void caminosMinimos() {
        Floyd<G> floyd = new Floyd(this);
        Integer[][] matriz = floyd.getMatrizPredecesor();
        for (int i = 0; i < cantidadDeVertices(); i++) {
            for (int j = 0; j < cantidadDeVertices(); j++) {
                
                 
                System.out.print(matriz[i][j]+", ");
            }
            System.out.println("");
        }
    }
     public void costosMinimos() {
        Floyd<G> floyd = new Floyd(this);
        Double[][] matriz = floyd.getMatrizAdyacencia();
        for (int i = 0; i < cantidadDeVertices(); i++) {
            for (int j = 0; j < cantidadDeVertices(); j++) {
                
                 
                System.out.print(matriz[i][j]+", ");
            }
            System.out.println("");
        }
    }
     public List<G> caminoMinimo(G verticeOrigen,G verticeDestino){
         Floyd<G> floyd = new Floyd(this);
         return floyd.getCaminoMinimo(verticeOrigen, verticeDestino);
     }

}


/*Algoritmo FordFulkersen

setpesoArista
Collectionsort( , comparadorAdyacentePorPeso;

mostrar grafo

G =>[[B | 40.0]->[A | 20.0]->[C | 0.0]]
A =>[[A | 20.0]->[G | 0.0]]


CREAR UN PRIVATE FINAL Comparate 
 */
