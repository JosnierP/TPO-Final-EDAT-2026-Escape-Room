package Clases;
import Estructuras.ArbolAVL.ArbolAVL;
import Estructuras.Grafos.GrafoEtiquetado;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Sistema {
    private GrafoEtiquetado grafo;
    private ArbolAVL habitaciones;
    private ArbolAVL desafios;
    private HashMap<String,Equipo> equipos;

    public Sistema(){
        grafo = new GrafoEtiquetado();
        desafios = new ArbolAVL();
        equipos = new HashMap<>();
    }
    //Alta-Bajas-Modificaciones
    public void altaHabitaciones(){}

    public void bajaHabitaciones(){}

    public void modificacionHabitaciones(){}

    public void altaDesafios(){}

    public void bajaDesafios(){}

    public void modificacionDesafios(){}

    public void altaEquipos(){}

    public void bajaEquipos(){}

    public void modificacionEquipos(){}

    //Consultas sobre Habitaciones
    public String mostrarHabitaciones(int codeRoom){
        String room = "";
        return room;
    }

    public Lista mostrarHabitacionesContiguas(int codeRoom){
        Lista listaHab = new Lista();
        return listaHab;
    }

    public boolean esPosibleLlegar(int codeRoom1, int codeRoom2, int k){
        boolean posible = false;
        return posible;
    }

    public int minimoPuntaje(int codeRoom1, int codeRoom2){
        int minimoPuntaje = 0;
        return minimoPuntaje;
    }

    public Lista sinPasarPor(int codeRoom1, int codeRoom2, int codeRoom3, int P){
        Lista listaPasar = new Lista();
        return  listaPasar;
    }

    //Consulta General
    public String mostrarSistema(){
        String sistema = "";
        return sistema;
    }
}