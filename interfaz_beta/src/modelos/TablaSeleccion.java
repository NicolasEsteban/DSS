
package modelos;


import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TablaSeleccion {
    
    private boolean[] editable = {false,false,false,true};
    ArrayList<PredioSeleccionado> Listapredios = new ArrayList();
    
    public void visualizar(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt= new DefaultTableModel(new String[]{"ID","PREDIOS","SUPER_MEN","ACCION"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Boolean.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        
        
        //tabla.getColumn("PREDIOS").setPreferredWidth(300);
        PredioModel pm = new PredioModel();
        Listapredios = pm.llamar_predios();
        //System.out.println(Listapredios.size());
        for(int i=0; i< Listapredios.size(); i++){
            Object fila[] = new Object[4];
            fila[0] = Listapredios.get(i).getId();
            fila[1] = Listapredios.get(i).getNombre_predio();
            fila[2] = Listapredios.get(i).getSuper_men();
            fila[3] = false;
            //System.out.println(fila[0]+" "+fila[1]);
            dt.addRow(fila);
        }
        
        
        
        tabla.setModel(dt);
        tabla.getColumn("PREDIOS").setPreferredWidth(200);
        tabla.getColumn("ID").setPreferredWidth(20);
        tabla.getColumn("ACCION").setPreferredWidth(50);
    
    }

    
    
}

