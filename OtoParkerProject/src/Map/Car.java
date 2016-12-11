package Map; /**
 * Created by HÜSEYİN on 11.12.2016.
 */
import java.awt.Image;
import java.awt.image.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.util.*;


public class Car extends Sprite{
    public int dx;
    public int dy;
    private int x;
    private int y;
    public Image image;
    private boolean vis;
    public ArrayList<Cannonball> cnn;
    public BufferedImage buffered;



    public Car(int a,int b) {
        super(a,b);
        ImageIcon ii = new ImageIcon("images.jpg");
        image = ii.getImage();
        buffered = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);

        x = a;
        y = b;
        vis =true;
        System.out.println("Car working Directory = " +
                System.getProperty("user.dir"));

        cnn = new ArrayList<>();
        dx=0;
        dy=0;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return vis;
    }

    public void fire() {
        cnn.add(new Cannonball(x + image.getWidth(null), y + image.getHeight(null) / 2));
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            if(Math.abs(dx)<=5)
                dx--;
            else
                dx=dx;
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(Math.abs(dx)<=5)
                dx++;
            else
                dx=dx;
        }

        if (key == KeyEvent.VK_UP  ){
            if(Math.abs(dy)<=5)
                dy--;
            else
                dy=dy;
        }

        if (key == KeyEvent.VK_DOWN) {
            if(Math.abs(dy)<=5)
                dy++;
            else
                dy=dy;
        }
    }

    public void keyReleased(KeyEvent e) {  //deacceleration to be implemented

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

}
