/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import GrafoNoPesado.ControlMarcados;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author cobu-
 */
public class Dijkstra <G extends Comparable<G>> {
    private final GrafoPesadoDirigido<G> elGrafo;
    private final ControlMarcados controlMarcados;
    private final G verticeDestino;
    private final G verticeOrigen;
    private final List<Double> costos;  
    private final List<Integer> predecesores;
    
    public Dijkstra(GrafoPesadoDirigido<G> unGrafo , G verticeOrigen,G verticeDestino){
        this.verticeOrigen=verticeOrigen;
        this.verticeDestino=verticeDestino;
        predecesores=new ArrayList<>();
        elGrafo=unGrafo;
        costos=new ArrayList<>();
        for(int i=0;i < elGrafo.cantidadDeVertices();i++){
            costos.add(Double.POSITIVE_INFINITY);
            predecesores.add(-1);
        }
        controlMarcados= new ControlMarcados(elGrafo.cantidadDeVertices());
        
        int nroVerticeOrigen=elGrafo.nroVertice(verticeOrigen);
        costos.set(nroVerticeOrigen,0.0);
        this.ejecutarDijkstra();
    }
    
    public void ejecutarDijkstra(){
        
        int nroVerticeDestino=elGrafo.nroVertice(verticeDestino);
        
        while(!controlMarcados.estaMarcadoVertice(nroVerticeDestino)){
            
            int nroVerticeActual = nroVerticeMenorCostoSinMarcar();
            G verticeActual=elGrafo.listaDeVertices.get(nroVerticeActual);
            controlMarcados.marcarVertice(nroVerticeActual);
          
            
            List<AdyacenteConPeso> listaAdyacencia = elGrafo.listaDeAdyacencias.get(nroVerticeActual);
            
            for(AdyacenteConPeso adyacente : listaAdyacencia){
                
               if(!controlMarcados.estaMarcadoVertice(adyacente.getNroVertice())){
                   
                   G verticeAControlar = elGrafo.listaDeVertices.get(adyacente.getNroVertice());
                   
                   double costoAComparar=costos.get(nroVerticeActual)+ elGrafo.peso( verticeActual,verticeAControlar);
                  
                   
                   if(costos.get(adyacente.getNroVertice()) > costoAComparar){
                       costos.set(adyacente.getNroVertice(),costoAComparar);
                       predecesores.set(adyacente.getNroVertice(), nroVerticeActual);
                   }
               } 
            }    
            }
        
    }
    
    public double costoMinimo(){
        int nroVerticeDestino=elGrafo.nroVertice(verticeDestino);
        return costos.get(nroVerticeDestino);
    }
    
    public List<G> caminoCostoMinimo(){
        Stack<Integer> camino = new Stack<>();
        int nroVerticeDestino=elGrafo.nroVertice(verticeDestino);
        int predecesor = nroVerticeDestino;
        while(predecesor != -1){
            camino.push(predecesor);
           predecesor=predecesores.get(predecesor);       
        }
        List<G> caminoCostoMinimo=new ArrayList<>();
        int cantidad = camino.size();
        for (int i=0;i<cantidad;i++){
            G vertice = elGrafo.listaDeVertices.get(camino.pop());
            caminoCostoMinimo.add(vertice);
        }
      return caminoCostoMinimo;  
    }
    
    public int nroVerticeMenorCostoSinMarcar(){
   
      double costoMenor = Double.POSITIVE_INFINITY;  
    for(int i=0;i<elGrafo.cantidadDeVertices();i++){
        if(!controlMarcados.estaMarcadoVertice(i) && costos.get(i)<costoMenor){
            costoMenor=costos.get(i);
        }
    }
    return costos.indexOf(costoMenor);
    }
    
}
