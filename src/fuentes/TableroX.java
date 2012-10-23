package fuentes;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class TableroX extends JPanel{
	private int tamanio;
	private int filas;
	private int columnas;
	private LetraX letras[][];
	private int posiciones[][];
	private char letrasGeneradas[];
	private int letrasAGenerar;

	public TableroX(int filasMatrix, int columMatrix){
        super(new GridLayout(filasMatrix, columMatrix, 1,1));
		this.filas = filasMatrix;
		this.columnas = columMatrix;
		this.tamanio = filasMatrix * columMatrix;
		
		this.posiciones = new int[filas][columnas];
		letras = new LetraX[filas][columnas];
		
		initLetras();
		llenar();
        asignarLetras();
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
	
	public LetraX getLetra(int fila, int columna){
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
        cambiarVisibilidad(c.y, c.x, visible);
	}
	
	public void cambiarVisibilidad(int y, int x, boolean visible){
		this.letras[y][x].visible = visible;
	}
	
	public void DescubrirLetra(Coordenada c){
		DescubrirLetra(c.y, c.x);
	}
	
	public void DescubrirLetra(int y, int x){
		this.letras[y][x].descubierta = true;
        this.letras[y][x].setColor(LetraX.VERDE);
	}
	
	public boolean estaDescubierta(Coordenada c){
		return this.letras[c.y][c.x].descubierta;
	}

	private void initLetras(){
		letrasAGenerar = this.tamanio / 2;
		letrasGeneradas = new char[letrasAGenerar];
		
		for(int i = 0; i < letrasGeneradas.length; i++){
			letrasGeneradas[i] = ' ';
		}
		
		for(int i = 0; i < letrasGeneradas.length; i++){
			boolean existe;
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
				letras[i][k] = new LetraX(k, i); //bug found and repaired
			}
		}
	}

    private void asignarLetras(){
        for(int i = 0; i < letras.length; i++){
            for(int k = 0; k < letras[i].length; k++){
                this.add(letras[i][k]);
            }
        }
    }
    
    public void descubrirTodas(){
        for(int i = 0; i < letras.length; i++){
            for(int k = 0; k < letras[i].length; k++){
                letras[i][k].descubierta = true;
            }
        }
    }
    
	private void llenar(){
		for(int i = 0; i < letrasGeneradas.length; i++){
			char letra = letrasGeneradas[i];
			int tempX, tempY;
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
                        this.letras[tempY][tempX].setText("#");
					}
				}while(!posicionLibre);
			}
		}
	}
	
	public void mostrarDescubiertas(){
		for(int i = 0; i < this.filas; i++){
			for(int k = 0; k < this.columnas; k++){
                if(letras[i][k].descubierta){
                    letras[i][k].setText("" + letras[i][k].valor());
                }else{
                    letras[i][k].setText("#");
                }
			}
		}
	}
	
	public void mostrarVisibles(){
		for(int i = 0; i < this.filas; i++){
			for(int k = 0; k < this.columnas; k++){
                if(letras[i][k].descubierta || letras[i][k].visible){
                    letras[i][k].setText("" + letras[i][k].valor());
                }else{
                    letras[i][k].setText("#");
                }
			}
		}
	}

	public void mostrar(){
		for(int i = 0; i < this.filas; i++){
			for(int k = 0; k < this.columnas; k++){
                letras[i][k].setText(letras[i][k].valor() + "");
			}
		}
	}
    
	public void mostrarRojas(){
        mostrar();
		for(int i = 0; i < this.filas; i++){
			for(int k = 0; k < this.columnas; k++){
                if(!letras[i][k].descubierta){
                    letras[i][k].setColor(LetraX.ROJO);
                }
			}
		}
	}
    
    public void resizeLetras(){
		for(int i = 0; i < this.filas; i++){
			for(int k = 0; k < this.columnas; k++){
                letras[i][k].setSizeX(this.getFilas());
			}
		}

    }
}