package GameObjects;

import javax.swing.*;
import java.awt.*;


public class Cannonball extends Sprite {

    private final int BOARD_WIDTH = 800;
    private double angle;
    private String im = "images/daringfireball.png";

    public Cannonball(int x, int y,double an) {
        super(x, y);
        angle=an;
        initCannonball();
    }

    private void initCannonball() {
        loadImage(im);
        System.out.println(im);
        getImageDimensions();
    }

    public void move() {

        x += 10 * Math.sin(angle);
        y += 10 * -Math.cos(angle);

        if (x > BOARD_WIDTH)
            vis = false;
    }
    public void loadImage(String imageName) {

        ImageIcon icon = new ImageIcon(imageName);
        image = icon.getImage();
    }


}
