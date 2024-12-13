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
public class Prim<G extends Comparable<G>> {

    private GrafoPesadoNoDirigido<G> grafo;
    private GrafoPesadoNoDirigido<G> grafoOriginal;
    
    public Prim(GrafoPesadoNoDirigido<G> unGrafo, G verticeInicial) {
        this.grafoOriginal = unGrafo;
        grafo = new GrafoPesadoNoDirigido<>();
        ejecutarPrim(verticeInicial);

    }

    public void ejecutarPrim(G verticePartida) {
        
        grafo.insertarVertice(verticePartida);
        AdyacenciaConPeso ady = buscarMenorCosto();
        while( ady != null ){
               
        G verticeOrigen = grafoOriginal.listaDeVertices.get(ady.getVerticeOrigen());
        G verticeDestino = grafoOriginal.listaDeVertices.get(ady.getVerticeDestino());
            System.out.println(verticeOrigen);
            System.out.println(verticeDestino);
            
        double peso = ady.getPeso();
        grafo.insertarVertice(verticeDestino);
       
        grafo.insertarArista(verticeOrigen,verticeDestino, peso);
        ady = buscarMenorCosto();
            System.out.println(grafo);
        }
    }
    
public GrafoPesadoNoDirigido<G> getGrafo(){
    return grafo;
}
    public AdyacenciaConPeso buscarMenorCosto() {
        double menorPeso = Double.POSITIVE_INFINITY;
       
       
        int nroVerticeOrigen = -1;
        int nroVerticeDestino = -1;
        
        for (G verticeActual : grafo.listaDeVertices) {

           
            int nroVerticeActualOriginal = grafoOriginal.nroVertice(verticeActual);
            
            for (G adyacente : grafoOriginal.getAdyacentesDelVertice(verticeActual)) {
                            
                int nroAdyacencia = grafoOriginal.nroVertice(adyacente);
              
               int index = grafo.listaDeVertices.indexOf(adyacente);
                if (grafoOriginal.peso(verticeActual, adyacente) < menorPeso && index==-1) {
                    menorPeso = grafoOriginal.peso(verticeActual, adyacente);
                    nroVerticeOrigen=nroVerticeActualOriginal;
                    nroVerticeDestino=nroAdyacencia;
                }
            }
        }
        if(nroVerticeOrigen == -1){
           
            return null;
        }
        AdyacenciaConPeso ady = new AdyacenciaConPeso(nroVerticeOrigen,nroVerticeDestino,menorPeso);
        return ady;
    }
}
