/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author cobu-
 */
public class Floyd<G extends Comparable<G>> {

    Double[][] matrizAdyacencia;
    Integer[][] matrizPredecesor;
    GrafoPesadoDirigido<G> grafo;

    public Floyd(GrafoPesadoDirigido<G> elGrafo) {
        this.grafo = elGrafo;
        matrizAdyacencia = new Double[grafo.cantidadDeVertices()][grafo.cantidadDeVertices()];
        matrizPredecesor = new Integer[grafo.cantidadDeVertices()][grafo.cantidadDeVertices()];

        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            G verticeOrigen = grafo.listaDeVertices.get(i);
            for (int j = 0; j < grafo.cantidadDeVertices(); j++) {
                G verticeDestino = grafo.listaDeVertices.get(j);
                if (i == j) {
                    matrizAdyacencia[i][j] = 0.;
                    matrizPredecesor[i][j] = -1;

                } else if (!grafo.existeAdyacencia(verticeOrigen, verticeDestino)) {
                    matrizAdyacencia[i][j] = Double.POSITIVE_INFINITY;
                    matrizPredecesor[i][j] = j;
                } else {
                    matrizAdyacencia[i][j] = grafo.peso(verticeOrigen, verticeDestino);
                    matrizPredecesor[i][j] = j;
                }

            }
        }
        ejecutarFloyd();
    }

    public void ejecutarFloyd() {
        for (int pivote = 0; pivote < grafo.cantidadDeVertices(); pivote++) {
            for (int i = 0; i < grafo.cantidadDeVertices(); i++) {

                for (int j = 0; j < grafo.cantidadDeVertices(); j++) {
                    if (pivote != i && pivote != j) {
                        if (matrizAdyacencia[i][j] > matrizAdyacencia[i][pivote] + matrizAdyacencia[pivote][j]) {
                            matrizAdyacencia[i][j] = matrizAdyacencia[i][pivote] + matrizAdyacencia[pivote][j];
                            matrizPredecesor[i][j] = pivote;
                        }
                        // if (matrizPredecesor[i][j] > matrizPredecesor[i][pivote]+ matrizPredecesor[pivote][j]) {
                        //  System.out.println("hola");
                        //     matrizPredecesor[i][j]=pivote;
                        // }
                    }

                }
            }
        }
    }

    public Double[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public Integer[][] getMatrizPredecesor() {
        return matrizPredecesor;
    }
    public List<G> getCaminoMinimo(G verticeOrigen,G verticeDestino){
        Stack<G> camino=new Stack<>();
        int nroVerticeOrigen = grafo.nroVertice(verticeOrigen);
        int nroVerticeDestino = grafo.nroVertice(verticeDestino);
        camino.push(verticeDestino);
        while(nroVerticeDestino != matrizPredecesor[nroVerticeOrigen][nroVerticeDestino]){
             
            nroVerticeDestino=matrizPredecesor[nroVerticeOrigen][nroVerticeDestino];
            verticeDestino=grafo.listaDeVertices.get(nroVerticeDestino);
            camino.push(verticeDestino);
           
        }
        camino.push(verticeOrigen);
        List<G> caminoMinimo = new ArrayList<>();
        int tamaño = camino.size();
        for (int i=0;i<tamaño;i++){
         caminoMinimo.add(camino.pop());
        }
        return caminoMinimo;
    }
    
}
