package fuentes;

import java.util.Scanner;

public class Juego{
	private Scanner sc;
	public Tablero tablero;
	public Nivel nivel;
	public Jugador jugador;
	private int fallos;
	private int porcentajeFallos;
	private int paresFormados;
	private int puntos;
	
	public void setJugador(Jugador j){
		this.jugador = j;
		this.jugador.yaJugo = true;
	}
	
	public Juego(Nivel nivel){
		sc = new Scanner(System.in);
		this.nivel = nivel;	
		tablero = new Tablero(this.nivel.filasTablero, this.nivel.columTablero);
	}
	
	public Coordenada pedirCoordenadas(){
		boolean succes;
		Coordenada xy;
		do{
			int y = pedirFila();
			int x = pedirColumna(y);
			xy = new Coordenada(x, y);
			
			if(this.tablero.estaDescubierta(xy)){
				System.out.println("Esa letra ya esta descubierta (" + this.tablero.getLetra(xy) + ")");
				succes = false;
			}else{
				succes = true;
			}
		}while(!succes);
		return xy;
	}

	public int pedirColumna(int fila){
		boolean succes;
		String in;
		int tempInt;
		do{
			System.out.println("Ingrese la columna de la letra");
			in = sc.next();
			tempInt = Ut.darValor(in, -1) - 1;
			if(Ut.esColumnaValida(this.tablero.getColumnas(), tempInt)){
				return tempInt;
			}else{
				System.out.println("Columna Invalida");
				succes = false;
			}
		}while(!succes);
		return -1;
	}
	
	public int pedirFila(){
		boolean succes;
		String in;
		int tempInt;
		do{
			System.out.println("Ingrese la fila de la letra");
			in = sc.next();
			tempInt = Ut.darValor(in, -1) -1;
			if(Ut.esFilaValida(this.tablero.getFilas(), tempInt)){
				return tempInt;
			}else{
				System.out.println("Fila Invalida");
				succes = false;
			}
		}while(!succes);
		return -1;
	}
	
	private void setPorcentajeFallos(){
		this.porcentajeFallos = (int)((double)(((double)this.fallos / (double)this.nivel.maxFallos) * 100));
	}
	
	private void setPuntos(){
		this.setPorcentajeFallos();
		int pf = this.porcentajeFallos;

		int p;
		if(pf >= 100){
			p = 0;
		}else if(pf >= 81){
			p = 10;
		}else if(pf >= 61){
			p = 20;
		}else if(pf >= 50){
			p = 50;
		}else if(pf >= 21){
			p = 70;
		}else if(pf >= 1){
			p = 90;
		}else{
			p = 100;
		}
		this.puntos = p;
	}
	
	public void validarPareja(){
		System.out.println("Primera letra de la pareja");
		Coordenada L1 = pedirCoordenadas();
		this.tablero.cambiarVisibilidad(L1, true);
		this.tablero.imprimirVisibles();
		
        boolean succes;
        Coordenada L2 = null;
        do{
            System.out.println("Segunda letra de la pareja");
            L2 = pedirCoordenadas();
            succes = true;
            if(L1.x == L2.x && L1.y == L2.y){
                System.out.println("No puede elegir la misma letra. Intente de nuevo");
                succes = false;
            }
        }while(!succes);

		this.tablero.cambiarVisibilidad(L2, true);
		this.tablero.imprimirVisibles();

		if(this.tablero.getLetra(L1) == this.tablero.getLetra(L2)){
			this.tablero.DescubrirLetra(L1);
			this.tablero.DescubrirLetra(L2);
			this.paresFormados++;
		}else{
			this.fallos++;
		}
		
		this.tablero.resetVisibilidad();
	}
	
	public void mostrarInfo(){
		System.out.print("\n\nAciertos: " + this.paresFormados);
		System.out.print("\tDesaciertos: " + this.fallos);
		System.out.print("\tMaximo: " + this.nivel.maxFallos);
		System.out.print("\tPuntos: " + this.puntos);
	}
	
	public Jugador jugar(Jugador j){
		jugador = j;
		while(this.fallos < this.nivel.maxFallos && this.paresFormados < tablero.getNumeroPares()){
			mostrarInfo();
			this.tablero.imprimirDescubiertas();
			validarPareja();
            setPuntos();
		}
		
		if(paresFormados == tablero.getNumeroPares()){
			System.out.println("Felicidades! ha completado el nivel " + this.nivel.getNombre());
			jugador.victoria = true;
			if(!this.jugador.yaJugo){
				jugador.setNombre();			
			}
			jugador.yaJugo = true;
			setPuntos();
			jugador.actualizarPuntajes(this.nivel.dificultad, this.puntos);
		}else{
			System.out.println("Lo siento! ha perdido :(");
		}
		return jugador;
	}
}