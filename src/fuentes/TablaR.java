package fuentes;

public class TablaR{
	public Jugador jugadores[];
	public int nJugadores;
    private int cJugadores;
	private int indexLibre;
	public int ptsPorNivelO[];
	public int ptsPorNivelN[];
	public int indicesJ[];

	public TablaR(int nJugadores){
		this.nJugadores = nJugadores;
		jugadores = new Jugador[nJugadores];
		initJugadores();
	}
	
	private void initJugadores(){
		for(Jugador j: this.jugadores){
			j = new Jugador();
		}
	}

	public void mostrarGlobal(){
		System.out.println("#  Nombre:\t\tBasico\tIntermedio\tAvanzado");
		int i = 1;
		for(Jugador j: this.jugadores){
			if(j != null){
				String orden = Ut.encajarString("" + i, 3);
				System.out.print(orden + j.getNombre() + "\t" + j.pNivelBa() + "\t" + j.pNivelIn() + "\t\t" + j.pNivelAv());
				System.out.println();
				i++;
			}
		}
	}

    public void initPuntos(){
		ptsPorNivelO = new int[cJugadores];
		ptsPorNivelN = new int[cJugadores];
		indicesJ = new int[cJugadores];
    }

	public void mostrarPorNivel(int nivel){
		System.out.println("\nCampeones del nivel " + Nivel.getNombreNivel(nivel));
		System.out.println("#  Nombre:\t\tPuntos");
		initPuntos();
		llenarPts(nivel);
		
		ordenarPtsN();
		for(int i = 0; i < cJugadores; i++){
			if(jugadores[i] != null){
				imprimirPts(i);
			}
		}
	}
	
	public void imprimirPts(int index){
		int i2 = indicesJ[index];
		String orden = Ut.encajarString("" + (index + 1), 3);
        if(i2 != -1){
            System.out.println(orden + jugadores[i2].getNombre() + "\t" + ptsPorNivelO[i2]);            
        }
	}

	public void llenarPts(int nivel){
		for(int i = 0; i < ptsPorNivelO.length; i++){
			if(jugadores[i] != null){
				int temp = this.jugadores[i].getPuntosNivel(nivel);
				this.ptsPorNivelN[i] = temp;
				this.ptsPorNivelO[i] = temp;
			}
		}
	}

    
    
	public void ordenarPtsN(){
        for(int i = 0; i < indicesJ.length; i++){
            indicesJ[i] = i;
        }
		for(int i = 0; i < ptsPorNivelN.length; i++){
            for(int j = 0; j < ptsPorNivelN.length - 1; j++){
                if(ptsPorNivelN[j] < ptsPorNivelN[j + 1]){
                    int tempP = ptsPorNivelN[j];
                    int tempIndex = indicesJ[j];
                    
                    ptsPorNivelN[j] =  ptsPorNivelN[j + 1];
                     ptsPorNivelN[j + 1] = tempP;
                     
                     indicesJ[j] = indicesJ[j + 1];
                     indicesJ[j + 1] = tempIndex;
                }
            }
        }
	}

	public void agregarJugador(Jugador jugador){
		this.jugadores[indexLibre] = jugador;
		setIndexLibre();
        if(cJugadores < nJugadores){
            cJugadores++;
        }
	}
    
    public int getNJugadores(){
        return this.cJugadores;
    }
    
	public void setIndexLibre(){
		this.indexLibre = Ut.nextIndex(indexLibre, (nJugadores - 1));
	}

    private void printIndexes() {
        for(int i = 0; i < indicesJ.length; i++){
            System.out.print(indicesJ[i] + " ");
        }
    }
}