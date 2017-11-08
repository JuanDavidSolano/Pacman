
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Moneda {

    String path="MonedaSprites";
    Image image;
    public void loadPics() throws Exception {
        image = new ImageIcon(getClass().getResource(path + "//moneda.gif")).getImage();
    }
    public void draw(Graphics g,int x, int y){
         g.drawImage(image,x,y,null);
    }
}
