package fuentes;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Jugador{
	private Scanner scj = new Scanner(System.in);
	private String nombre;
	private int pNivelBa;
	private int pNivelIn;
	private int pNivelAv;
	public boolean victoria;
	public boolean yaJugo;
	
	public Jugador(){
		scj.useDelimiter(System.getProperty("line.separator"));
		setNombre(" ");
	}

    public Jugador(String nom, int pB, int pI, int pA){
        setNombre(nom);
        this.pNivelBa = pB;
        this.pNivelIn = pI;
        this.pNivelAv = pA;
    }
	
	public void setNombre(){
		System.out.println("\nIngrese su nombre");
        String ss = scj.next();
		setNombre(ss);
        System.out.println("\nGuardado");
	}
    
    public void setNombre(boolean encajar){
        String res = JOptionPane.showInputDialog(null, "Ingrese su nombre", "Victoria", JOptionPane.INFORMATION_MESSAGE);
        if(res == null || res.equals("")){
            res = Nombre.randomNombre();
            JOptionPane.showMessageDialog(null, ("Dado que no ingreso datos su nombre sera " + res), "No ingreso nombre", JOptionPane.ERROR_MESSAGE);
            setNombre(res);
        }
        if(encajar){
            setNombre(res);
        }else{
            this.nombre = res;
        }
    }

	public void setNombre(String nom){
		nombre = Ut.encajarString(nom, 14);
	}
	
	public String getNombre(){
		return this.nombre;
	}
    
    public String getNombreT(){
		return this.nombre.trim();
	}
	
	public int pNivelBa(){
		return this.pNivelBa;
	}
	
	public int pNivelIn(){
		return this.pNivelIn;
	}
	
	public int pNivelAv(){
		return this.pNivelAv;
	}
	
	public int getPuntosNivel(int nivel){
		switch(nivel){
			case 0:
				return pNivelBa();
			case 1:
				return pNivelIn();
			case 2:
				return pNivelAv();
			case 3:
				break;
			default:
				//throw NivelInvalidoException();
		}
		return 0;
	}
	
	public void actualizarPuntajes(int nivel, int puntaje){
		switch(nivel){
			case 0:
				this.pNivelBa = puntaje;
				break;
			case 1:
				this.pNivelIn = puntaje;
				break;
			case 2:
				this.pNivelAv = puntaje;
				break;
			case 3:
				break;
			default:
				//throw new NivelInvalidoException();
		}
	}
}