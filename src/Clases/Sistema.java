package Clases;
import Estructuras.ArbolAVL.ArbolAVL;
import Estructuras.Grafos.GrafoEtiquetado;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Sistema {

    ArbolAVL arbolHabitaciones = new ArbolAVL();
    ArbolAVL arbolDesafios = new ArbolAVL();
    HashMap<String,Equipo> mapeoEquipos = new HashMap<>();
    HashMap<Equipo,Desafio> mapeoDesafiosResueltosXEquipo = new HashMap<>();
    GrafoEtiquetado casaEscapeRoom = new GrafoEtiquetado();;
    ArrayList<Puerta> listaPuertas = new  ArrayList<>();

    //Metodos cargadores de archivos .txt
    public void cargarHabitaciones(){
        String linea = null;
        try{
            FileReader lectura = new FileReader("habitaciones.txt");
            BufferedReader buffer = new BufferedReader(lectura);
            while((linea = buffer.readLine()) != null){
                String[] datos = linea.split(";");
                int codigo = Integer.parseInt(datos[0]);
                String roomName = datos[1];
                int floor = Integer.parseInt(datos[2]);
                double squareMeter = Double.parseDouble(datos[3]);
                boolean exit = Boolean.parseBoolean(datos[4]);
                Habitacion hab = new Habitacion(codigo,roomName,floor,squareMeter,exit);
                arbolHabitaciones.insertar(hab);
                casaEscapeRoom.insertarVertice(hab);
            }
            buffer.close();
        }catch(FileNotFoundException ex){
            System.out.println("Error: Archivo no encontrado" + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException e){
            System.out.println("Error: Leyendo el archivo" + e.getMessage());
            e.printStackTrace();
        }
        conectarHabitaciones();
    }

    public void cargarDesafios(){
        String linea = null;
        try{
            FileReader lectura = new FileReader("desafios.txt");
            BufferedReader buffer = new BufferedReader(lectura);
            while((linea = buffer.readLine()) != null){
                String[] datos = linea.split(";");
                int scoredAwared = Integer.parseInt(datos[0]);
                String name = datos[1];
                String type = datos[2];
                Desafio desafio = new Desafio(scoredAwared,name,type);
                arbolDesafios.insertar(desafio);
            }
            buffer.close();
        }catch(FileNotFoundException ex){
            System.out.println("Error: Archivo no encontrado" + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException e){
            System.out.println("Error: Leyendo el archivo" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cargarEquipos(){
        String linea = null;
        try{
            FileReader lectura = new FileReader("equipos.txt");
            BufferedReader buffer = new BufferedReader(lectura);
            while((linea = buffer.readLine()) != null){
                String[] datos = linea.split(";");
                String nombre = datos[0];
                int pointsRequires = Integer.parseInt(datos[1]);
                int currentPoints= Integer.parseInt(datos[2]);
                int currentRoom = Integer.parseInt(datos[3]);
                Equipo equipo = new Equipo(nombre,pointsRequires,currentPoints,currentRoom);
                mapeoEquipos.put(nombre,equipo);
            }
            buffer.close();
        }catch(FileNotFoundException ex){
            System.out.println("Error: Archivo no encontrado" + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException e){
            System.out.println("Error: Leyendo el archivo" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cargarPuertas(){
        String linea = null;
        try{
            FileReader lectura = new FileReader("puertas.txt");
            BufferedReader buffer = new BufferedReader(lectura);
            while((linea = buffer.readLine()) != null){
                String[] datos = linea.split(";");
                int numberDoor = Integer.parseInt(datos[0]);
                int entranceDoor = Integer.parseInt(datos[1]);
                int exitDoor = Integer.parseInt(datos[2]);
                Puerta puerta = new Puerta(numberDoor,entranceDoor,exitDoor);
                listaPuertas.add(puerta);
            }
            buffer.close();
        }catch(FileNotFoundException ex){
            System.out.println("Error: Archivo no encontrado" + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException e){
            System.out.println("Error: Leyendo el archivo" + e.getMessage());
            e.printStackTrace();
        }
    }


    //Metodo conector del grafo
    public void conectarHabitaciones(){
        for(Puerta puerta : listaPuertas){
            casaEscapeRoom.insertarArco(puerta.getEntranceDoor(),puerta.getExitDoor(),puerta.getPoint());
        }
    }
}
