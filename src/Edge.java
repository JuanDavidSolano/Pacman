

import java.awt.Color;

public class Edge {

    int Ninicial;
    int Nfinal;
    int x1, y1, x2, y2;
    int peso;
    boolean dirigido;
    Color color;

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Color getColor() {
        return color;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Edge(int Ninicial, int Nfinal, int x1, int y1, int x2, int y2,int peso, Color color) {
        this.Ninicial = Ninicial;
        this.Nfinal = Nfinal;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.peso = peso;
        this.dirigido = false;
    }

    public int getNinicial() {
        return Ninicial;
    }

    public void setNinicial(int Ninicial) {
        this.Ninicial = Ninicial;
    }

    public int getNfinal() {
        return Nfinal;
    }

    public void setNfinal(int Nfinal) {
        this.Nfinal = Nfinal;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

}
