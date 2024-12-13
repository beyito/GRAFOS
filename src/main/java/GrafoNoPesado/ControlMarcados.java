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
public class ControlMarcados {

    private final List<Boolean> listaDeMarcados;

    public ControlMarcados(int nroDeVertices) {
        this.listaDeMarcados = new ArrayList<Boolean>();
        for (int i = 0; i < nroDeVertices; i++) {
            listaDeMarcados.add(Boolean.FALSE);
        }
    }

    public void desmarcarTodos() {
        for (int i = 0; i < listaDeMarcados.size(); i++) {
            listaDeMarcados.set(i, Boolean.FALSE);
        }
    }

    public boolean estaMarcadoVertice(int nroVertice) {
        return listaDeMarcados.get(nroVertice);
    }

    public void marcarVertice(int nroVertice) {
        listaDeMarcados.set(nroVertice, true);
    }

    public void desmarcarVertice(int nroVertice) {
        listaDeMarcados.set(nroVertice, false);
    }

    public boolean estanTodosLosVerticesMarcados() {
       return !listaDeMarcados.contains(false);
    }

    public int nroVerticeNoMarcado(){
        return listaDeMarcados.indexOf(false);
    }
}
