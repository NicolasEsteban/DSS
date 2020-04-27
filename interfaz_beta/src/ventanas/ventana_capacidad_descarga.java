/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelos.*;
import static ventanas.ventana_ejecucion_grasp.calcularHora;
import static ventanas.ventana_ejecucion_grasp.n;

/**
 *
 * @author Nicolas
 */
public class ventana_capacidad_descarga extends javax.swing.JDialog {

    List<JTextField> campos = new ArrayList<>(); // para guardar contenido de los campos de textos
    int capDescarga[];
    public static int eventoBoton=0; 
    
    public ArrayList<Date> horas = new ArrayList();
    ArrayList<Predio> entradaGrasps = new ArrayList();
    ArrayList<PredioSeleccionado> listaSelec = new ArrayList();
    
    public ventana_capacidad_descarga(java.awt.Frame parent, boolean modal,ArrayList<Predio> entradaGrasp,ArrayList<PredioSeleccionado> listaS,int bl,String ti) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        entradaGrasps.clear();
        listaSelec.clear();
        campos.clear();
        horas.clear();
        
        capDescarga =new int[bl]; // se le asigna la cantidad de bloques elegidos como tamaño ideal 
        
        for(int i=0;i<listaS.size();i++){ // se pasan datos del arraylist al arraylist global
            listaSelec.add(listaS.get(i));
        }
        
        for(int i=0;i<entradaGrasp.size();i++){// se pasan los datos de entrada al grasp
            entradaGrasps.add(entradaGrasp.get(i));
        }
        
        
        DateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss"); // se crea el formato de las horas
        // se declaran variables y arreglos que se usaran para la manipulacion de las horas
        Date aux;
        
        ArrayList<Date> horasFin = new ArrayList();
        ArrayList<String> etiquetaCampos = new ArrayList();
        float rango=0;
        float contador = 1440/bl;
        try {
           
            for(int i=0;i<bl;i++){ // ciclo para guardar las horas como Date
               aux = dateFormat.parse(calcularHora(rango));
               horas.add(aux);
               rango = rango +contador;
            }
            
            Collections.sort(horas);// se ordenan
            
            for(int i=0;i<bl;i++){ // se les resta un segundo a todas las horas
                Calendar c = Calendar.getInstance();
                c.setTime(horas.get(i));
                c.set(Calendar.SECOND, -1);
                horasFin.add(c.getTime());
            }
            
            for(int i=0;i<(bl-1);i++){ // se almacenan como String para ocuparlas en las etiquetas
                etiquetaCampos.add("Bloque que va desde "+dateFormat.format(horas.get(i))+" hasta "+dateFormat.format(horasFin.get((i+1))));                
            }
            etiquetaCampos.add("Bloque que va desde "+dateFormat.format(horas.get(bl-1))+" hasta "+dateFormat.format(horasFin.get(0)));
            
            /*for(int i=0;i<bl;i++){
                System.out.println(etiquetaCampos.get(i));
            }*/
            
        } catch (ParseException ex) {
            Logger.getLogger(ventana_capacidad_descarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //panel_eleccion.setBackground(Color.GRAY);
        panel.setBackground(Color.GRAY);       
        GridBagLayout gridBag = new GridBagLayout (); // se selecciona tipo de layout
        panel.setLayout(gridBag);
        GridBagConstraints restricciones = new GridBagConstraints ();
        restricciones.weightx = 1.5;
        int j=0;
        for(int i=0;i<bl;i++){
            
            restricciones.gridwidth = GridBagConstraints.RELATIVE; // label antepenultimo espacio
            restricciones.weightx = 1.5;
            JLabel label= new JLabel(etiquetaCampos.get(i));
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
        
        for(int i=0;i<campos.size();i++){ // se desabilitan los campos de texto
            campos.get(i).setEnabled(false);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accion = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        panel_eleccion = new javax.swing.JPanel();
        variable = new javax.swing.JRadioButton();
        uniforme = new javax.swing.JRadioButton();
        panel_titulo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 602, 120, 50));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 460, 270));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Siguiente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 140, 40));

        accion.add(variable);
        variable.setText("Variable");
        variable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variableMouseClicked(evt);
            }
        });

        accion.add(uniforme);
        uniforme.setText("Uniforme");
        uniforme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uniformeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_eleccionLayout = new javax.swing.GroupLayout(panel_eleccion);
        panel_eleccion.setLayout(panel_eleccionLayout);
        panel_eleccionLayout.setHorizontalGroup(
            panel_eleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_eleccionLayout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(uniforme)
                .addGap(18, 18, 18)
                .addComponent(variable)
                .addGap(158, 158, 158))
        );
        panel_eleccionLayout.setVerticalGroup(
            panel_eleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_eleccionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_eleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(variable)
                    .addComponent(uniforme))
                .addContainerGap())
        );

        getContentPane().add(panel_eleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 460, 40));

        panel_titulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Agregar capacidad de descarga ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("por cada bloque horario");

        javax.swing.GroupLayout panel_tituloLayout = new javax.swing.GroupLayout(panel_titulo);
        panel_titulo.setLayout(panel_tituloLayout);
        panel_tituloLayout.setHorizontalGroup(
            panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_tituloLayout.setVerticalGroup(
            panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(panel_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 460, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo-tomates.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int j=0,verificador=0;
        
        if(eventoBoton==0){ // si es que aun no se ha elegido ninguna opcion
            JOptionPane.showMessageDialog(null, "Elegir opcion variable o uniforme", " Mensaje", JOptionPane.WARNING_MESSAGE);
        }else{
            if(eventoBoton==2){// si se elige opcion de ingresar manualmente capacidad de descarga de cada bloque
                for(JTextField txt: campos){// se verifican que no hayan campos vacios
            
                    if(txt.getText().equals("")){
                        verificador=1;
            
                    }
                    j++;
                }
                if(verificador==1){// se verifica que no hayan campos vacios
                    JOptionPane.showMessageDialog(null, "Se dejaron campos vacios", " Mensaje", JOptionPane.WARNING_MESSAGE);
                }else{           
                       
                     for(int i=0;i<campos.size();i++){// se leen datos 
                        //System.out.println(campos.get(i).getText());
                        capDescarga[i]=Integer.parseInt(campos.get(i).getText()); // se almacenan cargas
                
                     }
             
            
                
                ventana_ejecucion_grasp dialog = new ventana_ejecucion_grasp(new javax.swing.JFrame(), true,entradaGrasps,listaSelec,capDescarga,horas);// se llama ventana para ejecutar algoritmo
                dialog.setVisible(true);
                
                
                dispose();
                }
            }else{ // aqui se debe empzar a modificar
                //JOptionPane.showMessageDialog(null, "holaaaa", " Mensaje", JOptionPane.WARNING_MESSAGE); 
                int capDesc=0,cargasTotales=0;
                /*for(int i=0;i<entradaGrasps.size();i++){
                    cargasTotales=cargasTotales+entradaGrasps.get(i).getNumC();
                    
                }
                capDesc=(int) Math.ceil((float)cargasTotales/24);
                
                for(int i=0;i<campos.size();i++){// se le asignan el promedio al arreglo dependiendo del de ka cantidad de cargas 
                        //System.out.println(campos.get(i).getText());
                    capDescarga[i]=capDesc; // se almacenan cargas               
                }*/
                
                //ventana_ejecucion_grasp dialog = new ventana_ejecucion_grasp(new javax.swing.JFrame(), true,entradaGrasps,listaSelec,capDescarga,horas);// se llama ventana para ejecutar algoritmo
                //dialog.setVisible(true);
                Ventana_definir_cargas_uniforme dialog = new Ventana_definir_cargas_uniforme(new javax.swing.JFrame(), true,entradaGrasps,listaSelec,capDescarga,horas);
                dialog.setVisible(true);
                
                dispose();
            }
        
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void uniformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uniformeMouseClicked
        eventoBoton=1;// si se elige opcion uniforme
        for(int i=0;i<campos.size();i++){ // se desabilitan los campos de texto
            campos.get(i).setEnabled(false);
        }
    }//GEN-LAST:event_uniformeMouseClicked

    private void variableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_variableMouseClicked
        eventoBoton=2; // si se elige opcion variable
        for(int i=0;i<campos.size();i++){ // se habilitan los campos de texto
            campos.get(i).setEnabled(true);
        }
    }//GEN-LAST:event_variableMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.ButtonGroup accion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel_eleccion;
    private javax.swing.JPanel panel_titulo;
    private javax.swing.JRadioButton uniforme;
    private javax.swing.JRadioButton variable;
    // End of variables declaration//GEN-END:variables
}
