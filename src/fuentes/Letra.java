package fuentes;

public class Letra{
	private char valor;
	public boolean visible;
	public boolean descubierta;
	
	public Letra(){
		this.valor = ' ';
		this.visible = false;
		this.descubierta = false;
	}
	
	public void setValor(char val){
		this.valor = val;
	}
	
	public char valor(){
		return this.valor;
	}
}