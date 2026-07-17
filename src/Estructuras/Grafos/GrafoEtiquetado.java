package Estructuras.Grafos;

public class GrafoEtiquetado {

    private NodoVertEtiquetado inicio;

    public GrafoEtiquetado() {
        this.inicio = null;
    }

    //Metodos publicos del TDA de Grafo Etiquetado

    public boolean insertarVertice(Object vertice){
        boolean exito = false;
        NodoVertEtiquetado aux = this.ubicarVertice(vertice);
        if(aux == null){
            this.inicio = new NodoVertEtiquetado(vertice,this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object vertice){
        boolean exito = false;
        NodoVertEtiquetado verticeBuscado = this.inicio;
        NodoVertEtiquetado anterior = null;
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
            NodoVertEtiquetado verticeAux = this.inicio;
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
        NodoVertEtiquetado nodoInicio = this.ubicarVertice(inicio);
        NodoVertEtiquetado nodoFin = this.ubicarVertice(fin);
        if(nodoInicio != null && nodoFin != null){
            NodoAdyEtiquetado primerNodoAdy = nodoInicio.getPrimerAdy();
            nodoInicio.setPrimerAdy(new NodoAdyEtiquetado(nodoInicio,primerNodoAdy,etiqueta));
            exito = true;
        }
        return exito;
    }

    public boolean existeArco(Object inicio, Object fin){
        boolean existe = false;
        NodoVertEtiquetado nodoInicio = this.ubicarVertice(inicio);
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


    //Metodos privados
    private NodoVertEtiquetado ubicarVertice(Object vertice){
        NodoVertEtiquetado aux = this.inicio;
        while(aux != null && !aux.getElem().equals(vertice)){
            aux = aux.getSigVertice();
        }
        return aux;
    }

    private boolean eliminarArcosAux(Object inicio, Object fin){
        boolean exito = false;
        NodoVertEtiquetado verticeInicial = ubicarVertice(inicio);
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

}
