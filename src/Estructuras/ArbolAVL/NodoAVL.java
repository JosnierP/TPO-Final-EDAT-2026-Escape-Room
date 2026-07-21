package Estructuras.ArbolAVL;

public class NodoAVL {
    private Comparable clave;
    private Object elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable clave,Object elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.clave = clave;
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setAltura(int altura) {
        this.altura = altura;
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
