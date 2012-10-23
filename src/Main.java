import fuentes.*;
import java.util.Scanner;
import ventanas.MenuPrincipalJF;

public class Main{
    public static void main(String ar[]){
        
        Scanner sc = new Scanner(System.in);    

        Nivel nivelBasico = new Nivel(0, true);
        Nivel nivelIntermedio = new Nivel(1, true);
        Nivel nivelAvanzado = new Nivel(2, true);
        Juego juego = null;             
        Jugador resJ = null;            
        TablaR tResultados = new TablaR(20);     

        String in;			

        char resC = 'N';    

        int resM;			
        int res2;			
        int res3;           
        int nJuegos = 0;    

        boolean nivelesConfigurados = false;	
        boolean nBasConf = false;				
        boolean nIntConf = false;				
        boolean nAvaConf = false;				
        boolean nivelesCorrectos = false;				
        boolean bResult;						

        System.out.println("\n\nJUEGO de MEMORIA");
        do{
            mostrarMenuP(); 
            in = sc.next(); 
            resM = Ut.darValor(in, 0); 

            switch(resM){
                case 1:
                    if(nJuegos > 0){
                        System.out.println("Los niveles no pueden cambiarse si ya se realizo al menos un juego");
                        break;
                    }

                    if(nivelesConfigurados){
                        System.out.println("Los niveles ya fueron configurados, desea modificarlos S/N");
                        resC = sc.next().toUpperCase().charAt(0);
                        if(resC != 'S'){
                            break;
                        }
                        
                    }

                    do{
                        mostrarMenuN();     
                        in = sc.next();     
                        res2 = Ut.darValor(in, 0); 

                        switch(res2){
                            case 1:
                                nivelBasico = new Nivel(Nivel.FACIL);   
                                nBasConf = true; 
                                break;
                            case 2:
                                nivelIntermedio = new Nivel(Nivel.INTERMEDIO);
                                nIntConf = true;
                                break;
                            case 3:
                                nivelAvanzado = new Nivel(Nivel.DIFICIL);
                                nAvaConf = true;
                                break;
                            case 4:
                                if(nivelesConfigurados){
                                    Nivel.verConfiguracion(nivelBasico, nivelIntermedio, nivelAvanzado);
                                    
                                }else{
                                    System.out.println("Los niveles no se han configurado");
                                    
                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Opcion Invalida");
                        }
                        
                        
                        nivelesConfigurados = (nBasConf && nIntConf && nAvaConf)? true: false;
                        if(nivelesConfigurados){
                            bResult = Nivel.validarNiveles(nivelBasico, nivelIntermedio, nivelAvanzado);
                            nivelesCorrectos = bResult;
                        }
                    
                    }while(res2 != 5);

                    break;
                case 2:
                    if(!nivelesConfigurados){
                        
                        System.out.println("Aun no se han configurado todos los niveles");
                        break;
                    }
                    if(!nivelesCorrectos){
                        
                        System.out.println("Los niveles se han configurado ilogicamente");
                        break;
                    }
                    do{
                        mostrarMenuJ();     
                        in = sc.next();     
                        res2 = Ut.darValor(in, 0);  

                        switch(res2){
                            case 1:
                                juego = new Juego(nivelBasico);     
                                    
                                resJ = juego.jugar(new Jugador());  
                                                                    
                                    nJuegos++;                      
                                    if(resJ.victoria){              
                                        tResultados.agregarJugador(resJ);   
                                        if(pasarAlSiguiente()){     
                                            juego = new Juego(nivelIntermedio); 

                                            resJ = juego.jugar(resJ);   
                                            nJuegos++;
                                            if(resJ.victoria){
                                                if(pasarAlSiguiente()){
                                                    juego = new Juego(nivelAvanzado);

                                                    resJ = juego.jugar(resJ);
                                                    nJuegos++;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    juego = new Juego(nivelIntermedio);
                                    resJ = juego.jugar(new Jugador());

                                    nJuegos++;
                                    if(resJ.victoria){
                                        tResultados.agregarJugador(resJ);
                                        if(pasarAlSiguiente()){
                                            juego = new Juego(nivelAvanzado);

                                            resJ = juego.jugar(resJ);
                                            nJuegos++;
                                        }
                                    }								
                                    break;
                                case 3:
                                    juego = new Juego(nivelAvanzado);
                                    resJ = juego.jugar(new Jugador());

                                    nJuegos++;
                                    if(resJ.victoria){
                                        tResultados.agregarJugador(resJ);
                                    }   
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println("Opcion Invalida");
                                }
                            }while(res2 != 4);
                            break;
                        case 3:
                            do{
                                mostrarMenuR();     
                                in = sc.next();     
                                res2 = Ut.darValor(in, 0);  

                                switch(res2){
                                    case 1:
                                        tResultados.mostrarGlobal();    
                                        break;
                                    case 2:
                                        do{
                                            mostrarMenuRN();        
                                            in = sc.next();
                                            res3 = Ut.darValor(in, 0);
                                            switch(res3){
                                                case 1:
                                                    tResultados.mostrarPorNivel(0);
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    break;
                                                case 2:
                                                    tResultados.mostrarPorNivel(Nivel.INTERMEDIO);
                                                    break;
                                                case 3:
                                                    tResultados.mostrarPorNivel(Nivel.DIFICIL);
                                                    break;
                                                default:
                                                    
                                                    System.out.println("Opcion invalida");
                                            }
                                        }while(res3 != 4);
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Opcion Invalida");
                                }
					}while(res2 != 3);
                            break;
                        case 4:
                            Nivel nivs[] = {nivelBasico, nivelIntermedio, nivelAvanzado};
                            Object salir[] = {false};
                            MenuPrincipalJF mp = new MenuPrincipalJF(nivs, nJuegos, tResultados, salir, nivelesCorrectos);
                            System.out.println("Entrando en Modo GUI...");
                            
                            mp.mostrar();
                            if((boolean)salir[0]){
                                resM = 5;
                            }
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opcion inválida");
			}
		}while(resM != 5);
    }

    public static void mostrarMenuP(){
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Niveles");
        System.out.println("2. Juego");
        System.out.println("3. Resultados");
        System.out.println("4. Jugar en Modo Grafico");
        System.out.println("5. Salir");
    }

    public static void mostrarMenuJ(){
        System.out.println("\nSELECCION DE DIFICULTAD");
        System.out.println("1. Nivel Basico");
        System.out.println("2. Nivel Intermedio");
        System.out.println("3. Nivel Avanzado");
        System.out.println("4. Volver al Menu Principal");
    }

    public static void mostrarMenuN(){
        System.out.println("\nCONFIGURACION DE NIVELES");
        System.out.println("1. Nivel Basico");
        System.out.println("2. Nivel Intermedio");
        System.out.println("3. Nivel Avanzado");
        System.out.println("4. Ver Configuracion");
        System.out.println("5. Volver al Menu Principal");
    }

    public static void mostrarMenuR(){
        System.out.println("\nRESULTADOS");
        System.out.println("1. Global");
        System.out.println("2. Por nivel");
        System.out.println("3. Volver al Menu Principal");
    }
    
    public static void mostrarMenuRN(){
        System.out.println("\nRESULTADOS POR NIVEL - SELECCION DE NIVEL");
        System.out.println("1. Nivel Basico");
        System.out.println("2. Nivel Intermedio");
        System.out.println("3. Nivel Avanzado");
        System.out.println("4. Volver Al Menu de Resultados");
    }
    
    
    public static boolean pasarAlSiguiente(){
        System.out.println("Desea avanzar al siguiente nivel? S/N");
        Scanner sc2 = new Scanner(System.in);
        char R = sc2.next().toUpperCase().charAt(0); 
        return (R == 'S')?true:false; 
    }
}