package Estructuras.Lista;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista(){
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object element, int pos){
        boolean flag = true;
        if(pos < 1 || pos > this.longitud() + 1){
            flag = false;
        }else{
            if(pos == 1){
                this.cabecera = new Nodo(element, this.cabecera);
                this.longitud = this.longitud + 1;
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos - 1){
                    aux = aux.getEnlace();
                    i++;
                }

                Nodo nuevo = new Nodo(element, aux.getEnlace());
                aux.setEnlace(nuevo);
                this.longitud = this.longitud + 1;
            }
        }
        return flag;
    }

    public boolean eliminar(int pos){
        boolean flag = true;
        if(pos < 1 || pos > this.longitud + 1){
            flag = false;
        }else{
            if(pos == 1){
                this.cabecera = this.cabecera.getEnlace();
                this.longitud = this.longitud - 1;
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos - 1){
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo aux2 = aux.getEnlace();
                aux.setEnlace(aux2.getEnlace());
                this.longitud = this.longitud - 1;
            }
        }
        return flag;
    }

    public Object recuperar(int pos){
        Object elemento;
        if(pos < 1 || pos >= this.longitud + 1){
            elemento = null;
        }else{
            if(this.cabecera == null){
                elemento = null;
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos){
                    aux = aux.getEnlace();
                    i++;
                }
                elemento = aux.getElement();
        }
        }
        return elemento;
    }

    public int localizar(Object elemento){
        int lugar = 0,pos = 1;
        if(this.cabecera != null){
            Nodo aux = this.cabecera;
            Object auxElemento = aux.getElement();
            while(pos < this.longitud && elemento != auxElemento){
                pos++;
                aux = aux.getEnlace();
                auxElemento = aux.getElement();
            }if(elemento == auxElemento){
                lugar = pos;
            }
        }
        return lugar;
    }

    public void vaciar(){
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia(){
        return this.cabecera == null;
    }

    @Override
    public Lista clone(){
        Lista listaClonada = new Lista();
        if(this.cabecera != null){
            Nodo aux = this.cabecera;
            listaClonada.longitud = this.longitud;
            Nodo nuevo = new Nodo(aux.getElement(),null);
            listaClonada.cabecera = nuevo;
            while(aux.getEnlace() != null){
                Nodo auxNext = aux.getEnlace();
                Nodo nuevo2 = new Nodo(auxNext.getElement(),null);
                nuevo.setEnlace(nuevo2);
                aux = aux.getEnlace();
                nuevo = nuevo.getEnlace();
            }
        }
        return listaClonada;
    }
    
    public int longitud(){
        return this.longitud;
    }

    @Override
    public String toString(){
        String str = "";
        if(this.cabecera == null){
            str = "[]";
        }
        else{
            Nodo aux = this.cabecera;
            str = "[";
            for (int i = 0; i < this.longitud(); i++) {
                str = str + aux.getElement().toString();
                aux = aux.getEnlace();
                if(aux != null){
                    str += ",";
                }
            }
            str += "]";
        }
        return str;
    }

}
