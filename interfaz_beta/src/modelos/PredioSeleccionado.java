
package modelos;

import java.util.ArrayList;


public class PredioSeleccionado {
    
    public int id;
    public String nombre_predio;
    public String super_men;
    public int lugar;
    

    public PredioSeleccionado(int id,String nombre_predio, String super_men) {
        
        this.id=id;
        this.nombre_predio = nombre_predio;
        this.super_men = super_men;
        int lugar=0;
    }

    public PredioSeleccionado(int id,String nombre_predio, String super_men, int lugar) {
       
        this.id=id;
        this.nombre_predio = nombre_predio;
        this.super_men = super_men;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    

    

    public String getNombre_predio() {
        return nombre_predio;
    }

    public void setNombre_predio(String nombre_predio) {
        this.nombre_predio = nombre_predio;
    }

    public String getSuper_men() {
        return super_men;
    }

    public void setSuper_men(String super_men) {
        this.super_men = super_men;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
    
    

  
    
    
   
}
