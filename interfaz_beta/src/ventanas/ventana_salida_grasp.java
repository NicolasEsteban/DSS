/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;


import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.*;
import static ventanas.ventana_ejecucion_grasp.calcularHora;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import static ventanas.Ventana_definir_bloques.calcularRangoHora;


public class ventana_salida_grasp extends javax.swing.JDialog {

    //boolean[] editable = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
    
    
    ArrayList<Predio> predios = new ArrayList();
    ArrayList<Bloque> bloques = new ArrayList();
    ArrayList<PredioSeleccionado> nombresPredios = new ArrayList();
    ArrayList<Integer> bloquesSeleccionados = new ArrayList();
    ArrayList<String> horasBloque =new ArrayList(); // se guardan horarios de cada bloque
    
    //String horarios[];
    ArrayList<String> horariosBl = new ArrayList();
    ArrayList<Integer> vectorBinario = new ArrayList();
    int frecuenciaCasillas[];
    int frecuencia[];
    
    DateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss"); // se crea el formato de las horas
    
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    void agregar(ArrayList<Predio> listaPredioFinal, ArrayList<Bloque> listaBloqueFinal, ArrayList <PredioSeleccionado> listaSelec ,int bloqueAsignado,int cantidadBloques){ // mostrar datos de los predios en una tabla
        
        int tamano=2+(cantidadBloques*bloqueAsignado); //se calcula tamño del encabezado
        int contador =0;
        int aux=1,auxBloque=1,par=1; // variable auxiliar utilizado para asignar las casillas a cada bloque
        //System.out.println(tamaño);
        vectorBinario.add(-1);
        vectorBinario.add(-1);
        int pos=0;
        
        for(int i=0;i<asignarHorariosBloques(listaBloqueFinal).size();i++){ // se almacena la hora de inicio de cada bloque
            horasBloque.add(asignarHorariosBloques(listaBloqueFinal).get(i));
        }
        /*
        for(int i=0;i<horasBloque.size();i++){
            System.out.println(horasBloque.get(i));
        }*/
        
        for(int i=2;i<tamano;i++){ // se ingresan las horas de inicio y se le asigna una casilla en la tabla
            
            if(aux==(bloqueAsignado+1)){
                aux=1;
            }
         
            if(aux==1){ // se escribe el nombre del bloque correspondiente y se almacena su hora de inicio
                //titulo[i]="  BLOQUE "+auxBloque;
                
                horariosBl.add(horasBloque.get(pos));
                auxBloque++;

                pos++;
            }else{ // en caso de que no se escriba nada

                
                horariosBl.add("|");
            }

            aux++;
        }
        
        
        if(bloqueAsignado>1){ // en caso de que la cantidad de bloques sea mayor a 1
            int bloqueAux=1;
            int num=0;
            while(contador<horasBloque.size()){ // se recorre un arrayList con las horas de inicio de cada bloque
                String bloque = horasBloque.get(contador);
                String[] parts = bloque.split(":");
                num = Integer.parseInt(parts[0]);
                for(int i=bloqueAux;i<(bloqueAux+bloqueAsignado)-1;i++){ // se utiliza un for para recorrer 
                                                                         // siertas partes del arreglo
                    num=num+1;// y rellenar los espacios vacantes
                    int val= num%24;
                    
                    horariosBl.set(i,val+":00:00");
                }
                bloqueAux=bloqueAux+bloqueAsignado;
                contador=contador+1;
               
                
            }
            
            
            for(int i=1;i<horasBloque.size();i++){ // luego se verificara si un bloque necesitara alguna casilla mas 
                int variable=1;
                for(int j=1;j<horariosBl.size();j++){ // se recorre arrayList horariosBl 
                    if(horasBloque.get(i).equals(horariosBl.get(j))){ // si la diferencia es mas de una hora 
                                                                      // se ingresa una casilla mas al medio 
                        variable =j;                                
                        //System.out.println(horariosBl.get(variable));
                    }
                }
                try {
                    Date varRes=dateFormat.parse(horariosBl.get(variable-1));
                    Date var=dateFormat.parse(horariosBl.get(variable));
                    
                    Calendar c = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c.setTime(var);
                    c.add(Calendar.HOUR, -1);
                    
                    c2.setTime(var);
                    c2.add(Calendar.HOUR, -2);
                    
                    String sDate =  dateFormat.format(c.getTime());
                    String sDate2 =  dateFormat.format(c2.getTime());
                    
                    Date varPrin = dateFormat.parse(sDate);
                    Date varSec = dateFormat.parse(sDate2);
                    if(varPrin.compareTo(varRes) >0 && varSec.compareTo(varRes) <0){ // si la diferencia entre la hora de un bloque y el anterior
                                                                                     // es mayor a 1 hora, se crera una casilla en me dio que contenga el tiempo sobrante
                        String[] parts = horariosBl.get(variable).split(":");
                        int num1 = Integer.parseInt(parts[0]);
                        horariosBl.add(variable,num1+":00:00");
                        
                    }
                    
                        
                        if ((dateFormat.parse("00:00:00").compareTo(varRes) <= 0) && (dateFormat.parse("00:59:59").compareTo(varRes) >= 0)){
                        
                        
                        
                         if ((dateFormat.parse("00:00:00").compareTo(varPrin) < 0) && (dateFormat.parse("00:59:59").compareTo(varPrin) > 0)){
                            if ((dateFormat.parse("23:00:00").compareTo(varSec) < 0) && (dateFormat.parse("23:59:59").compareTo(varSec) > 0)){
                                String[] parts = horariosBl.get(variable).split(":");
                                int num1 = Integer.parseInt(parts[0]);
                                horariosBl.add(variable,num1+":00:00");
                                int posicion=variable+2;
                         
                            
                            }
                        
                        
                         }
                        }
                    
                    
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ventana_salida_grasp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
           
            
                       
        }
        
        for(int i=0;i<horasBloque.size();i++){ // aqui se llena de valores el vectorBinario para pintar el encabezado
            int l=(i+1)%horasBloque.size();    // de cada bloque
            int extremoIn=0, extremoFn=0;
            String rangoIn = horasBloque.get(i);
            String rangoFn = horasBloque.get(l);
            for( int j=0;j<horariosBl.size();j++){
                if(rangoIn.equals(horariosBl.get(j))){ // se obtienen las posiciones de cada rango
                    extremoIn=j;
                }
                if(rangoFn.equals(horariosBl.get(j))){
                    if(j!=0){
                        extremoFn=j;
                    }else{
                        extremoFn=horariosBl.size();
                    }
                }
            }
            //System.out.println(extremoFn-extremoIn);
            for(int k=0;k<(extremoFn-extremoIn);k++){ // se ocupan los rangos para llenar el vector binario
                if(par%2==0){                         // y asi pintar cada bloque en la tabla
                    vectorBinario.add(1);
                }else{
                    vectorBinario.add(0);
                }
            }
            par++;
        }
        
        boolean[] editable = new boolean[vectorBinario.size()]; //arreglo para no editar tabla
        
        for(int i=0;i<editable.length;i++){ // arreglo booleano se llena con puros false
            editable[i]=false;
        }
        
        DefaultTableModel modelo = new DefaultTableModel(){ 
            public boolean isCellEditable(int row, int column){
                    return editable[column];
            } 
        };
        
        frecuenciaCasillas = new int[horariosBl.size()]; // se ocupa para almacenar la frecuencia en cada casilla
                                                         // de la tabla 
        for(int i=0;i<frecuenciaCasillas.length;i++){    // se inicializa arreglo con puros ceros
            frecuenciaCasillas[i]=0;
        }
        
        String[] titulo = new String[vectorBinario.size()];
        titulo[0]="PREDIO";
        titulo[1]="TIEMPO DE INICIO DE COSECHA";
              
        
        for(int i=2 ;i<vectorBinario.size();i++){
            titulo[i]="";
        }
        
        for(int i=0;i<horasBloque.size();i++){ // se escriben los encabezados de cada bloque 
            for(int j=0;j<horariosBl.size();j++){
                if(horasBloque.get(i).equals(horariosBl.get(j))){
                    
                    titulo[j+2]="<html><center>BLOQUE "+(i+1)+"<br>"+"("+horasBloque.get(i)+")";
                }
            }
        }
        
        
        
        
        modelo.setColumnIdentifiers(titulo); // se guarda el arreglo de las cabeceras para mostrarlas en la tabla
        tabla_salida.setModel(modelo);
        
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer headerRenderer2 = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.gray);// color de camp        
        headerRenderer2.setBackground(Color.white);// color de campo
        //headerRenderer.setForeground(Color.green);// color de texto
        
        for (int i = 2; i < tabla_salida.getModel().getColumnCount(); i++) { // se pintan las casillas por bloque
            if(vectorBinario.get(i)==0){ // dependiendo en los valores del vector se ocuparan para pintar las casillas de la cabecera
                tabla_salida.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }
            if(vectorBinario.get(i)==1){
                tabla_salida.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer2);
            }
        }
        
        
        
        
        
        for(int i=0;i<listaPredioFinal.size();i++){ // se recorre la lista de los predios seleccionados
            Object fila[] = new Object[vectorBinario.size()];
            fila[0]="  "+listaSelec.get(i).getNombre_predio(); // se guarda el nombre y la hora de inicio de cosecha
            fila[1]="  "+calcularHora(listaPredioFinal.get(i).y);
            
            
            for (Carga c : listaPredioFinal.get(i).C) { // se recorren las llegadas de cada carga
                
                try {
                    //System.out.println(calcularHora(c.x));
                    Date llegadaCarga= dateFormat.parse(calcularHora(c.x)); // se obtiene el numero de la casilla en la que ira la carga
                    int numCasilla= buscarCasilla(llegadaCarga,horariosBl);
                    
                    
                    fila[numCasilla+2]="  "+calcularHora(c.x); // posteriormente se almacena en esa posicion
                    frecuenciaCasillas[numCasilla]=frecuenciaCasillas[numCasilla]+1; // y se modifica el vector para saber cuantas cargas hay en cada casilla
                    //System.out.println(dateFormat.format(llegadaCarga));
                } catch (ParseException ex) {
                    Logger.getLogger(ventana_salida_grasp.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            modelo.addRow(fila); // se ingresa cada fila 
        }

        // se configuran el tamaño de estas dos columnas
        tabla_salida.getColumn("PREDIO").setPreferredWidth(230);
        tabla_salida.getColumn("TIEMPO DE INICIO DE COSECHA").setPreferredWidth(170);
        
       
        
    }
    
    public ventana_salida_grasp(java.awt.Frame parent, boolean modal,ArrayList<Predio> listaPredioFinal,ArrayList <Bloque> listaBloqueFinal,ArrayList <PredioSeleccionado> listaSelec,int cantidadBloques) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        tabla_salida.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // se desabilita e auto ajuste de las columnas
        
        predios.clear();
        bloques.clear();
        nombresPredios.clear();
        bloquesSeleccionados.clear();
        horariosBl.clear();
        horasBloque.clear();
        vectorBinario.clear();
        
        int bloquesAsignado;
        if(cantidadBloques<24){ // en caso de que se divida el horario en un numero de bloques menos a 24
            String rango= calcularRangoHora(1440/cantidadBloques);
            String[] parts = rango.split(":");
        /*for(int i=0;i<parts.length;i++){
            System.out.println(parts[i]);
        }*/
            bloquesAsignado = Integer.parseInt(parts[0]);
            if(Integer.parseInt(parts[1])>0){
                bloquesAsignado=bloquesAsignado+1;    
            }
            
        }else{
            bloquesAsignado=1;
        }
        
        //System.out.println(bloquesAsignado);
        agregar(listaPredioFinal,listaBloqueFinal,listaSelec,bloquesAsignado,cantidadBloques);
        
        
        for(int i=0;i<listaPredioFinal.size();i++){ // se traspasan los datos a la variable de estas ventana
            predios.add(listaPredioFinal.get(i));
            nombresPredios.add(listaSelec.get(i));
        }
        
        for(int i=0;i<listaBloqueFinal.size();i++){
            bloques.add(listaBloqueFinal.get(i));
        }
        // Se ordenan bloques seleccionados
        Collections.sort(bloquesSeleccionados);
        
        frecuencia=frecuencia_camiones(horariosBl,horasBloque,frecuenciaCasillas);
        
        
        

        // graficas
        
        
        // se ingresan valores para crear el grafico de barra
        for(int i=0;i<frecuencia.length;i++){
            if(frecuencia[i]>0){
                int l=i+1;
                String caracter = Integer.toString(l);
                dataset.addValue(frecuencia[i], "Bloques",caracter);
            }
           
        }
        
        JFreeChart chart = ChartFactory.createBarChart(
        "Grafica de barras", // El titulo de la gráfica
        "Bloques de horario", // Etiqueta de categoria
        "Frecuencia Camiones", // Etiqueta de valores
        dataset, // Datos
        PlotOrientation.VERTICAL, // orientacion
        true, // Incluye Leyenda
        true, // Incluye tooltips
        false // URLs?
        ); 
        
       
        //ChartFrame frame = new ChartFrame("Graficador", chart);
        ChartPanel panel = new ChartPanel(chart);
        
        panel_grafico.add(panel);
        panel_grafico.removeAll();
        panel_grafico.setLayout(new java.awt.BorderLayout());
        panel_grafico.add(panel);
        panel_grafico.validate();
        
        
        
        
    }
    
    public static int[] frecuencia_camiones(ArrayList<String> horariosBl,ArrayList<String> horasBloque,int[] frecuenciaCasillas){
        int frecuencia[]=new int[horasBloque.size()];
        
        for(int i=0;i<frecuencia.length;i++){
            frecuencia[i]=0;    
        }
        
        for(int i=0;i<horasBloque.size();i++){
            int l=(i+1)%horasBloque.size();
            int ini=0,fin=0;
            String rangoIn=horasBloque.get(i);
            String rangoFn=horasBloque.get(l);
            
            for( int j=0;j<horariosBl.size();j++){
                if(rangoIn.equals(horariosBl.get(j))){
                    ini=j;
                }
                if(rangoFn.equals(horariosBl.get(j))){
                    if(j!=0){
                        fin=j;
                    }else{
                        fin=horariosBl.size();
                    }
                }
            }
            //System.out.println(ini+" "+fin);
            int k=ini;
            while(k<fin){
                frecuencia[i]=frecuencia[i]+frecuenciaCasillas[k];
                k++;
            }
        }
        
        /*for(int i=0;i<frecuencia.length;i++){
            System.out.println(frecuencia[i]);
        }*/
        //System.out.println(frecuenciaCasillas.length+" "+horariosBl.size());
        
        //System.out.println("\n");
        return frecuencia;
    }

    public  int buscarCasilla(Date llegadaCarga, ArrayList<String> horarios){
       int j=0;
        try {
            
            for(int i=0;i<horarios.size();i++){
                int l=(i+1);
                if(l==horarios.size()){
                    l=0;
                }
                Date rangoIn = dateFormat.parse(horarios.get(i));
                Date rangoFin = dateFormat.parse(horarios.get(l));
                
                
                if((rangoIn.compareTo(llegadaCarga))<=0 && (rangoFin.compareTo(llegadaCarga)>0)){
                    j=i;
                    
                    return j;
                }else if(rangoFin.compareTo(rangoIn)<0){
                    if(rangoIn.compareTo(llegadaCarga)<=0){
                        if(rangoFin.compareTo(dateFormat.parse("0:00:00"))>=0 && rangoFin.compareTo(dateFormat.parse("0:59:59"))<0 ){
                            j=i;
                            return j;
                        }
                    }
                }
                
                
            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(ventana_salida_grasp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        return j;
        
    }
    
    public static ArrayList<String> asignarHorariosBloques(ArrayList<Bloque> listaBloque){
        int i=0;
        ArrayList<String> horasBloque = new ArrayList();
        for (Bloque b : listaBloque) {
            i++;
            horasBloque.add(calcularHora(b.a));
            //System.out.println("Bloque "+i+": Tinicio->"+calcularHora(b.a)+" CapDesc->"+b.N+" CS->"+b.CS+" CF->"+b.CF);
        }
        return horasBloque;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_salida = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panel_grafico = new javax.swing.JPanel();
        panel_titulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_salida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_salida);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 990, 270));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 640, 120, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Descargar Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 170, 170, 50));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Descargar Pdf");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 240, 170, 50));
        getContentPane().add(panel_grafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 990, 280));

        panel_titulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Resultado planificacion zona sur");
        panel_titulo.add(jLabel1);

        getContentPane().add(panel_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 990, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo-tomates.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ModeloExcel me = new ModeloExcel();
        try {
            me.salida_grasp(predios, bloques, nombresPredios,horasBloque,horariosBl,vectorBinario,frecuencia);
        } catch (ParseException ex) {
            Logger.getLogger(ventana_salida_grasp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error en la creacion del archivo", " Mensaje", JOptionPane.WARNING_MESSAGE); 
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ModeloPdf mp = new ModeloPdf();
        mp.crearPdf(predios, bloques, nombresPredios,horasBloque,horariosBl,frecuenciaCasillas);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_grafico;
    private javax.swing.JPanel panel_titulo;
    private javax.swing.JTable tabla_salida;
    // End of variables declaration//GEN-END:variables
}
