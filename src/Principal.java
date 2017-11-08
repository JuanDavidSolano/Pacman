
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
    public Pacman J2;
    public Moneda J3;
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
        J1 = new Pacman(410, 460, 5, 5, "PacmanSprites");
        J2 = new Pacman(760,60,5,5,"FantasmaSprites");
        J3 = new Moneda();
        String[] names = {"abajo", "arriba", "derecha", "izquierda"};
        J1.loadPics(names);
        J2.loadPics(names);
        J3.loadPics();
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

                                if (!colision(J1.x + 32, J1.y + 16)) {
                                    J1.moveRigth(currentTime);
                                }
                                break;
                            }
                            case Pacman.DOWN: {
                                if (!colision(J1.x + 16, J1.y + 32)) {
                                    J1.moveDown(currentTime);
                                }

                                break;
                            }
                            case Pacman.LEFT: {
                                if (!colision(J1.x-1, J1.y+16)) {
                                    J1.moveLeft(currentTime);
                                }
                                break;
                            }
                            case Pacman.UP: {
                                if (!colision(J1.x+16,J1.y-1)) {
                                    J1.moveUp(currentTime);
                                }
                                break;
                            }
                        }
                        J1.draw(g);
                        J2.draw(g);
                        J3.draw(g,60,55);
                        J3.draw(g,60,105);
                        J3.draw(g,60,155);
                        J3.draw(g,60,205);
                        J3.draw(g,60,255);
                        J3.draw(g,60,305);
                        J3.draw(g,60,355);
                        J3.draw(g,60,405);
                        J3.draw(g,60,455);
                        J3.draw(g,110,455);
                        J3.draw(g,160,455);
                        J3.draw(g,210,455);
                        J3.draw(g,260,455);
                        J3.draw(g,310,455);
                        J3.draw(g,210,405);
                        J3.draw(g,210,355);
                        J3.draw(g,260,355);
                        J3.draw(g,310,355);
                        J3.draw(g,360,355);
                        J3.draw(g,410,355);
                        J3.draw(g,460,355);
                        J3.draw(g,510,355);
                        J3.draw(g,560,355);
                        J3.draw(g,610,355);
                        J3.draw(g,660,355);
                        J3.draw(g,160,355);
                        J3.draw(g,160,305);
                        J3.draw(g,110,305);
                        
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
        if (color.getBlue() == 255) {
            return true;
        } else {
            return false;
        }
    }

}