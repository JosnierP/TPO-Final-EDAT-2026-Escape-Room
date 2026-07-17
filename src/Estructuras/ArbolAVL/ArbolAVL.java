package Estructuras.ArbolAVL;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL(){
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem){
        boolean pertenece = true;
        if(this.raiz != null){
            pertenece = perteneceAux(this.raiz, elem);
        }else{
            pertenece = false;
        }
        return pertenece;
    }

    private boolean perteneceAux(NodoAVL n, Comparable elem){
        boolean pertenece = true;
        if(n != null){
            if(n.getElem().compareTo(elem) > 0){
                pertenece = perteneceAux(n.getDerecho(), elem);
            }else if(n.getElem().compareTo(elem) < 0){
                pertenece = perteneceAux(n.getIzquierdo(), elem);
            }else if(n.getElem().compareTo(elem) == 0){
                pertenece = true;
            }else{
                pertenece = false;
            }
        }
        return pertenece;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elemento);
            exito = true;
        } else {
            exito = insertarAux(this.raiz, null, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, NodoAVL padre, Comparable elemento) {
        boolean exito;
        if (elemento.compareTo(n.getElem()) == 0) {
            exito = false; // elemento repetido
        } else if (elemento.compareTo(n.getElem()) < 0) {
            if (n.getIzquierdo() == null) {
                n.setIzquierdo(new NodoAVL(elemento));
                exito = true;
            } else {
                exito = insertarAux(n.getIzquierdo(), n, elemento);
            }
            n.recalcularAltura();
            reemplazarSiCorresponde(n, padre);
        } else {
            if (n.getDerecho() == null) {
                n.setDerecho(new NodoAVL(elemento));
                exito = true;
            } else {
                exito = insertarAux(n.getDerecho(), n, elemento);
            }
            n.recalcularAltura();
            reemplazarSiCorresponde(n, padre);
        }
        return exito;
    }

    private void reemplazarSiCorresponde(NodoAVL n, NodoAVL padre) {
        NodoAVL nuevaRaizSubarbol = balancear(n);
        if (nuevaRaizSubarbol != n) {
            // hubo rotación, hay que reconectar
            if (padre == null) {
                this.raiz = nuevaRaizSubarbol;
            } else if (padre.getIzquierdo() == n) {
                padre.setIzquierdo(nuevaRaizSubarbol);
            } else {
                padre.setDerecho(nuevaRaizSubarbol);
            }
        }
    }

    private int altura(NodoAVL n) {
        return (n == null) ? -1 : n.getAltura();
    }

    private int balance(NodoAVL n) {
        return altura(n.getIzquierdo()) - altura(n.getDerecho());
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito;
        if (this.raiz == null) {
            exito = false;
        } else {
            exito = eliminarAux(this.raiz, null, elemento);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n, NodoAVL padre, Comparable elemento) {
        boolean exito;
        if (n == null) {
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            exito = eliminarAux(n.getIzquierdo(), n, elemento);
            n.recalcularAltura();
            reemplazarSiCorresponde(n, padre);
        } else if (elemento.compareTo(n.getElem()) > 0) {
            exito = eliminarAux(n.getDerecho(), n, elemento);
            n.recalcularAltura();
            reemplazarSiCorresponde(n, padre);
        } else {
            // nodo encontrado
            if (n.getIzquierdo() == null) {
                conectarConPadre(n.getDerecho(), n, padre);
            } else if (n.getDerecho() == null) {
                conectarConPadre(n.getIzquierdo(), n, padre);
            } else {
                // dos hijos: Candidato A (máximo del subárbol izquierdo)
                NodoAVL candidatoA = maximoNodo(n.getIzquierdo());
                n.setElem(candidatoA.getElem());
                eliminarMaximoAux(n.getIzquierdo(), n);
                n.recalcularAltura();
                reemplazarSiCorresponde(n, padre);
            }
            exito = true;
        }
        return exito;
    }

    private void conectarConPadre(NodoAVL nuevoHijo, NodoAVL viejoNodo, NodoAVL padre) {
        if (padre == null) {
            this.raiz = nuevoHijo;
        } else if (padre.getIzquierdo() == viejoNodo) {
            padre.setIzquierdo(nuevoHijo);
        } else {
            padre.setDerecho(nuevoHijo);
        }
    }

    private NodoAVL maximoNodo(NodoAVL n) {
        NodoAVL actual = n;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        return actual;
    }

    private void eliminarMaximoAux(NodoAVL n, NodoAVL padre) {
        if (n.getDerecho() == null) {
            conectarConPadre(n.getIzquierdo(), n, padre);
        } else {
            eliminarMaximoAux(n.getDerecho(), n);
            n.recalcularAltura();
            reemplazarSiCorresponde(n, padre);
        }
    }

    private NodoAVL rotacionIzquierda(NodoAVL r){
        NodoAVL h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        h.setDerecho(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotacionDerecha(NodoAVL r){
        NodoAVL h = r.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL balancear(NodoAVL n) {
        NodoAVL nuevaRaiz = n;
        int bal = balance(n);

        if (bal == 2) {
            if (balance(n.getIzquierdo()) >= 0) {
                nuevaRaiz = rotacionDerecha(n);
            } else {
                n.setIzquierdo(rotacionIzquierda(n.getIzquierdo()));
                nuevaRaiz = rotacionDerecha(n);
            }
        } else if (bal == -2) {
            if (balance(n.getDerecho()) <= 0) {
                nuevaRaiz = rotacionIzquierda(n);
            } else {
                n.setDerecho(rotacionDerecha(n.getDerecho()));
                nuevaRaiz = rotacionIzquierda(n);
            }
        }
        return nuevaRaiz;
    }

    @Override
    public String toString(){
        String str = "";
        if(this.raiz != null){
            str = toStringAux(this.raiz);
        }
        return str;
    }

    private String toStringAux(NodoAVL n){
        String resp1 = "";
        if(n != null){
            resp1 += n.getElem().toString();
            if(n.getIzquierdo() != null && n.getDerecho() != null){
                resp1 += " HI:" + n.getIzquierdo().getElem().toString() + " HD:"+n.getDerecho().getElem().toString();
            }else if(n.getIzquierdo() != null && n.getDerecho() == null){
                resp1 += " HI:" + n.getIzquierdo().getElem().toString() + " HD:-";
            }else if(n.getDerecho() != null && n.getIzquierdo() == null){
                resp1 += " HI:- HD:" + n.getDerecho().getElem().toString();
            }else if(n.getDerecho() == null && n.getIzquierdo() == null){
                resp1 += " HI:- HD:-";
            }
            resp1 += "\n" + toStringAux(n.getIzquierdo()) + toStringAux(n.getDerecho());
        }
        return resp1;
    }
}
