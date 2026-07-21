package Clases;
import Estructuras.ArbolAVL.ArbolAVL;
import Estructuras.Grafos.GrafoEtiquetado;
import Estructuras.Lista.Lista;
import java.io.*;
import java.util.*;


public class Sistema {
    private GrafoEtiquetado grafo;
    private ArbolAVL arbolHabitaciones;
    private ArbolAVL arbolDesafios;
    private Map<String,Equipo> equipos;
    private Scanner sc;

    public Sistema(){
        grafo = new GrafoEtiquetado();
        arbolHabitaciones = new ArbolAVL();
        arbolDesafios = new ArbolAVL();
        equipos = new HashMap<>();
        sc = new Scanner(System.in);
    }

    public void cargaInicial(){
        String linea = null;
        try {
            FileReader archivo = new FileReader("datos.txt");
            BufferedReader buffer = new BufferedReader(archivo);
            while ((linea = buffer.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linea,";");
                switch(st.nextToken()){
                    case "H":
                        int codeRoom = Integer.parseInt(st.nextToken());
                        String nameRoom = st.nextToken();
                        int floorRoom = Integer.parseInt(st.nextToken());
                        double sizeRoom = Double.parseDouble(st.nextToken());
                        boolean exit = Boolean.parseBoolean(st.nextToken());
                        Habitacion hab = new Habitacion(codeRoom,nameRoom,floorRoom,sizeRoom,exit);
                        arbolHabitaciones.insertar(codeRoom,hab);
                        grafo.insertarVertice(hab);
                        break;
                    case "E":
                        String nameTeam = st.nextToken();
                        int requieredPoints = Integer.parseInt(st.nextToken());
                        int currentRoom = Integer.parseInt(st.nextToken());
                        int currentPoints = Integer.parseInt(st.nextToken());
                        Equipo team = new Equipo(nameTeam,requieredPoints,currentRoom,currentPoints);
                        equipos.put(nameTeam,team);
                        break;
                    case "D":
                        break;
                    case "P":
                        int entranceDoor = Integer.parseInt(st.nextToken());
                        int exitDoor = Integer.parseInt(st.nextToken());
                        int pointsRequired = Integer.parseInt(st.nextToken());
                        Lista listaHab = arbolHabitaciones.listar();
                        Habitacion EntraceRoom = buscarHabitacion(entranceDoor,listaHab);
                        Habitacion ExitRoom = buscarHabitacion(exitDoor,listaHab);
                        grafo.insertarArco(EntraceRoom,ExitRoom,pointsRequired);
                        grafo.insertarArco(ExitRoom,EntraceRoom,pointsRequired);
                        break;
                }

            }
        }catch(FileNotFoundException ex){
            System.out.println("Archivo no encontrado" + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException e){
            System.out.println("Error al leer el archivo" + e.getMessage());
            e.printStackTrace();
        }
    }

    //Alta-Bajas-Modificaciones

    //Habitaciones
    public void altaHabitaciones(){
        System.out.print("Ingrese el codigo de la Habitacion:");
        int codeRoom = sc.nextInt();
        if(!arbolHabitaciones.pertenece(codeRoom)){
            System.out.print("Ingrese el nombre de la Habitacion:");
            String nameRoom = sc.nextLine();
            Lista listaHabitaciones = grafo.listarEnProfundidad();
            if(!nombreValido(nameRoom,listaHabitaciones)){
                System.out.print("Ingrese el piso de la Habitacion:");
                int floorRoom = sc.nextInt();
                System.out.print("Ingrese los metros cuadrados:");
                int squaredMeters = sc.nextInt();
                System.out.print("Posee salida?S/N:");
                char exit = sc.next().charAt(0);
                boolean salida = exit == 'S' || exit == 's';
                Habitacion hab = new Habitacion(codeRoom,nameRoom,floorRoom,squaredMeters,salida);
                arbolHabitaciones.insertar(codeRoom,hab);
                registrarLog("Se creo la habitacion " + codeRoom);
                grafo.insertarVertice(hab);
            }
        }else{
            System.out.println("La habitacion ya existe");
        }
    }

    public void bajaHabitaciones(){
        System.out.print("Ingrese el codigo de la Habitacion:");
        int codeRoom = sc.nextInt();
        if(arbolHabitaciones.pertenece(codeRoom)){
            if(!equiposEnHabitacion(codeRoom,equipos)) {
                Lista listaHab = grafo.listarEnProfundidad();
                Habitacion hab = buscarHabitacion(codeRoom, listaHab);
                grafo.eliminarVertice(hab);
                arbolHabitaciones.eliminar(codeRoom);
                registrarLog("Se elimino la habitacion " + codeRoom);
            }else{
                System.out.println("Hay un equipo en la habitacion");
            }
        }else{
            System.out.println("La habitacion no se encuentra en la casa");
        }
    }

    public void modificacionHabitaciones(){
        System.out.print("Ingrese el codigo de la habitacion:");
        int codeRoom = sc.nextInt();
        if(arbolHabitaciones.pertenece(codeRoom)){
            Lista listaHab = grafo.listarEnProfundidad();
            Habitacion hab = buscarHabitacion(codeRoom,listaHab);
            System.out.print("Ingrese el nuevo nombre de la Habitacion:");
            String nameRoom = sc.nextLine();
            if(!nombreValido(nameRoom,listaHab)){
                hab.setName(nameRoom);
                System.out.print("Ingrese el nuevo piso:");
                int floorRoom = sc.nextInt();
                hab.setFloor(floorRoom);
                System.out.print("Ingrese los nuevos metros cuadrados:");
                double squaredMeters = sc.nextDouble();
                hab.setSquaredMeters(squaredMeters);
                System.out.print("Posee Salida?S/N:");
                char salida = sc.next().charAt(0);
                boolean exit = salida == 'S' || salida == 's';
                hab.setExit(exit);
                registrarLog("Se modifico la habitacion " + codeRoom);
            }else{
                System.out.println("El nombre no esta disponible");
            }
        }else{
            System.out.println("La habitacion no se encuentra en la casa");
        }
    }

    //Desafios
    public void altaDesafios(){}

    public void bajaDesafios(){}

    public void modificacionDesafios(){}

    //Equipos
    public void altaEquipos(){
        System.out.print("Ingrese el nombre del equipo: ");
        String name = sc.nextLine();
        sc.nextLine();
        if(equipos.containsKey(name)){
            System.out.println("El equipo ya se encuentra registrado");
        }else{
            System.out.print("Ingrese el puntaje requerido para salir:");
            int pointRequired = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese la habitacion Inicial:");
            int entranceDoor = sc.nextInt();
            if(validarEquipoEnHabitacion(entranceDoor,equipos)){
                Equipo e = new Equipo(name,pointRequired,entranceDoor,0);
                equipos.put(name,e);
                registrarLog("Se creo el equipo " + name);
            }
        }
    }

    public void bajaEquipos(){
        System.out.print("Ingrese el nombre del equipo:");
        String name = sc.nextLine();
        if(equipos.containsKey(name)){
            equipos.remove(name);
            registrarLog("Se borra el equipo " + name);
        }else{
            System.out.println("No existe equipo con ese nombre");
        }
    }

    public void modificacionEquipos(){
        System.out.print("Ingrese el nombre del equipo:");
        String name = sc.nextLine();
        if(equipos.containsKey(name)){
            System.out.print("Ingrese el nuevo nombre del equipo:");
            String newNameTeam = sc.nextLine();
            newNameTeam = newNameTeam.toLowerCase();
            if(!equipos.containsKey(newNameTeam)){
                Equipo e = equipos.get(name);
                e.setNameTeam(newNameTeam);
                System.out.print("Ingrese los nuevos puntos requeridos:");
                int pointsRequiered = sc.nextInt();
                e.setRequieredPoints(pointsRequiered);
                System.out.print("Ingrese la habitacion Inicial:");
                int newCodeRoom = sc.nextInt();
                if(arbolHabitaciones.pertenece(newCodeRoom)){
                    e.setCurrentRoom(newCodeRoom);
                    System.out.print("Ingrese los puntos actuales:");
                    int currentPoint = sc.nextInt();
                    e.setCurrentPoints(currentPoint);
                    registrarLog("Se modifico el equipo " + name);
                }
            }
        }
    }

    //Consultas sobre Habitaciones
    public void consultaHabitaciones(int opcion){
        switch(opcion){
            case 1:
                System.out.print("Ingrese un codigo de habitacion:");
                int codeRoom = sc.nextInt();
                System.out.println();
                this.mostrarHabitaciones(codeRoom);
                break;
            case 2:
                System.out.print("Ingrese el codigo de la habitacion:");
                codeRoom = sc.nextInt();
                this.mostrarHabitacionesContiguas(codeRoom);
                break;
            case 3:
                System.out.print("Ingrese el codigo de la primera Habitacion:");
                int codeRoom1 = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el codigo de la segunda Habitacion:");
                int codeRoom2 = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el puntaje:");
                int points = sc.nextInt();
                sc.nextLine();
                if(esPosibleLlegar(codeRoom1,codeRoom2,points)){
                    System.out.println("Si es posible llegar");
                }else{
                    System.out.println("No es posible llegar");
                }
                break;
            case 4:
                System.out.print("Ingrese el codigo de la primera Habitacion:");
                codeRoom1 = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el codigo de la segunda Habitacion:");
                codeRoom2 = sc.nextInt();
                int minimoPuntaje = minimoPuntaje(codeRoom1,codeRoom2);
                System.out.println("El minimo puntaje que se requiere para llegar de la habitacion " + codeRoom1 +
                        " a la habitacion " + codeRoom2 + " es de: " + minimoPuntaje);
                break;
            case 5:
                System.out.print("Ingrese el codigo de la primera Habitacion:");
                codeRoom1 = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el codigo de la segunda Habitacion:");
                codeRoom2 = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el codigo de la tercera Habitacion:");
                int codeRoom3 = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el puntaje:");
                points = sc.nextInt();
                sc.nextLine();
                System.out.println(sinPasarPor(codeRoom1,codeRoom2,codeRoom3,points).toString());
        }
    }

    public void mostrarHabitaciones(int codeRoom){
        if(arbolHabitaciones.pertenece(codeRoom)){
            Lista listaHabitaciones = arbolHabitaciones.listar();
            Habitacion recoverRoom = buscarHabitacion(codeRoom, listaHabitaciones);
            System.out.println(recoverRoom.toString());
        }else{
            System.out.println("Habitacion no encontrada");
        }
    }

    public void mostrarHabitacionesContiguas(int codeRoom){
        String resultado = "";
        Lista listaHab = new Lista();
        if(arbolHabitaciones.pertenece(codeRoom)){
            listaHab = grafo.listarEnProfundidad();
            Habitacion recoverRoom =  buscarHabitacion(codeRoom,listaHab);
            Lista[] listaHabConsiguas = grafo.HabitacionesContiguas(recoverRoom);
            resultado = "Numero de Habitaccion:" + listaHabConsiguas[0].toString() + "\nPuntaje Requerido:" + listaHabConsiguas[1].toString();
        }else{
            resultado = "Habitacion no encontrada";
        }
        System.out.println(resultado);
    }

    public boolean esPosibleLlegar(int codeRoom1, int codeRoom2, int k){
        boolean posible = false;
        if(arbolHabitaciones.pertenece(codeRoom1) && arbolHabitaciones.pertenece(codeRoom2)){
            Lista listaHabitaciones = grafo.listarEnProfundidad();
            Habitacion recoverRoom1 = buscarHabitacion(codeRoom1,listaHabitaciones);
            Habitacion recoverRoom2 = buscarHabitacion(codeRoom2,listaHabitaciones);
            posible = grafo.esPosibleLlegar(recoverRoom1,recoverRoom2,k);
        }else{
            System.out.println("Alguna de las habitaciones no se encuentran en la casa");
        }
        return posible;
    }

    public int minimoPuntaje(int codeRoom1, int codeRoom2){
        int minimoPuntaje = 0;
        if(arbolHabitaciones.pertenece(codeRoom1) && arbolHabitaciones.pertenece(codeRoom2)){
            Lista listaHabitaciones = grafo.listarEnProfundidad();
            Habitacion hab1 = buscarHabitacion(codeRoom1,listaHabitaciones);
            Habitacion hab2 = buscarHabitacion(codeRoom2,listaHabitaciones);
            Lista caminoFinal = new Lista();
            minimoPuntaje = grafo.minimoPuntaje(hab1,hab2,caminoFinal);
        }else{
            System.out.println("Una de las habitaciones no exite en la casa!!!");
        }
        return minimoPuntaje;
    }

    public Lista sinPasarPor(int codeRoom1, int codeRoom2, int codeRoom3, int P){
        Lista listaPasar = new Lista();
        if(arbolHabitaciones.pertenece(codeRoom1) && arbolHabitaciones.pertenece(codeRoom2) && arbolHabitaciones.pertenece(codeRoom3)){
            Lista listaHabitaciones = grafo.listarEnProfundidad();
            Habitacion hab1 = buscarHabitacion(codeRoom1,listaHabitaciones);
            Habitacion hab2 = buscarHabitacion(codeRoom2,listaHabitaciones);
            Habitacion hab3 = buscarHabitacion(codeRoom3,listaHabitaciones);
            listaPasar = grafo.sinPasarPor(hab1,hab2,hab3,P);
        }else{
            System.out.println("Una habitacion no pertenece a la casa");
        }
        return  listaPasar;
    }

    //Consulta General
    public String mostrarSistema(){
        String mensaje = "";
        if(!this.grafo.esVacio() && !this.arbolHabitaciones.esVacio() && this.arbolDesafios.esVacio() && !this.equipos.isEmpty()){
            mensaje = grafo.toString() + "\n" + arbolHabitaciones.toString() + "\n" /*+ arbolDesafios.toString()*/ + "\n" +
                    equipos.toString();
        }else{
            mensaje = "No hay datos cargados";
        }
        return mensaje;
    }

    public void registrarLog(String mensaje) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sistema.log", true))) {
            java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter
                    .ofPattern("yyyy/MM/dd HH:mm:ss");
            bw.write("[" + dtf.format(java.time.LocalDateTime.now()) + "] " + mensaje);
            bw.newLine();
        } catch (IOException e) {
            // Fallo silencioso: si no se puede loguear, el programa sigue
        }
    }

    //Metodos privados
    private Habitacion buscarHabitacion(int codeRoom, Lista listaHabitaciones){
        Lista listaClon = listaHabitaciones.clone();
        Habitacion recoverRoom = null;
        while(!listaClon.esVacia() && recoverRoom == null){
            Habitacion roomAux = (Habitacion) listaClon.recuperar(1);
            if(roomAux.getCode()==codeRoom){
                recoverRoom = roomAux;
            }else{
                listaClon.eliminar(1);
            }
        }
        return recoverRoom;
    }

    private boolean validarEquipoEnHabitacion(int currentRoom, Map<String,Equipo> map){
        boolean exito = true;
        Iterator<Equipo> iterator = map.values().iterator();
        while(iterator.hasNext() && exito){
            Equipo equipoAux =  iterator.next();
            if(equipoAux.getCurrentRoom() == currentRoom){
                exito = false;
            }
        }
        return  exito;
    }

    private boolean nombreValido(String name, Lista listaHab){
        boolean exito = false;
        Lista listaClon = listaHab.clone();
        while(!listaClon.esVacia() && !exito){
            Habitacion hab = (Habitacion) listaClon.recuperar(1);
            if(hab.getName().equals(name)){
                exito = true;
            }else{
                listaClon.eliminar(1);
            }
        }
        return  exito;
    }

    private boolean equiposEnHabitacion(int codeRoom, Map<String,Equipo> map){
        boolean estan = false;
        Iterator<Equipo> iterator = map.values().iterator();
        while(!estan && iterator.hasNext()){
            Equipo equipo = iterator.next();
            if(equipo.getCurrentRoom() == codeRoom){
                estan = true;
            }
        }
        return  estan;
    }
}