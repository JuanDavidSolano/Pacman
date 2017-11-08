
import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author examen
 */
public class Principal extends JFrame {

    public Thread movieLoop;
    public Canvas c;
    public Pacman J1;
    public int[][] mundo = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    /*....................*/ {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
    /*....................*/ {1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
    /*....................*/ {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1},
    /*....................*/ {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
    /*....................*/ {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
    /*....................*/ {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
    /*....................*/ {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
    /*....................*/ {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
    /*....................*/ {1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    /*....................*/ {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public Principal(int w, int h) throws Exception {
        c = new Canvas();
        this.setSize(w, h);
        c.setSize(w, h);
        this.add(c);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        J1.currentDirection = Pacman.UP;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        J1.currentDirection = Pacman.DOWN;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        J1.currentDirection = Pacman.LEFT;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        J1.currentDirection = Pacman.RIGTH;
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        J1.currentDirection = Pacman.NONE;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        J1.currentDirection = Pacman.NONE;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        J1.currentDirection = Pacman.NONE;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        J1.currentDirection = Pacman.NONE;
                        break;
                    }
                }
            }
        });
        J1 = new Pacman(32, 32, 1, 1, "PacmanSprites");
        String[] names = {"abajo", "arriba", "derecha", "izquierda"};
        J1.loadPics(names);
        movieLoop = new Thread(new Runnable() {

            @Override
            public void run() {
                c.createBufferStrategy(2);
                Graphics g = c.getBufferStrategy().getDrawGraphics();

                long startTime = System.currentTimeMillis();
                long currentTime = 0;
                while (true) {
                    try {
                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, c.getWidth(), c.getHeight());
                        for (int i = 0; i < 11; i++) {
                            for (int j = 0; j < 17; j++) {
                                if (mundo[i][j] == 1) {
                                    g.setColor(new Color(0, 0, 255));
                                    g.fillRect(50 * j, 50 * i, 50, 50);
                                } else {
                                    g.setColor(new Color(0, 0, 0));
                                    g.fillRect(50 * j, 50 * i, 50, 50);
                                }
                            }
                        }

                        currentTime = System.currentTimeMillis() - startTime;

                        switch (J1.currentDirection) {
                            case Pacman.RIGTH: {

                                System.out.println("antes" + J1.x + "," + J1.y);
                                if (!colision(J1.x+32, J1.y+16)) {
                                    J1.moveRigth(currentTime);
                                System.out.println(J1.x + "," + J1.y);
                                }
                                

                                break;
                            }
                            case Pacman.DOWN: {
                                System.out.println("antes" + J1.x + "," + J1.y);
                                J1.moveDown(currentTime);
                                System.out.println(J1.x + "," + J1.y);
                                break;
                            }
                            case Pacman.LEFT: {
                                System.out.println("antes" + J1.x + "," + J1.y);
                                if (!(J1.x == 0)) {
                                    J1.moveLeft(currentTime);
                                    System.out.println(J1.x + "," + J1.y);
                                }
                                break;
                            }
                            case Pacman.UP: {
                                System.out.println("antes" + J1.x + "," + J1.y);

                                J1.moveUp(currentTime);

                                System.out.println(J1.x + "," + J1.y);
                                break;
                            }
                        }
                        J1.draw(g);
                        Thread.sleep(30);
                        c.getBufferStrategy().show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main() {
        try {
            Principal p = new Principal(860, 580);
            p.setLocationRelativeTo(null);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setVisible(true);
            p.movieLoop.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean colision(int x, int y) throws AWTException {
        BufferedImage captura = new Robot().createScreenCapture(
                new Rectangle(c.getLocationOnScreen(), new Dimension(840, 540)));
        int codigoColor = captura.getRGB(x, y);
        Color color = new Color(codigoColor);
        System.out.println("Azul aca del pixel " + x + "," + y + " es de " + color.getBlue());
        if (color.getBlue() == 255) {
            return true;
        } else {
            return false;
        }
    }

}
