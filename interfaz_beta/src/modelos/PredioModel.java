package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class PredioModel {
    public void insertar_predio(ArrayList<String> celdas,int aux){ // aux indica posicion en el arreglo de string
        String sql = "INSERT INTO PREDIO (tecnico,productor,super_men,nombre_predio,comuna_predio,km_talca,productividad) VALUES(?,?,?,?,?,?,?)";
        
        Conexion con = new Conexion();
        PreparedStatement ps;
        Connection conn = con.getConexion();
        
        
        try { // se ingresa cada elemento en el atributo correspondiente 
            ps = conn.prepareStatement(sql); 
            ps.setString(1,celdas.get(0+aux));
            ps.setString(2,celdas.get(1+aux));
            ps.setString(3,celdas.get(2+aux));
            ps.setString(4,celdas.get(3+aux));
            ps.setString(5,celdas.get(4+aux));
            ps.setInt(6, Integer.parseInt(celdas.get(5+aux)));
            ps.setString(7,"");
            ps.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList llamar_predios(){ // funcion que llama la lista de predios en la vista de planificacion
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConexion();
        ArrayList<PredioSeleccionado> Listapredios = new ArrayList();
        
        try {
            ps = conn.prepareStatement("SELECT id,nombre_predio,super_men FROM PREDIO");
            rs = ps.executeQuery();
            
            while (rs.next()){ // se guardan los datos en un arreglo de tipo objeto 
                //Listapredios.add(rs.getString(1));
                PredioSeleccionado Psel = new PredioSeleccionado(rs.getInt(1),rs.getString(2),rs.getString(3));
                Listapredios.add(Psel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Listapredios;        
    }
    
    public int llamar_predio_seleccionado(int id){ // obtiene el km a partir de dos atributos
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConexion();
        String sql= "SELECT km_talca FROM PREDIO WHERE id=?;"; // se crea string con la consulta

        int km_talca=0;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //ps.setString(2, nombre_predio);
            rs = ps.executeQuery();
            while (rs.next()){  
                km_talca=rs.getInt("km_talca");               
            }
            
            //System.out.println(km_talca);//se imprime km
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return km_talca;
    }
    
    /*public  Predio llamar_predio_seleccionado(int id,int numC){
        Predio pr = new Predio();
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConexion();
        String sql= "SELECT super_men,km_talca FROM PREDIO WHERE id=?;";
        String super_men=null;
        int km_talca=0;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){  
                super_men=rs.getString("super_men");
                km_talca=rs.getInt("km_talca");               
            }
            String[] parts = super_men.split(","); //se separa las partes usando la coma
            String part1 = parts[0]; 
            String part2 = parts[1];
            int velocidad=50;
           
            float tiempo= (float)km_talca/velocidad*60;
            float tiempoC = (float)10/numC*60;
            
            
            pr= new Predio(id,numC,tiempoC,tiempo);
            
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }*/
    
    public ArrayList llamarTodosLosDatos(){ // funcion que llama todo los predios de la bd
        ArrayList<TablaPredio> tablapredios = new ArrayList<TablaPredio>();
        Connection cn = null;
        ResultSet rs = null;
        Conexion c = new Conexion();// se crea instancia de 
        cn = c.getConexion();
        
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery("select id,productor,nombre_predio,km_talca from PREDIO");//consulta 
            
            while (rs.next()){ // se guardan los datos en un arreglo de tipo objeto 
                TablaPredio tp = new TablaPredio(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)));
                tablapredios.add(tp);                
            }
            
            return tablapredios;
            
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tablapredios;
    }
    
    public Object[] llamar_tupla(int id){//llama tupla de datos por medio del id
        Object fila[] = new Object[6];
        
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConexion();
        String sql= "SELECT * FROM PREDIO WHERE id=?;"; // se crea string con la consulta
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                /*System.out.println(rs.getString("tecnico"));
                System.out.println(rs.getString("productor"));
                System.out.println(rs.getString("super_men"));
                System.out.println(rs.getString("nombre_predio"));
                System.out.println(rs.getString("comuna_predio")); 
                System.out.println(rs.getInt("km_talca"));*/
                fila[0]=rs.getString("tecnico");
                fila[1]=rs.getString("productor");
                fila[2]=rs.getString("super_men");
                fila[3]=rs.getString("nombre_predio");
                fila[4]=rs.getString("comuna_predio");
                fila[5]=rs.getInt("km_talca");
            }
            
            //System.out.println(km_talca);//se imprime km
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fila;

    }
    
    public void actualizar_tupla(int id,String tecnico,String productor,String super_men,String nombre_predio,String comuna_predio,int km_talca){ // Actualiza la tupla dependiendo del id que se mande
        
        /*System.out.println(id);
        System.out.println(tecnico);
        System.out.println(productor);
        System.out.println(super_men);
        System.out.println(nombre_predio);
        System.out.println(comuna_predio);
        System.out.println(km_talca);*/
        
        String query = "";
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConexion();
        
        
        try {
            query = "UPDATE PREDIO SET  tecnico=? , productor=?, super_men=?,nombre_predio=?, comuna_predio=?, km_talca=? WHERE id = ?;";
            
            PreparedStatement sentenciaP = conn.prepareStatement(query);
            sentenciaP.setString(1, tecnico);
            sentenciaP.setString(2, productor);
            sentenciaP.setString(3, super_men);
            sentenciaP.setString(4, nombre_predio);
            sentenciaP.setString(5, comuna_predio);
            sentenciaP.setInt(6, km_talca);
            sentenciaP.setInt(7, id);
            
            sentenciaP.executeUpdate();
            sentenciaP.close();
            conn.close();
            JOptionPane.showMessageDialog(null,"Actualizacion realizada con exito");
           
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
    
    public void eliminar_predio(int id){
        //JOptionPane.showMessageDialog(null,"El predio "+id+" se eliminara");
        
        String sql = "";
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConexion();
        
        
        try {
            sql = "DELETE FROM PREDIO WHERE id = ?;";
            PreparedStatement sentenciaP = conn.prepareStatement(sql);
            sentenciaP.setInt(1, id);
            sentenciaP.execute();
            sentenciaP.close();
            conn.close();
            JOptionPane.showMessageDialog(null,"Predio eliminado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(PredioModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
    
    
    }
    
}
