
package ventanas;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.*;

public class ventana_ejecucion_grasp extends javax.swing.JDialog {
    
    int contadorBarra=0;
    TablaInputGrasp tv = new TablaInputGrasp();
    //DecimalFormat formato = new DecimalFormat("#.0");
    public static ArrayList<Predio> inputGrasp = new ArrayList(); // almacena los datos que se ingresaran a grasp
    public static ArrayList<PredioSeleccionado> listaSelec = new ArrayList();
    public static ArrayList<Date> horas = new ArrayList();
    public static int capDescarga[];
    DateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss"); // se crea el formato de las horas
    
    // variables Grasp
    public static int n;    //Numero de bloques del dia.
    public static int I;    //Numero de predios a cosechar.
    public static float alpha;    //constante de criterio para seleccion de aleatoriedad
    public static int[] turnos = {0, 360, 600};  //turnos expresados en minutos.
    
    public static int ciclos = 1000;
    
    
    public static int probando=0;
    public ventana_ejecucion_grasp(java.awt.Frame parent, boolean modal,ArrayList<Predio> entradaGrasp,ArrayList<PredioSeleccionado> listaS,int[] capDes,ArrayList<Date> hr) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        //tabla_igrasp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jpredios.setText(String.valueOf(entradaGrasp.size()));
        jpredios.setEditable(false);
        jpredios.setHorizontalAlignment(JTextField.CENTER);
        int suma_cargas=0;
        
        listaSelec.clear();
        inputGrasp.clear();
        horas.clear();
        
        for(int i=0;i<entradaGrasp.size();i++){
            suma_cargas=suma_cargas+entradaGrasp.get(i).getNumC();
        }
        
        jcargas.setText(String.valueOf(suma_cargas));//ingreso del valor de las cargas el campo correspondiente
        jcargas.setEditable(false);
        jcargas.setHorizontalAlignment(JTextField.CENTER);
        
        for(int i=0;i<entradaGrasp.size();i++){// los datos se pasan a inputGrasp
            inputGrasp.add(entradaGrasp.get(i));
        }
        
        for(int i=0;i<listaS.size();i++){// los datos se pasan a listaSelec
            listaSelec.add(listaS.get(i));
        }
        
        for(int i=0;i<hr.size();i++){// los datos se pasan al arreglo horas
            horas.add(hr.get(i));
        }
        
        /*for(int i=0;i<horas.size();i++){
                System.out.println(dateFormat.format(horas.get(i)));
        }*/
        
        capDescarga =new int[capDes.length]; // se le asigna la cantidad de bloques
        for(int i=0;i<capDes.length;i++){//se pasan las capacidad de descarga de cada bloque
            capDescarga[i]=capDes[i];
        }
        
        
         
        
        tv.visualizar(tabla_igrasp, inputGrasp, listaSelec);
        
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_igrasp = new javax.swing.JTable();
        jpredios = new javax.swing.JTextField();
        jcargas = new javax.swing.JTextField();
        barraProgreso = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 640, 110, 50));

        tabla_igrasp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_igrasp);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 580, 310));
        getContentPane().add(jpredios, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 80, 30));

        jcargas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcargasActionPerformed(evt);
            }
        });
        getContentPane().add(jcargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 80, 30));

        barraProgreso.setStringPainted(true);
        getContentPane().add(barraProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 150, 40));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Cargas: ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Predios:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Ejecutar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 490, 100, 40));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 100, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo plomo.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 170, 310));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Planificacion Zona sur");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 750, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo-tomates.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcargasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcargasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcargasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int rnd,turno;
        int Ztemporal=999;
        ArrayList<Predio> listaPredioFinal = new ArrayList<>();
        ArrayList<Bloque> listaBloqueFinal = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < ciclos; i++) {   
            
            ArrayList<Predio> listaPredio = new ArrayList<Predio>();
            ArrayList<Bloque> listaBloque = new ArrayList<Bloque>();
            
            
            
            
            inicializarParametros(listaPredio, listaBloque);
            
            Predio pElegido;
            rnd = (int) (Math.random()*listaPredio.size()); //elegir aleatoriamente un predio
            //rnd=0;
            pElegido = listaPredio.get(rnd);
            turno = elegirTurno(pElegido); //elegir el turno en que inicie el predio
            //turno=0;
            pElegido.y = turno; //asignar el turno al predio

            calcularPredio(pElegido, listaBloque);
            actualizarBloques(pElegido, listaBloque);        
            
            while(prediosPendientes(listaPredio)){
                //Lista para candidatos
                ArrayList<Predio> listaPrediosCandidatos = new ArrayList<>();

                for (Predio P : listaPredio){
                    if (P.y == -1) {    //Si el predio no tiene turno asignado.
                        for (Integer turn : posiblesTurnos(P)) {
                            Predio PredioPiloto = new Predio(P.id, P.numC, P.h, P.t, turn);    //copiar valores en un predio piloto (posible candidato)
                            calcularPredio(PredioPiloto, listaBloque);
                            listaPrediosCandidatos.add(PredioPiloto);
                        }
                    }
                }

                pElegido = elegirCandidato(listaPrediosCandidatos,listaBloque);
                listaPredio.get((pElegido.id)-1).y = pElegido.y;
                calcularPredio(listaPredio.get((pElegido.id)-1), listaBloque);
                actualizarBloques(listaPredio.get((pElegido.id)-1), listaBloque);
            }


            /******************************************************************************/
            System.out.print("Ciclo "+i+" => F.O.: "+funcionObjetivo(listaBloque));
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            busquedaLocal(listaBloque, listaPredio);
            
            //System.out.println(" + BusLocal: "+funcionObjetivo(listaBloque));
            
            if (funcionObjetivo(listaBloque)<=Ztemporal) {                
                Ztemporal = funcionObjetivo(listaBloque);
                listaBloqueFinal = (ArrayList<Bloque>) listaBloque.clone();
                listaPredioFinal = (ArrayList<Predio>) listaPredio.clone();

            }
            
            if((i+1)%250==0){ // para ir incrementando valor de la barra de progreso
            
                contadorBarra=contadorBarra+25;
            }
            barraProgreso.setValue(contadorBarra);
            
        }
        
        System.out.println("**************************************");
        mostrarListaPredios(listaPredioFinal);
        System.out.println("");
        mostrarListaBloques(listaBloqueFinal);
        System.out.println("");
        System.out.println("F.O.: "+funcionObjetivo(listaBloqueFinal));
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("TiempoEjecucion: "+endTime);
        
        for (Predio p : listaPredioFinal) {
            System.out.print("Predio "+p.id+" -> ");
            int i=0;
            for (Carga c : p.C) {
                i++;
                System.out.print(calcularHora(c.x));
                if (i!=p.C.size()) {
                    System.out.print(" | ");
                }
                else{
                    System.out.println("");
                }
            }
        }
        
        //System.out.println("variable probando: "+probando);
        
        
        JOptionPane.showMessageDialog(null,"Ejecucion realizada con exito");
        ventana_salida_grasp dialog = new ventana_salida_grasp(new javax.swing.JFrame(), true,listaPredioFinal,listaBloqueFinal,listaSelec,capDescarga.length);
        dialog.setVisible(true);
        
        
        
        dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void mostrarListaPredios(ArrayList<Predio> listaPredio){
        for (Predio p : listaPredio) {
            System.out.println("Predio "+p.id+": cargas->"+p.numC+" Tcosecha->"+p.h+" Tviaje->"+p.t+" TinicioCosecha->"+calcularHora(p.y));
        }
    }
    
    public static void mostrarListaBloques(ArrayList<Bloque> listaBloque){
        int i=0;
        for (Bloque b : listaBloque) {
            i++;
            System.out.println("Bloque "+i+": Tinicio->"+calcularHora(b.a)+" CapDesc->"+b.N+" CS->"+b.CS+" CF->"+b.CF);
        }
    }
    
    public static void mostrarCargas(Predio p){
        int i=0;
        for (Carga c : p.C) {
            i++;
            //System.out.println("Carga "+i+": Tllegada->"+c.x+" ak->"+c.ak.a+" ak1->"+c.ak1.a+" lambdak->"+c.lambdak+" lambdak1->"+c.lambdak1);
            System.out.println("Carga "+i+": Tllegada->"+c.x+" ak->"+c.ak.a);
            //System.out.println("Carga "+i+": Tllegada->"+c.x);
        }
    }
    
    public void inicializarParametros(ArrayList<Predio> listaPredio, ArrayList<Bloque> listaBloque){
        n = capDescarga.length;    //numero de bloques
        I = inputGrasp.size();    //numero de predios
        //Proyecto.I = 4;
        alpha = (float) 0.5;
        int cargasTotales=0,capDesc=0;
        int ratioMin=0; //porcion de minutos para cada bloque.
        //int horaInicio = 420; //hora en minutos (7:00 am)
        int horaInicio = 0;
        int guardarHorasInicio[] = new int[capDescarga.length];
        
        
        
        ratioMin = 24*60/n; //en minutos.
        
        for(int i=0;i<inputGrasp.size();i++){
            Predio predio = new Predio(inputGrasp.get(i).id, inputGrasp.get(i).numC,inputGrasp.get(i).h , inputGrasp.get(i).t);
            listaPredio.add(predio);
        }
        for(int i=0;i<n;i++){ // se guardan las horas de inicio en un arreglo
            guardarHorasInicio[i]=horaInicio+ratioMin*i;
        }
        
        try{
        for(int i=0;i<n;i++){ // se agrean a bloques sus horarios y su capacidad de descarga 
            //System.out.println("hora "+i+" "+calcularHora(guardarHorasInicio[i]));
            for(int j=0;j<n;j++){
                Date aux;
                aux = dateFormat.parse(calcularHora(guardarHorasInicio[i]));
                
                if(aux.compareTo(horas.get(j))==0){ 
                    listaBloque.add(new Bloque(guardarHorasInicio[i],capDescarga[j]));
                }
            }
        }
        }catch (ParseException ex) {
                    Logger.getLogger(ventana_ejecucion_grasp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        /*
        for (int i = 0; i < n; i++) {
            //listaBloque.add(new Bloque(horaInicio+ratioMin*i,capDescarga[i]));
            listaBloque.add(new Bloque(guardarHorasInicio[i],capDescarga[i]));
        }*/
        
    }
    
    public static boolean prediosPendientes(ArrayList<Predio> Predios){
        return Predios.stream().anyMatch((p) -> (p.y == -1));
    }
    
    //¿Es necesario que la cosecha empieze en horario de turno?
    public static Integer elegirTurno(Predio p){
        float hAux = p.h;
        float tAux = p.t;
        int numCAux = p.numC;
        int random;
        ArrayList<Integer> turnosCandidatos = new ArrayList<>();
        
        float top = (24*60) - ((numCAux*hAux)+tAux);
        
        for (int b : turnos) {
            if (b > top) {
                break;
            }
            else{
                turnosCandidatos.add(b);
            }
        }
        
        random = (int) (Math.random()*turnosCandidatos.size());
        return turnosCandidatos.get(random);
    }
    
    public static Integer funcionSensibilidad(Predio p, ArrayList<Bloque> listaBloque){
        ArrayList<Integer> bloques = new ArrayList<Integer>();
        int bloque = 0,Z=0;
        int CF, CS;
        for (Carga c : p.C) {
            for (Bloque b : listaBloque) {
                if (c.ak.a == b.a) {
                    bloques.add(listaBloque.indexOf(b));
                    break;
                }
            }
        }
        for (Bloque b : listaBloque) {
            CF=b.CF;
            CS=b.CS;
            while (bloques.contains(listaBloque.indexOf(b))) {
                if (CF>0) {
                    CF+=1;
                }
                else if(CS>0){
                    CS-=1;
                }
                else if(CF==0 && CS==0){
                    CF+=1;
                }    
                bloques.remove(bloques.indexOf(listaBloque.indexOf(b)));
            }
            Z+=CF+CS;
        }
        return Z;
    }
    
    //Funcion que calcula horario de llegada de las cargas y asigna bloques
    public static void calcularPredio(Predio P, ArrayList<Bloque> listaBloque){
        int bloque=0,i=1;
        for (Carga c : P.C) {
            c.x = P.y + ((i)*P.h) + P.t; // horario de llegada
            i++;
            for (Bloque b : listaBloque) {
                if (c.x >= b.a) {
                    bloque = listaBloque.indexOf(b); 
                }
                else{
                    
                    
                    break;
                }
            }
            c.ak=listaBloque.get(bloque); // se asigna bloque de a la cual pertenece la carga
        }
    }
    
    //Funcion para actualizar capacidad de los bloques
    public static void actualizarBloques(Predio P, ArrayList<Bloque> listaBloque){
        for (Bloque b : listaBloque){ // verifica FO    se recorren bloques
            for (Carga c : P.C) {                      // se recorren las cargas del predio
                if (c.ak.a == b.a) {    //si pertenece al bloque
                    if (b.CF>0) {
                        b.CF+=1;
                    }
                    else if(b.CS>0){
                        b.CS-=1;
                    }
                    else if(b.CF==0 && b.CS==0){
                        b.CF+=1;
                    }
                }
            }
        }
    }
    
    public static ArrayList<Integer> posiblesTurnos(Predio p){
        ArrayList<Integer> lista = new ArrayList<>();
        float hAux = p.h;
        float tAux = p.t;
        int numCAux = p.numC;
        
        float top = (24*60) - ((numCAux*hAux)+tAux);
        
        for (int b : turnos) {
            if (b > top) {
                break;
            }
            else{
                lista.add(b);
            }
        }
        
        return lista;
    }
    
    public static Predio elegirCandidato(ArrayList<Predio> listaPrediosCandidatos, ArrayList<Bloque> listaBloque){
        int Smin, Smax, random;
        Collections.sort(listaPrediosCandidatos, (Predio p1, Predio p2) -> funcionSensibilidad(p1, listaBloque).compareTo(funcionSensibilidad(p2, listaBloque)) );
        Smin = funcionSensibilidad(listaPrediosCandidatos.get(0), listaBloque);
        Smax = funcionSensibilidad(listaPrediosCandidatos.get(listaPrediosCandidatos.size()-1), listaBloque);
        random = (int) (Math.random()*(listaPrediosCandidatos.size()));
        //System.out.println("Rango -> ["+Smin+", "+(Smin+alpha*(Smax-Smin))+"]");
        while(true){
            if (funcionSensibilidad(listaPrediosCandidatos.get(random), listaBloque)>=Smin && funcionSensibilidad(listaPrediosCandidatos.get(random), listaBloque) <= (Smin+alpha*(Smax-Smin))) {
                //System.out.println("F.S.: "+funcionSensibilidad(listaPrediosCandidatos.get(random), listaBloque));
                return listaPrediosCandidatos.get(random);
            }
            else{
                random = (int) (Math.random()*(listaPrediosCandidatos.size()));
            }
        }
    }
    
    //Funcion Objetivo
    public static Integer funcionObjetivo(ArrayList<Bloque> listaBloque){
        int Z=0;        
        for (Bloque b : listaBloque) {
            Z+=b.CF+b.CS;
        }
        return Z;
    }
    
    public static void busquedaLocal(ArrayList<Bloque> listaBloque, ArrayList<Predio> listaPredio){ 
        ArrayList<Bloque> listaBloqueAux = new ArrayList<Bloque>();
        int valor=999;
        Predio pElegido = null;
        
        for (int i = 0; i < listaPredio.size(); i++) {
            
            listaBloqueAux.clear();
            for (Bloque b : listaBloque) {
                Bloque Bl = new Bloque(b.a, b.N, b.CS, b.CF);
                listaBloqueAux.add(Bl);
            }
        
            Predio P = listaPredio.get(i);
            
            for (Bloque b : listaBloqueAux) {
                for (Carga c : P.C){
                    if (b.a == c.ak.a) {
                        if (b.CF>0) {
                            b.CF--;
                        }
                        else if(b.CS>0){
                            b.CS++;
                        }
                        else if(b.CF==0 && b.CS==0){
                            b.CS++;
                        }
                    }
                }
            }

            for (Integer t : turnos) {
                Predio PredioPiloto = new Predio(listaPredio.get(i).id, listaPredio.get(i).numC, listaPredio.get(i).h, listaPredio.get(i).t, t);
                calcularPredio(PredioPiloto, listaBloqueAux);
                if (valor>funcionSensibilidad(PredioPiloto, listaBloqueAux)) {
                    pElegido = new Predio(PredioPiloto.id, PredioPiloto.numC, PredioPiloto.h, PredioPiloto.t, PredioPiloto.y);
                    valor = funcionSensibilidad(PredioPiloto, listaBloqueAux);
                }
            }
        }
        
        
        /****************************************************/
        Predio P;
        P = listaPredio.get(pElegido.id-1);
        
        for (Bloque b : listaBloque) {
            for (Carga c : P.C){
                if (b.a == c.ak.a) {
                    if (b.CF>0) {
                        b.CF--;
                    }
                    else if(b.CS>0){
                        b.CS++;
                    }
                    else if(b.CF==0 && b.CS==0){
                        b.CS++;
                    }
                }
            }
        }
        
        P.y = pElegido.y;
        //System.out.println("Se movió: Predio "+P.id);
        calcularPredio(P, listaBloque);
        actualizarBloques(P, listaBloque);
    }
    
    public static String calcularHora(float hraMin){
        int hra, min, seg;
        String horafinal;
        
        hra = (int) (hraMin/60)+7;
        min = (int) (hraMin%60);
        seg = (int) ((hraMin%60)*60)%60;
        
        if (hra>23) {
            hra=hra-24;
        }
        
        horafinal=hra+":";
        
        if (min<10) {
            if(seg<10){
                horafinal+="0"+min+":0"+seg;
            }
            else{
                horafinal+="0"+min+":"+seg;
            }
        }
        else if(seg<10){
            horafinal+=min+":0"+seg;
        }
        else{
            horafinal+=min+":"+seg;
        }
        
        return horafinal;
    }
    
    
    
    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ventana_modificar_cargas dialog = new ventana_modificar_cargas(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        
        tv.visualizar(tabla_igrasp, inputGrasp, listaSelec);
        jcargas.setText(String.valueOf(ventana_modificar_cargas.cargasTotales));
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jcargas;
    private javax.swing.JTextField jpredios;
    private javax.swing.JTable tabla_igrasp;
    // End of variables declaration//GEN-END:variables
}
