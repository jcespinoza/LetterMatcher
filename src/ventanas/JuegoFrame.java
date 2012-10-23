package ventanas;

import fuentes.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class JuegoFrame extends JDialog implements ActionListener, WindowListener{
    private Timer tm;
    private Timer lost;
    private TableroX tablero;
	private Nivel nivel;
	private Jugador jugador;
    public Coordenada c1;
    public Coordenada c2;
	private int fallos;
	private int porcentajeFallos;
	private int paresFormados;
	private int puntos;
    private JLabel lNombre;
    private JLabel lAciertos;
    private JLabel lFallos;
    private JLabel lMaxFallos;
    private JLabel lPuntos;
    private JPanel info;
    private JButton play;
    private JButton salir;
    private JPanel jpContenedor;
    private boolean juegoEnProgreso;
    private boolean parejaCompleta;
    private int ordenActual = 1;

    public JuegoFrame(JFrame frame, Nivel nivel){
        super(frame, true);
        setTitle("Juego de Memoria - Nivel " + nivel.getNombre());
        setMinimumSize(new Dimension(231, 416));
        setMaximumSize(new Dimension(1200, 700));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.nivel = nivel;
		tablero = new TableroX(this.nivel.filasTablero, this.nivel.columTablero);
        this.setLayout(new BorderLayout());
        setSize(tablero.getColumnas() * 80, tablero.getFilas() * 80);
        setLocationRelativeTo(null);
        initComponentes();
        agregarComponentes();
        tablero.mostrar();
    }

	public void setJugador(Jugador j){
		this.jugador = j;
		this.jugador.yaJugo = true;
	}

    private void initComponentes(){
        this.tm = new Timer(1000, this);
        this.lost = new Timer(1500, this);
        jugador = new Jugador("Jugador1", 0, 0, 0);
        info = new JPanel();
        this.play = new JButton("Jugar");
        this.salir = new JButton("Salir");
        this.play.setMaximumSize(new Dimension(100, 50));
        this.lNombre = new JLabel("Nombre: " + jugador.getNombre());
		this.lAciertos = new JLabel("Aciertos: " + this.paresFormados);
		this.lFallos = new JLabel("Desaciertos: " + this.fallos);
		this.lMaxFallos = new JLabel("Maximo: " + this.nivel.maxFallos);
		this.lPuntos = new JLabel("Puntos: " + this.puntos);
        info.setLayout(new GridLayout(3,1,1,1));
        this.jpContenedor = new JPanel(new BorderLayout(12, 12));
        tablero.resizeLetras();
    }

    public final void agregarComponentes(){
        agregarInfo();
        this.add(tablero, BorderLayout.CENTER);
        setEventSupport();
        
        
        jpContenedor.setLayout(new FlowLayout());
        play.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpContenedor.add(play);
        jpContenedor.add(salir);
        this.add(jpContenedor, BorderLayout.PAGE_END);
        
    }

	public void mostrarInfo(){
        this.lNombre.setText("Nombre: " + jugador.getNombre());
		this.lAciertos.setText("Aciertos: " + this.paresFormados);
		this.lFallos.setText("Desaciertos: " + this.fallos);
		this.lMaxFallos.setText("Maximo: " + this.nivel.maxFallos);
		this.lPuntos.setText("Puntos: " + this.puntos);
	}

    public void agregarInfo(){
        JPanel r1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        r1.setOpaque(false);
        JPanel r2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        r2.setOpaque(false);
        JPanel r3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        r3.setOpaque(false);
        r1.add(lNombre);
        r2.add(lAciertos);
        r2.add(lFallos);
        r2.add(lMaxFallos);
        r2.add(lPuntos);
        this.info.add(r1);
        this.info.add(r2);
        this.info.add(r3);
        info.setLayout(new GridLayout(3,1,1,1));

        this.add(info, BorderLayout.NORTH);
    }

	private void setPorcentajeFallos(Nivel nivel){
		this.porcentajeFallos = (int)((double)(((double)this.fallos / (double)this.nivel.maxFallos) * 100));
	}

    public void setEventSupport(){
        this.addWindowListener(this);
        if(tablero != null){
            for(int i = 0; i < tablero.getFilas(); i++){
                for(int k = 0; k < tablero.getColumnas(); k++){
                    tablero.getLetra(i, k).addActionListener(this);
                }
            }
        }
        this.play.addActionListener(this);
        this.salir.addActionListener(this);
    }

	private void setPuntos(Nivel nivel){
		this.setPorcentajeFallos(nivel);
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

    public void siguienteOrden(){
        if(ordenActual < 2){
            ordenActual++;
        }else{
            ordenActual--;
        }
    }

    private void procesarLetra(Coordenada c) {
        if(this.ordenActual == 1){
            if(!this.tablero.getLetra(c.y, c.x).descubierta){
                c1 = c;
                siguienteOrden();
                tablero.cambiarVisibilidad(c1, true);                
            }else{
                JOptionPane.showMessageDialog(this, "Esa letra ya esta descubierta", "Error", JOptionPane.ERROR_MESSAGE);                
            }
        }else{
            if(c.x == c1.x && c.y == c1.y){
                JOptionPane.showMessageDialog(this, "Ha clickeado la misma letra", "Error", JOptionPane.ERROR_MESSAGE);
                tablero.resetVisibilidad();
            }else{
                if(!this.tablero.getLetra(c.y, c.x).descubierta){
                    c2 = c;
                    siguienteOrden();
                    tablero.cambiarVisibilidad(c2, true);
                    parejaCompleta = true;             
                }else{
                    JOptionPane.showMessageDialog(this, "Esa letra ya esta descubierta", "Error", JOptionPane.ERROR_MESSAGE);                
                }
            }
        }
        if(parejaCompleta){
            validarPareja(c1, c2);
            parejaCompleta = false;
        }
            tablero.mostrarVisibles();
    }

	public void validarPareja(Coordenada c1, Coordenada c2){
        if(this.tablero.getLetra(c1) == this.tablero.getLetra(c2)){
            this.tablero.DescubrirLetra(c1);
            this.tablero.DescubrirLetra(c2);
            this.paresFormados++;
        }else{
            this.fallos++;   
            this.tm.start();
        }
        if(this.fallos == this.nivel.maxFallos || this.paresFormados == tablero.getNumeroPares()){
            terminarJuego();
        }
        setPuntos(this.nivel);
        mostrarInfo();
	}

    public void terminarJuego(){
        mostrarInfo();
        this.juegoEnProgreso = false;
        if(this.paresFormados == tablero.getNumeroPares()){
           this.jugador.victoria = true;
           this.jugador.actualizarPuntajes(this.nivel.dificultad, puntos);
           JOptionPane.showMessageDialog(this, ("Felicidades has completao el nivel " + this.nivel.getNombre()), "Juego finalizado", JOptionPane.INFORMATION_MESSAGE);
           if(!jugador.yaJugo){
               jugador.setNombre(true);
           }
           this.jugador.yaJugo = true;
           setVisible(false);
        }else if(this.nivel.maxFallos == this.nivel.maxFallos){
            this.jugador.victoria = false;
            JOptionPane.showMessageDialog(this, "Lo siento, has perdido", "Juego Terminado", JOptionPane.ERROR_MESSAGE);
            this.lost.start();
        }
        mostrarInfo();
    }

    public void mostrar(){
        this.setVisible(true);
    }

    public Jugador jugar(Jugador j){
        this.jugador = j;
        if (!j.yaJugo){
            this.jugador.setNombre("Jugador1");
        }
        mostrar();
        return this.jugador;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().getClass() == tablero.getLetra(1, 1).getClass()){  //KeyFeature!!
            if(juegoEnProgreso){
                LetraX lx = (LetraX)e.getSource(); //KeyFeature!!
                Coordenada c = lx.getCoordenadas();
                procesarLetra(c);
            }
        }
        if(e.getSource() == this.play){
            play.setEnabled(false);
            this.juegoEnProgreso = true;
            tablero.mostrarDescubiertas();
        }
        if(e.getSource() == this.tm){
            this.tablero.resetVisibilidad();
            this.tablero.mostrarVisibles();
            this.tm.stop();     //Key Feature
        }
        if(e.getSource() == this.lost){
            tablero.mostrarRojas();
            tablero.descubrirTodas();
            tablero.mostrar();
            this.setVisible(false);
            lost.stop();
        }
        if(e.getSource() == this.salir){
            salir();
        }
    }
    
    public void salir(){
        int confirmacion = JOptionPane.showConfirmDialog(this, "Esta seguro que desea terminar el juego?",
                "Confirmatcion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(confirmacion == JOptionPane.YES_OPTION){
            this.setVisible(false);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        salir();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
}