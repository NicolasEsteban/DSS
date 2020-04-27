/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.Collections;


public class Proyecto {

    /**
     * @param args the command line arguments
     */
    
    public static int n;    //Numero de bloques del dia.
    public static int I;    //Numero de predios a cosechar.
    public static float alpha;    //constante de criterio para seleccion de aleatoriedad
    public static int[] turnos = {0, 360, 600};  //turnos expresados en minutos.
    
    public static int ciclos = 1000;
    
    public static void main(String[] args) {
        int rnd,turno;
        int Ztemporal=999;
        ArrayList<Predio> listaPredioFinal = new ArrayList<>();
        ArrayList<Bloque> listaBloqueFinal = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < ciclos; i++) {            
        
            ArrayList<Predio> listaPredio = new ArrayList<Predio>();
            ArrayList<Bloque> listaBloque = new ArrayList<Bloque>();
            inicializarParametros(listaPredio, listaBloque);
            /*
            listaPredio.get(0).y = 600;   calcularPredio(listaPredio.get(0), listaBloque);    actualizarBloques(listaPredio.get(0), listaBloque);
            listaPredio.get(1).y = 360;   calcularPredio(listaPredio.get(1), listaBloque);    actualizarBloques(listaPredio.get(1), listaBloque);
            listaPredio.get(2).y = 0;   calcularPredio(listaPredio.get(2), listaBloque);    actualizarBloques(listaPredio.get(2), listaBloque);
            listaPredio.get(3).y = 600;   calcularPredio(listaPredio.get(3), listaBloque);    actualizarBloques(listaPredio.get(3), listaBloque);
            listaPredio.get(4).y = 600;   calcularPredio(listaPredio.get(4), listaBloque);    actualizarBloques(listaPredio.get(4), listaBloque);
            listaPredio.get(5).y = 0;   calcularPredio(listaPredio.get(5), listaBloque);    actualizarBloques(listaPredio.get(5), listaBloque);
            listaPredio.get(6).y = 0;   calcularPredio(listaPredio.get(6), listaBloque);    actualizarBloques(listaPredio.get(6), listaBloque);
            listaPredio.get(7).y = 360;   calcularPredio(listaPredio.get(7), listaBloque);    actualizarBloques(listaPredio.get(7), listaBloque);
            listaPredio.get(8).y = 600;   calcularPredio(listaPredio.get(8), listaBloque);    actualizarBloques(listaPredio.get(8), listaBloque);
            listaPredio.get(9).y = 0;   calcularPredio(listaPredio.get(9), listaBloque);    actualizarBloques(listaPredio.get(9), listaBloque);
            listaPredio.get(10).y = 0;   calcularPredio(listaPredio.get(10), listaBloque);    actualizarBloques(listaPredio.get(10), listaBloque);
            listaPredio.get(11).y = 0;   calcularPredio(listaPredio.get(11), listaBloque);    actualizarBloques(listaPredio.get(11), listaBloque);
            listaPredio.get(12).y = 600;   calcularPredio(listaPredio.get(12), listaBloque);    actualizarBloques(listaPredio.get(12), listaBloque);
            listaPredio.get(13).y = 600;   calcularPredio(listaPredio.get(13), listaBloque);    actualizarBloques(listaPredio.get(13), listaBloque);
            listaPredio.get(14).y = 600;   calcularPredio(listaPredio.get(14), listaBloque);    actualizarBloques(listaPredio.get(14), listaBloque);
            listaPredio.get(15).y = 0;   calcularPredio(listaPredio.get(15), listaBloque);    actualizarBloques(listaPredio.get(15), listaBloque);
            listaPredio.get(16).y = 600;   calcularPredio(listaPredio.get(16), listaBloque);    actualizarBloques(listaPredio.get(16), listaBloque);
            listaPredio.get(17).y = 600;   calcularPredio(listaPredio.get(17), listaBloque);    actualizarBloques(listaPredio.get(17), listaBloque);
            listaPredio.get(18).y = 0;   calcularPredio(listaPredio.get(18), listaBloque);    actualizarBloques(listaPredio.get(18), listaBloque);
            listaPredio.get(19).y = 0;   calcularPredio(listaPredio.get(19), listaBloque);    actualizarBloques(listaPredio.get(19), listaBloque);
            listaPredio.get(20).y = 600;   calcularPredio(listaPredio.get(20), listaBloque);    actualizarBloques(listaPredio.get(20), listaBloque);
            listaPredio.get(21).y = 360;   calcularPredio(listaPredio.get(21), listaBloque);    actualizarBloques(listaPredio.get(21), listaBloque);
            listaPredio.get(22).y = 0;   calcularPredio(listaPredio.get(22), listaBloque);    actualizarBloques(listaPredio.get(22), listaBloque);
            listaPredio.get(23).y = 600;   calcularPredio(listaPredio.get(23), listaBloque);    actualizarBloques(listaPredio.get(23), listaBloque);
            listaPredio.get(24).y = 600;   calcularPredio(listaPredio.get(24), listaBloque);    actualizarBloques(listaPredio.get(24), listaBloque);
            listaPredio.get(25).y = 0;   calcularPredio(listaPredio.get(25), listaBloque);    actualizarBloques(listaPredio.get(25), listaBloque);
            listaPredio.get(26).y = 360;   calcularPredio(listaPredio.get(26), listaBloque);    actualizarBloques(listaPredio.get(26), listaBloque);
            listaPredio.get(27).y = 0;   calcularPredio(listaPredio.get(27), listaBloque);    actualizarBloques(listaPredio.get(27), listaBloque);
            listaPredio.get(28).y = 600;   calcularPredio(listaPredio.get(28), listaBloque);    actualizarBloques(listaPredio.get(28), listaBloque);
            listaPredio.get(29).y = 600;   calcularPredio(listaPredio.get(29), listaBloque);    actualizarBloques(listaPredio.get(29), listaBloque);
            listaPredio.get(30).y = 0;   calcularPredio(listaPredio.get(30), listaBloque);    actualizarBloques(listaPredio.get(30), listaBloque);
            listaPredio.get(31).y = 600;   calcularPredio(listaPredio.get(31), listaBloque);    actualizarBloques(listaPredio.get(31), listaBloque);
            */
            /**************************************************************************/
            
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
        
    }
    
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
    
    public static void inicializarParametros(ArrayList<Predio> listaPredio, ArrayList<Bloque> listaBloque){
        Proyecto.n =24;    //numero de bloques
        Proyecto.I = 34;    //numero de predios
        //Proyecto.I = 4;
        Proyecto.alpha = (float) 0.5;
        int cargasTotales=0,capDesc=0;
        int ratioMin=0; //porcion de minutos para cada bloque.
        //int horaInicio = 420; //hora en minutos (7:00 am)
        int horaInicio = 0;
        
        /*20 Predios Zona Norte (pdf tesis)
        Predio predio = new Predio(1, 8, 78, 72);
        cargasTotales += predio.numC;
        listaPredio.add(predio);
        Predio predio1 = new Predio(2, 6, 102, 72);
        cargasTotales += predio1.numC;
        listaPredio.add(predio1);
        Predio predio2 = new Predio(3, 5, 120, 66);
        cargasTotales += predio2.numC;
        listaPredio.add(predio2);
        Predio predio3 = new Predio(4, 8, 78, 114);
        cargasTotales += predio3.numC;
        listaPredio.add(predio3);
        Predio predio4 = new Predio(5, 7, 84, 84);
        cargasTotales += predio4.numC;
        listaPredio.add(predio4);
        Predio predio5 = new Predio(6, 7, 84, 162);
        cargasTotales += predio5.numC;
        listaPredio.add(predio5);
        Predio predio6 = new Predio(7, 4, 150, 18);
        cargasTotales += predio6.numC;
        listaPredio.add(predio6);
        Predio predio7 = new Predio(8, 8, 78, 60);
        cargasTotales += predio7.numC;
        listaPredio.add(predio7);
        Predio predio8 = new Predio(9, 7, 84, 54);
        cargasTotales += predio8.numC;
        listaPredio.add(predio8);
        Predio predio9 = new Predio(10, 7, 84, 96);
        cargasTotales += predio9.numC;
        listaPredio.add(predio9);
        Predio predio10 = new Predio(11, 6, 102, 66);
        cargasTotales += predio10.numC;
        listaPredio.add(predio10);
        Predio predio11 = new Predio(12, 6,102, 66);
        cargasTotales += predio11.numC;
        listaPredio.add(predio11);
        Predio predio12 = new Predio(13, 6, 102, 120);
        cargasTotales += predio12.numC;
        listaPredio.add(predio12);
        Predio predio13 = new Predio(14, 5, 120, 126);
        cargasTotales += predio13.numC;
        listaPredio.add(predio13);
        Predio predio14 = new Predio(15, 6, 102, 114);
        cargasTotales += predio14.numC;
        listaPredio.add(predio14);
        Predio predio15 = new Predio(16, 6, 102, 84); 
        cargasTotales += predio15.numC;
        listaPredio.add(predio15);
        Predio predio16 = new Predio(17, 5, 120, 78);
        cargasTotales += predio16.numC;
        listaPredio.add(predio16);
        Predio predio17 = new Predio(18, 5, 120, 180);
        cargasTotales += predio17.numC;
        listaPredio.add(predio17);
        Predio predio18 = new Predio(19, 8, 78, 96); 
        cargasTotales += predio18.numC;
        listaPredio.add(predio18);
        Predio predio19 = new Predio(20, 5, 120, 174);
        cargasTotales += predio19.numC;
        listaPredio.add(predio19);
        
        //12 predios Zona Sur (pdf tesis)
        Predio predio = new Predio(1, 9, 66, 96);
        cargasTotales += predio.numC;
        listaPredio.add(predio);
        Predio predio1 = new Predio(2, 6, 102, 48);
        cargasTotales += predio1.numC;
        listaPredio.add(predio1);
        Predio predio2 = new Predio(3, 10, 60, 18);
        cargasTotales += predio2.numC;
        listaPredio.add(predio2);
        Predio predio3 = new Predio(4, 4, 150, 36);
        cargasTotales += predio3.numC;
        listaPredio.add(predio3);
        Predio predio4 = new Predio(5, 3, 198, 90);
        cargasTotales += predio4.numC;
        listaPredio.add(predio4);
        Predio predio5 = new Predio(6, 7, 84, 36);
        cargasTotales += predio5.numC;
        listaPredio.add(predio5);
        Predio predio6 = new Predio(7, 8, 78, 18);
        cargasTotales += predio6.numC;
        listaPredio.add(predio6);
        Predio predio7 = new Predio(8, 6, 78, 60);
        cargasTotales += predio7.numC;
        listaPredio.add(predio7);
        Predio predio8 = new Predio(9, 6, 78, 66);
        cargasTotales += predio8.numC;
        listaPredio.add(predio8);
        Predio predio9 = new Predio(10, 5, 120, 18);
        cargasTotales += predio9.numC;
        listaPredio.add(predio9);
        Predio predio10 = new Predio(11, 9, 66, 18);
        cargasTotales += predio10.numC;
        listaPredio.add(predio10);
        Predio predio11 = new Predio(12, 10, 60, 48);
        cargasTotales += predio11.numC;
        listaPredio.add(predio11);*/
        
        //12 Predios Zona Sur (excel)
        
        Predio predio = new Predio(1, 9, (float)(60*1.11111111111111), (float) (60*2.58));
        cargasTotales += predio.numC;
        listaPredio.add(predio);
        Predio predio1 = new Predio(2, 6, (float) (60*1.66666666666667), (float) (60*0.8));
        cargasTotales += predio1.numC;
        listaPredio.add(predio1);
        Predio predio2 = new Predio(3, 10, (float) (60*1.0), (float) (60*0.32));
        cargasTotales += predio2.numC;
        listaPredio.add(predio2);
        Predio predio3 = new Predio(4, 4, (float) (60*2.5), (float) (60*0.64));
        cargasTotales += predio3.numC;
        listaPredio.add(predio3);
        Predio predio4 = new Predio(5, 3, (float) (60*3.33333333333333), (float) (60*1.5));
        cargasTotales += predio4.numC;
        listaPredio.add(predio4);
        Predio predio5 = new Predio(6, 7, (float) (60*1.42857142857143), (float) (60*0.6));
        cargasTotales += predio5.numC;
        listaPredio.add(predio5);
        Predio predio6 = new Predio(7, 8, (float) (60*1.25), (float) (60*0.28));
        cargasTotales += predio6.numC;
        listaPredio.add(predio6);
        Predio predio7 = new Predio(8, 6, (float)(60*1.66666666666667), (float) (60*0.9));
        cargasTotales += predio7.numC;
        listaPredio.add(predio7);
        Predio predio8 = new Predio(9, 6, (float) (60*1.66666666666667), (float) (60*1.08));
        cargasTotales += predio8.numC;
        listaPredio.add(predio8);
        Predio predio9 = new Predio(10, 5, (float) (60*2.0), (float) (60*0.26));
        cargasTotales += predio9.numC;
        listaPredio.add(predio9);
        Predio predio10 = new Predio(11, 9, (float) (60*1.11111111111111), (float) (60*0.3));
        cargasTotales += predio10.numC;
        listaPredio.add(predio10);
        Predio predio11 = new Predio(12, 10, (float) (60*1.0), (float) (60*0.76));
        cargasTotales += predio11.numC;
        listaPredio.add(predio11);
        
        Predio predio12 = new Predio(13, 6, (float) (60*1.66666666666667), (float) (60*1.5));
        cargasTotales += predio12.numC;
        listaPredio.add(predio12);
        Predio predio13 = new Predio(14, 7, (float) (60*1.42857142857143), (float) (60*1.12));
        cargasTotales += predio13.numC;
        listaPredio.add(predio13);
        Predio predio14 = new Predio(15, 8, (float) (60*1.25), (float) (60*2.02));
        cargasTotales += predio14.numC;
        listaPredio.add(predio14);
        Predio predio15 = new Predio(16, 9, (float) (60*1.11111111111111), (float) (60*0.46));
        cargasTotales += predio15.numC;
        listaPredio.add(predio15);
        Predio predio16 = new Predio(17, 10, (float) (60*1.0), (float) (60*1.06));
        cargasTotales += predio16.numC;
        listaPredio.add(predio16);
        Predio predio17 = new Predio(18, 6, (float) (60*1.66666666666667), (float) (60*2.64));
        cargasTotales += predio17.numC;
        listaPredio.add(predio17);
        Predio predio18 = new Predio(19, 7, (float) (60*1.42857142857143), (float) (60*0.38));
        cargasTotales += predio18.numC;
        listaPredio.add(predio18);
        Predio predio19 = new Predio(20, 9, (float) (60*1.11111111111111), (float) (60*0.38));
        cargasTotales += predio19.numC;
        listaPredio.add(predio19);
        Predio predio20 = new Predio(21, 10, (float) (60*1.0), (float) (60*1.06));
        cargasTotales += predio20.numC;
        listaPredio.add(predio20);
        Predio predio21 = new Predio(22, 6, (float) (60*1.66666666666667), (float) (60*0.76));
        cargasTotales += predio21.numC;
        listaPredio.add(predio21);
        Predio predio22 = new Predio(23, 7, (float) (60*1.42857142857143), (float) (60*0.14));
        cargasTotales += predio22.numC;
        listaPredio.add(predio22);
        Predio predio23 = new Predio(24, 8, (float) (60*1.25), (float) (60*0.20));
        cargasTotales += predio23.numC;
        listaPredio.add(predio23);
        Predio predio24 = new Predio(25, 9, (float) (60*1.11111111111111), (float) (60*1.50));
        cargasTotales += predio24.numC;
        listaPredio.add(predio24);
        Predio predio25 = new Predio(26, 10, (float) (60*1.0), (float) (60*1.0));
        cargasTotales += predio25.numC;
        listaPredio.add(predio25);
        Predio predio26 = new Predio(27, 6, (float) (60*1.66666666666667), (float) (60*0.36));
        cargasTotales += predio26.numC;
        listaPredio.add(predio26);
        Predio predio27 = new Predio(28, 7, (float) (60*1.42857142857143), (float) (60*0.52));
        cargasTotales += predio27.numC;
        listaPredio.add(predio27);
        Predio predio28 = new Predio(29, 8, (float) (60*1.25), (float) (60*1.76));
        cargasTotales += predio28.numC;
        listaPredio.add(predio28);
        Predio predio29 = new Predio(30, 9, (float) (60*1.11111111111111), (float) (60*1.50));
        cargasTotales += predio29.numC;
        listaPredio.add(predio29);
        Predio predio30 = new Predio(31, 10, (float) (60*1.0), (float) (60*0.6));
        cargasTotales += predio30.numC;
        listaPredio.add(predio30);
        Predio predio31 = new Predio(32, 6, (float) (60*1.66666666666667), (float) (60*2.20));
        cargasTotales += predio31.numC;
        listaPredio.add(predio31);
        Predio predio32 = new Predio(33, 7, (float) (60*1.42857142857143), (float) (60*1.20));
        cargasTotales += predio32.numC;
        listaPredio.add(predio32);
        Predio predio33 = new Predio(34, 8, (float) (60*1.25), (float) (60*0.06));
        cargasTotales += predio33.numC;
        listaPredio.add(predio33);
        
        /*
        Predio predio = new Predio(1, 10, (float)(60*1.0), (float) (60*2.58));
        cargasTotales += predio.numC;
        listaPredio.add(predio);
        Predio predio1 = new Predio(2, 10, (float) (60*1.0), (float) (60*0.8));
        cargasTotales += predio1.numC;
        listaPredio.add(predio1);
        Predio predio2 = new Predio(3, 10, (float) (60*1.0), (float) (60*0.32));
        cargasTotales += predio2.numC;
        listaPredio.add(predio2);
        Predio predio3 = new Predio(4, 10, (float) (60*1.0), (float) (60*0.64));
        cargasTotales += predio3.numC;
        listaPredio.add(predio3);*/
        
        ratioMin = 24*60/n; //en minutos.
        
        //System.out.println("Cargas Totales: "+cargasTotales);
        //capDesc=(int) Math.ceil((float)cargasTotales/n);    //redondear hacia arriba
        capDesc=5;
        for (int i = 0; i < n; i++) {
            listaBloque.add(new Bloque(horaInicio+ratioMin*i,capDesc));
        }
        
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
        
        for (int b : Proyecto.turnos) {
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
                if (c.x > b.a) {
                    bloque = listaBloque.indexOf(b); 
                }
                else{
                    break;
                }
            }
            c.ak=listaBloque.get(bloque); // horario de llegada de la carga
        }
    }
    
    //Funcion para actualizar capacidad de los bloques
    public static void actualizarBloques(Predio P, ArrayList<Bloque> listaBloque){
        for (Bloque b : listaBloque){ // verifica FO
            for (Carga c : P.C) {
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
        
        for (int b : Proyecto.turnos) {
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

            for (Integer t : Proyecto.turnos) {
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
    
}
