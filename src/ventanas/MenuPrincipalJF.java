package ventanas;

import fuentes.Juego;
import fuentes.Jugador;
import fuentes.Nivel;
import fuentes.TablaR;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuPrincipalJF extends JFrame implements ActionListener{
    
    JLabel iMenu = new JLabel("MENU PRINCIPAL", JLabel.CENTER);

    
    JButton jbNivs = new JButton("Configurar Niveles");
    JButton jbJuego = new JButton("Juego");
    JButton jbResults = new JButton("Resultados");
    JPanel jpBotones = new JPanel(new BorderLayout(0,1));
    JButton jbVolver = new JButton("Volver a Modo Consola");
    JButton jbSalir = new JButton("Salir");
    
    
        Nivel niveles[];
        Juego juego = null;             
        Jugador resJ = null;            
        TablaR rTabla;
        String in;			
        int nJuegos = 0;    

        boolean nivelesConfigurados = false;	
        boolean nivelesCorrectos = false;				
        boolean bResult;
        Object accion[];
    
    public MenuPrincipalJF(Nivel nivs[], int nJuegosJ, TablaR preTabla, Object accion[], boolean nivsOk){
        super("Juego de Memoria");
        setVars(nivs, nJuegosJ, preTabla, accion, nivsOk);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(480, 320));
        this.setSize(getMinimumSize());
        BorderLayout br = new BorderLayout(0, 2);
        this.setLayout(br);

        agregarComponentes();
        setEventSupport();
        setLocationRelativeTo(null);
    }
    
    private void agregarComponentes(){
        iMenu.setFont(new Font("Candara", Font.BOLD, 32));
        add(iMenu, BorderLayout.NORTH);        
        add(jbNivs, BorderLayout.WEST);
        add(jbJuego, BorderLayout.CENTER);
        add(jbResults, BorderLayout.EAST);
        jpBotones.add(jbVolver, BorderLayout.NORTH);
        jpBotones.add(jbSalir, BorderLayout.SOUTH);
        
        add(jpBotones, BorderLayout.SOUTH);
    }
    
    private void setEventSupport(){
        jbSalir.addActionListener(this);
        jbNivs.addActionListener(this);
        jbJuego.addActionListener(this);
        jbResults.addActionListener(this);
        jbSalir.addActionListener(this);
        jbVolver.addActionListener(this);
    }
    
    public void mostrar(){
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if(ev.getSource() == jbSalir){
            this.accion[0] = true;
            this.dispose();
        }else if(ev.getSource() == jbNivs){
           accionNivel();
        }else if(ev.getSource() == jbJuego){
            if(nivelesCorrectos){
                jugar();                
            }else{
                JOptionPane.showMessageDialog(this, "Los niveles no se han configurado correctamente", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else if(ev.getSource() == jbResults){
            mostrarMenuR();
        }else if(ev.getSource() == jbVolver){
            setVisible(false);
            this.accion[0] = false;
            this.dispose();
        }
    }

    private void accionNivel() {
        boolean ok = true;
        if(nJuegos > 0){
            JOptionPane.showMessageDialog(this, "No se puede cambiar la configuracion de los niveles si ya se jugo al menos un juego."+
                            "\nLos jugados en Modo Consola tambien cuentan.", "Error", JOptionPane.WARNING_MESSAGE);
            ok = false;
        }
        if(nivelesCorrectos && nJuegos == 0){
            int resN = JOptionPane.showConfirmDialog(this, "Los niveles ya fueron configurados, desea cambiarlos?", "Configuracion de Niveles",
                    JOptionPane.YES_NO_OPTION);
            if(resN == 0){
                ok = true;
            }else{
                ok = false;
            }
        }
        if(ok){
            CNivelesF cn = new CNivelesF(this, this.niveles);
            if(cn.mostrar()){
                this.nivelesCorrectos = true;
            }
        }
    }

    private void setVars(Nivel nivs[], int jj, TablaR tt, Object bb[], boolean nivelesOk){
        boolean nOK = nivs != null;
        boolean ok = nOK && nivs[0] != null && nivs[1] != null && nivs[2] != null;
        niveles = new Nivel[3];
        if(ok){
            niveles = nivs;
            nivelesConfigurados = true;
        }else{
            nivelesConfigurados =false;
            nivelesCorrectos = false;
        }
        if(tt != null){
            rTabla = tt;
        }else{
            rTabla = new TablaR(20);
        }
        nJuegos = jj;
        this.accion = bb;
        this.nivelesCorrectos = nivelesOk;
    }
    
    public int preguntarNivel(){
        String ops[] = {"Basico", "Intermedio", "Avanzado"};
        int res = JOptionPane.showOptionDialog(this, "Para cual nivel desea mostrar resultados?", "Seleccion de Nivel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, ops, rootPane);
        return res;
    }

    public int preguntarNivelJ(){
        String ops[] = {"Basico", "Intermedio", "Avanzado"};
        int res = JOptionPane.showOptionDialog(this, "Que nivel desea jugar?", "Seleccion de Nivel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, ops, rootPane);
        return res;
    }
        
    public boolean continuar(Jugador j){
        int res = JOptionPane.showConfirmDialog(this, (j.getNombreT() + ", desea continuar con el siguiente nivel?"), "Siguiente nivel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return (res == JOptionPane.YES_OPTION);
    }
    
    public void jugar(){
        int res1 = preguntarNivelJ();
        int niv = res1;
        if(niv > -1){
            Jugador j = new Jugador();
            nuevoJuego(niv, j);            
        }
    }

    private void nuevoJuego(int nivel, Jugador jg) {
        JuegoFrame jf = new JuegoFrame(this, niveles[nivel]);
        Jugador j = jf.jugar(jg);
        jf.dispose();
        if(j.victoria){
            if(nivel < Nivel.DIFICIL){
                if(continuar(j)){
                    nuevoJuego(++nivel, j); //KeyFEature
                }else{
                    rTabla.agregarJugador(jg);
                }
            }else{
                rTabla.agregarJugador(jg);
            }
        }
        nJuegos++;
    }

    private void mostrarMenuR() {
        ResultsF rf;
        String op1[] = {"Global", "Por Nivel"};
        int res = JOptionPane.showOptionDialog(this, "Que tipo de resultados desea ver?", "Seleccion de Resultados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, op1, rootPane);
        switch(res){
            case JOptionPane.OK_OPTION:
                rf = new ResultsF(this, rTabla, 3);
                rf.mostrar();
                break;
            case JOptionPane.NO_OPTION:
                int tipo = preguntarNivel();
                if(tipo >=0){
                    rf = new ResultsF(this, rTabla, tipo);
                    rf.mostrar();
                }
                break;
        }
    }
}