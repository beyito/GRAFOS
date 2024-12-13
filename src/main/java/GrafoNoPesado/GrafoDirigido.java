/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoNoPesado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author cobu-
 * @param <G>
 */
public class GrafoDirigido<G extends Comparable<G>> extends GrafoNoDirigido<G> {

    
    
    
    @Override
    public void insertarArista(G verticeOrigen, G verticeDestino) {
        if (existeAdyacencia(verticeOrigen, verticeDestino)) {
            throw new IllegalArgumentException("Ya existe esa arista");
        }
        int nroDelVerticeOrigen = nroVertice(verticeOrigen);
        int nroDelVerticeDestino = nroVertice(verticeDestino);
        //List<Integer> para GRAFO NO PESADO
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(nroDelVerticeOrigen);
        adyacentesDelOrigen.add(nroDelVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public void eliminarArista(G verticeOrigen, G verticeDestino) {
        if (existeAdyacencia(verticeOrigen, verticeDestino)) {

            int nroVerticeOrigen = nroVertice(verticeOrigen);
            int nroVerticeDestino = nroVertice(verticeDestino);
            List<Integer> listaAdyacenciaActual = listaDeAdyacencias.get(nroVerticeOrigen);
            int posVerticeDestino = listaAdyacenciaActual.indexOf(nroVerticeDestino);
            listaAdyacenciaActual.remove(posVerticeDestino);
        }
    }

    public boolean[][] wharsall() {
        int nroVertices = cantidadDeVertices();
        boolean[][] matrizDeCaminos = matrizDeAdyacencia();
        for (int pivote = 0; pivote < nroVertices; pivote++) {

            for (int i = 0; i < nroVertices; i++) {

                for (int j = 0; j < nroVertices; j++) {

                    if (i != pivote && j != pivote) {
                        matrizDeCaminos[i][j] = matrizDeCaminos[i][j] || (matrizDeCaminos[i][pivote] && matrizDeCaminos[pivote][j]);
                    }
                }
            }
        }

        return matrizDeCaminos;
    }

    public int gradoEntrada(G vertice){
        int nroVertice=nroVertice(vertice);
        int cantidad=0;
        for(List<Integer> listaAdyacencia : listaDeAdyacencias){
            if (listaAdyacencia.contains(nroVertice))
                cantidad++;
        }
        return cantidad;
    }
    
    public int gradoSalida(G vertice){
        int nroVertice=nroVertice(vertice);
        List<Integer> adyacencias = listaDeAdyacencias.get(nroVertice);
        return adyacencias.size();
    }
    public boolean esConexo(){
        for(G verticeActual:listaDeVertices){
            List<G> recorrido=recorridoDFS(verticeActual);
            for(G verticeAComprobar:listaDeVertices){
                if(verticeActual != verticeAComprobar){
                    if (!recorrido.contains(verticeAComprobar))
                        return false;
                }
            }
        } 
        return true;
    }
    public List<G> ordenamientoTopologico(){
        
        List<Integer> listaDeGrados = new ArrayList<>();
        List<G> recorrido = new ArrayList<>();
        Queue<G> colaVertices = new LinkedList<>();
        
        for(G verticeActual:listaDeVertices) {            
            if(gradoEntrada(verticeActual)==0){
                colaVertices.add(verticeActual);
            }
            listaDeGrados.add(gradoEntrada(verticeActual));
        }
        
        while(!colaVertices.isEmpty()){
          G verticeActual=colaVertices.poll();
          recorrido.add(verticeActual);
            int nroVertice=nroVertice(verticeActual);
            List<Integer> adyacenciaActual = listaDeAdyacencias.get(nroVertice);
            for(int nroAdyacencia:adyacenciaActual){
                int nuevoGradoEntrada=listaDeGrados.get(nroAdyacencia)-1;
                if(nuevoGradoEntrada==0){
                    colaVertices.add(listaDeVertices.get(nroAdyacencia));
                }
                listaDeGrados.set(nroAdyacencia, nuevoGradoEntrada);
            }
            
        }
        return recorrido;
    }
    
    public boolean[][] matrizDeAdyacencia() {
        int nroVertices = cantidadDeVertices();
        boolean[][] matrizDeAdyacencia = new boolean[nroVertices][nroVertices];

        for (int fila = 0; fila < nroVertices; fila++) {
            G verticeFila = listaDeVertices.get(fila);
            for (int columna = 0; columna < nroVertices; columna++) {
                G verticeColumna = listaDeVertices.get(columna);
                matrizDeAdyacencia[fila][columna] = existeAdyacencia(verticeFila, verticeColumna);
            }
        }
        return matrizDeAdyacencia;
    }
    
    public List<G> recBFS(G verticePartida){
        
    List<Boolean> listaMarcados = new ArrayList<>();   
    List<G> recorrido = new ArrayList<>();
    for(int i=0;i<cantidadDeVertices();i++){
        listaMarcados.add(false);
    }
   
    
    Queue<Integer> colaVertices =  new LinkedList<>();
    int nroVertice=nroVertice(verticePartida);
    colaVertices.add(nroVertice);
    while(!colaVertices.isEmpty()){
        List<Integer> adyacentes = listaDeAdyacencias.get(nroVertice);
        
    }
    
    return recorrido;
    }
}
