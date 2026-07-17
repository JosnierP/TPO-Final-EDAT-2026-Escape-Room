package Estructuras.Grafos;

public class NodoVertEtiquetado {
    private Object elem;
    private NodoVertEtiquetado sigVertice;
    private NodoAdyEtiquetado primerAdy;

    public NodoVertEtiquetado(Object elem, NodoVertEtiquetado sigVertice) {
        this.elem = elem;
        this.sigVertice = sigVertice;
        this.primerAdy = null;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoVertEtiquetado getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVertEtiquetado sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdyEtiquetado getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdyEtiquetado primerAdy) {
        this.primerAdy = primerAdy;
    }
}
