
package modelos;


public class Bloque {
    
    public int a;   //Tiempo de inicio del bloque (0...n).
    public int N;   //Capacidad de descarga en la planta para el bloque (0...n-1).
    
    public int CS;  //Capacidad de vaciado Sobrante en la planta en el bloque.
    public int CF;  //Capacidad de vaciado Faltante en la planta en el bloque.

    public Bloque(int a, int N) {
        this.a = a;
        this.N = N;
        this.CS = N;
        this.CF = 0;
    }
    
    public Bloque(int a, int N, int CS, int CF) {
        this.a = a;
        this.N = N;
        this.CS = CS;
        this.CF = CF;
    }

    public Bloque() {
    }
}
