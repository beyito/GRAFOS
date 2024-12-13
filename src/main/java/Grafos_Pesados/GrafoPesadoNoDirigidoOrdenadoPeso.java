/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author cobu-
 */
public class GrafoPesadoNoDirigidoOrdenadoPeso <G extends Comparable<G>> extends GrafoPesadoNoDirigido<G>{
    
   @Override 
   public void insertarArista(G verticeOrigen,G verticeDestino,double peso){
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
        if (nroDelVerticeOrigen != nroDelVerticeDestino) {
            List<AdyacenteConPeso> adyacentesDelDestino = listaDeAdyacencias.get(nroDelVerticeDestino);
            AdyacenteConPeso adyDelDestino = new AdyacenteConPeso(nroDelVerticeOrigen, peso);
            adyacentesDelDestino.add(adyDelDestino);
            Comparador comparadorAdyacentePorPeso = new Comparador();
            Collections.sort(adyacentesDelDestino,comparadorAdyacentePorPeso);
        }  
   }
   
    public GrafoPesadoNoDirigidoOrdenadoPeso ( GrafoPesadoNoDirigido<G> grafoOriginal){
    
     
    }
    public void copiarGrafo(GrafoPesadoNoDirigido<G> grafo){
        
    }
    private final  class Comparador  implements Comparator<AdyacenteConPeso> {
     
        @Override
        public int compare(AdyacenteConPeso o1, AdyacenteConPeso o2) {
           if(o1.getNroVertice()>o1.getNroVertice()){
               return -1;
           }
           else if (o1.getNroVertice()<o2.getNroVertice()){
            return 1;
        }
           return 0;
        }
    }
}
