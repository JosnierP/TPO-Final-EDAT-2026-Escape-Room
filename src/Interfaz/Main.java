package Interfaz;

import Clases.Sistema;

public class Main {

    public static void main(String[] args) {

        //Inicializador del sistema
        Sistema miSistema = new Sistema();

        Menu menu = new Menu(miSistema);

        menu.iniciar();
    }
}
