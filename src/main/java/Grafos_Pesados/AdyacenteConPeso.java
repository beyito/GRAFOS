/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos_Pesados;

/**
 *
 * @author cobu-
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {

    private int nroVertice;
    private double peso;

    public AdyacenteConPeso(int nroVertice) {
        this.nroVertice = nroVertice;
    }

    public AdyacenteConPeso(int nroVertice, double peso) {
        this.nroVertice = nroVertice;
        this.peso = peso;
    }

    public AdyacenteConPeso() {
    }

    public void setNroVertice(int nroVertice) {
        this.nroVertice = nroVertice;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getNroVertice() {
        return nroVertice;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public int compareTo(AdyacenteConPeso o) {
      // si puede dar nulo poner un exception   
        if(this.nroVertice > o.nroVertice){
            return 1;
        }
        
        if(this.nroVertice < o.nroVertice){
            return -1;
        }
        return 0;
    }
    
    @Override 
    public boolean equals(Object obj){
      AdyacenteConPeso o= (AdyacenteConPeso) obj;
      return this.compareTo(o) == 0;
    }
    
}
