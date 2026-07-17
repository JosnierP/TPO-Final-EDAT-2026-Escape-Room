package Clases;
/************* Autores ***********
Josnier Jose Jesus Paez Ruiz, Legajo FAI-5889
Enzo Albornoz, Legajo FAI-4547
Franco Paredes, Legajo FAI-4059
*/

public class Nodo {
    private Object element;
    private Nodo enlace;
    
    //Constructot
    public Nodo(Object element,Nodo enlace){
        this.element = element;
        this.enlace = enlace;
    }
    
    //Modificador
    public void setElement(Object element){
        this.element = element;
    }
    
    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }
    
    //Visualizadores
    public Object getElement(){
        return element;
    }
    
    public Nodo getEnlace()
    {
       return enlace; 
    }
}
