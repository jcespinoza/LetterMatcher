package ventanas;

import fuentes.Nivel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CNivelesF extends JDialog implements ActionListener, WindowListener, ChangeListener{
    private Nivel tempNivelB, tempNivelI, tempNivelA, niveles[];
    private JLabel lBasico, lBfilas, lBcolu, lBfallos;
    private JLabel lIntermedio, lIfilas, lIcolu, lIfallos;
    private JLabel lAvanzado, lAfilas, lAcolu, lAfallos;
    private JLabel lfNB, lfNI, lfNA, lcNB, lcNI, lcNA;
    private JSlider slfBasico,slfInter, slfAvanz;
    private JSlider slcBasico,slcInter, slcAvanz;
    private JSpinner spfBasico, spfInter, spfAvanz;
    private JButton bGuardar, bCancelar;
    private JPanel bFilas, iFilas, aFilas;
    private JPanel bColus, iColus, aColus;
    private JPanel bFallos, iFallos, aFallos;
    private JPanel bConf, iConf, aConf;
    private JPanel pBasico, pInter, pAvanz, jpBotones, jpNiveles;
    private boolean nivelesSet;
    
    public CNivelesF(JFrame jf, Nivel nivs[]){
        super(jf, true);
        setTitle("Configuracion de Niveles");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(320, 480));
        setSize(320, 480);
        setLocationRelativeTo(jf);
        
        initComponentes();
        agregarComponentes();
        setEventSuppport();
        llenarValores(nivs);
    }
    
    private void initComponentes(){
        
        SpinnerModel spmb = new SpinnerNumberModel(1, 1, null, 1);
        SpinnerModel spmi = new SpinnerNumberModel(1, 1, null, 1);
        SpinnerModel spma = new SpinnerNumberModel(1, 1, null, 1);
        jpNiveles = new JPanel(new BorderLayout(1, 30));
        bGuardar = new JButton("Guardar");
        bCancelar = new JButton("Cancelar");
        jpBotones = new JPanel(new BorderLayout(2, 2));

        lBasico = new JLabel("NIVEL BASICO", JLabel.CENTER);
        lBasico.setFont(new Font("Candara", Font.BOLD, 20));
        bFilas = new JPanel(new BorderLayout());
        lBfilas = new JLabel("Numero de filas:           ");
        slfBasico = new JSlider(1, 25);
        lfNB = new JLabel("0");
        bColus = new JPanel(new BorderLayout());
        lBcolu = new JLabel("Numero de Columnas:");
        slcBasico = new JSlider(1, 25);
        lcNB = new JLabel("0");
        bFallos = new JPanel(new BorderLayout(5, 0));
        lBfallos = new JLabel("Fallos permitidos:         ");
        spfBasico = new JSpinner(spmb);
        spfBasico.setSize(100, 50);
        bConf = new JPanel(new BorderLayout(2, 6));
        pBasico = new JPanel(new BorderLayout(2, 5));

        lIntermedio = new JLabel("NIVEL INTERMEDIO", JLabel.CENTER);
        lIntermedio.setFont(new Font("Candara", Font.BOLD, 20));
        iFilas = new JPanel(new BorderLayout());
        lIfilas = new JLabel("Numero de filas:           ");
        slfInter = new JSlider(1, 25);
        lfNI = new JLabel("0");
        iColus = new JPanel(new BorderLayout());
        lIcolu = new JLabel("Numero de Columnas:");
        slcInter = new JSlider(1, 25);
        lcNI = new JLabel("0");
        iFallos = new JPanel(new BorderLayout(5, 0));
        lIfallos = new JLabel("Fallos permitidos:         ");
        spfInter = new JSpinner(spmi);
        iConf = new JPanel(new BorderLayout(2, 6));
        pInter = new JPanel(new BorderLayout(2, 5));

        lAvanzado = new JLabel("NIVEL AVANZADO", JLabel.CENTER);
        lAvanzado.setFont(new Font("Candara", Font.BOLD, 20));
        aFilas = new JPanel(new BorderLayout());
        lAfilas = new JLabel("Numero de filas:           ");
        slfAvanz = new JSlider(1, 25);
        lfNA = new JLabel("0");
        aColus = new JPanel(new BorderLayout());
        lAcolu = new JLabel("Numero de Columnas:");
        slcAvanz = new JSlider(1, 25);
        lcNA = new JLabel("0");
        aFallos = new JPanel(new BorderLayout(5, 0));
        lAfallos = new JLabel("Fallos permitidos:         ");
        spfAvanz = new JSpinner(spma);
        aConf = new JPanel(new BorderLayout(2, 6));
        pAvanz = new JPanel(new BorderLayout(2, 5));
    }
    
    private void agregarComponentes(){
        setLayout(new BorderLayout(2, 3));
        jpBotones.add(bGuardar);
        jpBotones.add(bCancelar, BorderLayout.EAST);
        
        bFilas.add(lBfilas, BorderLayout.WEST);
        bFilas.add(slfBasico, BorderLayout.CENTER);
        bFilas.add(lfNB, BorderLayout.EAST);
        bColus.add(lBcolu, BorderLayout.WEST);
        bColus.add(slcBasico, BorderLayout.CENTER);
        bColus.add(lcNB, BorderLayout.EAST);
        bFallos.add(lBfallos, BorderLayout.WEST);
        bFallos.add(spfBasico);
        bConf.add(bFilas, BorderLayout.NORTH);
        bConf.add(bColus, BorderLayout.CENTER);
        bConf.add(bFallos, BorderLayout.SOUTH);
        
        pBasico.add(lBasico, BorderLayout.NORTH);
        pBasico.add(bConf, BorderLayout.SOUTH);
        
        iFilas.add(lIfilas, BorderLayout.WEST);
        iFilas.add(slfInter, BorderLayout.CENTER);
        iFilas.add(lfNI, BorderLayout.EAST);
        iColus.add(lIcolu, BorderLayout.WEST);
        iColus.add(slcInter, BorderLayout.CENTER);
        iColus.add(lcNI, BorderLayout.EAST);
        iFallos.add(lIfallos, BorderLayout.WEST);
        iFallos.add(spfInter);
        iConf.add(iFilas, BorderLayout.NORTH);
        iConf.add(iColus, BorderLayout.CENTER);
        iConf.add(iFallos, BorderLayout.SOUTH);

        pInter.add(lIntermedio, BorderLayout.NORTH);
        pInter.add(iConf, BorderLayout.SOUTH);
        
        aFilas.add(lAfilas, BorderLayout.WEST);
        aFilas.add(slfAvanz, BorderLayout.CENTER);
        aFilas.add(lfNA, BorderLayout.EAST);
        aColus.add(lAcolu, BorderLayout.WEST);
        aColus.add(slcAvanz, BorderLayout.CENTER);
        aColus.add(lcNA, BorderLayout.EAST);
        aFallos.add(lAfallos, BorderLayout.WEST);
        aFallos.add(spfAvanz);
        aConf.add(aFilas, BorderLayout.NORTH);
        aConf.add(aColus, BorderLayout.CENTER);
        aConf.add(aFallos, BorderLayout.SOUTH);

        pAvanz.add(lAvanzado, BorderLayout.NORTH);
        pAvanz.add(aConf, BorderLayout.SOUTH);

        jpNiveles.add(pBasico, BorderLayout.NORTH);
        jpNiveles.add(pInter, BorderLayout.CENTER);
        jpNiveles.add(pAvanz, BorderLayout.SOUTH);
        
        add(jpNiveles, BorderLayout.NORTH);
        add(jpBotones, BorderLayout.SOUTH);
    }
    
    private void setEventSuppport(){
        bGuardar.addActionListener(this);
        bCancelar.addActionListener(this);
        slfBasico.addChangeListener(this);
        slcBasico.addChangeListener(this);
        slfInter.addChangeListener(this);
        slcInter.addChangeListener(this);
        slfAvanz.addChangeListener(this);
        slcAvanz.addChangeListener(this);
    }

    private boolean nivsOK(){
        int fb = this.slfBasico.getValue();
        int cb = this.slcBasico.getValue();
        int ffb = (int)(this.spfBasico.getValue());
        tempNivelB = new Nivel(0, fb, cb, ffb);
        
        int fi = this.slfInter.getValue();
        int ci = this.slcInter.getValue();
        int ffi = (int)(this.spfInter.getValue());
        tempNivelI = new Nivel(1, fi, ci, ffi);
        
        int fa = this.slfAvanz.getValue();
        int ca = this.slcAvanz.getValue();
        int ffa = (int)(this.spfAvanz.getValue());
        tempNivelA = new Nivel(2, fa, ca, ffa);
        
        String errores = "";
        boolean logicOK = Nivel.validarNiveles(tempNivelB, tempNivelI, tempNivelA);
        boolean tamOK = Nivel.validarTamanio(tempNivelB) && Nivel.validarTamanio(tempNivelI)
                && Nivel.validarTamanio(tempNivelA);
        boolean divisiblesOK = Nivel.validarDivisibilidad(tempNivelB)
                && Nivel.validarDivisibilidad(tempNivelI) && Nivel.validarDivisibilidad(tempNivelA);
        if(!logicOK){
            errores += "Los niveles no estan configurados logicamente.\nReglas:\n"+
                    "El tamanio del nivel Basico debe ser menor que el tamanio tamanio del nivel Intermedio.\n"+
                    "El tamanio del nivel Intermedio debe ser menor que el tamanio del nivel Avanzado\n\n";
        }
        if(!tamOK){
            errores += "Por lo menos uno de los tamanios excede el limete de 50 celdas.\n\n";
        }
        if(!divisiblesOK){
            errores += "Por lo menos uno de los tamanios no es divisible entre dos.\nNo se puede formar parejas en tamanios impares.\n\n";
        }

        errores += "Para solucionar esto modifique las filas y columnas apropiadamente";
        boolean todoOK = logicOK && tamOK && divisiblesOK;
        if(!todoOK){
            JOptionPane.showMessageDialog(this, errores, "Configuracion Invalida", JOptionPane.ERROR_MESSAGE);
        }
        
        return todoOK;
    }
    
    private void llenarValores(Nivel nivs[]){
        if(nivs != null){
            this.niveles = nivs;
            if(nivs[0] != null){
                this.slfBasico.setValue(nivs[0].filasTablero);
                this.slcBasico.setValue(nivs[0].columTablero);
                this.spfBasico.setValue(nivs[0].maxFallos);
            }
            if(nivs[1] != null){
                this.slfInter.setValue(nivs[1].filasTablero);
                this.slcInter.setValue(nivs[1].columTablero);
                this.spfInter.setValue(nivs[1].maxFallos);
            }
            if(nivs[2] != null){
                this.slfAvanz.setValue(nivs[2].filasTablero);
                this.slcAvanz.setValue(nivs[2].columTablero);
                this.spfAvanz.setValue(nivs[2].maxFallos);
            }
        }else{
            this.spfBasico.setValue(1);
            this.spfInter.setValue(2);
            this.spfAvanz.setValue(3);
        }
    }
    
    private void setNiveles(){
        this.niveles[0] = tempNivelB;
        this.niveles[1] = tempNivelI;
        this.niveles[2] = tempNivelA;
        this.nivelesSet = true;
    }
    
    public boolean mostrar(){
        setVisible(true);
        return this.nivelesSet;
    }

    private void salir(){
        this.setVisible(false);
        this.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bCancelar){
            salir();
        }else if(e.getSource() == bGuardar){
            if(this.nivsOK()){
                setNiveles();
                salir();
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
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

    @Override
    public void stateChanged(ChangeEvent ev) {
        JSlider source = (JSlider)ev.getSource();
        if(source == slfBasico){
            lfNB.setText("" + slfBasico.getValue());
        }else if(source == slcBasico){
            lcNB.setText("" + slcBasico.getValue());
        }else if(source == slfInter){
            lfNI.setText("" + slfInter.getValue());
        }else if(source == slcInter){
            lcNI.setText("" + slcInter.getValue());
        }else if(source == slfAvanz){
            lfNA.setText("" + slfAvanz.getValue());
        }else if(source == slcAvanz){
            lcNA.setText("" + slcAvanz.getValue());
        }
    }
}
