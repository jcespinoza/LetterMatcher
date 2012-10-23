package fuentes;

import java.util.Scanner;

public class Nivel{
	private final Scanner sca = new Scanner(System.in);;
	public int maxFallos;
	public int filasTablero;
	public int columTablero;
	public int tamTablero;
	private String in = "5g";
	private int res = -1;
	public static final int FACIL = 0;
	public static final int INTERMEDIO = 1;
	public static final int DIFICIL = 2;
	public static final int IMPOSIBLE = 3;
	public static final String nombre[] = {"Basico", "Intermedio", "Avanzado", "Imposible"};
	public int dificultad;
	
    public Nivel(int dificultad, boolean temp){
        this.dificultad = dificultad;
    }
    
	public Nivel(int dificultad){
		setDificultad(dificultad);
		setTamanio();
		setFallos();
	}
	
	public Nivel(int dif, int fil, int col, int maxF){
		setDificultad(dif);
		this.filasTablero = fil;
		this.columTablero = col;
		this.tamTablero = col * fil;
		this.maxFallos = maxF;
	}
	
	private void setDificultad(int dif){
		this.dificultad = (dif < this.FACIL || dif > this.IMPOSIBLE)?this.FACIL: dif;
	}
	
	public String getNombre(){
		return nombre[this.dificultad];
	}
	
	public static String getNombreNivel(int index){
		return (Ut.dentroDelRangoI(index, 0, 3, true))?Nivel.nombre[index]:"Nivel de Dificultad Invalido";
	}
	
    public static boolean validarDivisibilidad(Nivel niv){
        if(niv.tamTablero % 2 == 0){
            return true;
        }
        return false;
    }
    
    public static boolean validarTamanio(Nivel niv){
        int area = niv.columTablero * niv.filasTablero;
        if(area <= 50 ){
            return true;
        }
        return false;
    }
    
	public static boolean validarNiveles(Nivel basic, Nivel inter, Nivel avanz){
		boolean basicInterFallos = basic.maxFallos >= inter.maxFallos;
		boolean basicInterTablero = basic.tamTablero < inter.tamTablero;
		
		boolean interAvanzFallos = inter.maxFallos >= avanz.maxFallos;
		boolean interAvanzTablero = inter.tamTablero < avanz.tamTablero;
		
		
		
		boolean basicInter = basicInterTablero;
		boolean interAvanz = interAvanzTablero;
		boolean correcto = basicInter && interAvanz;
		
		if(correcto){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean setFilas(){
		boolean error = true;
		do{
			System.out.println("\nIngrese el numero de filas del tablero para este nivel");
			in = sca.next();
			if(Ut.puedeConvertirse(in, 'i')){
				res = Integer.parseInt(in);
				if(res > -1){
					if(res > 0){
						this.filasTablero = res;
						error = false;
					}else{
						error = true;
						System.out.println("No puede haber una matriz de 0 filas");
					}
				}else{
					error = true;
					System.out.println("No puede ingresar numeros negativos aqui");
				}
			}else{
				error = true;
				System.out.println("Debe ingresar un numero entero");
			}
		}while(error);
		return true;
	}
	
	public boolean setColumnas(){
		boolean error = true;
		do{
			System.out.println("\nIngrese el numero de columnas del tablero para este nivel");
			in = sca.next();
			if(Ut.puedeConvertirse(in, 'i')){
				res = Integer.parseInt(in);
				if(res > -1){
					if(res > 0){
						this.columTablero = res;
						error = false;
					}else{
						error = true;
						System.out.println("No puede haber una matriz de 0 columnas");
					}
				}else{
					error = true;
					System.out.println("No puede ingresar numeros negativos aqui");
				}
			}else{
				error = true;
				System.out.println("Debe ingresar un numero entero");
			}			
		}while(error);
		return true;
	}
	
	public void setTamanio(){
		boolean succes = false;
		do{
			do{
				succes = setFilas();
			}while(!succes);
			
			do{
				succes = setColumnas();
			}while(!succes);
			
			this.tamTablero = this.filasTablero * this.columTablero;
			
			if(this.tamTablero % 2 != 0){
				System.out.println("El producto de las filas y columnas debe ser un numero par");
				System.out.println(this.filasTablero + " x " + this.columTablero + " > " + this.tamTablero);
				succes = false;
			}else{
				if(this.tamTablero > 50){
					System.out.println("El tamanio excede el limite de 50 celdas" + "(> "+ this.tamTablero +")");
					succes = false;
				}else{
					succes = true;
				}
			}
		}while(!succes);
	}
	
	public void setFallos(){
		boolean error = true;
		do{
			System.out.println("Ingrese el maximo numero de Fallos para este nivel");
			in = sca.next();
			if(Ut.puedeConvertirse(in, 'i')){
				res = Integer.parseInt(in);
				do{
					if(res > -1){
						this.maxFallos = res;
						error = false;
					}else{
						error = true;
						System.out.println("No puede ingresar numeros negativos aqui");
					}
				}while(error);
				
			}else{
				error = true;
				System.out.println("Debe ingresar un numero entero");
			}
		}while(error);
	}
	
	public static void verConfiguracion(Nivel... niveles){
		for(Nivel nivel: niveles){
			System.out.println("Configuracion del nivel " + nivel.getNombre());
			System.out.println("Numero de Fallos:\t" + nivel.maxFallos);
			System.out.println("Numero de Filas:\t" + nivel.filasTablero);
			System.out.println("Numero de Columnas:\t" + nivel.columTablero + "\n");
		}
	}
}