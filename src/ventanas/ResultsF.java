package ventanas;

import fuentes.Jugador;
import fuentes.Nivel;
import fuentes.TablaR;
import java.awt.GridLayout;
import javax.swing.*;


public class ResultsF extends JDialog{
    private String columnas[];
    private TablaR tr;
    private Object data[][];

    public ResultsF(JFrame papa, TablaR results, int tipo){
        super(papa, "Resultados", true);
        setSize(320, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.tr = results;
        crearObjetos(tipo);
    }
    
    public void mostrar(){
        JTablaGlobal jtg = new JTablaGlobal(columnas, data);
        add(jtg);
        setVisible(true);
    }

    private void crearObjetos(int tipo) {
        if(tipo == 3){
            data = new Object[tr.getNJugadores()][4];
            for(int i = 0; i < tr.nJugadores; i++){
                if(tr.jugadores[i] != null){
                    data[i][0] = tr.jugadores[i].getNombreT();
                    data[i][1] = tr.jugadores[i].getPuntosNivel(0);
                    data[i][2] = tr.jugadores[i].getPuntosNivel(1);
                    data[i][3] = tr.jugadores[i].getPuntosNivel(2);
                }
            }
            this.columnas = new String[4];
            this.columnas[0] = "Nombre";
            this.columnas[1] = "Basico";
            this.columnas[2] = "Intermedio";
            this.columnas[3] = "Avanzado";
        }else{
            data = new Object[tr.getNJugadores()][2];
            for(int i = 0; i < tr.nJugadores; i++){
                if(tr.jugadores[i] != null){
                    data[i][0] = tr.jugadores[i].getNombreT();
                    data[i][1] = tr.jugadores[i].getPuntosNivel(tipo);
                }
            }
            String niv = Nivel.getNombreNivel(tipo);
            this.columnas = new String[2];
            this.columnas[0] = "Nombre";
            this.columnas[1] = "Nivel " + niv;
        }
    }
    
    private class JTablaGlobal extends JPanel{
        public JTablaGlobal(String cols[], Object data[][]){
            super(new GridLayout(1,0));

            JTable tabla = new JTable(data, cols);
            tabla.setAutoCreateRowSorter(true);
            tabla.setEnabled(false);
            JScrollPane js = new JScrollPane(tabla);
            add(js);
        }
    }
}