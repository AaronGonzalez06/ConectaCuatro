/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conectacuatro;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Aaron
 */
public class ConectaCuatro extends JFrame {

    private int sizex = 700, sizey = 700;

    private int cuadrado = 100;

    boolean ocupado;

    Tablero tablero = new Tablero();

    public ConectaCuatro() {
        this.setTitle("Conecta 4");
        this.setSize(sizex + 20, sizey + 40);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciar();
    }

    private void iniciar() {

        tablero.setBounds(0, 0, sizex, sizey);
        this.add(tablero);

        tablero.setTurno(Ficha.Turno.JUGADOR1);

        tablero.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(tablero.getTurno());
                int x = (int) (e.getX() / cuadrado);
                int y = (int) (e.getY() / cuadrado);
                acciones(e, x, y);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    ;  
    
    
    public void acciones(MouseEvent e, int ejeX, int ejeY) {
        
        if (tablero.getTurno() == Ficha.Turno.JUGADOR1) {
            
            ocupado = tablero.colocarColor(Ficha.Turno.JUGADOR1, ejeX, ejeY);
            boolean empate = tablero.empate();
            boolean resultado = tablero.columnas(Ficha.Turno.JUGADOR1, Ficha.Turno.JUGADOR2, ejeX, ejeY);
            boolean resultado2 = tablero.filas(Ficha.Turno.JUGADOR1, Ficha.Turno.JUGADOR2, ejeX, ejeY);
            boolean resultado3 = tablero.diagonales(Ficha.Turno.JUGADOR1, Ficha.Turno.JUGADOR2, ejeX, ejeY);
            boolean resultado4 = tablero.diagonalesInversas(Ficha.Turno.JUGADOR1, Ficha.Turno.JUGADOR2, ejeX, ejeY);
            
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Ganan los rojos en columnas");
            } else if (resultado2) {
                JOptionPane.showMessageDialog(null, "Ganan los rojos en filas");
            } else if (resultado3) {
                JOptionPane.showMessageDialog(null, "Ganan los rojos en diagonal");
            } else if (resultado4) {
                JOptionPane.showMessageDialog(null, "Ganan los rojos en diagonal");
            }  else if (empate) {
                JOptionPane.showMessageDialog(null, "Empate, vamos a reiniciar el juego");
                tablero.reiniciar();
                tablero.repaint();
            }

            if (ocupado) {
                tablero.setTurno(Ficha.Turno.JUGADOR2);
            } else {
                tablero.setTurno(Ficha.Turno.JUGADOR1);
            }

        } else if (tablero.getTurno() == Ficha.Turno.JUGADOR2) {

            ocupado = tablero.colocarColor(Ficha.Turno.JUGADOR2, ejeX, ejeY);

            boolean empate = tablero.empate();
            
            boolean resultado = tablero.columnas(Ficha.Turno.JUGADOR2, Ficha.Turno.JUGADOR1, ejeX, ejeY);
            boolean resultado2 = tablero.filas(Ficha.Turno.JUGADOR2, Ficha.Turno.JUGADOR1, ejeX, ejeY);
            boolean resultado3 = tablero.diagonales(Ficha.Turno.JUGADOR2, Ficha.Turno.JUGADOR1, ejeX, ejeY);
            boolean resultado4 = tablero.diagonalesInversas(Ficha.Turno.JUGADOR2, Ficha.Turno.JUGADOR1, ejeX, ejeY);

            if (resultado) {
                JOptionPane.showMessageDialog(null, "Gana los verdes en columnas");
            } else if (resultado2) {
                JOptionPane.showMessageDialog(null, "Gana los verdes en filas");
            } else if (resultado3) {
                JOptionPane.showMessageDialog(null, "Gana los verdes en diagonales");
            } else if (resultado4) {
                JOptionPane.showMessageDialog(null, "Gana los verdes en diagonales");
            } else if (empate) {
                JOptionPane.showMessageDialog(null, "Empate, vamos a reiniciar el juego");
                tablero.reiniciar();
                tablero.repaint();
            }

            if (ocupado) {
                tablero.setTurno(Ficha.Turno.JUGADOR1);
            } else {
                tablero.setTurno(Ficha.Turno.JUGADOR2);
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConectaCuatro ventana = new ConectaCuatro();
        ventana.setVisible(true);
    }

}
