package fuentes;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class LetraX extends JButton{
	private char valor;
	public boolean visible;
	public boolean descubierta;
    public Coordenada coordenadas;
    public static final int ROJO = 1;
    public static final int AZUL = 3;
    public static final int VERDE = 2;

	public LetraX(int xPos, int yPos){
        super();
		this.valor = ' ';
		this.visible = false;
		this.descubierta = false;
        this.coordenadas = new Coordenada(xPos, yPos);
        this.setBackground(new Color(223, 247, 247));
	}
    
    public void setSizeX(int r){
        int ft = (int)((this.getParent().getSize().height * 1.9)/r);
        this.setFont(new Font("Cambria", Font.BOLD, 28));
    }
    
    public void setColor(int color){
        switch(color){
            case 1:
                this.setBackground(Color.orange);
                this.setForeground(Color.white);
                break;
            case 2:
                this.setBackground(Color.green);
                this.setForeground(Color.white);
                break;
            case 3:
                this.setBackground(Color.blue);
        }
    }
    
    @Override
    public String toString(){
        return "" + this.valor;
    }
	
	public void setValor(char val){
		this.valor = val;
        this.setText(this.valor + "");
	}
	
	public char valor(){
		return this.valor;
	}
    
    public Coordenada getCoordenadas(){
        return coordenadas;
    }
}