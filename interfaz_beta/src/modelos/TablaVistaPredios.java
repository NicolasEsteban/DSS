
package modelos;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaVistaPredios {
    private boolean[] editable = {false,false,false,false,false,false};
    ArrayList<TablaPredio> tablapredios = new ArrayList<TablaPredio>();
    
    public void visualizar(JTable tabla){
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt= new DefaultTableModel(new String[]{"ID","PRODUCTOR","PREDIOS","DISTANCIAS A TALCA (KM)","",""}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        
        JButton btn_editar = new JButton("Editar");
        btn_editar.setName("e");
        btn_editar.setBackground(Color.BLUE);
        btn_editar.setForeground(Color.BLUE);
        
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setName("el");
        btn_eliminar.setBackground(Color.RED);
        btn_eliminar.setForeground(Color.RED);
        
        	
        
        PredioModel pm = new PredioModel(); // se crea instancia del metodo predioModel
        tablapredios = pm.llamarTodosLosDatos();
        for(int i=0;i<tablapredios.size();i++){
            Object fila[] = new Object[6];
            fila[0]=tablapredios.get(i).getId();
            fila[1]=tablapredios.get(i).getProductor();
            fila[2]=tablapredios.get(i).getNombre_predio();
            fila[3]=tablapredios.get(i).getKm_talca();
            fila[4]=btn_editar;
            fila[5]=btn_eliminar;
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        
        tabla.getColumn("PREDIOS").setPreferredWidth(200);
        tabla.getColumn("PRODUCTOR").setPreferredWidth(200);
        tabla.getColumn("ID").setPreferredWidth(10);
        tabla.getColumn("DISTANCIAS A TALCA (KM)").setPreferredWidth(130);
                
    }
    
}
