/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoNoPesado;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author cobu-
 * @param <G>

 */
public class BFS<G extends Comparable<G>> {
// IMPLEMENTAR GRAFO Y DIGRAFO
    private final GrafoNoDirigido<G> elGrafo;
    private final ControlMarcados controlMarcados;
    private final List<G> recorrido;

    public BFS(GrafoNoDirigido<G> unGrafo, G verticeDePartida) {
        elGrafo = unGrafo;
        controlMarcados = new ControlMarcados(elGrafo.cantidadDeVertices());
        recorrido = new ArrayList<>();
        ejecutarBFS(verticeDePartida);
    }

    public void ejecutarBFS(G verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        Queue<G> colaDeVertices=new LinkedList<>();
        colaDeVertices.add(verticeEnTurno);
        controlMarcados.marcarVertice(elGrafo.nroVertice(verticeEnTurno));
        
        while(!colaDeVertices.isEmpty()){
            
            G vertice= colaDeVertices.poll();
            recorrido.add(vertice);
            Iterable<G> adyacentesDelVertice=elGrafo.getAdyacentesDelVertice(vertice);
            for(G adyacente: adyacentesDelVertice){
                int nroDelAdyacente = elGrafo.nroVertice(adyacente);
                if(!controlMarcados.estaMarcadoVertice(nroDelAdyacente)){
                    colaDeVertices.add(adyacente);
                    controlMarcados.marcarVertice(nroDelAdyacente);
                }
            }
            
        }
    }
    
    public List<G> getRecorrido(){
        return recorrido;
    }
    public boolean seVisitoVertice(G vertice){
        int nroVertice= elGrafo.nroVertice(vertice);
        return controlMarcados.estaMarcadoVertice(nroVertice);
    }
    public boolean seVisitoTodosLosVertices(){
        return controlMarcados.estanTodosLosVerticesMarcados();
    }

}
