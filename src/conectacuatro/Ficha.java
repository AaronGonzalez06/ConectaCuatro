/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectacuatro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Aaron
 */
public class Ficha {

    private Turno turno;
    private int x;
    private int y;

    private int cuadrado = 100;

    //Graphics2D g2d;

    public enum Turno {
        JUGADOR1, JUGADOR2, VACIO;
    }

    public Ficha(int x, int y) {
        this.turno = Turno.VACIO;
        this.x = x;
        this.y = y;
    }
    
    

    public void agregarFicha(Graphics2D g2d) {
        //System.out.println(this.turno);
        int posicionX = this.x * 100;
        int posicionY = this.y * 100;
        switch (this.turno) {
            case JUGADOR1:
                Color col = new Color(255, 0, 0);
                g2d.setColor(col);
                g2d.fillOval(posicionX, posicionY, 100, 100);
                break;
            case JUGADOR2:
                Color col2 = new Color(100, 200, 0);
                g2d.setColor(col2);
                //System.err.println(posicionX + " " + posicionY);
                g2d.fillOval(posicionX, posicionY, 100, 100);

                break;
            default:
                //System.err.println("algo va mal");
                break;

        }
        /*Color col = new Color(255, 0, 0);
        g2d.setColor(col);
        g2d.fillOval(this.x, this.y, 100, 100);*/
        //System.out.println("llegue");
    }
    

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

}
