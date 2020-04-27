
package modelos;

import java.io.File;
import java.sql.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.IOUtil;
import static ventanas.ventana_ejecucion_grasp.calcularHora;
//import ventanas.ventana_salida_grasp;
//import static ventanas.ventana_salida_grasp.buscarCasilla;






public class ModeloExcel {
    
    DateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss"); // se crea el formato de las horas
   
   public void reporte(){
       // se crea libro en excel
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("ZONA SUR");
        try {
            
            
            //cell.setCellStyle(cs);
            
            
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            
            // estilo para titulo 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Informacion de los predios en detalle");
            
            // se crean estilos para los encabezados en el reporte
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
            
            
            
            
            String[] cabecera = new String[]{"Tecnico", "Productor", "Super men", "Nombre del Predio", "Comuna donde pretenece el predio","Distancia con talca en KM"};
            Row filaEncabezados = sheet.createRow(3);
            
           
            for (int i = 0; i < cabecera.length; i++) {// imprime encabezado en excel
                
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
                
                
            }
            
            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConexion();
            
            int numFilaDatos = 4;
            
            ps = conn.prepareStatement("SELECT tecnico,productor,super_men,nombre_predio,comuna_predio,km_talca cantidad FROM PREDIO");
            rs = ps.executeQuery();
            
            CellStyle prueba = book.createCellStyle();
            prueba.setAlignment(HorizontalAlignment.CENTER);
            prueba.setVerticalAlignment(VerticalAlignment.CENTER);
            
            int numCol = rs.getMetaData().getColumnCount(); // se calcula la cantidad de variables que trae la consulta
            while (rs.next()){ // 
                
                Row fila_datos= sheet.createRow(numFilaDatos); // se crea la fila que se usara
                for (int i = 1; i <= numCol; i++) {// se van registrando los datos en el archivo
                Cell celdadatos = fila_datos.createCell(i-1); 
                celdadatos.setCellStyle(prueba);
                celdadatos.setCellValue(rs.getString(i));
                sheet.autoSizeColumn(i-1); // se van ajustando las columnas 
                
                }
                
                numFilaDatos++;
                
                               
            }
            
            String ruta= System.getProperty("user.home") + "\\desktop"; // se obtiene ruta de escritorio
            //System.out.println(ruta);
            
            FileOutputStream fileOut = new FileOutputStream(ruta+"\\Lista de predios almacenados.xlsx");
            book.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null,"Lista de predios almacenados.xlsx se descargo con exito en su escritorio");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void leer(File archivo) throws IOException {
        //FileInputStream file = new FileInputStream(new File("C:\\Users\\Nicolas\\Desktop\\DISTANCIAS PREDIOS TEMP 15-16.xlsx"));
        ArrayList<String> celdas = new ArrayList<String>();
        try {
            //String rutaArchivoExcel = "C:/Users/Nicolas/Desktop/DISTANCIAS PREDIOS TEMP 15-16.xlsx";
            FileInputStream inputStream = new FileInputStream(archivo);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();
            
            DataFormatter formatter = new DataFormatter();
            int verificador=0; // para verificar si la fila tiene una determinada columna
            
            /*Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConexion();*/
            
            while (iterator.hasNext()) { // se leen filas
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                //System.out.println("\n");
                int j=0;
                while(cellIterator.hasNext()) { // se leen columnas
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    if(verificador==1){
                        celdas.add(contenidoCelda);
                    }
                    j++;
                }
                if(j==6){ // si en la fila hay 6 columnas el verificador cambia de valor
                    verificador=1;
                }                                
            }
            
            int filas= celdas.size()/6,aux=0;
            PredioModel in = new PredioModel();
            for(int i=0;i<filas;i++){
               in.insertar_predio(celdas,aux);              
               aux=aux+6;
                
            }
            
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public void salida_grasp(ArrayList<Predio> predios,ArrayList<Bloque> bloques,ArrayList<PredioSeleccionado> nombresPredios,ArrayList<String> horasBloque,ArrayList<String> horariosBl,ArrayList<Integer> vectorBinario,int [] frecuencia) throws ParseException{
       
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("PLANIFICACION ZONA SUR");
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy"); 
        Date date = new Date();
        //System.out.println(formateador.format(date));
        try {
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setWrapText(true);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            
            // estilos para el titulo
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
            
            // se describe la ubicacion del titulo y la descripcion
            Row filaTitulo = sheet.createRow(0);
            Cell celdaTitulo = filaTitulo.createCell(0);
            celdaTitulo.setCellStyle(tituloEstilo);           
            celdaTitulo.setCellValue("Resultado planificacion zona sur");
            sheet.addMergedRegion(new CellRangeAddress(0,1,0,4));
            
            // se crean estilos para los encabezados en el reporte
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
                 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            
            headerStyle.setFont(font);
            
            Row filaEncabezadosPlanificacion = sheet.createRow(3); // asegura una  fila para colocar el encabezado
            String[] cabeceraPlanificacion = new String[vectorBinario.size()];
            cabeceraPlanificacion[0]=" PREDIOS\n";
            cabeceraPlanificacion[1]=" TIEMPO DE INICIO DE COSECHA\n";
            
            
            for(int i=2 ;i<vectorBinario.size();i++){ // se inicializa el vector
                cabeceraPlanificacion[i]="";
            }
        
            for(int i=0;i<horasBloque.size();i++){ // se escriben los encabezados de cada bloque 
                for(int j=0;j<horariosBl.size();j++){
                    if(horasBloque.get(i).equals(horariosBl.get(j))){                    
                        cabeceraPlanificacion[j+2]=" BLOQUE "+(i+1)+"\n "+"("+horasBloque.get(i)+")";
                    }
                }
            }
            
            for(int i=0;i<cabeceraPlanificacion.length;i++){ // se rellenan los espacios en blanco 
                if(cabeceraPlanificacion[i].equals("")){
                    cabeceraPlanificacion[i]="\n "+"("+horariosBl.get(i-2)+")";
                }
            }
            
             // cabezera de la tabla que mostrara la planificacion
            for (int i = 0; i < cabeceraPlanificacion.length; i++) {// imprime encabezado en excel para la tabla de planificacion
                
                Cell celdaEnzabezado = filaEncabezadosPlanificacion.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabeceraPlanificacion[i]);
                sheet.autoSizeColumn(i);                 
            }
            
            // estilos para el contenido de la tabla 
            CellStyle prueba = book.createCellStyle();
            prueba.setAlignment(HorizontalAlignment.CENTER);
            prueba.setVerticalAlignment(VerticalAlignment.CENTER);
            
            int filaDatosPlanificacion=4;
            
            for(int i=0;i<predios.size();i++){
                int j=0;
                Row fila_planificacion= sheet.createRow(filaDatosPlanificacion);
                Cell celdadatos = fila_planificacion.createCell(j); 
                celdadatos.setCellStyle(prueba);
                celdadatos.setCellValue(nombresPredios.get(i).getNombre_predio()); // se coloca en una celda el nombre del predio
                sheet.autoSizeColumn(j);
                j=1;
                celdadatos = fila_planificacion.createCell(j); 
                celdadatos.setCellStyle(prueba);
                celdadatos.setCellValue(calcularHora(predios.get(i).y)); // se escribe el horario en su respectiva celda  
                sheet.autoSizeColumn(j);
                
                
                for (Carga c : predios.get(i).C) {
                    
                    //System.out.println(calcularHora(c.x));
                    Date llegadaCarga= dateFormat.parse(calcularHora(c.x)); // se obtiene el numero de la casilla en la que ira la carga
                    j= buscarCasilla2(llegadaCarga,horariosBl);
                    
                    celdadatos = fila_planificacion.createCell(j+2); // se obtiene la celda que se pintara
                    celdadatos.setCellStyle(prueba);
                    celdadatos.setCellValue(calcularHora(c.x)); // se escribe el horario en su respectiva celda  
                    sheet.autoSizeColumn(j+2); 
                
                }
                
                filaDatosPlanificacion++;
            }
            
            //segundo estilo para encabezado
            CellStyle encabezado = book.createCellStyle();
            
            encabezado.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setFont(font);
            
            String resultadoBloque [] = {"CARGAS TOTALES","CAPACIDAD FALTANTE","CAPACIDAD SOBRANTE","CAPACIDAD DE DESCARGA"};
            
            Row fila_cargas_totales= sheet.createRow(filaDatosPlanificacion);
            
            Cell celdadatos_totales = fila_cargas_totales.createCell(1); 
            celdadatos_totales.setCellStyle(encabezado); //aqui
            celdadatos_totales.setCellValue(resultadoBloque[0]);
            int carTotales=2;
            int bl=0;
            int ran;
            
            for(int i=0;i<frecuencia.length;i++){
                ran=rango(bl,horasBloque,horariosBl);
                
                celdadatos_totales = fila_cargas_totales.createCell(carTotales); 
                celdadatos_totales.setCellStyle(prueba);
                celdadatos_totales.setCellValue(frecuencia[i]);
                
                if(ran>1){
                    sheet.addMergedRegion(new CellRangeAddress(filaDatosPlanificacion,filaDatosPlanificacion,carTotales,carTotales+ran-1));
                }
                
                carTotales=carTotales+ran;
                
                bl++;
                
                
            }
            filaDatosPlanificacion++;
            bl=0;
            Row fila_capacidad_faltante= sheet.createRow(filaDatosPlanificacion);
            Cell celdadatos_faltante = fila_capacidad_faltante.createCell(1); 
            celdadatos_faltante.setCellStyle(encabezado); //aqui
            celdadatos_faltante.setCellValue(resultadoBloque[1]);
            
           
            
            int capF=2,capS=2,capD=2;
            
            
            
            
            
            for (Bloque b : bloques) {// imprime capacidad faltante en excel
                ran=rango(bl,horasBloque,horariosBl);
                
                celdadatos_faltante = fila_capacidad_faltante.createCell(capF); 
                celdadatos_faltante.setCellStyle(prueba);
                celdadatos_faltante.setCellValue(b.CF);
                if(ran>1){
                    sheet.addMergedRegion(new CellRangeAddress(filaDatosPlanificacion,filaDatosPlanificacion,capF,capF+ran-1));
                }
                capF=capF+ran;
                
                bl++;
                
            }
            
            filaDatosPlanificacion++;
            bl=0;
            
            Row fila_capacidad_sobrante= sheet.createRow(filaDatosPlanificacion);
            Cell celdadatos_sobrante = fila_capacidad_sobrante.createCell(1); 
            celdadatos_sobrante.setCellStyle(encabezado); // aqui
            celdadatos_sobrante.setCellValue(resultadoBloque[2]); 
            
            for (Bloque b : bloques) {// imprime capacidad sobrante en excel
                ran=rango(bl,horasBloque,horariosBl);
                celdadatos_sobrante = fila_capacidad_sobrante.createCell(capS); 
                celdadatos_sobrante.setCellStyle(prueba);
                celdadatos_sobrante.setCellValue(b.CS);
                if(ran>1){
                    sheet.addMergedRegion(new CellRangeAddress(filaDatosPlanificacion,filaDatosPlanificacion,capS,capS+ran-1));
                }
                capS=capS+ran;
                
                bl++;
            }
            
            filaDatosPlanificacion++;
            bl=0;
            
            Row fila_llegadas= sheet.createRow(filaDatosPlanificacion);
            Cell celdadatos_llegadas = fila_llegadas.createCell(1); 
            celdadatos_llegadas.setCellStyle(encabezado); // aqui
            celdadatos_llegadas.setCellValue(resultadoBloque[3]);  
            sheet.autoSizeColumn(1);
            
            for (Bloque b : bloques) {// imprime capacidad de carga en cada bloque
                ran=rango(bl,horasBloque,horariosBl);
                celdadatos_llegadas = fila_llegadas.createCell(capD); 
                celdadatos_llegadas.setCellStyle(prueba);
                celdadatos_llegadas.setCellValue(b.N); 
                if(ran>1){
                    sheet.addMergedRegion(new CellRangeAddress(filaDatosPlanificacion,filaDatosPlanificacion,capD,capD+ran-1));
                }
                capD=capD+ran;
                bl++;
            }
            
            FileOutputStream fileOut;
            String ruta= System.getProperty("user.home") + "\\desktop"; // se obtiene ruta de escritorio
            fileOut = new FileOutputStream(ruta+"\\Planificacion "+formateador.format(date)+".xlsx");
            book.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null,"Planificacion "+formateador.format(date)+" se descargo con exito en su escritorio");
       } catch (FileNotFoundException ex) {
            Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Cerrar el archivo Excel para poder actualizar los datos", " Mensaje", JOptionPane.WARNING_MESSAGE); 
            
        } catch (IOException ex) {
            Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"hola 2");
        } 
        
       
   }
   
   public void plantilla_carga(){
       
       Workbook book = new XSSFWorkbook();
       Sheet sheet = book.createSheet("ZONA SUR");
       
       
       try {
          
           CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            
            // estilo para titulo 
            /*CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Plantilla para cargar datos");*/
            
            // se crean estilos para los encabezados en el reporte
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
            
            
            
            
            String[] cabecera = new String[]{"Tecnico", "Productor", "Super men", "Nombre del Predio", "Comuna donde pretenece el predio","Distancia con talca en KM"};
            Row filaEncabezados = sheet.createRow(0);
            
           
            for (int i = 0; i < cabecera.length; i++) {// imprime encabezado en excel
                
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
                
                sheet.autoSizeColumn(i);
                
            }
            
            String ruta= System.getProperty("user.home") + "\\desktop"; // se obtiene ruta de escritorio
            //System.out.println(ruta);
            
            FileOutputStream fileOut = new FileOutputStream(ruta+"\\Plantilla para cargar datos.xlsx");
            book.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null,"Plantilla creada correctamente");
       
       } catch (FileNotFoundException ex) {
           Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null,"Cerrar archivo");
       } catch (IOException ex) {
           Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
       }
            
       
   }
   
   
   public  int buscarCasilla2(Date llegadaCarga, ArrayList<String> horarios){
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
            Logger.getLogger(ModeloExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        return j;
        
    }
   
   public int rango(int posIn,ArrayList<String> horasBloque,ArrayList<String> horariosBl){ 
       int retorno=0,extremoIn=0,extremoFn=0;
       int l=(posIn+1)%horasBloque.size();
       String rangoIn=horasBloque.get(posIn);
       String rangoFn=horasBloque.get(l);
       
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
       
       retorno=extremoFn-extremoIn;
       
       return retorno;
   }
   
}
