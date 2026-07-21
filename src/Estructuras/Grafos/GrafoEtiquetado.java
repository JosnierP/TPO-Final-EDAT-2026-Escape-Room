package Estructuras.Grafos;

import Clases.Habitacion;
import Estructuras.Lista.Lista;

public class GrafoEtiquetado {

    private NodoVert inicio;

    public GrafoEtiquetado() {
        this.inicio = null;
    }

    //Metodos publicos del TDA de Grafo Etiquetado

    public boolean insertarVertice(Object vertice){
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(vertice);
        if(aux == null){
            this.inicio = new NodoVert(vertice,this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object vertice){
        boolean exito = false;
        NodoVert verticeBuscado = this.inicio;
        NodoVert anterior = null;
        while(verticeBuscado != null && !exito){
            if(verticeBuscado.getElem().equals(vertice)){
                if(anterior == null){
                    this.inicio = verticeBuscado.getSigVertice();
                }else{
                    anterior = verticeBuscado.getSigVertice();
                }
                exito = true;
            }else {
                anterior = verticeBuscado;
                verticeBuscado = verticeBuscado.getSigVertice();
            }
        }
        if(exito){
            NodoVert verticeAux = this.inicio;
            while(verticeAux != null){
                eliminarArcosAux(verticeAux.getElem(),vertice);
                verticeAux = verticeAux.getSigVertice();
            }
        }
        return exito;
    }

    public boolean existeVertice(Object vertice){
        return ubicarVertice(vertice) != null;
    }

    public boolean eliminarArco(Object inicio, Object fin){
        return eliminarArcosAux(inicio,fin);
    }

    public boolean insertarArco(Object inicio, Object fin, Object etiqueta){
        boolean exito = false;
        NodoVert nodoInicio = this.ubicarVertice(inicio);
        NodoVert nodoFin = this.ubicarVertice(fin);
        if(nodoInicio != null && nodoFin != null){
            NodoAdyEtiquetado primerNodoAdy = nodoInicio.getPrimerAdy();
            nodoInicio.setPrimerAdy(new NodoAdyEtiquetado(nodoFin,primerNodoAdy,etiqueta));
            exito = true;
        }
        return exito;
    }

    public boolean existeArco(Object inicio, Object fin){
        boolean existe = false;
        NodoVert nodoInicio = this.ubicarVertice(inicio);
        NodoAdyEtiquetado primerNodoAdy = nodoInicio.getPrimerAdy();
        while(primerNodoAdy != null && !existe){
            if(primerNodoAdy.getVertice().getElem().equals(fin)){
                existe = true;
            }else{
                primerNodoAdy = primerNodoAdy.getSigAdyacente();
            }
        }
        return existe;
    }

    public boolean esVacio(){
        return this.inicio == null;
    }

    @Override
    public String toString() {
        return toStringAux(this.inicio);
    }

    private String toStringAux(NodoVert nodoVertice) {
        String resultado = "";
        if (nodoVertice != null) {
            String salidaPosterior = toStringAux(nodoVertice.getSigVertice());
            StringBuilder sbActual = new StringBuilder();
            sbActual.append(nodoVertice.getElem()).append(" -> ");

            NodoAdyEtiquetado adyacente = nodoVertice.getPrimerAdy();
            while (adyacente != null) {
                sbActual.append(adyacente.getVertice().getElem())
                        .append(" (peso:").append(adyacente.getEtiqueta()).append("), ");
                adyacente = adyacente.getSigAdyacente();
            }
            sbActual.append("\n");
            resultado = salidaPosterior + sbActual.toString();
        }
        return resultado;
    }

    //Metodos del Dominio
    public Lista[] HabitacionesContiguas(Habitacion recoverRoom){
        Lista[] lista = new Lista[2];
        lista[0] = new Lista();
        lista[1] = new Lista();
        NodoVert habVertice = ubicarVertice(recoverRoom);
        NodoAdyEtiquetado habConsigua = habVertice.getPrimerAdy();
        while(habConsigua != null){
            NodoVert hab = habConsigua.getVertice();
            Habitacion habitacion = (Habitacion) hab.getElem();
            lista[0].insertar(habitacion.getCode() , lista[0].longitud() + 1);
            lista[1].insertar(habConsigua.getEtiqueta() , lista[1].longitud() + 1);
            habConsigua = habConsigua.getSigAdyacente();
        }
        return lista;
    }

    public boolean esPosibleLlegar(Object hab1, Object hab2, int k) {
        boolean exito = false;
        NodoVert aux0 = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((aux0 == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(hab1)) aux0 = aux;
            if (aux.getElem().equals(hab2)) auxD = aux;
            aux = aux.getSigVertice();
        }

        if (aux0 != null && auxD != null) {
            Lista visitados = new Lista();
            exito = existeCaminoHabitacion(aux0, hab2, visitados, k, 0);
        }
        return exito;
    }

    public int minimoPuntaje(Object hab1,Object hab2, Lista caminoResultado){
        int minimo = -1;
        NodoVert origen = ubicarVertice(hab1);
        NodoVert destino = ubicarVertice(hab2);
        if(origen != null && destino != null){
            Lista visitados = new Lista();
            Lista caminoActual = new Lista();
            caminoActual.insertar(origen.getElem(), 1);
            minimo = existeCaminoMinPoints(origen,destino,visitados,0,caminoActual, caminoResultado);
        }
        return minimo;
    }

    public Lista sinPasarPor(Object hab1, Object hab2, Object hab3, int P) {
        Lista todosLosCaminos = new Lista();
        NodoVert origen = ubicarVertice(hab1);
        NodoVert destino = ubicarVertice(hab2);
        NodoVert prohibida = ubicarVertice(hab3);
        if (origen != null && destino != null) {
            Lista visitados = new Lista();
            Lista caminoActual = new Lista();
            Habitacion hab = (Habitacion) origen.getElem();
            caminoActual.insertar(hab.getCode(), 1);
            buscarCaminosSinPasarPor(origen, destino, prohibida, visitados, 0, P, caminoActual, todosLosCaminos);
        }
        return todosLosCaminos;
    }

    //Metodos de Recocorridos
    public Lista listarEnProfundidad(){
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while(aux != null){
            Object elemento = aux.getElem();
            int lugar = visitados.localizar(elemento);
            if(visitados.localizar(aux.getElem()) <= 0){
                listarEnProfundidadAux(aux,visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    //Metodos privados
    private NodoVert ubicarVertice(Object vertice){
        NodoVert aux = this.inicio;
        while(aux != null && !aux.getElem().equals(vertice)){
            aux = aux.getSigVertice();
        }
        return aux;
    }

    private boolean eliminarArcosAux(Object inicio, Object fin){
        boolean exito = false;
        NodoVert verticeInicial = ubicarVertice(inicio);
        if (verticeInicial != null) {
            NodoAdyEtiquetado actual = (NodoAdyEtiquetado) verticeInicial.getPrimerAdy();
            NodoAdyEtiquetado anterior = null;

            while (actual != null && !exito) {
                if (actual.getVertice().getElem().equals(fin)) {
                    if (anterior == null) {
                        verticeInicial.setPrimerAdy(actual.getSigAdyacente());
                    } else {
                        anterior.setSigAdyacente(actual.getSigAdyacente());
                    }
                    exito = true;
                } else {
                    anterior = actual;
                    actual = actual.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis){
        if(n != null){
            vis.insertar(n.getElem(),vis.longitud() + 1);
            NodoAdyEtiquetado ady = n.getPrimerAdy();
            while(ady != null){
                if(vis.localizar(ady.getVertice().getElem()) <= 0){
                    listarEnProfundidadAux(ady.getVertice(),vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    private boolean existeCaminoHabitacion(NodoVert n, Object dest, Lista vis, int k, int acumulado) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdyEtiquetado ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    int nuevoAcumulado = acumulado + (int) ady.getEtiqueta();
                    if (vis.localizar(ady.getVertice().getElem()) <= 0 && nuevoAcumulado <= k) {
                        exito = existeCaminoHabitacion(ady.getVertice(), dest, vis, k, nuevoAcumulado);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    private int existeCaminoMinPoints(NodoVert n, NodoVert dest, Lista vis, int acumulado,Lista caminoActual, Lista mejorCamino){
        int points = Integer.MAX_VALUE;
        if(n != null){
            if(n.getElem().equals(dest.getElem())){
                points = acumulado;
                mejorCamino.vaciar();
                mejorCamino = caminoActual.clone();
            }else{
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdyEtiquetado ady = n.getPrimerAdy();
                while(ady != null){
                    if(vis.localizar(ady.getVertice().getElem()) <= 0){
                        int nuevoAcumulado = acumulado + (int) ady.getEtiqueta();
                        caminoActual.insertar(ady.getVertice().getElem(), caminoActual.longitud() + 1);
                        int resultado = existeCaminoMinPoints(ady.getVertice(),dest,vis,nuevoAcumulado,caminoActual,mejorCamino);
                        if(resultado < points){
                            points = resultado;
                        }

                        caminoActual.eliminar(caminoActual.longitud());
                    }
                    ady = ady.getSigAdyacente();
                }
                vis.eliminar(vis.longitud());
            }
        }
        return points;
    }

    private void buscarCaminosSinPasarPor(NodoVert n, NodoVert dest, NodoVert prohibida, Lista vis, int acumulado, int P, Lista caminoActual, Lista todosLosCaminos){
        if (n != null && !n.equals(prohibida)) {
            if (n.getElem().equals(dest.getElem())) {
                Lista caminoCopia = new Lista();
                caminoCopia = caminoActual.clone();
                todosLosCaminos.insertar(caminoCopia, todosLosCaminos.longitud() + 1);
            } else {
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdyEtiquetado ady = n.getPrimerAdy();
                while (ady != null) {
                    boolean esProhibida = ady.getVertice().equals(prohibida);
                    boolean yaVisitado = vis.localizar(ady.getVertice().getElem()) > 0;
                    int nuevoAcumulado = acumulado + (int) ady.getEtiqueta();

                    if (!esProhibida && !yaVisitado && nuevoAcumulado <= P) {
                        Habitacion hab = (Habitacion) ady.getVertice().getElem();
                        caminoActual.insertar(hab.getCode() , caminoActual.longitud() + 1);
                        buscarCaminosSinPasarPor(ady.getVertice(), dest, prohibida, vis, nuevoAcumulado, P, caminoActual, todosLosCaminos);
                        caminoActual.eliminar(caminoActual.longitud());
                    }
                    ady = ady.getSigAdyacente();
                }
                vis.eliminar(vis.longitud());
            }
        }
    }
}