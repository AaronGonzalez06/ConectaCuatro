/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectacuatro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aaron
 */
public class Tablero extends JPanel {

    private int sizex = 700, sizey = 700;
    private int cuadrado = 100;

    //private Ficha ficha = new Ficha(200,200);    
    private Ficha ficha[][] = new Ficha[7][7];

    private Ficha.Turno turno = Ficha.Turno.VACIO;

    public Tablero() {
        for (int x = 0; x < ficha.length; x++) {
            for (int y = 0; y < ficha.length; y++) {
                ficha[x][y] = new Ficha(x, y);
            }
        }
        //ficha[1][0].setTurno(Ficha.Turno.USUARIO);
        //ficha[0][2].setTurno(Ficha.Turno.IA);
        //turno = Ficha.Turno.USUARIO;
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //pintamos fondo blanco
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, sizex, sizey);

        //lineas hotizontales 
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, 100, 700, 100);
        g2d.drawLine(0, 200, 700, 200);
        g2d.drawLine(0, 300, 700, 300);
        g2d.drawLine(0, 400, 700, 400);
        g2d.drawLine(0, 500, 700, 500);
        g2d.drawLine(0, 600, 700, 600);

        //Lineas verticales
        g2d.drawLine(100, 0, 100, 700);
        g2d.drawLine(200, 0, 200, 700);
        g2d.drawLine(300, 0, 300, 700);
        g2d.drawLine(400, 0, 400, 700);
        g2d.drawLine(500, 0, 500, 700);
        g2d.drawLine(600, 0, 600, 700);

        /*Color col = new Color (255, 0, 0);
        Color col2 = new Color (100, 200, 0);
            g2d.setColor (col);
            g2d.fillOval(0, 0, 100, 100);
            
            g2d.setColor(col2);
            g2d.fillOval(600, 600, 100, 100);*/
        //ficha.agregarFicha(g2d);
        //ficha.agregarFicha(g2d,300, 500);
        //pruebas
        pintarFichas(g2d);

    }

    private void pintarFichas(Graphics2D g2d) {

        for (int x = 0; x < ficha.length; x++) {
            for (int y = 0; y < ficha.length; y++) {
                ficha[x][y].agregarFicha(g2d);
                //System.out.println(ficha[x][y].getTurno());
            }
        }

    }

    public boolean colocarColor(Ficha.Turno jugador, int ejeX, int ejeY) {

        boolean resultado = false;
        for (int y = ficha.length - 1; y >= 0; y--) {
            System.out.println(ejeX + " " + y);

            if (ficha[ejeX][0].getTurno() != Ficha.Turno.VACIO) {
                JOptionPane.showMessageDialog(null, "Columna no disponible");
                this.repaint();
                y = 0;
                resultado = false;
            } else if (ficha[ejeX][y].getTurno() == Ficha.Turno.VACIO) {
                ficha[ejeX][y].setTurno(jugador);
                this.repaint();
                y = 0;
                resultado = true;
            }
        }

        return resultado;
    }

    public boolean columnas(Ficha.Turno jugador, Ficha.Turno perdedor, int ejeX, int ejeY) {
        boolean ganador = false;
        int contador = 0;
        int columna = 0;
        for (int i = 0; i < ficha.length; i++) {
            for (int j = 0; j < ficha.length; j++) {
                //System.out.println(i + " " + j);
                if (ficha[i][j].getTurno() == jugador) {
                    contador++;
                    if (contador == 4) {
                        i = 6;
                        j = 6;
                        columna++;
                        ganador = true;
                    }
                } else if (ficha[i][j].getTurno() == perdedor) {
                    contador = 0;
                    columna++;
                }
            }
            columna = 0;
            contador = 0;
        }

        return ganador;
    }

    public boolean filas(Ficha.Turno jugador, Ficha.Turno perdedor, int ejeX, int ejeY) {
        boolean ganador = false;
        int contador = 0;
        int columna = 0;
        for (int i = 0; i < ficha.length; i++) {
            for (int j = 0; j < ficha.length; j++) {
                //System.out.println(i + " " + j);
                if (ficha[j][i].getTurno() == jugador) {
                    contador++;
                    if (contador == 4) {
                        i = 6;
                        j = 6;
                        columna++;
                        ganador = true;
                    }
                } else if (ficha[j][i].getTurno() == perdedor || ficha[j][i].getTurno() == Ficha.Turno.VACIO) {
                    contador = 0;
                    columna++;
                }
            }
            columna = 0;
            contador = 0;
        }

        return ganador;
    }

    public boolean diagonales(Ficha.Turno jugador, Ficha.Turno perdedor, int ejeX, int ejeY) {
        boolean ganador = false;
        int empezar = 0;
        int contador = 0;

        for ( // Recorre los inicios de cada diagonal en los bordes de la matriz.
                Integer diagonal = 1 - ficha.length; // Comienza con un número negativo.
                diagonal <= ficha.length - 1; // Mientras no llegue a la última diagonal.
                diagonal += 1 // Avanza hasta el comienzo de la siguiente diagonal.
                ) {
            for ( // Recorre cada una de las diagonales a partir del extremo superior izquierdo.
                    Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                    vertical < ficha.length && horizontal < ficha.length; // Mientras no excedan los límites.
                    vertical += 1, horizontal += 1 // Avanza en diagonal incrementando ambos ejes.
                    ) {
                // Muestra cada punto de la matriz ordenadamente.
                //System.out.println(vertical +" "+ horizontal);
                if (empezar == 3 || empezar == 9) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[vertical][horizontal].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[vertical][horizontal].getTurno() == perdedor) {
                        contador = 0;

                    }
                } else if (empezar == 4 || empezar == 8) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[vertical][horizontal].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[vertical][horizontal].getTurno() == perdedor) {
                        contador = 0;

                    }

                } else if (empezar == 5 || empezar == 7) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[vertical][horizontal].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[vertical][horizontal].getTurno() == perdedor) {
                        contador = 0;

                    }
                } else if (empezar == 6) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[vertical][horizontal].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[vertical][horizontal].getTurno() == perdedor) {
                        contador = 0;

                    }
                }

            }
            empezar++;
            contador = 0;
        }

        return ganador;

    }

    public boolean diagonalesInversas(Ficha.Turno jugador, Ficha.Turno perdedor, int ejeX, int ejeY) {
        boolean ganador = false;
        int empezar = 0;
        int contador = 0;
        for ( // Recorre los inicios de cada diagonal en los bordes de la matriz.
                Integer diagonal = -6; // Comienza con un número negativo.
                diagonal <= 0; // Mientras no llegue a la última diagonal.
                diagonal++ // Avanza hasta el comienzo de la siguiente diagonal.
                ) {
            for ( // Recorre cada una de las diagonales a partir del extremo superior izquierdo.
                    Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                    vertical < ficha.length && horizontal < ficha.length; // Mientras no excedan los límites.
                    vertical += 1, horizontal += 1 // Avanza en diagonal incrementando ambos ejes.
                    ) {
                // Muestra cada punto de la matriz ordenadamente.
                //System.out.println(vertical +" "+ horizontal);
                if (empezar == 3 || empezar == 9) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[horizontal][vertical].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[horizontal][vertical].getTurno() == perdedor) {
                        contador = 0;

                    }
                } else if (empezar == 4 || empezar == 8) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[vertical][horizontal].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[vertical][horizontal].getTurno() == perdedor) {
                        contador = 0;

                    }

                } else if (empezar == 5 || empezar == 7) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[horizontal][vertical].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[horizontal][vertical].getTurno() == perdedor) {
                        contador = 0;

                    }
                } else if (empezar == 6) {
                    System.out.println(vertical + " " + horizontal);
                    if (ficha[horizontal][vertical].getTurno() == jugador) {
                        contador++;
                        if (contador == 4) {
                            vertical = 6;
                            horizontal = 0;
                            ganador = true;
                        }
                    } else if (ficha[horizontal][vertical].getTurno() == perdedor) {
                        contador = 0;

                    }
                }

            }
            empezar++;
            contador = 0;
        }

        return ganador;

    }

    public boolean empate() {
        boolean empate = false;
        int num = 0;
        for (int x = 0; x < ficha.length; x++) {
            for (int y = 0; y < ficha.length; y++) {
                if (ficha[x][y].getTurno() != Ficha.Turno.VACIO) {
                    num++;
                }
            }
        }

        if (num == 49) {
            empate = true;
        }

        return empate;

    }

    public void reiniciar() {

        for (int x = 0; x < ficha.length; x++) {
            for (int y = 0; y < ficha.length; y++) {
                ficha[x][y].setTurno(Ficha.Turno.VACIO);
            }
        }

    }

    public Ficha.Turno getTurno() {
        return turno;
    }

    public void setTurno(Ficha.Turno turno) {
        this.turno = turno;
    }

}
