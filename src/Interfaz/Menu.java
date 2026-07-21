package Interfaz;

import java.util.Scanner;
import java.util.regex.Pattern;
import Clases.*;
import Estructuras.Lista.Lista;


public class Menu {

    private Sistema sistemaEscapeRoom;
    private Scanner sc;

    public Menu(Sistema s){
        this.sistemaEscapeRoom = s;
        this.sc = new Scanner(System.in);
    }

    public void iniciar(){
        int opcion;
        do{
            Opciones();
            System.out.print("Ingresa el opcion:");
            opcion = sc.nextInt();
            if(opcion < 0 || opcion > 7){
                System.out.println("Opcion no valida");
            }else {
                operaciones(opcion);
            }
        }while(opcion != 7);
    }

    public void Opciones(){
        System.out.println("\n=========================================");
        System.out.println("|   MENU DE OPCIONES                     |");
        System.out.println("=========================================");
        System.out.println("[1] Cargar Datos Iniciales");
        System.out.println("[2] Alta,Bajas,Modificaciones");
        System.out.println("[3] Consulta de Habitaciones");
        System.out.println("[4] Consulta de Desafios");
        System.out.println("[5] Consulta de Equipos");
        System.out.println("[6] Consulta General");
        System.out.println("[7] Salir");
    }

    public void ABM(){
        System.out.println("=========================================");
        System.out.println("Habitaciones");
        System.out.println("[1] Alta de Habitaciones");
        System.out.println("[2] Baja de Habitaciones");
        System.out.println("[3] Modificacion de Habitaciones");
        System.out.println("=========================================");
        System.out.println("Desafios");
        System.out.println("[4] Alta de Desafios");
        System.out.println("[5] Baja de Desafios");
        System.out.println("[6] Modificacion de Desafios");
        System.out.println("=========================================");
        System.out.println("Equipos");
        System.out.println("[7] Alta de Equipos");
        System.out.println("[8] Baja de Equipos");
        System.out.println("[9] Modificacion de Equipos");
        System.out.println("=========================================");
    }

    public void opcionesHabitaciones(){
        System.out.println("[1] Mostrar una Habitacion");
        System.out.println("[2] Mostrar Habitaciones Coniguas");
        System.out.println("[3] Es Posible LLegar");
        System.out.println("[4] Minimo Puntaje");
        System.out.println("[5] Sin Pasar Por");
        System.out.println("[6] Salir");
    }

    public void operaciones(int opciones){
        switch(opciones){
            case 1:
                sistemaEscapeRoom.cargaInicial();
                break;
            case 2:
                //sistemaEscapeRoom.ABM();
                break;
            case 3:
                int opcion;
                do{
                    opcionesHabitaciones();
                    System.out.print("Ingrese una opcion:");
                    opcion = sc.nextInt();
                    if(opcion < 1 || opcion > 6){
                        System.out.println("Opcion no valida");
                    }else{
                        sistemaEscapeRoom.consultaHabitaciones(opcion);
                    }
                }while(opcion != 6);
                break;
            case 6:
                System.out.println(sistemaEscapeRoom.mostrarSistema());
                break;
        }
    }
}
