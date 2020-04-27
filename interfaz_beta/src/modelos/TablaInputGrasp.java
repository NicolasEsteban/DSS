/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolas
 */
public class TablaInputGrasp {
    private boolean[] editable = {false,false,false,false};
    DecimalFormat formato = new DecimalFormat("#.0");
    public void visualizar(JTable tabla,ArrayList<Predio> entradaGrasp,ArrayList<PredioSeleccionado> listaSelec ){
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt= new DefaultTableModel(new String[]{"PREDIO","N°CARGAS","TIEMPO DE COSECHA(MIN)","TIEMPO DE VIAJE(MIN)"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        
        
        
        
        
        	
        
        
        for(int i=0;i<entradaGrasp.size();i++){
            Object fila[] = new Object[4];
            fila[0]=listaSelec.get(i).getNombre_predio();
            fila[1]=entradaGrasp.get(i).getNumC();
            fila[2]=formato.format(entradaGrasp.get(i).getH());
            fila[3]=formato.format(entradaGrasp.get(i).getT());
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        
        tabla.getColumn("PREDIO").setPreferredWidth(230);
        tabla.getColumn("TIEMPO DE COSECHA(MIN)").setPreferredWidth(150);
        tabla.getColumn("TIEMPO DE VIAJE(MIN)").setPreferredWidth(150);
        
                
    }
}
