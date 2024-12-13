/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import GrafoNoPesado.ControlMarcados;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author cobu-
 */
public class FordFulkerson<G extends Comparable<G>> {

    GrafoOrdenadoPeso<G> grafo;
    GrafoPesadoNoDirigido<G> grafoOriginal;
    G superFuente;
    G superSumidero;
    double flujoMaximo;

    public FordFulkerson(GrafoPesadoNoDirigido<G> grafoOriginal) {
        this.grafoOriginal = grafoOriginal;
        flujoMaximo=0;
        grafo = new GrafoOrdenadoPeso<G>();
        cargarVertices();        
        armarGrafo();
        ejecutarFordFulkerson();
      
    }
    
   public void ejecutarFordFulkerson(){
      
        
        List<G> caminoARecorrer = encontrarCamino();
        
        while(caminoARecorrer.size() > 1){
        double flujoMinimo = flujoMinimo(caminoARecorrer);
         
        flujoMaximo=flujoMaximo+flujoMinimo;
        actualizarFlujos(flujoMinimo,caminoARecorrer);
        caminoARecorrer = encontrarCamino();
        }
    }
   
   public void actualizarFlujos(double flujoMinimo,List<G> camino){
       System.out.println(camino);
     G verticeAnterior=camino.get(0);
     for(int i=1 ;i < camino.size();i++){
         G verticeActual = camino.get(i);
         double pesoActual=grafo.peso(verticeAnterior, verticeActual);
        grafo.setPeso(verticeAnterior, verticeActual, pesoActual-flujoMinimo);
         
         pesoActual=grafo.peso( verticeActual,verticeAnterior);
        grafo.setPeso( verticeActual,verticeAnterior, pesoActual+flujoMinimo);
        verticeAnterior=verticeActual;
     }  
   }
   public double flujoMinimo(List<G> camino){
     double flujoMinimo=Double.POSITIVE_INFINITY;
     G verticeAnterior=camino.get(0);
      
     for(int i=1;i<camino.size();i++){
         G verticeActual = camino.get(i);
         double peso =grafo.peso(verticeAnterior,verticeActual);     
         if(peso< flujoMinimo){
             flujoMinimo = peso;
         }
         verticeAnterior=verticeActual;
     }
       System.out.println(flujoMinimo);
     return flujoMinimo;
   }
 public List<G> encontrarCamino(){
     ControlMarcados controlMarcados = new ControlMarcados(grafo.cantidadDeVertices());
   List<G> camino = new ArrayList<>();
   Stack<G> pilaVertices = new Stack<>();
   pilaVertices.push(superFuente);
   camino.add(superFuente);
    controlMarcados.marcarVertice(grafo.nroVertice(superFuente));
   while(!pilaVertices.isEmpty() && !controlMarcados.estanTodosLosVerticesMarcados() ){
     
    G verticeActual=pilaVertices.pop(); 
     
    int nroVerticeActual = grafo.nroVertice(verticeActual);
   
    List<AdyacenteConPeso> adyacenciaActual= grafo.listaDeAdyacencias.get(nroVerticeActual);
    
    for(int i=adyacenciaActual.size()-1;i >=0;i--){
        AdyacenteConPeso adyacencia = adyacenciaActual.get(i);
        G vertice=grafo.listaDeVertices.get(adyacencia.getNroVertice());
        pilaVertices.push(vertice);
        controlMarcados.marcarVertice(adyacencia.getNroVertice());
        System.out.println(adyacencia.getNroVertice());
        camino.add(vertice);
        if(controlMarcados.estaMarcadoVertice(grafo.nroVertice(superSumidero))){
        return camino;
    }
       
       //controlMarcados.marcarVertice(adyacencia.getNroVertice());
    }
    
   }
  
   return camino;
 }
    
 public void cargarVertices(){
     List<G> sumideros=new ArrayList<>();
     List<G> fuentes=new ArrayList<>();
     for (G verticeOrigen : grafoOriginal.getVertices()) {
            if(grafoOriginal.gradoEntradaVertice(verticeOrigen)==0){
                    fuentes.add(verticeOrigen);
                    System.out.println(verticeOrigen);
                }
            if(grafoOriginal.gradoSalidaVertice(verticeOrigen)==0){
                sumideros.add(verticeOrigen);
            }
     }
     for (G verticeActual : grafoOriginal.getVertices()) {
            grafo.insertarVertice(verticeActual);
        }
     
       if(fuentes.size()>1){
            superFuente = (G) "Super_Fuente";
            grafo.insertarVertice(superFuente);
            for(G verticeFuente:fuentes){
                grafo.insertarArista(superFuente, verticeFuente, Double.POSITIVE_INFINITY);
                 grafo.insertarArista(verticeFuente, superFuente, 0);
            }
            
        }else{
           superFuente=fuentes.get(0);
       }
        if(sumideros.size()>1){
            superSumidero = (G) "Super_Sumidero";
            grafo.insertarVertice(superSumidero);
            for(G verticeSumidero:sumideros){
                grafo.insertarArista( verticeSumidero,superSumidero,Double.POSITIVE_INFINITY);
                grafo.insertarArista( superSumidero,verticeSumidero,0);
            }
        }else{
            superSumidero=sumideros.get(0);
        }
     
 }
 

    private class GrafoOrdenadoPeso<G extends Comparable<G>> extends GrafoPesadoNoDirigido<G> {

        @Override
        public void setPeso(G verticeOrigen, G verticeDestino, double peso) {

            if (existeAdyacencia(verticeOrigen, verticeDestino)) {
                int nroVerticeOrigen = nroVertice(verticeOrigen);
                int nroVerticeDestino = nroVertice(verticeDestino);
                List<AdyacenteConPeso> adyacencia = listaDeAdyacencias.get(nroVerticeOrigen);
                AdyacenteConPeso adySinPeso = new AdyacenteConPeso(nroVerticeDestino);
                int nroAdyacencia = adyacencia.indexOf(adySinPeso);
                AdyacenteConPeso adyConPeso = new AdyacenteConPeso(nroVerticeDestino, peso);
                adyacencia.set(nroAdyacencia, adyConPeso);
                ComparadorAdyacenteConPeso comparador = new ComparadorAdyacenteConPeso();
                Collections.sort(adyacencia, comparador);
            }
        }

        @Override
        public void insertarArista(G verticeOrigen, G verticeDestino, double peso) {
            if (existeAdyacencia(verticeOrigen, verticeDestino)) {
                setPeso(verticeOrigen, verticeDestino, peso);
            }
            int nroDelVerticeOrigen = nroVertice(verticeOrigen);
            int nroDelVerticeDestino = nroVertice(verticeDestino);
            //List<Integer> para GRAFO NO PESADO
            ComparadorAdyacenteConPeso comparadorAdyacentePorPeso = new ComparadorAdyacenteConPeso();
            List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(nroDelVerticeOrigen);
            AdyacenteConPeso adyDelOrigen = new AdyacenteConPeso(nroDelVerticeDestino, peso);
            adyacentesDelOrigen.add(adyDelOrigen);
            Collections.sort(adyacentesDelOrigen, comparadorAdyacentePorPeso);

        }

        @Override
        public void eliminarArista(G verticeOrigen, G verticeDestino) {
            validarVertice(verticeOrigen);
            validarVertice(verticeDestino);
            if (existeAdyacencia(verticeOrigen, verticeDestino)) {
                int nroVerticeOrigen = nroVertice(verticeOrigen);
                int nroVerticeDestino = nroVertice(verticeDestino);
                List<AdyacenteConPeso> adyConPeso = listaDeAdyacencias.get(nroVerticeOrigen);
                AdyacenteConPeso adySinPeso = new AdyacenteConPeso(nroVerticeDestino);
                adyConPeso.remove(adySinPeso);
            }
        }

    }

    private final class ComparadorAdyacenteConPeso implements Comparator<AdyacenteConPeso> {

        @Override
        public int compare(AdyacenteConPeso o1, AdyacenteConPeso o2) {
            if (o1.getPeso() > o2.getPeso()) {
                return -1;
            } else if (o1.getPeso() < o2.getPeso()) {
                return 1;
            }
            return 0;
        }
    }

    public void armarGrafo() {
        for (G verticeOrigen : grafoOriginal.getVertices()) {
            for (G verticeDestino : grafoOriginal.getVertices()) {
                if (grafoOriginal.existeAdyacencia(verticeOrigen, verticeDestino) && !verticeOrigen.equals(verticeDestino)) {
                    double peso = grafoOriginal.peso(verticeOrigen, verticeDestino);
                    grafo.insertarArista(verticeOrigen, verticeDestino, peso);
                } else {
                    grafo.insertarArista(verticeOrigen, verticeDestino, 0);
                }
                
            }
        }
    }
    public double getFlujoMaximo(){
        return flujoMaximo;
    }
}
