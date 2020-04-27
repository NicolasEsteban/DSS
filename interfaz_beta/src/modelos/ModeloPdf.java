/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.itextpdf.text.BaseColor;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static ventanas.ventana_ejecucion_grasp.calcularHora;
//import static ventanas.ventana_salida_grasp.buscarBloque;








public class ModeloPdf {
    
    DateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss"); // se crea el formato de las horas
    
    public void crearPdf(ArrayList<Predio> predios,ArrayList<Bloque> bloques,ArrayList<PredioSeleccionado> nombresPredios,ArrayList<String> horasBloque,ArrayList<String> horariosBl, int[] frecuenciaCasillas ){
        String ruta= System.getProperty("user.home") + "\\desktop"; 
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy"); 
        Date date = new Date();
        
        String Dest=ruta+"\\Reporte planificacion del dia '"+formateador.format(date)+"'.pdf";
        
        
        
        Document document = new Document();
        
        try{
            PdfWriter.getInstance(document, new FileOutputStream(Dest));
            document.open();
            // Se crea tabla en pdf
            PdfPTable tablaTitulo = new PdfPTable(3);
            PdfPCell primeraCelda = new PdfPCell(new Paragraph(" "));
            primeraCelda.setColspan(1);// se detalla columna que se ocupara en la tabla
            primeraCelda.setRowspan(2);// se detalla fila que se ocupara en la tabla
            tablaTitulo.addCell(primeraCelda);// se ingresan parrafos a la tabla
            // Se crea estilo en el parrafo
            Paragraph columna1 = new Paragraph("    Reporte Sistema");
            columna1.getFont().setStyle(Font.BOLD);
            columna1.getFont().setSize(15);
            columna1.setAlignment(1);
            
            tablaTitulo.addCell(columna1);
            
            
            //System.out.println(formateador.format(date));
            
            Paragraph columnaFinal =new Paragraph("    "+formateador.format(date));//se crea un parrafo
            columnaFinal.getFont().setStyle(Font.BOLD); // se le da un estilo
            PdfPCell celdaFinal = new PdfPCell(columnaFinal); // se agrega ese parrafo a la celda creada
         
            celdaFinal.setColspan(1);// se asigna la columna y la fila que ocupara la celda
            celdaFinal.setRowspan(2);
            
            tablaTitulo.addCell(celdaFinal); // se agrega la celda a la tabla
            // Estilos
            Paragraph columna2 = new Paragraph("    Sistema de\n    Planificacion de\n    Cosecha");
            columna2.getFont().setStyle(Font.BOLD);
            columna2.getFont().setSize(15);
            columna2.setAlignment(1);
            
            tablaTitulo.addCell(columna2);
            tablaTitulo.setWidthPercentage(100);
            // Se agrega tabla
            document.add(tablaTitulo);
            
            document.add(new Paragraph("\n\n"));// se crean saltos de linea en el documento
            
            int ini=0,fin=0;
            int nCiclos=(int) Math.ceil((float)predios.size()/3);
            
            
            for(int i=0;i<nCiclos;i++){ // ciclo para crear las tablas en las que se mostraran los resultados
                fin=fin+3;
                if(fin>predios.size()){
                    fin = predios.size();
                }

                PdfPTable tablaDatos=crear_tabla(ini,fin,predios,bloques,nombresPredios,horasBloque,horariosBl);  // se ingresa tabla 
                //if(fin-ini==3){
                tablaDatos.setWidthPercentage(100);
                    
                //}
                
                document.add(tablaDatos);
                document.add(new Paragraph("\n\n"));// se crean saltos de linea en el documento
                ini=fin;
            }
            
            // se crea tabla para observaciones
            Paragraph columnaObs = new Paragraph("Observaciones: \n\n");
            columnaObs.getFont().setStyle(Font.BOLD);
            
            //columnaObs.getFont().setSize(18);
            
            
            document.add(new Paragraph("\n\n"));
            document.add(columnaObs);
            PdfPTable tablaObservacion = new PdfPTable(8);
            
            PdfPCell celdaObservacion = new PdfPCell(new Paragraph("\n\n\n\n\n\n\n\n"));
            celdaObservacion.setColspan(8);
            //celdaObservacion.setRowspan(1);
            tablaObservacion.addCell(celdaObservacion);
            tablaObservacion.setWidthPercentage(100);
            document.add(tablaObservacion);
            
            document.close();
            JOptionPane.showMessageDialog(null,"Reporte del dia '"+formateador.format(date)+"' creado correctamente");
        }catch(Exception e)
        {
            //System.err.println("Ocurrio un error al crear el archivo");
            
            JOptionPane.showMessageDialog(null,"Error en la creacion del archivo PDF", " Mensaje", JOptionPane.WARNING_MESSAGE); 
        }
        
              
    }
    
    public PdfPTable crear_tabla(int ini,int fin,ArrayList<Predio> predios,ArrayList<Bloque> bloques,ArrayList<PredioSeleccionado> nombresPredios,ArrayList<String> horasBloque,ArrayList<String> horariosBl){
        int dif=fin-ini;
        PdfPTable tablaDatos = new PdfPTable(1+dif);
        
        // se ingresan los predios
        Paragraph columnaDatos = new Paragraph("\n PREDIOS\n");
        columnaDatos.getFont().setStyle(Font.BOLD);
        columnaDatos.getFont().setSize(10);   
        
        
        PdfPCell celda = new PdfPCell();
        // estilo de la celda
        celda.addElement(columnaDatos);
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY); 
        celda.setBorder(0);

       
       
        
        tablaDatos.addCell(celda);
        // se ingresan los nombres de los predios
        for(int i=ini;i<fin;i++){ // se ingresan nombres de predios
            columnaDatos=new Paragraph(nombresPredios.get(i).getNombre_predio()+"\n");
            columnaDatos.getFont().setStyle(Font.BOLD);
            columnaDatos.getFont().setSize(10);
            celda = new PdfPCell();
            
            celda.addElement(columnaDatos);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            celda.setBorder(0);
            tablaDatos.addCell(celda);
        }
        
        // se ingresan etiqueta de tiempo de inicio de cosecha
        columnaDatos = new Paragraph("\nTIEMPO DE INICIO DE COSECHA\n");
        columnaDatos.getFont().setStyle(Font.BOLD);
        columnaDatos.getFont().setSize(10);
        // se crea celda y se le da un estilo
        celda = new PdfPCell();
        celda.addElement(columnaDatos);
        celda.setBackgroundColor(BaseColor.GRAY);
        celda.setBorder(0);
      
        
        tablaDatos.addCell(celda);
        
        // se ingresan los tiempos de inicio de cada cosecha de cada uno de los predios
        for(int i=ini;i<fin;i++){
            columnaDatos=new Paragraph("\n  "+calcularHora(predios.get(i).y)+"\n");
            columnaDatos.getFont().setStyle(Font.BOLD);
            columnaDatos.getFont().setSize(10);
            celda = new PdfPCell();
            
            celda.addElement(columnaDatos);
           
            celda.setBackgroundColor(BaseColor.GRAY);
 
            celda.setBorder(0);           
            tablaDatos.addCell(celda);
        }
        
        int posBloques[]=new int[horasBloque.size()]; // arreglo para almacenar las posiciones de las horas de inicio de cada bloque
        ArrayList<Integer>numBloques= new ArrayList<>();// en este ArrayList se almacenaran solo los bloque que son ocupados
        
        
        for(int i=0;i<horasBloque.size();i++){ // se gurdan posiciones de cada inicio de cosecha 
            for(int j=0;j<horariosBl.size();j++){
                if(horasBloque.get(i)==horariosBl.get(j)){
                    posBloques[i]=j;
                }
            }
        }
        
        numBloques.clear();
        numBloques.addAll(seleccionandobloques(ini,fin,posBloques,horariosBl,predios)); // se guardan los bloques en donde 
        Collections.sort(numBloques);
        
        
        String cargasXbloque [] [] = new String [numBloques.size()] [1+dif]; // matriz para ingresar las cargas a las tablas del pdf
        
        for (int i=0; i < cargasXbloque.length; i++) { // se dejan campos vacios 
            for (int j=0; j <cargasXbloque[i].length; j++) {
                cargasXbloque[i][j]=" ";
            }
        }
        
        for(int i=0;i<cargasXbloque.length;i++){ // en la primera columna se agregan los numeros de bloques en la primera columna de la matriz
            cargasXbloque[i][0]="BLOQUE "+(numBloques.get(i)+1);
        }
        

        int col=1;
        for(int i=ini;i<fin;i++){
            
            for (Carga c : predios.get(i).C) { // se empiezan a leer las cargas de cada predio
                int k=0;
                // se obtiene el bloque al cual pertenece la carga que se esta leyendo
                int bloquePerteneciente=obtenerBloque(posBloques,horariosBl,predios,calcularHora(c.x)); 
                //sabiendo el bloque se obtiene la fila y con el contador col se buscara la columna correspondiente
                for(k=0;k<numBloques.size();k++){
                    if(numBloques.get(k)==bloquePerteneciente){ // ingresa el numero de carga en la matriz
                        cargasXbloque[k][col]=cargasXbloque[k][col]+calcularHora(c.x)+"\n  ";
                    }
                }
               
                
            }
            col++;
        }
        
        for (int i=0; i < cargasXbloque.length; i++) { // finalmente se usa los dos ciclos for para escribir los elementos de la matriz en el Pdf
            for (int j=0; j <cargasXbloque[i].length; j++) {
           
                columnaDatos=new Paragraph(" "+cargasXbloque[i][j]);
                columnaDatos.getFont().setStyle(Font.BOLD);            
                columnaDatos.getFont().setSize(10);
                
                celda = new PdfPCell();
            
                celda.addElement(columnaDatos);
                if(i%2==0){
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                }else{
                    celda.setBackgroundColor(BaseColor.GRAY);
                }
                celda.setBorder(0);
              
                
                tablaDatos.addCell(celda);
                
            }
        }
        
        return tablaDatos;     
    }
    
    public  int buscarCasilla(Date llegadaCarga, ArrayList<String> horarios){ // funcion para saber posicion de la carga
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
            Logger.getLogger(ModeloPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        return j;
        
    }
    
    public ArrayList<Integer> seleccionandobloques(int ini,int fin,int[] posBloques,ArrayList<String> horariosBl,ArrayList<Predio> predios){
        
        ArrayList<Integer>numBloques= new ArrayList<>();
        
        
        for(int i=ini;i<fin;i++){ // se recorre la lista de los predios seleccionados para saber los bloques que estaran
            for (Carga c : predios.get(i).C) { 
                //System.out.println(calcularHora(c.x));
                
                 try {
                    //System.out.println(calcularHora(c.x));
                    Date llegadaCarga= dateFormat.parse(calcularHora(c.x)); // se obtiene el numero de la casilla en la que ira la carga
                    int numCasilla= buscarCasilla(llegadaCarga,horariosBl);
                    
                    for(int j=0;j<posBloques.length;j++){
                        if(j==(posBloques.length-1)&& numCasilla>=posBloques[j]){
                            //System.out.println(j);
                            numBloques.add(j);
                        }else if(numCasilla>=posBloques[j] && numCasilla<posBloques[j+1]){
                            //System.out.println(j);
                            numBloques.add(j);
                        }
                        
                    }
                    
                    
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ModeloPdf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Set<Integer> hashSet = new HashSet<Integer>(numBloques);
        numBloques.clear();
        numBloques.addAll(hashSet);
        
        
        return numBloques;
        
    }
    
    public int obtenerBloque(int[] posBloques,ArrayList<String> horariosBl,ArrayList<Predio> predios,String hora){
        // se obtiene el bloque al cual pertenece la carga que se ingrese
        
        int aux=0;
        
        
            
                //System.out.println(calcularHora(c.x));
                
                 try {
                    //System.out.println(calcularHora(c.x));
                    Date llegadaCarga= dateFormat.parse(hora); // se obtiene el numero de la casilla en la que ira la carga
                    int numCasilla= buscarCasilla(llegadaCarga,horariosBl);
                    
                    for(int j=0;j<posBloques.length;j++){
                        if(j==(posBloques.length-1)&& numCasilla>=posBloques[j]){
                            aux=j;
                            return aux;
                        }else if(numCasilla>=posBloques[j] && numCasilla<posBloques[j+1]){
                            //System.out.println(j);
                            
                            aux=j;
                            return aux;
                        }
                        
                    }
                    
                    
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ModeloPdf.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        
        
        
        
        
        return aux;
        
    }
    
}
