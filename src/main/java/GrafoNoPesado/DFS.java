/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoNoPesado;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cobu-
 */
public class DFS<G extends Comparable<G>> {
// IMPLEMENTAR GRAFO Y DIGRAFO

    private final GrafoNoDirigido<G> elGrafo;
    private final ControlMarcados controlMarcados;
    private final List<G> recorrido;

    public DFS(GrafoNoDirigido<G> unGrafo, G verticeDePartida) {
        elGrafo = unGrafo;
        controlMarcados = new ControlMarcados(elGrafo.cantidadDeVertices());
        recorrido = new ArrayList<>();
        ejecutarDFS(verticeDePartida);
    }

    public void ejecutarDFS(G verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);

        controlMarcados.marcarVertice(elGrafo.nroVertice(verticeEnTurno));
        recorrido.add(verticeEnTurno);
        Iterable<G> adyacentesDelVertice = elGrafo.getAdyacentesDelVertice(verticeEnTurno);
        for (G adyacente : adyacentesDelVertice) {
            int nroDelAdyacente = elGrafo.nroVertice(adyacente);
            if (!controlMarcados.estaMarcadoVertice(nroDelAdyacente)) {
                ejecutarDFS(adyacente);
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
