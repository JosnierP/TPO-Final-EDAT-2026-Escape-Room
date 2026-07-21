package Estructuras.Grafos;

public class NodoVert {
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdyEtiquetado primerAdy;

    public NodoVert(Object elem, NodoVert sigVertice) {
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

    public NodoVert getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVert sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdyEtiquetado getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdyEtiquetado primerAdy) {
        this.primerAdy = primerAdy;
    }
}
