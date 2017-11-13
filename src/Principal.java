
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import sun.audio.*;


/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author examen
 */
public class Principal extends JFrame {

    public boolean sonando = false;
    public int score = 0;
    public int tiempo = 0;
    public static int whereis = 0;
    public Thread movieLoop;
    public Canvas c;
    public Pacman J1;
    public Pacman J2;
    public static String camino;
    long currentTime = 0;
    public static ArrayList<Node> coins;
    public static ArrayList<Edge> edges;
    public static ArrayList<Node> nodes;
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
                        sonando = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        J1.currentDirection = Pacman.NONE;
                        sonando = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        J1.currentDirection = Pacman.NONE;
                        sonando = false;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        J1.currentDirection = Pacman.NONE;
                        sonando = false;
                        break;
                    }
                }
            }
        });
        J1 = new Pacman(610, 360, 10, 10, "PacmanSprites");
        J2 = new Pacman(460, 660, 5, 5, "FantasmaSprites");
        String[] names = {"abajo", "arriba", "derecha", "izquierda"};
        J1.loadPics(names);
        J2.loadPics(names);
        movieLoop = new Thread(new Runnable() {

            @Override
            public void run() {
                c.createBufferStrategy(2);
                Graphics g = c.getBufferStrategy().getDrawGraphics();

                long startTime = System.currentTimeMillis();

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
                        g.setColor(Color.white);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                        g.drawString("SCORE:" + score, 45, 45);
                        currentTime = System.currentTimeMillis() - startTime;
                        whereis = whereIs(J1.x, J1.y);
                        moveEnemy();
                        tiempo++;
                        if (tiempo == 50) {
                            score++;
                            tiempo = 0;
                        }
                        switch (J1.currentDirection) {
                            case Pacman.RIGTH: {
                                if (!colision(J1.x + 32, J1.y + 16)) {
                                    playSound();
                                    sonando = true;
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

    public void playSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

    }

    public void DrawCoins(Graphics g) throws FileNotFoundException, IOException {
        try {
            for (Node node : coins) {
                g.setColor(node.getColor());
                g.fillOval(node.getPosx(), node.getPosy(), 24, 24);
            }
        } catch (Exception e) {
        }
        //Esto no va aca
//        try {
//            for (Node node : nodes) {
//                g.setColor(node.getColor());
//                g.fillOval(node.getPosx(), node.getPosy(), 15, 15);
//            }
//        } catch (Exception e) {
//        }
//        try {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setStroke(new BasicStroke(5));
//            for (Edge edge : edges) {
//                g.setColor(edge.getColor());
//                g2.drawLine(edge.getX1(), edge.getY1(), edge.getX2(), edge.getY2());
//            }
//        } catch (Exception e) {
//        }

    }

    public static void CreateGraph() throws FileNotFoundException, IOException {
        Color color = Color.yellow;
        // Create a file
        File f;
        f = new File("nodes.txt"); //BUSCO EL ARCHIVO
        System.out.println(f.getAbsolutePath());
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (br.ready()) {
                line = br.readLine();
                String[] Node = line.split(",");
                nodes.add(new Node(Integer.parseInt(Node[0]), Integer.parseInt(Node[1]), Integer.parseInt(Node[2]), color));
            }
            br.close();
        } catch (Exception e) {
        }
        f = new File("edges.txt"); //BUSCO EL ARCHIVO
        System.out.println(f.getAbsolutePath());
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (br.ready()) {
                line = br.readLine();
                String[] Node = line.split("-");
                edges.add(new Edge(Integer.parseInt(Node[0]), Integer.parseInt(Node[1]), Integer.parseInt(Node[2]), Integer.parseInt(Node[3]), Integer.parseInt(Node[4]), Integer.parseInt(Node[5]), 1, Color.white));
            }
            br.close();
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
            nodes = new ArrayList<Node>();
            edges = new ArrayList<Edge>();
            CreateCoins();
            CreateGraph();
            Principal p = new Principal(1260, 780);
            p.setLocationRelativeTo(null);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setUndecorated(true);
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

    public void moveEnemy() {
        camino = null;
        int V, E, origen, destino, peso, inicial;
        V = 69;//Numero de vertices
        E = 89;//Numero de aristas
        Dijkstra dijkstraAlgorithm = new Dijkstra(V);
        for (Edge edge : edges) {
            dijkstraAlgorithm.addEdge(edge.getNinicial(), edge.getNfinal(), edge.getPeso(), false);
        }
        dijkstraAlgorithm.dijkstra(whereIs(J2.x, J2.y));
        dijkstraAlgorithm.printShortestPath();

    }

    public int verifDireccion(int x, int y) {
        String[] linea = camino.split("-");
        Node obj = null;
        for (Node node : nodes) {
            if (Integer.parseInt(linea[1]) == node.getName()) {
                obj = node;
            }
        }
        System.out.println(obj.getName());
        System.out.println("x " + x + ",y " + y + " FANTASMA");
        System.out.println("x " + obj.getPosx() + ",y " + obj.posy + " OBJETIVO");
        if (x < obj.getPosx()) {
            return 1;
        } else {
            if (x > obj.getPosx()) {
                return 2;
            } else {
                if (y > obj.getPosy()) {
                    return 4;
                } else {
                    return 3;
                }
            }
        }
    }

    public int whereIs(int x, int y) {
        int min = 99999999;
        int minN = 0;
        int dist;
        for (Node coin : nodes) {
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
            score = score + 10;
        }
        if (coins.size() == 0) {
            Win win = new Win();
            win.setVisible(true);
            Win.Scorelbl.setText("SCORE:" + score);
            this.dispose();
        }

    }

    public boolean colision(int x, int y) throws AWTException {
        try {
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
        } catch (Exception e) {
        }
        return false;
    }

}
