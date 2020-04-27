
package modelos;
import java.util.ArrayList;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public int getNumC() {
        return numC;
    }

    public void setNumC(int numC) {
        this.numC = numC;
    }
    
    
    
}
