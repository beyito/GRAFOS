/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cobu-
 * @param <G>
 */
public class GrafoPesadoNoDirigido<G extends Comparable<G>> {

    protected List<G> listaDeVertices;
    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;
    public static final int NRO_DE_VERTICE_INVALIDO = -1;

    public GrafoPesadoNoDirigido() {
        listaDeVertices = new ArrayList<>();
        listaDeAdyacencias = new ArrayList<>();
    }

    public GrafoPesadoNoDirigido(Iterable<G> vertices) {
        this();
        for (G vertice : vertices) {
            insertarVertice(vertice);
        }
    }

    public int cantidadDeVertices(){
        return listaDeVertices.size();
    }
    public Iterable<G> getVertices() {
        return listaDeVertices;
    }

    public void eliminarVertice(G verticeAEliminar) {
        validarVertice(verticeAEliminar);
        int nroVerticeAEliminar = nroVertice(verticeAEliminar);
        AdyacenteConPeso adySinPeso=new AdyacenteConPeso(nroVerticeAEliminar);
        listaDeVertices.remove(verticeAEliminar);
        listaDeAdyacencias.remove(nroVerticeAEliminar);
        
        for (List<AdyacenteConPeso> adyacencia : listaDeAdyacencias) {
           
            int posVerticeAEliminar = adyacencia.indexOf(adySinPeso);
            
            if (posVerticeAEliminar >= 0) {
                adyacencia.remove(posVerticeAEliminar);
            }
            for (int i = 0; i < adyacencia.size(); i++) {
                AdyacenteConPeso adyacenciaConPesoActual = adyacencia.get(i);
                int nroVerticeAdyacente = adyacenciaConPesoActual.getNroVertice();
                if (nroVerticeAdyacente > nroVerticeAEliminar) {
                    adyacenciaConPesoActual.setNroVertice(nroVerticeAdyacente - 1);
                }
            }

        }
    }

    public void eliminarArista(G verticeOrigen,G verticeDestino){
        if(existeAdyacencia(verticeOrigen,verticeDestino)){
            int nroVerticeOrigen=nroVertice(verticeOrigen);
            int nroVerticeDestino=nroVertice(verticeDestino);
         List<AdyacenteConPeso> adyacenciaOrigen = listaDeAdyacencias.get(nroVerticeOrigen);
         List<AdyacenteConPeso> adyacenciaDestino = listaDeAdyacencias.get(nroVerticeDestino);
         AdyacenteConPeso adySinPeso =new AdyacenteConPeso(nroVerticeDestino);
         adyacenciaOrigen.remove(adySinPeso);
         
         adySinPeso =new AdyacenteConPeso(nroVerticeOrigen);
         adyacenciaDestino.remove(adySinPeso);
        }
    }
    
    public int gradoEntradaVertice(G verticeABuscar){
    validarVertice(verticeABuscar); 
    int gradoEntrada=0;
     for (G verticeActual:listaDeVertices){
         if(existeAdyacencia(verticeActual,verticeABuscar))
             gradoEntrada++;
    }
     return gradoEntrada;
    }
    
    public int gradoSalidaVertice(G verticeABuscar){
      validarVertice(verticeABuscar); 
      int nroVertice=nroVertice(verticeABuscar);
      List<AdyacenteConPeso> listaAdyacencia = listaDeAdyacencias.get(nroVertice);
      return listaAdyacencia.size();
    }
    
    public void insertarVertice(G vertice) {
        int nroDelVertice = nroVertice(vertice);
        if (nroDelVertice != NRO_DE_VERTICE_INVALIDO) {
            throw new IllegalArgumentException("el vertice: " + vertice + "  ya se encuentra en el grafo");
        }
        listaDeVertices.add(vertice);
        listaDeAdyacencias.add(new ArrayList<>());
        Collections.sort(listaDeVertices);
    }

    public void validarVertice(G vertice) {
        if (!listaDeVertices.contains(vertice)) {
            throw new IllegalArgumentException("el vertice: " + vertice + "  no se encuentra en el grafo");
        }
    }

    public int nroVertice(G vertice) {
        return listaDeVertices.indexOf(vertice);
    }

    public Iterable<G> getAdyacentesDelVertice(G vertice) {
        validarVertice(vertice);
        int nroDelVertice = nroVertice(vertice);
        List<AdyacenteConPeso> adyacentesDelVerticeXNro = listaDeAdyacencias.get(nroDelVertice);
        List<G> listaDeAdyacentesDelVertice = new ArrayList<>();
        for (AdyacenteConPeso adyConPeso : adyacentesDelVerticeXNro) {
            listaDeAdyacentesDelVertice.add(listaDeVertices.get(adyConPeso.getNroVertice()));
        }
        return listaDeAdyacentesDelVertice;
    }

    public double peso(G verticeOrigen, G verticeDestino) {
        if (!existeAdyacencia(verticeOrigen, verticeDestino)) {
            throw new IllegalArgumentException("no exite la dicha arista");
        }
        int nroVerticeOrigen = nroVertice(verticeOrigen);
        int nroVerticeDestino = nroVertice(verticeDestino);

        List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(nroVerticeOrigen);
        AdyacenteConPeso adyacenteSinPeso = new AdyacenteConPeso(nroVerticeDestino);
        int posicionDeAdyacencia = adyacentesDelOrigen.indexOf(adyacenteSinPeso);
        AdyacenteConPeso adyacencia = adyacentesDelOrigen.get(posicionDeAdyacencia);
        return adyacencia.getPeso();
    }
    
    public void setPeso(G verticeOrigen,G verticeDestino,double peso){
        if(existeAdyacencia(verticeOrigen,verticeDestino)){
            int nroVerticeOrigen=nroVertice(verticeOrigen);
            int nroVerticeDestino=nroVertice(verticeDestino);
          List<AdyacenteConPeso> adyacencia = listaDeAdyacencias.get(nroVerticeOrigen); 
            int nroAdyacencia = adyacencia.indexOf(nroVerticeDestino);
            AdyacenteConPeso adyConPeso = new AdyacenteConPeso(nroVerticeDestino,peso);
            adyacencia.set(nroAdyacencia, adyConPeso);
        }
    }
    public boolean existeAdyacencia(G verticeOrigen, G verticeDestino) {
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        int nroDelVerticeOrigen = nroVertice(verticeOrigen);
        int nroDelVerticeDestino = nroVertice(verticeDestino);
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = listaDeAdyacencias.get(nroDelVerticeOrigen);
        AdyacenteConPeso adySinPeso = new AdyacenteConPeso(nroDelVerticeDestino);
        return adyacentesDelVerticeOrigen.contains(adySinPeso);
    }

   
    public void insertarArista(G verticeOrigen, G verticeDestino, double peso) {// Grafo no pesado no lleva peso
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
            Collections.sort(adyacentesDelDestino);
        }

    }
public boolean hayCiclo(){
  Ciclo ciclo = new Ciclo(this);
return ciclo.hayCiclo();
}
    @Override
    public String toString() {
        String grafo = "";
        for (int i = 0; i < listaDeVertices.size(); i++) {
            grafo += "[" + listaDeVertices.get(i) + "] --> [";
            List<AdyacenteConPeso> adyacentes = listaDeAdyacencias.get(i);
            for (int j = 0; j < adyacentes.size(); j++) {
                grafo += listaDeVertices.get(adyacentes.get(j).getNroVertice()) + ",";
            }
            grafo += "]\n";
        }
        return grafo;
    }
public GrafoPesadoNoDirigido<G> kruskal(){
    Kruskal<G> kruskal = new Kruskal(this);
    return kruskal.getGrafo();
}

public GrafoPesadoNoDirigido<G> prim(G verticePartida){
    Prim<G> prim = new Prim(this,verticePartida);
    return prim.getGrafo();
}
}
