
import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
            public void keyPressed(KeyEvent e) {//SI SE PRESIONA LA TECLA ENTONCES EL PACMAN CAMBIA SU DIRECCION Y SE MUEVE
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
            public void keyReleased(KeyEvent e) { //SI SE SUELTA LA TECLA EL PACMAN CAMBIA SU DIRECCION A NONE Y DEJA DE MOVERSE
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
        J1 = new Pacman(610, 360, 10, 10, "PacmanSprites"); //Creo el pacman posx,posy,velx,vely, sprites
        J2 = new Pacman(460, 660, 5, 5, "FantasmaSprites"); //Creo el fantasma posx,posy,velx,vely,sprites
        String[] names = {"abajo", "arriba", "derecha", "izquierda"};//posibles movimientos
        J1.loadPics(names);//Cargo los sprites
        J2.loadPics(names);//Cargo los sprites
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
                        }//SE CREA EL MUNDO
                        g.setColor(Color.white);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                        g.drawString("SCORE:" + score, 45, 45);//SE DIBUJA EL SCORE
                        currentTime = System.currentTimeMillis() - startTime;
                        whereis = whereIs(J1.x, J1.y);//BUSCO DONDE ESTA EL PACMAN
                        moveEnemy();//MUEVO EL FANTASMA
                        tiempo++;//AUMENTO MI CONTADOR DE SEGUNDOS
                        if (tiempo == 50) {
                            score++;
                            tiempo = 0;
                        }   //SUMO 1 PUNTO CADA SEGUNDO
                        switch (J1.currentDirection) {//ESTE SW ES PARA MOVER EL PACMAN
                            case Pacman.RIGTH: {
                                if (!colision(J1.x + 32, J1.y + 16)) {//REVISO SI ES POSIBLE MOVERSE
                                    J1.moveRigth(currentTime);
                                }
                                break;
                            }
                            case Pacman.DOWN: {
                                if (!colision(J1.x + 16, J1.y + 32)) {//REVISO SI ES POSIBLE MOVERSE
                                    J1.moveDown(currentTime);
                                }

                                break;
                            }
                            case Pacman.LEFT: {
                                if (!colision(J1.x - 1, J1.y + 16)) {//REVISO SI ES POSIBLE MOVERSE
                                    J1.moveLeft(currentTime);
                                }
                                break;
                            }
                            case Pacman.UP: {
                                if (!colision(J1.x + 16, J1.y - 1)) {//REVISO SI ES POSIBLE MOVERSE
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
        //Esto no va aca
        //ACTIVAR ESTO PARA VER EL GRAFO
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

    public static void CreateGraph() throws FileNotFoundException, IOException {//CREO EL GRAFO POR MEDIO DE FILES
        Color color = Color.yellow;
        // Create a file
        File f;
        f = new File("nodes.txt"); //BUSCO EL ARCHIVO QUE CONTIENE LOS NODOS
        System.out.println(f.getAbsolutePath());
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (br.ready()) {
                line = br.readLine();
                String[] Node = line.split(",");
                nodes.add(new Node(Integer.parseInt(Node[0]), Integer.parseInt(Node[1]), Integer.parseInt(Node[2]), color));
            } //CREO EL NODO Y LO AGREGO A EL ARRAYLIST
            br.close();
        } catch (Exception e) {
        }
        f = new File("edges.txt"); //BUSCO EL ARCHIVO QUE CONTIENE LAS ARISTAS
        System.out.println(f.getAbsolutePath());
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (br.ready()) {
                line = br.readLine();
                String[] Node = line.split("-");
                edges.add(new Edge(Integer.parseInt(Node[0]), Integer.parseInt(Node[1]), Integer.parseInt(Node[2]), Integer.parseInt(Node[3]), Integer.parseInt(Node[4]), Integer.parseInt(Node[5]), 1, Color.white));
                //CREO LA ARISTA Y LA METO EN EL ARRAYLIST
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public static void CreateCoins() {//CREO LAS MONEDAS Y LAS METO EN UN ARRAY
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
            coins = new ArrayList<Node>();//inicio los array
            nodes = new ArrayList<Node>();
            edges = new ArrayList<Edge>();
            CreateCoins();//invoco crear monedas
            CreateGraph();//invoco crear grafo
            Principal p = new Principal(1260, 780);//INICIO MI JFRAME
            p.setLocationRelativeTo(null);//CENTRO EL JFRAME
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setUndecorated(true);//LE COLOCO UNDECORATED
            p.setVisible(true);
            p.movieLoop.start();//INICIO EL HILO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int findNode(int x, int y) {//METODO PARA BUSCAR LA MONEDA QUE AGARRE
        int min = 99999999;
        int minN = 0;
        int dist;
        for (Node coin : coins) {//CALCULO LA DISTANCIA CON TODAS LAS MONEDAS Y LA MAS CORTA ES LA QUE ME COMO
            dist = (int) (Math.sqrt(Math.pow(coin.getPosx() - x, 2) + Math.pow(coin.getPosy() - y, 2)));
            if (dist < min) {
                min = dist;
                minN = coin.getName();
            }
        }
        return minN;
    }

    public void moveEnemy() {//METODO PARA MOVER A EL ENEMIGO
        camino = null;
        int V, E, origen, destino, peso, inicial;
        V = 69;//Numero de vertices
        E = 89;//Numero de aristas
        Dijkstra dijkstraAlgorithm = new Dijkstra(V);
        for (Edge edge : edges) {
            dijkstraAlgorithm.addEdge(edge.getNinicial(), edge.getNfinal(), edge.getPeso(), false);
        }
        dijkstraAlgorithm.dijkstra(whereIs(J2.x, J2.y));//EJECUTO MI DIJKSTRA (int Ininio)
        dijkstraAlgorithm.printShortestPath();//ESCRIBO EN CONSOLA EL CAMINO
        try {
            switch (verifDireccion(J2.x, J2.y)) {//VERIFICO HACIA DONDE DEBE MOVERSE EL FANTASMA Y LO MUEVO
                case 1:
                    J2.moveUp(currentTime);
                    System.out.println("ARRIBA");
                    break;
                case 2:
                    J2.moveDown(currentTime);
                    System.out.println("ABAJO");
                    break;
                case 3:
                    J2.moveRigth(currentTime);
                    System.out.println("DERECHA");
                    break;
                case 4:
                    J2.moveLeft(currentTime);
                    System.out.println("IZQ");
                    break;
            }
        } catch (Exception e) {
        }

    }

    public int verifDireccion(int x, int y) {//METODO PARA VERIFICAR HACIA DONDE DEBE MOVERSE EL FANTASMA
        /*TENGO UN GRAN PROBLEMA YA QUE COMO DECIDO MI INICIO DEPENDIENDO DE EL NODO MAS CERCANO ENTONCES AL 
        MOVER EL FANTASMA ENTRE VARIOS NODOS NO IMPORTAN LAS PAREDES Y SE PUEDE IR A OTRO NODO QUE ESTE MAS 
        CERCA AUNQUE SEA INALCANSABLE*/
        String[] linea = camino.split("-");
        Node obj = null;
        for (Node node : nodes) {
            if (Integer.parseInt(linea[1]) == node.getName()) {//BUSCO EL NODO OBJETIVO, OSEA EL SIGUIENTE MOVIMIENTO
                obj = node;
                break;
            }
        }
        System.out.println(obj.getName());
        System.out.println("x " + x + ",y " + y + " FANTASMA");
        System.out.println("x " + obj.getPosx() + ",y " + obj.posy + " OBJETIVO");
        if (x < obj.getPosx()) {//DEPENDIENDO DE LAS POSICIONES DECIDO A DONDE MOVERME
            return Pacman.RIGTH;
        } else {
            if (x > obj.getPosx()) {
                return Pacman.LEFT;
            } else {
                if (y > obj.getPosy()) {
                    return Pacman.UP;
                } else {
                    return Pacman.DOWN;
                }
            }
        }
    }

    public int whereIs(int x, int y) {//ESTE METODO LO USO PARA SABER TANTO LA POSICION DE EL PACMAN Y LA POSICION DE EL FANTASMA
        /*ESTE METODO SE DEBE MEJORAR YA QUE TIENE UN ERROR AL MOVER EL FANTASMA, EXPLICO EL PROBLEMA EN LA LINEA 367*/

        int min = 99999999;
        int minN = 0;
        int dist;
        for (Node coin : nodes) {
            /*SELECCIONO EL NODO MAS CERCANO SIN IMPORTAR LAS PAREDES!!!*/
            dist = (int) (Math.sqrt(Math.pow(coin.getPosx() - x, 2) + Math.pow(coin.getPosy() - y, 2)));
            if (dist < min) {
                min = dist;
                minN = coin.getName();
            }
        }
        return minN;
    }

    public void removeCoin(int objetivo) {//ESTE METODO ELIMINA LA MONEDA QUE ME VAYA COMIENDO
        Node coinObj = null;
        for (Node coin : coins) {
            if (objetivo == coin.getName()) {//BUSCO EL NOMBRE DE LA MONEDA
                coinObj = coin;
            }
        }
        if (coinObj != null) {//SI HAY MONEDA ENTONCES LA ELIMINO Y SUMO PUNTAJE
            coins.remove(coinObj);
            score = score + 10;
        }
        if (coins.size() == 0) {//SI NO HAY MONEDAS EN EL ARRAYLIST SIGNIFICA QUE LAS AGARRE TODAS Y GANO
            Win win = new Win();
            win.setVisible(true);//MUESTRO VENTANA DE WIN
            Win.Scorelbl.setText("SCORE:" + score);
            this.dispose();
        }

    }

    public boolean colision(int x, int y) throws AWTException {//ESTE METODO CALCULA LAS COLISIONES DE EL PACMAN
        try {
            BufferedImage captura = new Robot().createScreenCapture(//CREO LA CAPTURA DE PANTALLA, SOLO TOMARA LA PARTE DE EL JUEGO
                    new Rectangle(c.getLocationOnScreen(), new Dimension(1260, 780)));//ESTO DEBIDO A LOS PARAMETROS
            int codigoColor = captura.getRGB(x, y);//OBTENGO EL COLOR EN RGB DE LA POSICION X Y QUE ES LA DE EL PACMAN
            Color color = new Color(codigoColor);//CREO EL COLOR USANDO EL CODIGO RGB ANTES OBTENIDO
            if (color.getBlue() == 255) {//VERIFICO SI ES AZUL
                return true;//RETORNO TRUE Y NO SE MUEVE
            } else {//SI NO ES AZUL
                if (color.getGreen() == 255) {//SI ES VERDE SE PUEDE MOVER PERO INICIO EL PROCESO DE ELIMINAR MONEDA
                    int coinObj = findNode(x, y);
                    removeCoin(coinObj);
                    return false;
                } else {
                    return false;//SI NO ES NADA ENTONCES SE PUEDE MOVER NORMAL
                }

            }
        } catch (Exception e) {
        }
        return false;
    }

}
