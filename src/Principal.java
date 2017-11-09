
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
    public static ArrayList<Node> coins;
    public static ArrayList<Edge> edges;
    public int[][] mundo = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    /*...................*/ {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    /*...................*/ {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
    /*...................*/ {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
    /*...................*/ {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
    /*...................*/ {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
    /*...................*/ {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
    /*...................*/ {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    /*...................*/ {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
    /*...................*/ {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    /*...................*/ {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
    /*...................*/ {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
    /*...................*/ {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
    /*...................*/ {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    /*...................*/ {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};

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
        J1 = new Pacman(610, 360, 10, 10, "PacmanSprites");
        J2 = new Pacman(760, 60, 5, 5, "FantasmaSprites");
        String[] names = {"abajo", "arriba", "derecha", "izquierda"};
        J1.loadPics(names);
        J2.loadPics(names);
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
                        for (int i = 0; i < 15; i++) {
                            for (int j = 0; j < 25; j++) {
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
                                if (!colision(J1.x - 1, J1.y + 16)) {
                                    J1.moveLeft(currentTime);
                                }
                                break;
                            }
                            case Pacman.UP: {
                                if (!colision(J1.x + 16, J1.y - 1)) {
                                    J1.moveUp(currentTime);
                                }
                                break;
                            }
                        }
                        J1.draw(g);
                        J2.draw(g);

                        DrawCoins(g);

                        Thread.sleep(30);
                        c.getBufferStrategy().show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void DrawCoins(Graphics g) throws FileNotFoundException, IOException {
        try {
            for (Node node : coins) {
                g.setColor(node.getColor());
                g.fillOval(node.getPosx(), node.getPosy(), 24, 24);
            }
        } catch (Exception e) {
        }

    }

    public static void CreateCoins() {
        coins.add(new Node(1, 60, 160, Color.green));
        coins.add(new Node(2, 560, 60, Color.green));
        coins.add(new Node(3, 260, 360, Color.green));
        coins.add(new Node(4, 60, 660, Color.green));
        coins.add(new Node(5, 460, 560, Color.green));
        coins.add(new Node(6, 760, 460, Color.green));
        coins.add(new Node(7, 1010, 410, Color.green));
        coins.add(new Node(8, 1010, 160, Color.green));
        coins.add(new Node(9, 1160, 660, Color.green));
        coins.add(new Node(10, 660, 60, Color.green));
    }

    public static void main() {
        try {
            coins = new ArrayList<Node>();
            CreateCoins();
            Principal p = new Principal(1260, 780);
            p.setLocationRelativeTo(null);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setVisible(true);
            p.movieLoop.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int findNode(int x, int y) {
        int min = 99999999;
        int minN = 0;
        int dist;
        for (Node coin : coins) {
            dist = (int) (Math.sqrt(Math.pow(coin.getPosx() - x, 2) + Math.pow(coin.getPosy() - y, 2)));
            if (dist < min) {
                min = dist;
                minN = coin.getName();
            }
        }
        return minN;
    }

    public void removeCoin(int objetivo) {
        Node coinObj = null;
        for (Node coin : coins) {
            if (objetivo == coin.getName()) {
                coinObj = coin;
            }
        }
        if (coinObj != null) {
            coins.remove(coinObj);
        }
        if (coins.size() == 0) {
            Menu menu = new Menu();
            menu.setVisible(true);
            this.dispose();
        }

    }

    public boolean colision(int x, int y) throws AWTException {
        BufferedImage captura = new Robot().createScreenCapture(
                new Rectangle(c.getLocationOnScreen(), new Dimension(1260, 780)));
        int codigoColor = captura.getRGB(x, y);
        Color color = new Color(codigoColor);
        if (color.getBlue() == 255) {
            return true;
        } else {
            if (color.getGreen() == 255) {
                int xyz = findNode(x, y);
                removeCoin(xyz);
                return false;
            } else {
                return false;
            }

        }
    }

}
