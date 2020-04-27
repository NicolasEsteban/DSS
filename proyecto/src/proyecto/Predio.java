/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;

/**
 *
 * @author Eric
 */
public class Predio {
    
    public int id;  //Identificador del predio.
    public int numC;   //Numero de cargas del predio.
    public float h;   //Tiempo requerido para cosechar una carga del predio.(en minutos)
    public float t;   //Tiempo de viaje del predio hacia la planta.
    public ArrayList<Carga> C = new ArrayList<Carga>();
    
    public int y;   //Tiempo de inicio de la cosecha en el predio.
    
    public Predio(int id, int numC, float h, float t) {
        this.id = id;
        this.numC = numC;
        this.h = h;
        this.t = t;
        this.y = -1;
        Carga c;
        for (int i = 0; i < numC; i++) {
            c = new Carga();
            C.add(c);
        }
    }
    
    public Predio(int id, int numC, float h, float t, int y) {
        this.id = id;
        this.numC = numC;
        this.h = h;
        this.t = t;
        this.y = y;
        Carga c;
        for (int i = 0; i < numC; i++) {
            c = new Carga();
            C.add(c);
        }
    }

    public Predio() {
        this.y=-1;
    }
    
}
