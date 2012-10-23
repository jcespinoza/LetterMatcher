package fuentes;

public class Tablero{
	private int tamanio;
	private int filas;
	private int columnas;
	private Letra letras[][];
	private int posiciones[][];
	private char letrasGeneradas[];
	private int letrasAGenerar;

	public Tablero(int filasMatrix, int columMatrix){
		this.filas = filasMatrix;
		this.columnas = columMatrix;
		this.tamanio = filasMatrix * columMatrix;
		
		this.posiciones = new int[filas][columnas];
		letras = new Letra[filas][columnas];
		
		initLetras();
		llenar();
	}
	
	public int getNumeroPares(){
		return this.tamanio/2;
	}
	
	public int getFilas(){
		return this.filas;
	}
	
	public char getLetra(Coordenada c){
		return this.letras[c.y][c.x].valor();
	}
	
	public int getColumnas(){
		return this.columnas;
	}
	
	public Letra getLetra(int fila, int columna){
		return this.letras[fila][columna];
	}
	
	public void resetVisibilidad(){
		for(int i = 0; i < letras.length; i++){
			for(int k = 0; k < letras[i].length; k++){
				cambiarVisibilidad(i, k, false);
			}
		}
	}
	
	public void cambiarVisibilidad(Coordenada c, boolean visible){
		this.letras[c.y][c.x].visible = visible;
	}
	
	public void cambiarVisibilidad(int y, int x, boolean visible){
		this.letras[y][x].visible = visible;
	}
	
	public void DescubrirLetra(Coordenada c){
		this.letras[c.y][c.x].descubierta = true;
	}
	
	public void DescubrirLetra(int y, int x){
		this.letras[y][x].descubierta = true;
	}
	
	public boolean estaDescubierta(Coordenada c){
		return this.letras[c.y][c.x].descubierta;
	}

	public void initLetras(){
		letrasAGenerar = this.tamanio / 2;
		letrasGeneradas = new char[letrasAGenerar];
		
		for(int i = 0; i < letrasGeneradas.length; i++){
			letrasGeneradas[i] = ' ';
		}
		
		for(int i = 0; i < letrasGeneradas.length; i++){
			boolean existe = false;
			do{
				existe = false;
				char tLetra = Ut.generarLetra();
				for(int k = 0; k < letrasGeneradas.length; k++){
					if(letrasGeneradas[k] == tLetra){
						existe = true;
						break;
					}
				}
				if(!existe){
					letrasGeneradas[i] = tLetra;
				}
			}while(existe);
		}

		for(int i = 0; i < this.letras.length; i++){
			for(int k = 0; k < this.letras[i].length; k++){
				letras[i][k] = new Letra();
			}
		}
	}

	public void llenar(){
		for(int i = 0; i < letrasGeneradas.length; i++){
			char letra = letrasGeneradas[i];
			int tempX = 0, tempY = 0;
			boolean posicionLibre;
			for(int k = 0; k < 2; k++){
				do{
					posicionLibre = false;
					tempX = Ut.generarNumero(this.columnas);
					tempY = Ut.generarNumero(this.filas);
					if(this.posiciones[tempY][tempX] == 0){
						posicionLibre = true;
						this.posiciones[tempY][tempX] = 1;
						this.letras[tempY][tempX].setValor(letra);
					}
				}while(!posicionLibre);
			}
		}
	}
	
	public void imprimirDescubiertas(){
		System.out.println();
		for(int i = 0; i < this.filas; i++){
			System.out.print("\n\t");
			for(int k = 0; k < this.columnas; k++){
				System.out.print( ((letras[i][k].descubierta)?letras[i][k].valor():"#") + " ");
			}
		}
		System.out.print("\n");		
	}
	
	public void imprimirVisibles(){
		System.out.println();
		for(int i = 0; i < this.filas; i++){
			System.out.print("\n\t");
			for(int k = 0; k < this.columnas; k++){		
				System.out.print(((letras[i][k].descubierta || letras[i][k].visible)?letras[i][k].valor():"#") + " ");
			}
		}
		System.out.print("\n");
	}

	public void imprimir(){
		System.out.println();
		for(int i = 0; i < this.filas; i++){
			System.out.print("\n\t");
			for(int k = 0; k < this.columnas; k++){
				System.out.print(letras[i][k].valor() + " ");
			}
		}
		System.out.print("\n");		
	}
}