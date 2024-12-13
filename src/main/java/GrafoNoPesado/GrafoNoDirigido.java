/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoNoPesado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cobu-
 * @param <G>
 */
public class GrafoNoDirigido<G extends Comparable<G>>  {

    protected List<G> listaDeVertices;
    protected List<List<Integer>> listaDeAdyacencias;
    public static final int NRO_DE_VERTICE_INVALIDO = -1;
    
    public GrafoNoDirigido(){
        listaDeVertices= new ArrayList<>();
        listaDeAdyacencias = new ArrayList();
    }
    
    public GrafoNoDirigido(Iterable<G> vertices) {
        this();
        for (G vertice : vertices) {
            insertarVertice(vertice);
        }
    }
    
     public int grado(G vertice){
        int nroVertice=nroVertice(vertice);
        List<Integer> adyacencias = listaDeAdyacencias.get(nroVertice);
        return adyacencias.size();
    }
   public Iterable<G> getVertices(){
       return listaDeVertices;
   }
    public void insertarVertice(G vertice){
        if(listaDeVertices.contains(vertice)){
            throw new IllegalArgumentException("ya existe ese vertice");
        }
      listaDeVertices.add(vertice);
      listaDeAdyacencias.add(new ArrayList<>());
      Collections.sort(listaDeVertices);
    }
    
    
    
    public Iterable<G> getAdyacentesDelVertice(G vertice){
        
        validarVertice(vertice);
        
        int nroDelVertice= nroVertice(vertice);
        
        List<Integer> adyacentesDelVerticeXNro = listaDeAdyacencias.get(nroDelVertice);
        
        List<G> listaDeAdyacentesDelVertice = new ArrayList<>();
        
        for(Integer adyConPeso: adyacentesDelVerticeXNro){
            listaDeAdyacentesDelVertice.add(listaDeVertices.get(adyConPeso));
        }
        return listaDeAdyacentesDelVertice;
    }
    
   
    
    public boolean existeAdyacencia(G verticeOrigen,G verticeDestino){
      validarVertice(verticeOrigen);
      validarVertice(verticeDestino);
      
      int nroDelVerticeOrigen = nroVertice(verticeOrigen);
      int nroDelVerticeDestino = nroVertice(verticeDestino);
      
      List<Integer> adyacentesDelVerticeOrigen = listaDeAdyacencias.get(nroDelVerticeOrigen);
      
     
      return adyacentesDelVerticeOrigen.contains(nroDelVerticeDestino);
    }
    
    
    
    public void insertarArista(G verticeOrigen,G verticeDestino){// Grafo no pesado no lleva peso
       if(existeAdyacencia(verticeOrigen,verticeDestino)){
         throw new IllegalArgumentException("Ya existe esa arista");  
       }
       
       int nroDelVerticeOrigen = nroVertice(verticeOrigen);
       int nroDelVerticeDestino = nroVertice(verticeDestino);
       //List<Integer> para GRAFO NO PESADO
       
       List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(nroDelVerticeOrigen);
       
       
       
       adyacentesDelOrigen.add(nroDelVerticeDestino);
       
       Collections.sort(adyacentesDelOrigen);
       
       if(nroDelVerticeOrigen != nroDelVerticeDestino){
           
         List<Integer> adyacentesDelDestino = listaDeAdyacencias.get(nroDelVerticeDestino);
         
         adyacentesDelDestino.add(nroDelVerticeOrigen);
         Collections.sort(adyacentesDelDestino);
         
       }
       
        
    }

    
    public int cantidadDeVertices(){
        return listaDeVertices.size();
    }
   
    
    public int nroVertice(G vertice){
        return listaDeVertices.indexOf(vertice);
    }
  
    public void eliminarVertice(G verticeAEliminar){
        validarVertice(verticeAEliminar);
        int nroVerticeAEliminar=nroVertice(verticeAEliminar);
        listaDeVertices.remove(verticeAEliminar);
        listaDeAdyacencias.remove(nroVerticeAEliminar);
        
        for(List<Integer> listaAdyacenciaActual:listaDeAdyacencias){
            
            int posVerticeAEliminar=listaAdyacenciaActual.indexOf(verticeAEliminar);
            if(posVerticeAEliminar >=0 ){
                listaAdyacenciaActual.remove(posVerticeAEliminar);
            }
            
            for(int i=0;i<listaAdyacenciaActual.size();i++){
                if(listaAdyacenciaActual.get(i) > nroVerticeAEliminar){
                    listaAdyacenciaActual.set(i, i-1);
                }
            }
        }
    }
    
    public void eliminarArista(G verticeOrigen,G verticeDestino){
        if(existeAdyacencia(verticeOrigen,verticeDestino)){
            
            int nroVerticeOrigen=nroVertice(verticeOrigen);
            int nroVerticeDestino=nroVertice(verticeDestino);
              List<Integer> listaAdyacenciaActual = listaDeAdyacencias.get(nroVerticeOrigen);
            int posVerticeAEliminar=listaAdyacenciaActual.indexOf(nroVerticeDestino);
              listaAdyacenciaActual.remove(posVerticeAEliminar);
            List<Integer> listaAdyacenciaDestino = listaDeAdyacencias.get(nroVerticeDestino);
              posVerticeAEliminar=listaAdyacenciaDestino.indexOf(nroVerticeOrigen);
            listaAdyacenciaDestino.remove(posVerticeAEliminar);
        }
    }
    public void validarVertice(G vertice){
     if (!listaDeVertices.contains(vertice))
         throw new IllegalArgumentException("el vertice: "+vertice+"  no se encuentra en el grafo");
    }
    
    public List<G> recorridoBFS(G verticePartida){
        BFS<G> recorrido = new BFS<> (this,verticePartida);
        return recorrido.getRecorrido();
    }
    
    public List<G> recorridoDFS(G verticePartida){
        DFS<G> recorrido = new DFS<> (this,verticePartida);
        return recorrido.getRecorrido();
    }
    
    /*public int cantidadIslas(){
    int islas=0;
    ControlMarcados marcados= new ControlMarcados(cantidadDeVertices());
    
    while(!marcados.estanTodosLosVerticesMarcados()){
       int nroVerticeActual = marcados.nroVerticeNoMarcado();
       G verticeActual=listaDeVertices.get(nroVerticeActual);
      List<G> recorrido = recorridoDFS(verticeActual);
      for(G verticeMarcado : recorrido){
          nroVerticeActual = nroVertice(verticeMarcado);
          marcados.marcarVertice(nroVerticeActual);
      }
      islas++;
     }
    return islas;
    }
    */
    
    public int cantidadIslas(){
        int islas=1;
        G verticeInicial=listaDeVertices.get(0);
         DFS<G> recorrido = new DFS<>(this,listaDeVertices.get(0));
        while(!recorrido.seVisitoTodosLosVertices()){  
         
            for(G verticeActual : listaDeVertices){
              if (!recorrido.seVisitoVertice(verticeActual)){
                  recorrido.ejecutarDFS(verticeActual);
                  break;
              }
            }
            islas++;
        
        }
        return islas;
    }
    
    @Override
    public String toString(){
     String grafo="";
     for(int i=0; i < listaDeVertices.size();i++){
       grafo+="["+listaDeVertices.get(i)+"] --> [";
       List<Integer> adyacentes = listaDeAdyacencias.get(i);
       for(int j=0;j<adyacentes.size();j++){
        grafo += listaDeVertices.get(adyacentes.get(j))+",";         
       }
       grafo+="]\n";
     }
     return grafo;
    }
}
