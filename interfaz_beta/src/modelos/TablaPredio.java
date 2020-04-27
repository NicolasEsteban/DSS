
package modelos;


public class TablaPredio {
    int id;
    String productor;
    String nombre_predio;
    int km_talca;

    public TablaPredio(int id, String productor, String nombre_predio, int km_talca) {
        this.id = id;
        this.productor = productor;
        this.nombre_predio = nombre_predio;
        this.km_talca = km_talca;
    }
    
    public TablaPredio(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getNombre_predio() {
        return nombre_predio;
    }

    public void setNombre_predio(String nombre_predio) {
        this.nombre_predio = nombre_predio;
    }

    public int getKm_talca() {
        return km_talca;
    }

    public void setKm_talca(int km_talca) {
        this.km_talca = km_talca;
    }
    
    
    
}
