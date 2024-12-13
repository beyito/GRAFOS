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
public class Ciclo<G extends Comparable<G>> {

    private final GrafoPesadoNoDirigido<G> elGrafo;
    private final ControlMarcados controlMarcados;
    private final List<G> recorrido;
    private boolean hayCiclo;

    public Ciclo(GrafoPesadoNoDirigido<G> unGrafo) {
        elGrafo = unGrafo;
        hayCiclo = false;
        controlMarcados = new ControlMarcados(elGrafo.cantidadDeVertices());
        recorrido = new ArrayList<>();
        verificarCiclo();
    }

    public void verificarCiclo() {
        while (!controlMarcados.estanTodosLosVerticesMarcados()) {
            G verticeInicial = buscarVerticeNoMarcado();
           
            elGrafo.validarVertice(verticeInicial);
           
    
            Stack<G> pila = new Stack<>();
            Stack<G> padres = new Stack<>(); // Para rastrear el padre del vértice en turno

            pila.push(verticeInicial);
            padres.push(null); // El nodo inicial no tiene padre
            controlMarcados.marcarVertice(elGrafo.nroVertice(verticeInicial));

            while (!pila.isEmpty()) {
                G verticeActual = pila.pop();
                G padreActual = padres.pop(); // Obtener el padre correspondiente

                Iterable<G> adyacentes = elGrafo.getAdyacentesDelVertice(verticeActual);

                for (G adyacente : adyacentes) {
                    int nroAdyacente = elGrafo.nroVertice(adyacente);

                    if (!controlMarcados.estaMarcadoVertice(nroAdyacente)) {
                        pila.push(adyacente);
                        padres.push(verticeActual); // Registrar el vértice actual como padre
                        controlMarcados.marcarVertice(nroAdyacente);
                    } else if (!adyacente.equals(padreActual)) {
                        hayCiclo = true;
                    }
                }
            }
            // Si terminamos el DFS sin encontrar un ciclo
        }

    }

    public boolean hayCiclo() {
        return hayCiclo;
    }

    public G buscarVerticeNoMarcado() {
        for (int i = 0; i < elGrafo.cantidadDeVertices(); i++) {
            if (!controlMarcados.estaMarcadoVertice(i)) {
                return elGrafo.listaDeVertices.get(i);
            }
        }
        return null;
    }

    public List<G> getRecorrido() {
        return recorrido;
    }

    public boolean seVisitoVertice(G vertice) {
        int nroVertice = elGrafo.nroVertice(vertice);
        return controlMarcados.estaMarcadoVertice(nroVertice);
    }

    public boolean seVisitoTodosLosVertices() {
        return controlMarcados.estanTodosLosVerticesMarcados();
    }
}
