package Estructuras.Grafos;

public class NodoAdyEtiquetado {
    private NodoVertEtiquetado vertice;
    private NodoAdyEtiquetado sigAdyacente;
    private Object etiqueta;

    public NodoAdyEtiquetado(NodoVertEtiquetado vertice, NodoAdyEtiquetado sigAdyacente, Object etiqueta) {
        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
        this.etiqueta = etiqueta;
    }

    public NodoVertEtiquetado getVertice() {
        return vertice;
    }

    public void setVertice(NodoVertEtiquetado vertice) {
        this.vertice = vertice;
    }

    public NodoAdyEtiquetado getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdyEtiquetado sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public Object getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }
}
