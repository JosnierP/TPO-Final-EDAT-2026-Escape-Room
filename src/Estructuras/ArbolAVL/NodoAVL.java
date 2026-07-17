package Estructuras.ArbolAVL;

public class NodoAVL {

    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elem){
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = -1;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura(){
        int HI = -1;
        int HD = -1;
        if(this.izquierdo != null){
            HI  = this.izquierdo.getAltura();
        }
        if(this.derecho != null){
            HD = this.derecho.getAltura();
        }
        this.altura = Math.max(HD, HI) + 1;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

}
