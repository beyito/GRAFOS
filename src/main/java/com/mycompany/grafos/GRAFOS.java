/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.grafos;

import GrafoNoPesado.BFS;
import GrafoNoPesado.DFS;
import GrafoNoPesado.GrafoDirigido;
import GrafoNoPesado.GrafoNoDirigido;
import Grafos_Pesados.GrafoPesadoDirigido;
import Grafos_Pesados.Kruskal;
import Grafos_Pesados.GrafoPesadoNoDirigido;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cobu-
 */
public class GRAFOS {

    public static void main(String[] args) {
        
        GrafoPesadoDirigido<Integer> grafo = new GrafoPesadoDirigido <>();
        
        //grafo.insertarVertice(0);
       
        
        grafo.insertarVertice(1);
        grafo.insertarVertice(2);
        grafo.insertarVertice(3);
        grafo.insertarVertice(4);
        grafo.insertarVertice(5);
        grafo.insertarVertice(6);
        grafo.insertarVertice(7);
        grafo.insertarVertice(8);
        grafo.insertarVertice(9);
        grafo.insertarVertice(10);
        
      grafo.insertarArista(1,2,5);
      grafo.insertarArista(1,3,10);
      grafo.insertarArista(1,4,8);
      
      grafo.insertarArista(2,6,5);
      grafo.insertarArista(2,4,6);
      
      grafo.insertarArista(3,4,7);
      grafo.insertarArista(3,5,8);
      grafo.insertarArista(3,8,15);
      
      
      grafo.insertarArista(4,6,11);
      grafo.insertarArista(4,5,5);
      
      grafo.insertarArista(5,7,4);
      grafo.insertarArista(5,8,3);
      
      grafo.insertarArista(6,9,7);
      grafo.insertarArista(6,7,9);
      
      grafo.insertarArista(7,9,4);
      grafo.insertarArista(7,10,6);
      grafo.insertarArista(7,8,12);
      
      //grafo.insertarArista(8,7,12);
      grafo.insertarArista(8,10,12);
      
      grafo.insertarArista(9,10,7);
      
        
     /* grafo.insertarArista(0,2,10);
      grafo.insertarArista(0,4,60);
      grafo.insertarArista(0,5,100);
      
      grafo.insertarArista(1,3,50);
      grafo.insertarArista(1,4,15);
      
      grafo.insertarArista(2,1,5);
      
      grafo.insertarArista(3,0,80);
      grafo.insertarArista(3,5,20);
      
      grafo.insertarArista(4,5,20);
      
      grafo.insertarArista(5,1,40);
      grafo.insertarArista(5,2,70);
      
      */
     /* 
     FLOY WHARSHALL
      grafo.insertarVertice("V1");
      grafo.insertarVertice("V2");
      grafo.insertarVertice("V3");
      grafo.insertarVertice("V4");
      grafo.insertarVertice("V5");
      
      grafo.insertarArista("V1", "V2", 1);
      
      grafo.insertarArista("V2", "V4", 4);
      grafo.insertarArista("V2", "V5", 7);
      
      grafo.insertarArista("V3", "V1", 3);
      grafo.insertarArista("V3", "V2", 2);
      grafo.insertarArista("V3", "V5", 4);
      
      grafo.insertarArista("V4", "V1", 6);
      grafo.insertarArista("V4", "V5", 2);
      
      grafo.insertarArista("V5", "V4", 3);
      */
     /* FORD 
     grafo.insertarVertice("A");
     grafo.insertarVertice("B");
     grafo.insertarVertice("C");
     grafo.insertarVertice("D");
     grafo.insertarVertice("E");
     grafo.insertarVertice("F");
     grafo.insertarVertice("G");
     grafo.insertarVertice("H");
     
     grafo.insertarArista("G","A",20);
     grafo.insertarArista("G","B",40);
     grafo.insertarArista("G","C",10);
     
     grafo.insertarArista("A","D",50);
     
     grafo.insertarArista("B","A",20);
     grafo.insertarArista("B","F",21);
     
     grafo.insertarArista("C","E",20);
     grafo.insertarArista("C","F",30);
     
     grafo.insertarArista("D","E",15);
     grafo.insertarArista("D","H",70);
     
     grafo.insertarArista("E","D",45);
     grafo.insertarArista("E","H",40);
     
     grafo.insertarArista("F","H",10);
     */
        System.out.println(grafo);
       
        System.out.println(grafo.caminoCosotoMinimo(1, 7));        //System.out.println(grafo.prim(1));
        //System.out.println(grafo.caminoCosotoMinimo(0, 5));
       // System.out.println(grafo.cantidadIslas());
        /*boolean[][] miMatriz= grafo.wharsall();
        for (boolean[] fila : miMatriz) {
            System.out.println(Arrays.toString(fila));  // Imprime cada fila usando Arrays.toString
        }*/
    }
}
