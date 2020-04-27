/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import jxl.format.Border;

import modelos.*;

public class Ventana_agregar_cargas extends javax.swing.JDialog {
    int posicion=0,Ncargas[],contador=0;
    ArrayList<PredioSeleccionado> listaSelec = new ArrayList();
    
    
    List<JTextField> campos = new ArrayList<>(); // para guardar contenido de los campos de textos
    
    public Ventana_agregar_cargas(java.awt.Frame parent, boolean modal,ArrayList<PredioSeleccionado> seleccionados) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        campos.clear();
        listaSelec.clear();
        
        
        Ncargas=new int[seleccionados.size()];
        contador= seleccionados.size();
        for(int i=0;i<seleccionados.size();i++){ // se pasan datos del arraylist al arraylist global
            listaSelec.add(seleccionados.get(i));
        }
        
        for(int i=0;i<seleccionados.size();i++){// se inicializa vector de cargas
            Ncargas[i]=-1;
        }
        
        panel.setBackground(Color.GRAY); // se le da el color gris al panel
        GridBagLayout gridBag = new GridBagLayout (); // se selecciona tipo de layout
        panel.setLayout(gridBag);
        GridBagConstraints restricciones = new GridBagConstraints ();
        
        
        
        for(int i=0;i<seleccionados.size();i++){
            
            
            
            restricciones.gridwidth = GridBagConstraints.RELATIVE; // label antepenultimo espacio
            restricciones.weightx = 1.5;
            JLabel label= new JLabel(seleccionados.get(i).getNombre_predio());
            gridBag.setConstraints (label, restricciones);      // se agrefa restriccion
            panel.add(label);                                   // se agrega a panel
            
            restricciones.gridwidth = GridBagConstraints.REMAINDER; // campo de texto, ultimo en la fila
            JTextField input = new JTextField(10);
            input.setHorizontalAlignment(JTextField.CENTER); 
            input.addKeyListener(new KeyAdapter(){ // se crea restriccion para evitar que se ingresen letras, 
                public void keyTyped(KeyEvent e)   // numeros flotantes, o negativos.
                {
                    char car= e.getKeyChar();
                    if((car<'0' || car>'9') && (car!=',' || car!='.' )) e.consume();
                    
        
                }
            });
            
            gridBag.setConstraints (input, restricciones);          // se agregan restricciones
            panel.add(input);
            campos.add(input); // se almacenan campos de texto en arrayList de campos de texto
        }
       
        // se desabilitan campos de textos
        /*jpredios.setEditable(false);
        jpredios.setText(seleccionados.get(posicion).getNombre_predio());
        
        
        jcargas.setHorizontalAlignment(JTextField.CENTER); 
        
        jcontador.setEditable(false);
        jcontador.setText(String.valueOf(seleccionados.size()));
        jcontador.setHorizontalAlignment(JTextField.CENTER); 
        */
        
        //PredioModel pm = new PredioModel();
        //pm.llamar_predio_seleccionado(seleccionados.get(posicion).getSuper_men(),seleccionados.get(posicion).getNombre_predio());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ingresar cantidad de cargas por predio");
        jPanel1.add(jLabel4);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 440, 40));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 650, 90, 50));

        panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                panelKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(panel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 440, 420));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Siguiente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 150, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo-tomates.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        ArrayList<Predio> entradaGrasp = new ArrayList();
        int km_talca=0,verificador=0;
        int j=0;
        
        for(JTextField txt: campos){// se verifican que no hayan campos vacios
            
            if(txt.getText().equals("")){
                verificador=1;
            
            }
            j++;
        }
        if(verificador==1){
            JOptionPane.showMessageDialog(null, "Se dejaron campos vacios", " Mensaje", JOptionPane.WARNING_MESSAGE); 
            
        }else{           
            j=0;
            for(JTextField txt: campos){// se leen datos 
                //System.out.println(txt.getText());
                Ncargas[j]=Integer.parseInt(txt.getText()); // se almacenan cargas
                j++;
            }
        
            PredioModel pm = new PredioModel();
            for(int i=0;i<listaSelec.size();i++){// se extrae la distancia del predio con respecto a talca
                km_talca=pm.llamar_predio_seleccionado(listaSelec.get(i).getId());
                Predio pr;
                float tiempo= (float)km_talca/50*60; // se multiplican por 60 para que se midan en minutos
                float tiempoC = (float)10/Ncargas[i]*60;
                pr=new Predio(listaSelec.get(i).getLugar(),Ncargas[i],tiempoC,tiempo);
                entradaGrasp.add(pr);
            }
            JOptionPane.showMessageDialog(null,"Cargas ingresadas correctamente"); // mensajae
           
            //ventana_capacidad_descarga dialog = new ventana_capacidad_descarga(new javax.swing.JFrame(), true,entradaGrasp,listaSelec);
            //dialog.setVisible(true);
            Ventana_definir_bloques dialog = new Ventana_definir_bloques(new javax.swing.JFrame(),true,entradaGrasp,listaSelec);
            dialog.setVisible(true);
            
            dispose();
            
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void panelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelKeyTyped
        char car= evt.getKeyChar();
        if((car<'0' || car>'9') && (car!=',' || car!='.')) evt.consume();
    }//GEN-LAST:event_panelKeyTyped

    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
