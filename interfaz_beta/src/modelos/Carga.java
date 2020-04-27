
package modelos;


public class Carga {
    public float x;   //Tiempo de llegada de la carga (hora, minuto).
    //public float lambdak;  //Momento de llegada de la carga.
    //public float lambdak1;  //lambda k+1
    public Bloque ak;   //bloque de llegada de la carga.
    
     public Carga(float x, Bloque ak) {
        this.x = x;
        this.ak = ak;
    }
    
    public Carga(){
        this.x = -1;
    }
}
