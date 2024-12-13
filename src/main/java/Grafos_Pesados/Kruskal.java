/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import GrafoNoPesado.ControlMarcados;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author cobu-
 */
public class Kruskal<G extends Comparable<G>> {

    GrafoPesadoNoDirigido<G> grafo;
    GrafoPesadoNoDirigido<G> grafoOriginal;
    List<AdyacenciaConPeso> listaOrdenados ;
    
    public Kruskal(GrafoPesadoNoDirigido<G> grafoOriginal) {
        this.grafoOriginal = grafoOriginal;
        listaOrdenados= new ArrayList<>();
        grafo = new GrafoPesadoNoDirigido<>();
        for(G vertice : grafoOriginal.getVertices()){
            grafo.insertarVertice(vertice);
        }
        crearListaOrdenados();
        ejecutarKruskal();
    }
    
   /* 
*/
    public void crearListaOrdenados (){
 
        for (G vertice : grafo.getVertices()) {
            int nroVerticeOrigen = grafo.nroVertice(vertice);
            for (G adyacente : grafoOriginal.getAdyacentesDelVertice(vertice)) {
               
                 int nroVerticeDestino = grafo.nroVertice(adyacente);
                 double peso = grafoOriginal.peso(vertice,adyacente);
                 
                
                AdyacenciaConPeso adyConPeso = new AdyacenciaConPeso(nroVerticeOrigen,nroVerticeDestino,peso);
   
                listaOrdenados.add(adyConPeso);
                Collections.sort(listaOrdenados);
                
            }

        }
    }
    
    public void copiarGrafo(GrafoPesadoNoDirigido<G> grafo) {
        for (G vertice : grafoOriginal.getVertices()) {
            grafo.insertarVertice(vertice);
        }

        for (G vertice : grafo.getVertices()) {
            for (G adyacente : grafoOriginal.getAdyacentesDelVertice(vertice)) {
                double peso = grafoOriginal.peso(vertice, adyacente);
                grafo.insertarArista(vertice, adyacente, peso);
            }

        }

    }
    public void ejecutarKruskal(){
        for(int i=0; i<listaOrdenados.size();i++){
          AdyacenciaConPeso adyacenciaActual=listaOrdenados.get(i);
          int nroVerticeOrigen = adyacenciaActual.getVerticeOrigen();
          int nroVerticeDestino = adyacenciaActual.getVerticeDestino();
          G verticeOrigen = grafo.listaDeVertices.get(nroVerticeOrigen);
          G verticeDestino = grafo.listaDeVertices.get(nroVerticeDestino);
          double peso = grafoOriginal.peso(verticeOrigen, verticeDestino);
            System.out.println(verticeOrigen+","+verticeDestino+","+peso);
          
          grafo.insertarArista(verticeOrigen, verticeDestino, peso);
          System.out.println(grafo.hayCiclo());
          if(grafo.hayCiclo()){
              
              grafo.eliminarArista(verticeOrigen, verticeDestino);
          }
          
        }
    }
    
    public GrafoPesadoNoDirigido<G> getGrafo(){
        return grafo;
    }

}
